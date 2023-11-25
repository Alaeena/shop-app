import HeaderTop from './HeaderTop';
import HeaderSearch from './HeaderSearch';
import HeaderCart from './HeaderCart';

import { routes } from '@/routes';
import { Link, useLocation } from 'react-router-dom';
import { Logo } from '@/components/Icon/Fill';

import classNames from 'classnames/bind';
import Styles from './Header.module.scss';
const cx = classNames.bind(Styles);

function Header() {
    const { pathname } = useLocation();

    return (
        <div className={cx('container')}>
            <div className={cx('content')}>
                <HeaderTop />
                <div className={cx('wrapper')}>
                    <Link to={routes.root} className={cx('logo')}>
                        <Logo />
                        <span className="super-text">Shop-app</span>
                    </Link>
                    <div className={cx('right-container')}>
                        <HeaderSearch />
                        {pathname != routes.cart && <HeaderCart />}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Header;
