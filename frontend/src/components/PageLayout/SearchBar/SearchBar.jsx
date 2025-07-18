import { useParams, Link } from 'react-router-dom';
import { useEffect, useState } from 'react';

import { routes } from '@/routes';
import { Star } from '@/components/Icon/Fill';
import { RegularStar } from '@/components/Icon/regular';
import { getCategoryList } from '@/Service/CategoryService';
import { defaultSort } from '@/Config/defaultSort';
import CategoryAttribute from './CategoryAttribute';

import classNames from 'classnames/bind';
import Styles from './SearchBar.module.scss';
const cx = classNames.bind(Styles);

function SearchBar() {
    const { categoryId } = useParams();
    const [list, setList] = useState({ subCategories: [], attributes: [] });
    const { id, name, subCategories, attributes } = list;

    useEffect(() => {
        getCategoryList(categoryId).then((res) => {
            if (res) {
                setList(res);
            }
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);
    const renderCategory = () =>
        subCategories.sort(defaultSort).map((sub) => (
            <Link
                to={`${routes.category}/${sub.id}`}
                key={sub.id}
                className={cx('link', { active: categoryId == sub.id })}
            >
                {sub.name}
            </Link>
        ));
    const renderAttributes = () =>
        attributes.map((attribute, index) => <CategoryAttribute key={index} data={attribute} />);

    return (
        <div className={cx('container')}>
            <div className={cx('header')}>Bộ lọc tìm kiếm</div>
            <div className={cx('category')}>
                <div className={cx('category-header')}>Theo danh mục</div>
                <Link to={`${routes.category}/${id}`} className={cx('link', { active: categoryId == id })}>
                    {name}
                </Link>
                {renderCategory()}
            </div>
            {renderAttributes()}
            <div className={cx('category')}>
                <div className={cx('category-header')}>Khoảng giá</div>
                <div className={cx('category-range')}>
                    <input placeholder="Từ" />
                    <span />
                    <input placeholder="Đến" />
                </div>
                <button className={cx('category-button')}>Áp dụng</button>
            </div>
            <div className={cx('category')}>
                <div className={cx('category-header')}>Đánh giá</div>
                <div>
                    <div className={cx('category-star')}>
                        <div>
                            <Star />
                            <Star />
                            <Star />
                            <Star />
                            <Star />
                        </div>
                    </div>
                    <div className={cx('category-star')}>
                        <div>
                            <Star />
                            <Star />
                            <Star />
                            <Star />
                            <RegularStar />
                        </div>
                        <span>Trở lên</span>
                    </div>
                    <div className={cx('category-star')}>
                        <div>
                            <Star />
                            <Star />
                            <Star />
                            <RegularStar />
                            <RegularStar />
                        </div>
                        <span>Trở lên</span>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SearchBar;
