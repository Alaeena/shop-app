import CustomTippy from '@tippyjs/react/headless';
import images from '@/assets/img';

import { routes } from '@/routes';
import { Link } from 'react-router-dom';

import classNames from 'classnames/bind';
import Styles from './Notification.module.scss';
const cx = classNames.bind(Styles);

function Notification({ children }) {
    const render = (attrs) => (
        <div className={cx('tippy')} {...attrs}>
            <div className={cx('header')}>Thông báo mới nhận</div>
            <ul className={cx('content')}>
                <li className={cx('item')}>
                    <img src={images.ads} alt="image"></img>
                    <div className={cx('container')}>
                        <h4>SIÊU HỘI SẮP BẮT ĐẦU</h4>
                        <span>
                            🎉Appshop hàng hiệu giảm đến 50% 🔥Thêm 100 iPhone đợi người rước 👉Săn deal ngay thôi
                        </span>
                    </div>
                </li>
                <li className={cx('item')}>
                    <img src={images.ads} alt="image"></img>
                    <div className={cx('container')}>
                        <h4>SIÊU HỘI SẮP BẮT ĐẦU</h4>
                        <span>
                            🎉Appshop hàng hiệu giảm đến 50% 🔥Thêm 100 iPhone đợi người rước 👉Săn deal ngay thôi
                        </span>
                    </div>
                </li>
            </ul>
            <Link to={routes.user} className={cx('link')}>
                Xem tất cả
            </Link>
        </div>
    );
    return (
        <CustomTippy
            appendTo={document.body}
            interactive
            render={render}
            delay={[100]}
            trigger="mouseenter"
            placement="bottom-end"
        >
            {children}
        </CustomTippy>
    );
}

export default Notification;
