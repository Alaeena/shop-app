import useCart from '@/Util/useCart';
import { deleteCart, getCart } from '@/Service/CartService';

import { Link } from 'react-router-dom';
import { useState } from 'react';
import { routes } from '@/routes';

import classNames from 'classnames/bind';
import Styles from './Cart.module.scss';
const cx = classNames.bind(Styles);

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});
const MIN = 1;
const MAX = 20;

function ShopItem({ data }) {
    const { action, state } = useCart();
    const { id, discount, number, price, productId, productName, productUrl } = data;

    const [value, setValue] = useState(number);
    const isSelected = state.item[id].value;

    const handleChange = () => {
        action.selectItem(id);
    };
    const handleDelete = () => {
        deleteCart(id).then(() =>
            getCart().then((list) => {
                if (list) {
                    action.setList(list);
                }
            }),
        );
    };
    const handleInput = (e) => {
        const value = e.target.value;
        const number = Math.min(Math.max(parseInt(value), MIN), MAX);
        setValue(number);
    };
    return (
        <div className={cx('item')}>
            <input onChange={handleChange} checked={isSelected} type="checkbox" />
            <img src={productUrl} alt="product" />
            <Link to={`${routes.product}/${productId}`}>{productName}</Link>
            <div className={cx('price')}>
                <span>{VND.format(price)}</span>
                <strong>{VND.format(price * (1 - discount / 100))}</strong>
            </div>
            <div className={cx('number-function')}>
                <button
                    onClick={() => setValue((prev) => (prev - 1 <= MIN ? MIN : prev - 1))}
                    className={cx('function-left')}
                >
                    -
                </button>
                <input type="number" max={20} min={1} value={value} onChange={handleInput} />
                <button
                    onClick={() => setValue((prev) => (prev + 1 >= MAX ? MAX : prev + 1))}
                    className={cx('function-right')}
                >
                    +
                </button>
            </div>
            <div>
                <strong className={cx('total')}>{VND.format(price * number * (1 - discount / 100))}</strong>
            </div>
            <div>
                <button onClick={handleDelete} className={cx('button')}>
                    Xóa
                </button>
            </div>
        </div>
    );
}

export default ShopItem;
