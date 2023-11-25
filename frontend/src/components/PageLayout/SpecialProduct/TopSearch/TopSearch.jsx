import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';

import ItemSlider from './ItemSlider';
import { RightButton } from '@/components/Icon/regular';
import { getSuggestedList } from '@/Service/ProductService';

import classNames from 'classnames/bind';
import Styles from './TopSearch.module.scss';
const cx = classNames.bind(Styles);

function TopSearch() {
    const [list, setList] = useState([]);

    useEffect(() => {
        getSuggestedList().then((res) => {
            if (res) {
                const { products } = res;
                setList(products);
            }
        });
    }, []);
    return (
        <div className={cx('container')}>
            <div className={cx('header')}>
                <Link className={cx('header-link')}>Được mua nhiều nhất</Link>
                <Link className={cx('header-button')}>
                    <span>Xem tất cả</span>
                    <RightButton />
                </Link>
            </div>
            <ItemSlider data={list} />
        </div>
    );
}

export default TopSearch;
