import { Link } from 'react-router-dom';
import { routes } from '@/routes';

import classNames from 'classnames/bind';
import Styles from './ProductDesc.module.scss';
const cx = classNames.bind(Styles);

function ProductDesc({ data }) {
    const { specification = [], category = {}, categoryName, description = '' } = data;

    const renderCategory = () => {
        const list = [];
        list.push(
            <Link key={1} href={`${routes.category}/${category.customId}}`}>
                {category.name}
            </Link>,
        );

        if (!category.subCategories) {
            return list;
        }
        const filterList = category.subCategories.filter((item) => item.name === categoryName);
        if (filterList.length > 0) {
            const filterItem = filterList[0];
            list.push(<i key={2}>{'>'}</i>);
            list.push(
                <Link key={3} href={`${routes.category}/${filterItem.customId}}`}>
                    {filterItem.name}
                </Link>,
            );
        }
        return list;
    };
    const render = () =>
        Object.keys(specification).map((key, index) => (
            <div key={index} className={cx('item')}>
                <span>{key}</span>
                <p>{specification[key]}</p>
            </div>
        ));
    const renderText = () =>
        description.split(',').map((text, index) => {
            return <p key={index}>{text}</p>;
        });
    return (
        <>
            <div className={cx('specification')}>
                <div className={cx('specification-header')}>Chi tiết sản phẩm</div>
                <div className={cx('content')}>
                    <div className={cx('item')}>
                        <span>Danh mục</span>
                        {renderCategory()}
                    </div>
                    {render()}
                </div>
            </div>
            <div className={cx('specification')}>
                <div className={cx('specification-header')}>Mô tả sản phẩm</div>
                <div className={cx('description', 'content')}>{renderText()}</div>
            </div>
        </>
    );
}

export default ProductDesc;
