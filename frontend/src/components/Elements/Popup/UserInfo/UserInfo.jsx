import CustomTippy from '@tippyjs/react/headless';
import { Link, useNavigate } from 'react-router-dom';
import { routes } from '@/routes';

import { useAuth } from '@/Util';
import { LogoutUser } from '@/Service/AuthService';

import classNames from 'classnames/bind';
import Styles from './UserInfo.module.scss';
const cx = classNames.bind(Styles);

function UserInfo({ children }) {
    const navigate = useNavigate();
    const { action, state } = useAuth();

    const handleLogout = () => {
        LogoutUser();
        action.Logout();
        navigate(routes.login);
    };
    const render = (attrs) => (
        <ul className={cx('list')} {...attrs}>
            <Link className={cx('item')} to={routes.profile}>
                Tài khoản của tôi
            </Link>
            <Link className={cx('item')} to={routes.order}>
                Đơn mua
            </Link>
            {state.data?.role === 'ADMIN' && (
                <Link className={cx('item')} to={routes.admin_category}>
                    Admin
                </Link>
            )}
            <li className={cx('item')} onClick={handleLogout}>
                Đăng xuất
            </li>
        </ul>
    );
    return (
        <CustomTippy
            appendTo={document.body}
            delay={[100]}
            trigger="mouseenter"
            placement="bottom-end"
            interactive
            render={render}
        >
            {children}
        </CustomTippy>
    );
}

export default UserInfo;
