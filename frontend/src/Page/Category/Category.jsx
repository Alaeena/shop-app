import { useEffect, useState } from 'react';
import { useSearchParams, useParams } from 'react-router-dom';

import { getProductList } from '@/Service/ProductService';
import { SearchOption } from '@/components/PageLayout';
import { Pagination, ProductList } from '@/components/Elements';

function Category() {
    const { categoryId } = useParams();
    const [searchParams] = useSearchParams();

    const [total, setTotal] = useState(0);
    const [page, setPage] = useState(0);
    const [list, setList] = useState([]);
    useEffect(() => {
        const query = { pageNumber: page };
        for (let entry of searchParams.entries()) {
            query[entry[0]] = entry[1];
        }
        getProductList(categoryId, query).then((res) => {
            if (res) {
                const { page, maxPage, products } = res;
                setPage(page);
                setTotal(maxPage);
                setList(products);
            }
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [searchParams, page, categoryId]);

    return (
        <div>
            <SearchOption />
            <div>
                <ProductList list={list} row={5} column={5} />
            </div>
            <Pagination total={total} page={page} setPage={setPage} />
        </div>
    );
}

export default Category;
