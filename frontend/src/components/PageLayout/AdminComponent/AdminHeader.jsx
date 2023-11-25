import images from '@/assets/img';
import { MenuIcon } from '@/components/Icon/Fill';
import { UserInfo } from '@/components/Elements';
import { Link } from 'react-router-dom';
import { useAuth } from '@/Util';
import { routes } from '@/routes';

import classNames from 'classnames/bind';
import Styles from './AdminComponent.module.scss';
const cx = classNames.bind(Styles);

function AdminHeader() {
    const { state } = useAuth();
    console.log(state);
    return (
        <div className={cx('header')}>
            <MenuIcon />
            <UserInfo>
                <Link to={routes.profile} className={cx('avatar')}>
                    <img src={images.ads}></img>
                    <span className={cx('item')}>{state.data?.email}</span>
                </Link>
            </UserInfo>
        </div>
    );
}

export default AdminHeader;
