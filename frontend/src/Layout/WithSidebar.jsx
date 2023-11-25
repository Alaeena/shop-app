import { Header, SearchBar, Footer } from '@/components/PageLayout';

import classNames from 'classnames/bind';
import Styles from './Layout.module.scss';
const cx = classNames.bind(Styles);

function WithSidebar({ children }) {
    return (
        <>
            <Header />

            <div className={cx('container')}>
                <SearchBar />
                <div className={cx('right')}>{children}</div>
            </div>
            <Footer />
        </>
    );
}

export default WithSidebar;
