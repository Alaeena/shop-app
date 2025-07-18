import { useState, useCallback, useEffect } from 'react';

import { Link } from 'react-router-dom';
import { LeftButton, RightButton } from '@/components/Icon/regular';
import images from '@/assets/img';

import classNames from 'classnames/bind';
import Styles from './Advertise.module.scss';
const cx = classNames.bind(Styles);
var timerId;

function Slider() {
    const [number, setNumber] = useState(1);
    const [width, setWidth] = useState(null);
    const maxNumber = 7;

    useEffect(() => {
        if (!width) {
            return;
        }
        timerId = setTimeout(() => {
            setNumber((prev) => (prev + 1 > maxNumber ? 1 : prev + 1));
        }, 2500);
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
        <>
            <div ref={onRefChange} style={{ right: (number - maxNumber) * width }} className={cx('slider')}>
                <Link className={cx('item')}>
                    <img src={images.slider1} alt="banner"></img>
                </Link>
                <Link className={cx('item')}>
                    <img src={images.slider2} alt="banner"></img>
                </Link>
                <Link className={cx('item')}>
                    <img src={images.slider3} alt="banner"></img>
                </Link>
                <Link className={cx('item')}>
                    <img src={images.slider4} alt="banner"></img>
                </Link>
                <Link className={cx('item')}>
                    <img src={images.slider5} alt="banner"></img>
                </Link>
                <Link className={cx('item')}>
                    <img src={images.slider6} alt="banner"></img>
                </Link>
                <Link className={cx('item')}>
                    <img src={images.slider7} alt="banner"></img>
                </Link>
            </div>
            <button onClick={onLeftClick} className={cx('left-button')}>
                <LeftButton />
            </button>
            <button onClick={onRightClick} className={cx('right-button')}>
                <RightButton />
            </button>
            <ul className={cx('pagination')}>{render()}</ul>
        </>
    );
}

export default Slider;
