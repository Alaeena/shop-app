import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';

import { getProductDetail } from '@/Service/ProductService';
import ProductInfo from './ProductInfo';
import Comment from './Comment';
import ProductDesc from './ProductDesc';

function Product() {
    const { productId } = useParams();
    const [data, setData] = useState({});

    useEffect(() => {
        getProductDetail(productId).then((res) => {
            if (res) {
                setData(res);
            }
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <>
            <ProductInfo data={data} />
            <ProductDesc data={data} />
            <Comment />
        </>
    );
}

export default Product;
