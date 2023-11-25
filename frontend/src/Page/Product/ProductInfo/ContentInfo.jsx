import { useAuth } from '@/Util';
import { Route, useNavigate } from 'react-router-dom';
import { routes } from '@/routes';

import { useState } from 'react';
import { CartLogo } from '@/components/Icon/regular';
import { addCartWithProductId } from '@/Service/CartService';

import classNames from 'classnames/bind';
import Styles from './ProductInfo.module.scss';
const cx = classNames.bind(Styles);

const MIN = 1;
const MAX = 20;

function ContentInfo({ quantity = 1, productId }) {
    const navigate = useNavigate();
    const { state } = useAuth();
    const [value, setValue] = useState(1);

    const handleCart = () => {
        if (state.isLogin) {
            addCartWithProductId(productId).then(() => navigate(routes.cart));
        } else {
            navigate(routes.login);
        }
    };

    const handleValue = (e) => {
        const value = e.target.value;
        const number = Math.min(Math.max(parseInt(value), MIN), MAX);
        setValue(number);
    };
    return (
        <div className={cx('content-info')}>
            <div className={cx('content-number')}>
                <span>Số Lượng</span>
                <div className={cx('number-function')}>
                    <button
                        onClick={() => setValue((prev) => (prev <= MIN ? MIN : prev - 1))}
                        className={cx('function-left')}
                    >
                        -
                    </button>
                    <input type="number" value={value} onChange={(e) => handleValue(e)} />
                    <button
                        onClick={() => setValue((prev) => (prev >= MAX ? MAX : prev + 1))}
                        className={cx('function-right')}
                    >
                        +
                    </button>
                    <span>{quantity} sản phẩm có sẵn</span>
                </div>
            </div>
            <div className={cx('footer')}>
                <button onClick={handleCart} className={cx('buy')}>
                    Mua ngay
                </button>
                <button onClick={handleCart} className={cx('cart')}>
                    <CartLogo />
                    Thêm vào Giỏ Hàng
                </button>
            </div>
        </div>
    );
}

export default ContentInfo;
