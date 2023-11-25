import { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import images from '@/assets/img';
import { LeftButton, RightButton } from '@/components/Icon/regular';

import classNames from 'classnames/bind';
import Styles from './TopDiscount.module.scss';
const cx = classNames.bind(Styles);
var timerId;

function ImageSlider() {
    const [visible, setVisible] = useState(false);
    const [number, setNumber] = useState(1);
    const [width, setWidth] = useState(null);
    const maxNumber = 4;

    useEffect(() => {
        if (!width) {
            return;
        }
        timerId = setTimeout(() => {
            setNumber((prev) => (prev + 1 > maxNumber ? 1 : prev + 1));
        }, 2000);
        return () => clearTimeout(timerId);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [width, number]);
    const onRefChange = useCallback((node) => {
        if (node) {
            setWidth(node.clientWidth / maxNumber);
        }
    }, []);
    const onRightClick = () => {
        setNumber((prev) => (prev + 1 > maxNumber ? 1 : prev + 1));
    };
    const onLeftClick = () => {
        setNumber((prev) => (prev - 1 <= 0 ? maxNumber : prev - 1));
    };
    const render = () => {
        var list = [];
        for (let index = 1; index <= maxNumber; index++) {
            list.push(
                <li key={index} onClick={() => setNumber(index)} className={index == number ? cx('active') : ''}></li>,
            );
        }
        return list;
    };
    return (
        <div
            onMouseEnter={() => setVisible(true)}
            onMouseLeave={() => setVisible(false)}
            className={cx('slider-container')}
        >
            <div ref={onRefChange} style={{ right: (number - maxNumber) * width }} className={cx('slider')}>
                <Link className={cx('banner')}>
                    <img src={images.banner1} alt="banner"></img>
                </Link>
                <Link className={cx('banner')}>
                    <img src={images.banner2} alt="banner"></img>
                </Link>
                <Link className={cx('banner')}>
                    <img src={images.banner3} alt="banner"></img>
                </Link>
                <Link className={cx('banner')}>
                    <img src={images.banner4} alt="banner"></img>
                </Link>
            </div>
            <button onClick={onLeftClick} className={cx('left-button', { visible })}>
                <LeftButton />
            </button>
            <button onClick={onRightClick} className={cx('right-button', { visible })}>
                <RightButton />
            </button>
            <ul className={cx('pagination')}>{render()}</ul>
        </div>
    );
}

export default ImageSlider;
