import { Header, Sidebar, Footer, Advertisement, SpecialProduct } from '@/components/PageLayout';

import classNames from 'classnames/bind';
import Styles from './Layout.module.scss';
const cx = classNames.bind(Styles);

function MainLayout({ children }) {
    return (
        <>
            <Header />
            <Advertisement />
            <SpecialProduct />
            <div className={cx('panel')}>
                <span>gợi ý hôm nay</span>
            </div>
            <div className={cx('container')}>
                <Sidebar />
                <div className={cx('right')}>{children}</div>
            </div>
            <Footer />
        </>
    );
}

export default MainLayout;
