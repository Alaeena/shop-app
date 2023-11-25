import { Link } from 'react-router-dom';
import { routes } from '@/routes';

import classNames from 'classnames/bind';
import Styles from './UserCheckout.module.scss';
const cx = classNames.bind(Styles);

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function OrderItem({ data }) {
    const { productId, productName, productUrl, price, discount, number } = data;
    return (
        <Link to={`${routes.product}/${productId}`} className={cx('product')}>
            <img alt="thumbnail" src={productUrl} />
            <h3>{productName}</h3>
            <div className={cx('product-info')}>
                <span>{VND.format(price)}</span>
                <strong>{VND.format(price * (1 - discount / 100))}</strong>
            </div>
            <div className={cx('number')}>x{number}</div>
            <div className={cx('total')}>{VND.format(price * number * (1 - discount / 100))}</div>
        </Link>
    );
}

export default OrderItem;
