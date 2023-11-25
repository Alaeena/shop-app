import { useState } from 'react';
import { Link } from 'react-router-dom';

import images from '@/assets/img';
import { routes } from '@/routes';
import { LeftButton, RightButton } from '@/components/Icon/regular';

import classNames from 'classnames/bind';
import Styles from './TopSearch.module.scss';
const cx = classNames.bind(Styles);
const NUMBER = new Intl.NumberFormat();

function ItemSlider({ data = [] }) {
    const [currentPage, setCurrentPage] = useState(1);
    const page = Math.ceil(data.length / 6);
    const right = `${(currentPage - 3) * 100}%`;

    const onRightClick = () => {
        setCurrentPage((prev) => (prev + 1 > page ? 1 : prev + 1));
    };
    const onLeftClick = () => {
        setCurrentPage((prev) => (prev - 1 <= 0 ? page : prev - 1));
    };
    const render = () =>
        data.map((item, index) => {
            const { name, thumbnails = [], sold, id } = item;
            const url = thumbnails.length > 0 ? thumbnails[0].url : images.searchItem;
            return (
                <Link
                    key={index}
                    to={`${routes.product}/${id}`}
                    style={{ width: 100 / 18 + '%' }}
                    className={cx('item')}
                >
                    <div className={cx('item-info')}>
                        <div>top</div>
                        <img src={url} alt="item"></img>
                        <span> đã bán {NUMBER.format(sold)} sản phẩm</span>
                    </div>
                    <span>{name}</span>
                </Link>
            );
        });

    return (
        <div className={cx('content')}>
            <div className={cx('holder')}>
                <div style={{ right: right }} className={cx('slider')}>
                    {render()}
                </div>
            </div>
            {currentPage > 1 && (
                <button onClick={onLeftClick} className={cx('left-button')}>
                    <LeftButton />
                </button>
            )}
            {currentPage !== page && (
                <button onClick={onRightClick} className={cx('right-button')}>
                    <RightButton />
                </button>
            )}
        </div>
    );
}

export default ItemSlider;
