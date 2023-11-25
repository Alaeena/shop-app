import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getProductComment } from '@/Service/ProductService';

import { Pagination } from '../../../components/Elements';
import CommentHeader from './CommentHeader';
import CommentList from './CommentList';

import classNames from 'classnames/bind';
import Styles from './Comment.module.scss';
const cx = classNames.bind(Styles);

function Comment() {
    const { productId } = useParams();
    const [active, setActive] = useState();

    const [list, setList] = useState([]);
    const [page, setPage] = useState(0);
    const [total, setTotal] = useState(0);

    useEffect(() => {
        const query = { pageNumber: page, rating: active };

        getProductComment(productId, query).then((res) => {
            if (res) {
                const { maxPage, comments } = res;
                setPage(res.page);
                setTotal(maxPage);
                setList(comments);
            }
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [page, active]);

    return (
        <div className={cx('container')}>
            <CommentHeader active={active} setActive={setActive} />
            <CommentList data={list} />
            <Pagination page={page} total={total} setPage={setPage} />
        </div>
    );
}

export default Comment;
