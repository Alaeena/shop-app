import { useState, useEffect } from 'react';
import { LeftButton, RightButton } from '@/components/Icon/regular';

import classNames from 'classnames/bind';
import Styles from './ProductInfo.module.scss';
const cx = classNames.bind(Styles);

function ProductSlider({ list = [] }) {
    const [currentPage, setCurrentPage] = useState(1);
    const [image, setImage] = useState(list[0]);
    const number = 4;
    const max = list.length;
    const left = (1 - currentPage) * (100 / number);

    useEffect(() => setImage(list[0]), [list]);
    const onRightClick = () => {
        setCurrentPage((prev) => (prev + number > max ? prev : prev + 1));
    };
    const onLeftClick = () => {
        setCurrentPage((prev) => (prev === 1 ? 1 : prev - 1));
    };
    const render = () =>
        list.map((value, index) => (
            <div onMouseEnter={() => setImage(list[index])} key={index} className={cx('item')}>
                <img src={value.url} alt="image" />
            </div>
        ));
    return (
        <div className={cx('left')}>
            <img src={image ? image.url : ''} />
            <div className={cx('slider')}>
                <div style={{ left: `${left}%`, width: `${(max * 100) / number}%` }} className={cx('list')}>
                    {render()}
                </div>
                {max > number && (
                    <>
                        <button onClick={onLeftClick} className={cx('left-button')}>
                            <LeftButton />
                        </button>
                        <button onClick={onRightClick} className={cx('right-button')}>
                            <RightButton />
                        </button>
                    </>
                )}
            </div>
        </div>
    );
}

export default ProductSlider;
