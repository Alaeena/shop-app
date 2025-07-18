import { Logo } from '@/components/Icon/Fill';
import { Github, Linkedin } from '@/components/Icon/Fill';

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
            <div className={cx('social')}>
                <a href="https://github.com/Alaeena">
                    <Github />
                    <span>Alaeena</span>
                </a>
                <a href="https://www.linkedin.com/in/alaeena/">
                    <Linkedin />
                    <span>Alaeena</span>
                </a>
            </div>
        </div>
    );
}

export default Footer;
