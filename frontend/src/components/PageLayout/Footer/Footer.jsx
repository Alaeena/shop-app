import { Logo } from '@/components/Icon/Fill';
import images from '@/assets/img';

import classNames from 'classnames/bind';
import Styles from './Footer.module.scss';
const cx = classNames.bind(Styles);

function Footer() {
    return (
        <div className={cx('container')}>
            <span>
                <Logo />
                Shop-app
            </span>
            <img src={images.animehay} alt="Quảng cáo"></img>
        </div>
    );
}

export default Footer;
