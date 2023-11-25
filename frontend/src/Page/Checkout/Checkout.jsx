import { useLocation, useNavigate } from 'react-router-dom';
import { useState } from 'react';

import { addOrder } from '@/Service/OrderService';
import CheckoutHeader from './CheckoutHeader';
import CheckoutList from './CheckoutList';

import classNames from 'classnames/bind';
import Styles from './Checkout.module.scss';
import { routes } from '@/routes';
const cx = classNames.bind(Styles);

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});
const ONLINE = 'ONLINE';
const UPFRONT = 'UPFRONT';
const option = {
    ONLINE: 'Thẻ Tín dụng/Ghi nợ',
    UPFRONT: 'Thanh toán khi nhận hàng',
};

function Checkout() {
    const [address, setAddress] = useState({});
    const [payment, setPayment] = useState(ONLINE);
    const navigate = useNavigate();
    const { state } = useLocation();

    const renderList = () => state.map((list, index) => <CheckoutList key={index} data={list} />);
    const itemList = state.reduce((prev, value) => prev.concat(value.cart), []);

    const total = () =>
        itemList.reduce((previous, current) => {
            const { number, discount, price } = current;
            const itemTotal = number * price * (1 - discount / 100);
            return previous + itemTotal;
        }, 0);
    const shipping = () => state.length * 42500;
    const handleClick = () => {
        const items = itemList.map((item) => item.id);

        addOrder(address.id, items, payment).then(() => {
            navigate(routes.order);
        });
    };
    return (
        <div className={cx('container')}>
            <CheckoutHeader address={address} setAddress={setAddress} />
            <div className={cx('content', 'blueContent')}>
                <div className={cx('content-header')}>
                    <p>Sản phẩm</p>
                    <span>Đơn giá</span>
                    <span>Số lượng</span>
                    <span>Thành tiền</span>
                </div>
            </div>
            {renderList()}
            <div className={cx('content')}>
                <div className={cx('payment')}>
                    <span>Phương thức thanh toán</span>
                    <button onClick={() => setPayment(ONLINE)} className={cx({ active: payment === ONLINE })}>
                        {option.ONLINE}
                    </button>
                    <button onClick={() => setPayment(UPFRONT)} className={cx({ active: payment === UPFRONT })}>
                        {option.UPFRONT}
                    </button>
                </div>
                <div className={cx('total')}>
                    <div>
                        <span>Tổng số tiền ({state.length} sản phẩm):</span>
                        <p>{VND.format(total())}</p>
                    </div>
                    <div>
                        <span>Phí vận chuyển</span>
                        <p>{VND.format(shipping())}</p>
                    </div>
                    <div>
                        <span>Tổng thanh toán:</span>
                        <strong>{VND.format(total() + shipping())}</strong>
                    </div>
                </div>
                <div className={cx('final')}>
                    <button onClick={handleClick}>Đặt hàng</button>
                </div>
            </div>
        </div>
    );
}

export default Checkout;
