import { createPortal } from 'react-dom';

import classNames from 'classnames/bind';
import Styles from './Modal.module.scss';
const cx = classNames.bind(Styles);

const modalRoot = document.getElementById('root');

function ConfirmModal({ setVisible, onClick, text }) {
    return createPortal(
        <div className={cx('overlay')}>
            <div className={cx('content')}>
                <h2>{text}</h2>
                <div className={cx('bottom')}>
                    <button onClick={() => setVisible(false)} className={cx('button')}>
                        Trở lại
                    </button>
                    <button onClick={onClick} className={cx('button', 'primary')}>
                        Xác nhận
                    </button>
                </div>
            </div>
        </div>,
        modalRoot,
    );
}

export default ConfirmModal;
