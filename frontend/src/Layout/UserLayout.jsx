import { Header, Footer, UserPanel } from '@/components/PageLayout';
import classNames from 'classnames/bind';
import Styles from './Layout.module.scss';
const cx = classNames.bind(Styles);

function UserLayout({ children }) {
    return (
        <>
            <Header />
            <div className={cx('column')}>
                <UserPanel>{children}</UserPanel>
            </div>
            <Footer />
        </>
    );
}

export default UserLayout;
