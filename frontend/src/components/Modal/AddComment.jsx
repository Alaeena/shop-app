import { useState } from 'react';
import { createPortal } from 'react-dom';

import { addComment, updateComment } from '@/Service/CommentService';
import { Star } from '../Icon/Fill';
import { RegularStar } from '../Icon/regular';

import classNames from 'classnames/bind';
import Styles from './Modal.module.scss';
const cx = classNames.bind(Styles);

const modalRoot = document.getElementById('root');

function AddComment({ payload, onBack }) {
    const { id, productId, url, name } = payload;
    const [message, setMessage] = useState(payload.message || '');
    const [rating, setRating] = useState(payload.rating || 3);

    const disabled = !message || !rating;
    const handleSubmit = () => {
        const commentDto = { id, message, rating, productId };
        if (id) {
            updateComment(commentDto).then(onBack);
        } else {
            addComment(commentDto).then(onBack);
        }
    };
    const renderStar = () => {
        const list = [];
        for (let index = 1; index <= 5; index++) {
            if (Math.round(rating) < index) {
                list.push(<RegularStar onClick={() => setRating(index)} key={index} />);
            } else {
                list.push(<Star onClick={() => setRating(index)} key={index} />);
            }
        }
        return list;
    };
    return createPortal(
        <div className={cx('overlay')} onClick={(e) => e.stopPropagation()}>
            <div className={cx('content')}>
                <h2>Đánh Giá Sản Phẩm</h2>
                <div className={cx('comment')}>
                    <div className={cx('comment-header')}>
                        <img src={url} alt="avatar" />
                        <p>{name}</p>
                    </div>
                    <span>Chất lượng sản phẩm</span>
                    <div className={cx('option')}>{renderStar()}</div>
                    <div className={cx('comment-content')}>
                        <div className={cx('description')}>
                            <textarea
                                spellCheck="false"
                                name="message"
                                value={message}
                                onChange={(e) => setMessage(e.target.value)}
                                placeholder="Hãy chia sẻ những điều bạn thích về sản phẩm này với những người mua khác nhé."
                            />
                        </div>
                    </div>
                </div>

                <div className={cx('bottom')}>
                    <button onClick={onBack} className={cx('button')}>
                        Trở lại
                    </button>
                    <button onClick={handleSubmit} className={cx('button', 'primary', { disabled })}>
                        Hoàn thành
                    </button>
                </div>
            </div>
        </div>,
        modalRoot,
    );
}

export default AddComment;
