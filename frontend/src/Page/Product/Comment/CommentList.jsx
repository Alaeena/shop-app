import { Link } from 'react-router-dom';
import { Star } from '@/components/Icon/Fill';
import { RegularStar } from '@/components/Icon/regular';
import images from '@/assets/img';

import classNames from 'classnames/bind';
import Styles from './Comment.module.scss';
const cx = classNames.bind(Styles);

function CommentList({ data = [] }) {
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
    const render = () =>
        data.map((comment, index) => (
            <div key={index} className={cx('item')}>
                <Link>
                    <img src={images.discountItem} alt="avatar" />
                </Link>
                <div className={cx('description')}>
                    <Link>{comment.username.split('@')[0]}</Link>
                    <div className={cx('stars')}>{renderStar(comment.rating)}</div>
                    <span>{comment.createdAt}</span>
                    <p>{comment.message}</p>
                    <div className={cx('thumbnails')}>{renderThumbnail(comment.thumbnails)}</div>
                </div>
            </div>
        ));

    return <div className={cx('list')}>{render()}</div>;
}

export default CommentList;
