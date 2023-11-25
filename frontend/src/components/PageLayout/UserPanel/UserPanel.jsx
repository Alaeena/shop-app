import images from '@/assets/img';
import { Link, useLocation } from 'react-router-dom';
import { BellIcon } from '@/components/Icon/regular';
import { routes } from '@/routes';

import classNames from 'classnames/bind';
import Styles from './UserPanel.module.scss';
const cx = classNames.bind(Styles);

function UserPanel({ children }) {
    const { pathname } = useLocation();

    return (
        <div className={cx('container')}>
            <div className={cx('sidebar')}>
                <div className={cx('avatar')}>
                    <img src={images.ads} alt="avatar"></img>
                    <span>Name</span>
                </div>
                <div className={cx('sidebar-list')}>
                    <Link to={routes.profile} className={cx('item')}>
                        <img src={images.user}></img>
                        <strong>Tài khoản của tôi</strong>
                    </Link>
                    <Link to={routes.profile} className={cx('item', { active: routes.profile === pathname })}>
                        Hồ sơ
                    </Link>
                    <Link to={routes.address} className={cx('item', { active: routes.address === pathname })}>
                        Địa chỉ
                    </Link>
                    <Link className={cx('item')}>Đổi mật khẩu</Link>
                    <Link className={cx('item')}>Ngân hàng</Link>
                    <Link to={routes.order} className={cx('item', { active: routes.order === pathname })}>
                        <img src={images.order}></img>
                        <strong>Đơn mua</strong>
                    </Link>
                    <Link className={cx('item')}>
                        <BellIcon />
                        <strong>Thông báo</strong>
                    </Link>
                </div>
            </div>
            {children}
        </div>
    );
}

export default UserPanel;
