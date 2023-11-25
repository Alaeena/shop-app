import { Header, Footer } from '@/components/PageLayout';
import { CartProvider } from '@/Config/CartContext';
import classNames from 'classnames/bind';
import Styles from './Layout.module.scss';
const cx = classNames.bind(Styles);

function DefaultCart({ children }) {
    return (
        <>
            <Header />
            <CartProvider>
                <div className={cx('column')}>{children}</div>
            </CartProvider>
            <Footer />
        </>
    );
}

export default DefaultCart;
