import { Star } from '@/components/Icon/Fill';
import { RegularStar } from '@/components/Icon/regular';
import { Heart } from '@/components/Icon/regular';
import { Facebook, Twitter, Pinterest } from '@/components/Icon/Fill';

import ContentInfo from './ContentInfo';
import classNames from 'classnames/bind';
import Styles from './ProductInfo.module.scss';
const cx = classNames.bind(Styles);

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function ProductDesc({ data }) {
    const { comments = [], price = 1 } = data;
    const discount = data.discount / 100 || 0;

    const renderStar = (value = 0) => {
        const list = [];
        for (let index = 1; index <= 5; index++) {
            if (Math.round(value) < index) {
                list.push(<RegularStar key={index} />);
            } else {
                list.push(<Star key={index} />);
            }
        }
        return list;
    };
    return (
        <div className={cx('content')}>
            <div className={cx('header')}>
                <h2>{data.name}</h2>
                <div className={cx('header-info')}>
                    <div className={cx('header-star')}>
                        <a href="#">{data.rating}</a>
                        {renderStar(data.rating)}
                    </div>
                    <div>
                        <a href="#">{comments.length}</a>
                        <span>Đánh giá</span>
                    </div>
                    <div>
                        <a>{data.sold}</a>
                        <span>Đã mua</span>
                    </div>
                </div>
                <div className={cx('header-discount')}>
                    <p>{VND.format(price * (1 - discount))}</p>
                    <span>{VND.format(price)}</span>
                    <div>-{data.discount}%</div>
                </div>
            </div>
            <ContentInfo productId={data.id} quantity={data.quantity} />
            <div className={cx('share')}>
                <div className={cx('share-left')}>
                    <span>Chia sẻ:</span>
                    <Facebook />
                    <Twitter />
                    <Pinterest />
                </div>
                <div className={cx('share-right')}>
                    <Heart />
                    <span>Đã thích ({data.love})</span>
                </div>
            </div>
        </div>
    );
}

export default ProductDesc;
