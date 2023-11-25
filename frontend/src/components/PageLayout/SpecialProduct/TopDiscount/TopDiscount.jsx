import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';

import { RightButton, Truck, Protected } from '@/components/Icon/regular';
import ImageSlider from './ImageSlider';
import TopList from './TopList';

import classNames from 'classnames/bind';
import Styles from './TopDiscount.module.scss';
import { getDiscountList } from '@/Service/ProductService';
const cx = classNames.bind(Styles);

function TopDiscount() {
    const [list, setList] = useState([]);

    useEffect(() => {
        getDiscountList().then((res) => {
            if (res) {
                setList(res.products);
            }
        });
    }, []);
    return (
        <div className={cx('container')}>
            <div className={cx('header')}>
                <div className={cx('header-left')}>
                    <Link>Shop MALL</Link>
                    <span>
                        <Protected />
                        Hàng Chính Hãng 100%
                    </span>
                    <span>
                        <Truck />
                        Miễn Phí Vận Chuyển
                    </span>
                </div>
                <Link className={cx('header-button')}>
                    <span>Xem tất cả</span>
                    <RightButton />
                </Link>
            </div>
            <div className={cx('content')}>
                <ImageSlider />
                <TopList data={list} />
            </div>
        </div>
    );
}

export default TopDiscount;
