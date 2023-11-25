import CustomTippy from '@tippyjs/react/headless';
import images from '@/assets/img';

import { useAuth } from '@/Util';
import { routes } from '@/routes';
import { Link } from 'react-router-dom';

import classNames from 'classnames/bind';
import Styles from './Cart.module.scss';
const cx = classNames.bind(Styles);

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function Cart({ children, data = [] }) {
    const { state } = useAuth();

    const renderItem = () =>
        data.map((item, index) => (
            <Link to={`${routes.product}/${item.id}`} key={index} className={cx('item')}>
                <img src={item.productUrl} alt="image"></img>
                <span>{item.productName}</span>
                <strong>{VND.format(item.total)}</strong>
            </Link>
        ));

    const render = (attrs) => (
        <div className={cx('tippy')} {...attrs}>
            {!state.isLogin || data.length === 0 ? (
                <>
                    <img src={images.cart} alt="cart" />
                    <span>Không có sản phẩm</span>
                </>
            ) : (
                <>
                    <div className={cx('header')}>Sản phẩm mới thêm</div>
                    <ul className={cx('content')}>{renderItem()}</ul>
                    <div className={cx('footer')}>
                        <Link to={routes.cart} className={cx('link')}>
                            Xem giỏ hàng
                        </Link>
                    </div>
                </>
            )}
        </div>
    );
    return (
        <CustomTippy
            appendTo={document.body}
            delay={[100]}
            trigger="mouseenter"
            placement="bottom-start"
            interactive
            render={render}
        >
            {children}
        </CustomTippy>
    );
}

export default Cart;
