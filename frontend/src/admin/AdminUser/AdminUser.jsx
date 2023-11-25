import UserContent from './UserContent';

import classNames from 'classnames/bind';
import Styles from './AdminUser.module.scss';
const cx = classNames.bind(Styles);

function AdminUser() {
    return (
        <div className={cx('container')}>
            <div className={cx('header')}>
                <strong>Người</strong>
                <span>dùng</span>
            </div>
            <UserContent />
        </div>
    );
}

export default AdminUser;
