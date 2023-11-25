import { useNavigate } from 'react-router-dom';
import { createPortal } from 'react-dom';
import { useState } from 'react';

import { updateCategory, addCategory } from '@/Service/CategoryService';

import classNames from 'classnames/bind';
import Styles from './Modal.module.scss';
const cx = classNames.bind(Styles);

const modalRoot = document.getElementById('root');

function AddCategory({ setVisible, data, visible }) {
    const navigate = useNavigate();
    const [value, setValue] = useState(data?.name || '');
    if (!visible) {
        return;
    }
    const handleResponse = (res) => {
        if (res) {
            navigate(0);
        } else {
            setVisible(false);
        }
    };
    const onClick = () => {
        if (data) {
            updateCategory(data.id, value).then(handleResponse);
        } else {
            addCategory(value).then(handleResponse);
        }
    };

    return createPortal(
        <div className={cx('overlay')}>
            <div className={cx('content')}>
                <h2>{data ? 'Chỉnh sửa danh mục' : 'Danh mục mới'}</h2>
                <input
                    name="category"
                    value={value}
                    onChange={(e) => setValue(e.target.value)}
                    placeholder="Tên danh mục"
                />
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

export default AddCategory;
