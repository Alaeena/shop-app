import { useSearchParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

import classNames from 'classnames/bind';
import Styles from './SearchOption.module.scss';
const cx = classNames.bind(Styles);

const config = {
    highPrice: { sortDirection: 'DESC', sortBy: 'price' },
    lowPrice: { sortDirection: 'ASC', sortBy: 'price' },
    discount: { sortDirection: 'DESC', sortBy: 'discount' },
    rating: { sortDirection: 'DESC', sortBy: 'rating' },
    sold: { sortDirection: 'DESC', sortBy: 'sold' },
};

function SearchOption() {
    const [searchParams, setSearchParams] = useSearchParams();
    const [active, setActive] = useState();

    useEffect(() => {
        if (active) {
            for (const name of Object.keys(active)) {
                searchParams.set(name, active[name]);
            }
            setSearchParams(searchParams);
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [active]);

    return (
        <div className={cx('container')}>
            <div className={cx('left')}>
                <span className={cx({ activated: active === config.rating })} onClick={() => setActive(config.rating)}>
                    Yêu thích
                </span>
                <span className={cx({ activated: active === config.sold })} onClick={() => setActive(config.sold)}>
                    Bán chạy
                </span>
                <span
                    className={cx({ activated: active === config.discount })}
                    onClick={() => setActive(config.discount)}
                >
                    Giảm giá nhiều
                </span>
                <span
                    className={cx({ activated: active === config.lowPrice })}
                    onClick={() => setActive(config.lowPrice)}
                >
                    Giá thấp đến cao
                </span>
                <span
                    className={cx({ activated: active === config.highPrice })}
                    onClick={() => setActive(config.highPrice)}
                >
                    Giá cao đến thấp
                </span>
            </div>
        </div>
    );
}

export default SearchOption;
