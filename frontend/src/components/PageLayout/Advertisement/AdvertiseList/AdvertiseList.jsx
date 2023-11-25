import { Link } from 'react-router-dom';
import images from '@/assets/img';

import classNames from 'classnames/bind';
import Styles from './AdvertiseList.module.scss';
const cx = classNames.bind(Styles);

function AdvertiseList() {
    return (
        <div className={cx('container')}>
            <Link className={cx('item')}>
                <div>
                    <img src={images.advertiseItem} alt="Advertise item"></img>
                </div>
                <span className="text">Khung Giờ Săn Sale</span>
            </Link>
            <Link className={cx('item')}>
                <div>
                    <img src={images.advertiseItem} alt="Advertise item"></img>
                </div>
                <span className="text">Khung Giờ Săn Sale</span>
            </Link>
            <Link className={cx('item')}>
                <div>
                    <img src={images.advertiseItem} alt="Advertise item"></img>
                </div>
                <span className="text">Khung Giờ Săn Sale</span>
            </Link>
            <Link className={cx('item')}>
                <div>
                    <img src={images.advertiseItem} alt="Advertise item"></img>
                </div>
                <span className="text">Khung Giờ Săn Sale</span>
            </Link>
            <Link className={cx('item')}>
                <div>
                    <img src={images.advertiseItem} alt="Advertise item"></img>
                </div>
                <span className="text">Khung Giờ Săn Sale</span>
            </Link>
            <Link className={cx('item')}>
                <div>
                    <img src={images.advertiseItem} alt="Advertise item"></img>
                </div>
                <span className="text">Khung Giờ Săn Sale</span>
            </Link>
            <Link className={cx('item')}>
                <div>
                    <img src={images.advertiseItem} alt="Advertise item"></img>
                </div>
                <span className="text">Khung Giờ Săn Sale, Khung Giờ Săn Sale</span>
            </Link>
        </div>
    );
}

export default AdvertiseList;
