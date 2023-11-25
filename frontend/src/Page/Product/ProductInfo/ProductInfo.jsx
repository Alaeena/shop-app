import ProductSlider from './ProductSlider';
import ProductDesc from './ProductDesc';

import classNames from 'classnames/bind';
import Styles from './ProductInfo.module.scss';
const cx = classNames.bind(Styles);

function ProductInfo({ data = {} }) {
    return (
        <div className={cx('container')}>
            <ProductSlider list={data.thumbnails} />
            <ProductDesc data={data} />
        </div>
    );
}

export default ProductInfo;
