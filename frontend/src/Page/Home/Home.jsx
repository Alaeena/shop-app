import { useEffect, useState } from 'react';
import { getAllProduct } from '@/Service/ProductService';
import { ProductList } from '@/components/Elements';

import classNames from 'classnames/bind';
import Styles from './Home.module.scss';
const cx = classNames.bind(Styles);

function Home() {
    const [list, setList] = useState([]);
    const [page, setPage] = useState(0);
    const [total, setTotal] = useState(0);

    useEffect(() => {
        getAllProduct(page).then((res) => {
            if (res) {
                const { maxPage, products } = res;
                setPage(res.page);
                setTotal(maxPage);
                setList(products);
            }
        });
    }, [page]);

    return (
        <div>
            <div>
                <ProductList list={list} column={5} />
            </div>
            {total - 1 > page && (
                <button onClick={() => setPage((prev) => prev + 1)} className={cx('button')}>
                    Xem thêm
                </button>
            )}
        </div>
    );
}

export default Home;
