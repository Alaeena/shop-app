import CustomTippy from '@tippyjs/react/headless';
import { Link } from 'react-router-dom';
import { routes } from '@/routes';

import classNames from 'classnames/bind';
import Styles from './SearchResult.module.scss';
const cx = classNames.bind(Styles);

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function SearchResult({ children, visible, setFocus, value, page = {} }) {
    const { results, products = [] } = page;
    const render = (attrs) => (results > 0 ? renderResult(attrs) : renderEmpty(attrs));
    const renderEmpty = (attrs) => (
        <div className={cx('tippy')} {...attrs}>
            <div className={cx('item')}>
                <p>Không có sản phẩm nào với từ khóa</p>
                <strong>`{value}`</strong>
            </div>
        </div>
    );
    const renderResult = (attrs) => (
        <div className={cx('tippy')} {...attrs}>
            <h3>Có {results} giá trị tìm thấy</h3>
            {products.map((item, key) => {
                const { id, price, thumbnails = [], discount, name } = item;
                const discountPrice = (price * (100 - discount)) / 100;
                return (
                    <Link to={`${routes.product}/${id}`} className={cx('item')} key={key}>
                        <img src={thumbnails[0].url} alt="product image" />
                        <span>{name}</span>
                        <div className={cx('price')}>
                            <span>{VND.format(price)}</span>
                            <strong>{VND.format(discountPrice)}</strong>
                        </div>
                    </Link>
                );
            })}
            <Link to={routes.search} state={value} className={cx('item')}>
                <p>Xem tất cả kết quả với từ khóa</p>
                <strong>`{value}`</strong>
            </Link>
        </div>
    );
    return (
        <CustomTippy
            placement="bottom-start"
            onClickOutside={() => setFocus(false)}
            appendTo={document.body}
            render={render}
            visible={visible}
            interactive
        >
            {children}
        </CustomTippy>
    );
}

export default SearchResult;
