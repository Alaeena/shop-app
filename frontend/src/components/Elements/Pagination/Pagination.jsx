import { LeftButton, RightButton } from '@/components/Icon/regular';
import images from '@/assets/img';

import classNames from 'classnames/bind';
import Styles from './Pagination.module.scss';
const cx = classNames.bind(Styles);

function Pagination({ total, page, setPage }) {
    if (total <= 1) {
        return;
    }
    const renderElement = (index) => (
        <div key={index} onClick={() => setPage(index - 1)} className={cx('item', { active: index == page + 1 })}>
            {index}
        </div>
    );
    const renderItem = (key) => (
        <div key={key} className={cx('item')}>
            <img src={images.threeDot} alt="three dot" />
        </div>
    );

    const handleLeft = () => {
        setPage((prev) => (prev - 1 < 0 ? prev : prev - 1));
    };
    const handleRight = () => {
        setPage((prev) => (prev + 1 >= total ? prev : prev + 1));
    };
    const render = () => {
        const list = [];
        const start = page <= 1 ? 1 : page;
        const end = page + 2 >= total ? total : page + 2;

        if (start > 2) {
            list.push(renderElement(1));
            list.push(renderItem(999));
        } else if (start === 2) {
            list.push(renderElement(1));
        }
        for (let index = start; index <= end; index++) {
            list.push(renderElement(index));
        }
        if (total - end > 1) {
            list.push(renderItem(1000));
            list.push(renderElement(total));
        } else if (total - end === 1) {
            list.push(renderElement(total));
        }
        return list;
    };

    return (
        <div className={cx('content')}>
            <button onClick={handleLeft} className={cx('button')}>
                <LeftButton />
            </button>
            {render()}
            <button onClick={handleRight} className={cx('button')}>
                <RightButton />
            </button>
        </div>
    );
}

export default Pagination;
