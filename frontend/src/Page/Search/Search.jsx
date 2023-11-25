import { useLocation, useSearchParams } from 'react-router-dom';
import { useState, useEffect } from 'react';

import { QuestionIcon } from '@/components/Icon/regular';
import { searchProduct } from '@/Service/ProductService';
import { SearchOption } from '@/components/PageLayout';
import { Pagination, ProductList } from '@/components/Elements';

import images from '@/assets/img';
import classNames from 'classnames/bind';
import Styles from './Search.module.scss';
const cx = classNames.bind(Styles);

function Search() {
    const [searchParams] = useSearchParams();
    const { state = '' } = useLocation();

    const [total, setTotal] = useState(0);
    const [page, setPage] = useState(0);
    const [list, setList] = useState([]);

    useEffect(() => {
        if (!state) {
            return;
        }
        const params = { pageNumber: page, pageSize: 10 };
        for (let entry of searchParams.entries()) {
            params[entry[0]] = entry[1];
        }
        searchProduct(state, params).then((res) => {
            if (res) {
                const { page, maxPage, products } = res;
                setPage(page);
                setTotal(maxPage);
                setList(products);
            }
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [state, page]);
    return (
        <>
            <div className={cx('panel')}>
                <QuestionIcon />
                <span>Sản phẩm tìm kiếm cho từ khoá</span>
                <strong>`{state}`</strong>
            </div>
            <SearchOption />
            <div>
                {list.length > 0 ? (
                    <ProductList list={list} column={5} />
                ) : (
                    <div className={cx('box')}>
                        <img src={images.notFound} />
                        <span>Không tìm thấy kết quả nào</span>
                        <p>Hãy thử sử dụng các từ khóa chung chung hơn</p>
                    </div>
                )}
            </div>
            <Pagination total={total} page={page} setPage={setPage} />
        </>
    );
}

export default Search;
