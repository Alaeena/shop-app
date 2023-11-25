import { AdminSidebar, AdminHeader } from '@/components/PageLayout';

import classNames from 'classnames/bind';
import Styles from './Layout.module.scss';
const cx = classNames.bind(Styles);

function AdminLayout({ children }) {
    return (
        <div className={cx('admin-container')}>
            <AdminSidebar />

            <div className={cx('flex-column')}>
                <AdminHeader />
                {children}
            </div>
        </div>
    );
}

export default AdminLayout;
