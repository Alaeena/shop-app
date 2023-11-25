import classNames from 'classnames/bind';
import Styles from './Checkout.module.scss';
const cx = classNames.bind(Styles);

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function CheckoutItem({ data }) {
    const { discount, number, price, productName, productUrl } = data;
    const value = price * (1 - discount / 100);
    return (
        <div className={cx('item')}>
            <img src={productUrl} alt="product" />
            <span>{productName}</span>

            <div className={cx('price')}>{VND.format(value)}</div>
            <div className={cx('number')}>{number}</div>
            <div className={cx('total')}>{VND.format(value * number)}</div>
        </div>
    );
}

export default CheckoutItem;
