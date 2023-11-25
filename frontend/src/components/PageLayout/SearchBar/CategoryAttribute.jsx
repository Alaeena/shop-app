import { useSearchParams } from 'react-router-dom';
import { useState, useEffect } from 'react';

import classNames from 'classnames/bind';
import Styles from './SearchBar.module.scss';
import { defaultSort } from '@/Config/defaultSort';
const cx = classNames.bind(Styles);

function CategoryAttribute({ data }) {
    const [searchParams, setSearchParams] = useSearchParams();
    const { name, attributes } = data;
    const [extend, setExtend] = useState(true);
    const [active, setActive] = useState();

    useEffect(() => {
        if (attributes.length > 5) {
            setExtend(false);
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);
    useEffect(() => {
        if (active) {
            searchParams.set(name, active);
        } else {
            searchParams.delete(name);
        }
        setSearchParams(searchParams);

        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [active]);
    const handleClick = (value) => {
        if (value === active) {
            setActive();
        } else {
            setActive(value);
        }
    };

    const renderAttributeValue = (values) =>
        values.sort(defaultSort).map((value, index) => (
            <label onClick={() => handleClick(value)} key={index}>
                <input checked={active === value} readOnly type="checkbox" />
                <span onClick={(e) => e.stopPropagation()}>{value}</span>
            </label>
        ));
    return (
        <div className={cx('category')}>
            <div className={cx('category-header')}>{name}</div>
            <div className={cx('category-list', { extend: !extend })}>{renderAttributeValue(attributes)}</div>
            {!extend && <span onClick={() => setExtend(true)}>Thêm</span>}
        </div>
    );
}

export default CategoryAttribute;
