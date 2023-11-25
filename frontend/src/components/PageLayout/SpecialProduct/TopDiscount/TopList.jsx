import images from '@/assets/img';
import { routes } from '@/routes';

import classNames from 'classnames/bind';
import Styles from './TopDiscount.module.scss';
import { Link } from 'react-router-dom';
const cx = classNames.bind(Styles);

function TopList({ data }) {
    const render = () =>
        data.map((item, index) => {
            const { price, discount, thumbnails, id } = item;
            const url = thumbnails.length > 0 ? thumbnails[0].url : images.discountItem;
            let reduce = (price * discount) / 10 / 1000000;
            reduce = Math.round(reduce) / 10;

            return (
                <Link to={`${routes.product}/${id}`} key={index} className={cx('item')}>
                    <img src={url} alt="Most search item"></img>
                    <span>Giảm đến {reduce} triệu</span>
                </Link>
            );
        });

    return <div className={cx('list-container')}>{render()}</div>;
}

export default TopList;
