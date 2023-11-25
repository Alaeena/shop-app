import { Link } from 'react-router-dom';
import images from '@/assets/img';
import Slider from './Slider';

import classNames from 'classnames/bind';
import Styles from './Advertise.module.scss';
const cx = classNames.bind(Styles);

function Advertise() {
    return (
        <div className={cx('container')}>
            <div className={cx('left')}>
                <Slider />
            </div>
            <div className={cx('right')}>
                <Link className={cx('banner')}>
                    <img src={images.banner1} alt="Advertisement" />
                </Link>
                <Link className={cx('banner')}>
                    <img src={images.banner2} alt="Advertisement" />
                </Link>
            </div>
        </div>
    );
}

export default Advertise;
