import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import { routes } from '@/routes';
import { Loading } from '@/components/Icon/Fill';
import { SearchResult } from '@/components/Elements';
import { searchProduct } from '@/Service/ProductService';

import classNames from 'classnames/bind';
import Styles from './HeaderSearch.module.scss';
const cx = classNames.bind(Styles);

function HeaderSearch() {
    const navigate = useNavigate();
    const [page, setPage] = useState({});
    const [value, setValue] = useState('');
    const [isLoading, setLoading] = useState(false);
    const [focus, setFocus] = useState(false);

    useEffect(() => {
        const query = value.trim();
        const params = { pageNumber: 0 };
        if (!query) {
            return;
        }
        setLoading(true);
        const timerId = setTimeout(() => {
            searchProduct(query, params)
                .then((res) => {
                    if (res) {
                        setPage(res);
                    }
                })
                .finally(() => setLoading(false));
        }, 300);
        return () => clearTimeout(timerId);
    }, [value]);

    function handleChange(textValue) {
        if (textValue.startsWith(' ')) {
            setValue('');
        } else {
            setValue(textValue);
        }
    }
    function handleUp(e) {
        if (e.code === 'Enter') {
            setFocus(false);
            navigate(routes.search, { state: value });
        }
    }
    return (
        <div className={cx('container')}>
            <SearchResult visible={focus && value} value={value} page={page} setFocus={setFocus}>
                <div className={cx('box')}>
                    <input
                        value={value}
                        onFocus={() => setFocus(true)}
                        onChange={(e) => handleChange(e.target.value)}
                        onKeyUp={handleUp}
                        className={cx('input', 'text')}
                        spellCheck={false}
                        placeholder="Tìm kiếm trong shop..."
                    />
                    {isLoading && <Loading className={cx('loading', 'spinner')} />}
                </div>
            </SearchResult>
            <button className={cx('button')}>Tìm kiếm</button>
        </div>
    );
}

export default HeaderSearch;
