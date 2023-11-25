import { Star } from '@/components/Icon/Fill';
import { RegularStar } from '@/components/Icon/regular';

import classNames from 'classnames/bind';
import Styles from './Comment.module.scss';
const cx = classNames.bind(Styles);

function CommentHeader({ setActive, active }) {
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
    return (
        <div className={cx('header')}>
            <h3>Đánh giá sản phẩm</h3>
            <div className={cx('header-option')}>
                <div className={cx('header-left')}>
                    <div>
                        <strong>4.3</strong>
                        <span> trên 5</span>
                    </div>
                    <div>{renderStar(4.3)}</div>
                </div>
                <div className={cx('header-right')}>
                    <button className={cx({ active: !active })} onClick={() => setActive()}>
                        Tất cả
                    </button>
                    <button className={cx({ active: active === 5 })} onClick={() => setActive(5)}>
                        5 sao
                    </button>
                    <button className={cx({ active: active === 4 })} onClick={() => setActive(4)}>
                        4 sao
                    </button>
                    <button className={cx({ active: active === 3 })} onClick={() => setActive(3)}>
                        3 sao
                    </button>
                    <button className={cx({ active: active === 2 })} onClick={() => setActive(2)}>
                        2 sao
                    </button>
                    <button className={cx({ active: active === 1 })} onClick={() => setActive(1)}>
                        1 sao
                    </button>
                </div>
            </div>
        </div>
    );
}

export default CommentHeader;
