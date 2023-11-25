import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import { routes } from '@/routes';
import { getCart } from '@/Service/CartService';

import useCart from '@/Util/useCart';
import ShopList from './ShopList';

import classNames from 'classnames/bind';
import Styles from './Cart.module.scss';
const cx = classNames.bind(Styles);

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function Cart() {
    const navigate = useNavigate();
    const { state, action } = useCart();

    const selectList = Object.keys(state.item).filter((id) => state.item[id].value);
    const selectLength = selectList.length;
    const selectTotal = selectList.reduce((prev, value) => {
        const data = state.item[value].data;
        const { discount, number, price } = data;

        return prev + number * price * (1 - discount / 100);
    }, 0);

    const handleClick = () => {
        const list = [];
        Object.keys(state.shop).forEach((shopId) => {
            const element = state.shop[shopId];
            if (element.isIncluded !== true) {
                return;
            }
            const object = {};
            object.shop = element.data;
            object.cart = [];

            element.itemId.forEach((index) => {
                const itemValue = state.item[index];
                if (itemValue.value) {
                    object.cart.push(itemValue.data);
                }
            });
            list.push(object);
        });
        navigate(routes.checkout, { state: list });
    };

    useEffect(() => {
        if (state.list.length !== 0) {
            return;
        }
        getCart().then((list) => {
            if (list) {
                action.setList(list);
            }
        });
    }, []);
    const render = (list) => list.map((item, index) => <ShopList key={index} data={item} />);
    return (
        <div className={cx('container')}>
            <div className={cx('header')}>
                <p>Sản phẩm</p>
                <span>Đơn giá</span>
                <span>Số lượng</span>
                <span>Số tiền</span>
                <span>Thao Tác</span>
            </div>
            {render(state.list)}
            <div className={cx('footer')}>
                <div>
                    <span>Tổng thanh toán({selectLength} sản phẩm):</span>
                    <strong>{VND.format(selectTotal)}</strong>
                </div>
                <button onClick={handleClick} className={cx('submit')}>
                    Mua ngay
                </button>
            </div>
        </div>
    );
}

export default Cart;
