import { payOrder, getOrder, cancelOrder } from '@/Service/OrderService';
import { addCartWithOrder } from '@/Service/CartService';

import UserReview from './UserReview';
import OrderItem from './OrderItem';
import { Link, useNavigate } from 'react-router-dom';

import classNames from 'classnames/bind';
import Styles from './UserCheckout.module.scss';
const cx = classNames.bind(Styles);

const stateList = {
    PAYING: 'Chờ thanh toán',
    PENDING: 'Chờ xác nhận',
    CONFIRMED: 'Đã xác nhận',
    DELIVERING: 'Đang giao',
    RECEIVED: 'Đã nhận',
    CANCELLED: 'Đã hủy',
};

const handleCancel = (orderId) => cancelOrder(orderId);
const handlePay = (orderId) => payOrder(orderId);
const handleAddWithOrder = (orderId) => addCartWithOrder(orderId);
const option = {
    PAYING: [
        { text: 'Thanh toán', onClick: handlePay, style: 'secondary' },
        { text: 'Hủy đơn', onClick: handleCancel, style: 'tertiary' },
    ],
    PENDING: [{ text: 'Liên Hệ Người Bán' }, { text: 'Hủy đơn', onClick: handleCancel, style: 'tertiary' }],
    CONFIRMED: [{ text: 'Xem thông tin hóa đơn' }],
    DELIVERING: [{ text: 'Xem thông tin hóa đơn' }],
    RECEIVED: [
        { text: 'Đánh giá sản phẩm', element: UserReview, style: 'secondary' },
        { text: 'Thêm vào giỏ hàng', onClick: handleAddWithOrder, navigate: '/cart', style: 'primary' },
        { text: 'Xem thông tin hóa đơn' },
    ],
    CANCELLED: [
        { text: 'Thêm vào giỏ hàng', onClick: handleAddWithOrder, navigate: '/cart', style: 'primary' },
        { text: 'Xem Chi Tiết Hủy Đơn' },
    ],
};
const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});
function OrderDetail({ data, section, setList }) {
    const navigate = useNavigate();
    const { items, shop, state, total } = data;

    const renderItem = (products) => products.map((product, key) => <OrderItem key={key} data={product} />);
    const renderOption = (state) =>
        option[state].map((button, key) => {
            const style = button.style || '';
            const classes = cx('button', style);

            const handleClick = () => {
                if (!button.onClick) {
                    return;
                }
                button.onClick(data.id).then(() => {
                    if (button.navigate) {
                        navigate(button.navigate);
                    } else {
                        getOrder(0, section).then((res) => {
                            if (res) {
                                setList(res.data);
                            }
                        });
                    }
                });
            };
            if (button.element) {
                const Element = button.element;
                return (
                    <Element orderId={data.id} key={key}>
                        <button className={classes} onClick={handleClick}>
                            {button.text}
                        </button>
                    </Element>
                );
            }
            return (
                <button key={key} className={classes} onClick={handleClick}>
                    {button.text}
                </button>
            );
        });
    return (
        <div className={cx('order')}>
            <div className={cx('order-header')}>
                {shop && (
                    <Link className={cx('shop-name')}>
                        <img src={shop.url} alt="shop avatar" />
                        <span>{shop.name}</span>
                    </Link>
                )}
                <p>
                    {data.createdAt} / {data.checkoutType}
                </p>
                <span className={cx(state)}>{stateList[state]}</span>
            </div>
            <div className={cx('order-list')}>{renderItem(items)}</div>
            <div className={cx('order-info')}>
                <div>
                    <span>Thành tiền:</span>
                    <strong>{VND.format(total)}</strong>
                </div>
            </div>
            <div className={cx('order-footer')}>{renderOption(state)}</div>
        </div>
    );
}

export default OrderDetail;
