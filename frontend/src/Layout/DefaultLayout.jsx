import { Header, Footer } from '@/components/PageLayout';
import classNames from 'classnames/bind';
import Styles from './Layout.module.scss';
const cx = classNames.bind(Styles);

function DefaultLayout({ children }) {
    return (
        <>
            <Header />
            <div className={cx('column')}>{children}</div>
            <Footer />
        </>
    );
}

export default DefaultLayout;
