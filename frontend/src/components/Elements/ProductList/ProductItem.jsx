import { Link } from 'react-router-dom';
import { routes } from '@/routes';
import { RegularStar } from '@/components/Icon/regular';
import { Star } from '@/components/Icon/Fill';

import classNames from 'classnames/bind';
import Styles from './Product.module.scss';
import images from '@/assets/img';
const cx = classNames.bind(Styles);

const NUMBER = new Intl.NumberFormat();
const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function ProductItem({ width, data }) {
    const { id, name, price, discount, sold, thumbnails, rating } = data;
    const url = thumbnails.length > 0 ? thumbnails[0].url : images.searchItem;

    const renderStar = () => {
        const list = [];
        for (let index = 1; index <= 5; index++) {
            if (Math.round(rating) < index) {
                list.push(<RegularStar key={index} />);
            } else {
                list.push(<Star key={index} />);
            }
        }
        return list;
    };
    return (
        <div style={{ width }} className={cx('item-container')}>
            <Link to={`${routes.product}/${id}`} className={cx('item')}>
                <div className={cx('image-info')}>
                    <img src={url} alt="item" />
                    <div className={cx('discount')}>
                        <p>{discount}%</p>
                        <span>Giảm</span>
                    </div>
                </div>

                <div className={cx('item-info')}>
                    <span>{name}</span>
                    <p>{VND.format(price)}</p>
                </div>

                <div className={cx('price-info')}>
                    <div>{renderStar()}</div>
                    <span>Đã bán {NUMBER.format(sold)}</span>
                </div>
            </Link>
        </div>
    );
}

export default ProductItem;
