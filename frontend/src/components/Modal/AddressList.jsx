import { useState } from 'react';
import { createPortal } from 'react-dom';
import { Link } from 'react-router-dom';
import { routes } from '@/routes';

import classNames from 'classnames/bind';
import Styles from './Modal.module.scss';
const cx = classNames.bind(Styles);

const modalRoot = document.getElementById('root');

function AddressList({ setVisible, data = [], address, setAddress }) {
    const [selecting, setSelecting] = useState(address);

    const handleClick = () => {
        setAddress(selecting);
        setVisible(false);
    };
    const render = () =>
        data.map((item, index) => (
            <div onClick={() => setSelecting(item)} key={index} className={cx('item')}>
                <input checked={selecting.id === item.id} type="radio" />
                <div className={cx('item-info')}>
                    <div>
                        <strong>{item.name}</strong>
                        <span>( {item.phone} )</span>
                    </div>
                    <span>{item.address}</span>
                </div>
                <Link to={routes.address}>Cập nhật</Link>
            </div>
        ));

    return createPortal(
        <div className={cx('overlay')}>
            <div className={cx('content')}>
                <h2>Địa Chỉ Của Tôi</h2>
                <div className={cx('list')}>
                    {render()}
                    <Link to={routes.address} className={cx('add')}>
                        + Thêm Địa Chỉ Mới
                    </Link>
                </div>
                <div className={cx('bottom')}>
                    <button onClick={() => setVisible(false)} className={cx('button')}>
                        Huỷ
                    </button>
                    <button onClick={() => handleClick()} className={cx('button', 'primary')}>
                        Xác nhận
                    </button>
                </div>
            </div>
        </div>,
        modalRoot,
    );
}

export default AddressList;
