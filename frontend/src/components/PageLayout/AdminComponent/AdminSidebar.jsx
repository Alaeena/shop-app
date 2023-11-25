import { Link, useLocation } from 'react-router-dom';
import { routes } from '@/routes';

import classNames from 'classnames/bind';
import Styles from './AdminComponent.module.scss';
const cx = classNames.bind(Styles);

function AdminSidebar() {
    const configurations = [
        {
            path: routes.admin_category,
            value: 'Danh mục',
            src: 'https://cdn-icons-png.flaticon.com/128/718/718970.png',
        },
        {
            path: routes.admin_user,
            value: 'Người dùng',
            src: 'https://cdn-icons-png.flaticon.com/128/3177/3177440.png',
        },
        { path: '#', value: 'Biểu đồ', src: 'https://cdn-icons-png.flaticon.com/128/7604/7604036.png' },
        { path: '#', value: 'Khuyến mại', src: 'https://cdn-icons-png.flaticon.com/128/12725/12725541.png' },
        { path: '#', value: 'Doanh thu', src: 'https://cdn-icons-png.flaticon.com/128/1365/1365346.png' },
        { path: '#', value: 'Cài đặt', src: 'https://cdn-icons-png.flaticon.com/512/3953/3953226.png' },
    ];
    const { pathname } = useLocation();
    const renderList = () =>
        configurations.map((item, key) => (
            <Link key={key} to={item.path} className={cx('item', { active: item.path === pathname })}>
                <img src={item.src} />
                <span>{item.value}</span>
            </Link>
        ));
    return (
        <div className={cx('container')}>
            <div className={cx('avatar')}>
                <strong>admin</strong>
                <span>panel</span>
            </div>
            <div className={cx('content')}>
                <h4>Danh sách</h4>
                <div className={cx('list')}>{renderList()}</div>
            </div>
        </div>
    );
}

export default AdminSidebar;
