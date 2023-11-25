import CheckoutItem from './CheckoutItem';

import classNames from 'classnames/bind';
import Styles from './Checkout.module.scss';
const cx = classNames.bind(Styles);

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function CheckoutList({ data }) {
    const { shop, cart } = data;

    const render = () => cart.map((item, index) => <CheckoutItem data={item} key={index} />);
    const total = cart.reduce((prev, value) => {
        const { number, discount, price } = value;
        return prev + number * price * (1 - discount / 100);
    }, 0);
    return (
        <div className={cx('content')}>
            <div className={cx('shop-name')}>
                <img src={shop.url} alt="shop avatar" />
                <span>{shop.name}</span>
            </div>
            <div className={cx('content-list')}>{render()}</div>
            <div className={cx('content-footer')}>
                <div>
                    <span>Tổng số tiền ({cart.length} sản phẩm):</span>
                    <p>{VND.format(total)}</p>
                </div>
            </div>
            <div className={cx('content-info')}>
                <div className={cx('message')}>
                    <span>Lời nhắn:</span>
                    <input placeholder="Lưu ý cho người bán..." />
                </div>
                <div className={cx('transport')}>
                    <span>Đơn vị vận chuyển:</span>
                    <div>
                        <p>Nhanh</p>
                        <span> Nhận hàng vào </span>
                        <span>11 Th10 - 14 Th10</span>
                    </div>
                    <button>Thay Đổi</button>
                    <p>₫42.500</p>
                </div>
            </div>
        </div>
    );
}

export default CheckoutList;
