import { createPortal } from 'react-dom';
import { useEffect, useState } from 'react';

import { RegularStar } from '../Icon/regular';
import { Star } from '../Icon/Fill';
import images from '@/assets/img';

import classNames from 'classnames/bind';
import Styles from './Modal.module.scss';
import { getComment } from '@/Service/CommentService';
const cx = classNames.bind(Styles);

const modalRoot = document.getElementById('root');

function CommentList({ setVisible, onClick, orderId }) {
    const [list, setList] = useState({ products: [], comments: [] });
    console.log(list);
    useEffect(() => {
        getComment(orderId).then((res) => {
            setList(res.data);
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);
    const renderComment = (comment, item) => {
        const { message, rating, thumbnails } = comment;
        const commentDto = { ...item, ...comment };
        return (
            <div className={cx('comment-content')}>
                <div className={cx('description')}>
                    <img src={images.searchItem} alt="avatar" />
                    <div>
                        <div className={cx('stars')}>{renderStar(rating)}</div>
                        <p>{message}</p>
                        <div className={cx('thumbnails')}>{renderThumbnail(thumbnails)}</div>
                    </div>
                    <button onClick={() => onClick(commentDto)}>Sửa</button>
                </div>
            </div>
        );
    };
    const render = () =>
        list.products.map((item, index) => {
            const { id, name, url } = item;
            const filteredList = list.comments.filter((item) => item.productId === id);
            const comment = filteredList[0];
            const payload = { name, url, productId: id };

            return (
                <div key={index} className={cx('comment')}>
                    <div className={cx('comment-header')}>
                        <img src={url} alt="avatar" />
                        <p>{name}</p>
                        {!comment && <button onClick={() => onClick(payload)}>Thêm</button>}
                    </div>
                    {comment && renderComment(comment, item)}
                </div>
            );
        });

    const renderStar = (value = 0) => {
        const list = [];
        for (let index = 1; index <= 5; index++) {
            if (Math.round(value) < index) {
                list.push(<RegularStar key={index} />);
            } else {
                list.push(<Star key={index} />);
            }
        }
        return list;
    };
    const renderThumbnail = (thumbnail) =>
        thumbnail.map((item, index) => <img key={index} src={images.discountItem} />);
    const handleClick = (e) => {
        e.stopPropagation();
        setVisible(false);
    };
    return createPortal(
        <div className={cx('overlay')} onClick={(e) => e.stopPropagation()}>
            <div className={cx('content', 'width')}>
                <h2>Đánh Giá Shop</h2>
                <div className={cx('list')}>{render([1, 2, 3, 4, 5])}</div>
                <div className={cx('bottom')}>
                    <button onClick={handleClick} className={cx('button', 'primary')}>
                        Ok
                    </button>
                </div>
            </div>
        </div>,
        modalRoot,
    );
}

export default CommentList;
