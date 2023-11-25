import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useAuth } from '@/Util';

import { getCartList } from '@/Service/CartService';
import { routes } from '@/routes';
import { CartLogo } from '@/components/Icon/regular';
import { Cart } from '@/components/Elements';

import classNames from 'classnames/bind';
import Styles from './Header.module.scss';
const cx = classNames.bind(Styles);

function HeaderCart() {
    const [list, setList] = useState([]);
    const { state } = useAuth();

    useEffect(() => {
        if (!state.isLogin) {
            return;
        }
        getCartList().then((res) => {
            if (res) {
                setList(res);
            }
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);
    return (
        <Cart data={list}>
            <Link to={routes.cart} className={cx('cart')}>
                <CartLogo />
                {state.isLogin && <span>{list.length}</span>}
            </Link>
        </Cart>
    );
}

export default HeaderCart;
