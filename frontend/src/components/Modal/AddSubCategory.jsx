import { createPortal } from 'react-dom';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { addSubCategory } from '@/Service/CategoryService';

import classNames from 'classnames/bind';
import Styles from './Modal.module.scss';
const cx = classNames.bind(Styles);

const modalRoot = document.getElementById('root');

function AddSubCategory({ setVisible, visible, id }) {
    const navigate = useNavigate();
    const [value, setValue] = useState('');
    const [error, setError] = useState('');
    if (!visible) {
        return;
    }
    const disabled = !value;
    const onClick = () => {
        addSubCategory(id, value).then((res) => {
            if (res) {
                navigate(0);
            } else {
                setError('Invalid category name');
            }
        });
    };

    return createPortal(
        <div className={cx('overlay')}>
            <div className={cx('content')}>
                <h2>{'Danh mục con mới'}</h2>
                <input
                    name="category"
                    value={value}
                    onChange={(e) => setValue(e.target.value)}
                    placeholder="Tên danh mục con"
                />
                {error && <span className={cx('error')}>{error}</span>}
                <div className={cx('bottom')}>
                    <button onClick={() => setVisible(false)} className={cx('button')}>
                        Trở lại
                    </button>
                    <button onClick={onClick} className={cx('button', 'primary', { disabled })}>
                        Xác nhận
                    </button>
                </div>
            </div>
        </div>,
        modalRoot,
    );
}

export default AddSubCategory;
