import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { ConfirmModal } from '@/components/Modal';
import { deleteUser } from '@/Service/UserService';

import classNames from 'classnames/bind';
import Styles from './AdminUser.module.scss';
const cx = classNames.bind(Styles);

function ContentItem({ index, data = {} }) {
    const navigate = useNavigate();
    const [visible, setVisible] = useState(false);
    const { activated, shop, email, role, id } = data;

    const handleRemove = () => {
        deleteUser(id).then((res) => {
            if (res) {
                navigate(0);
            } else {
                setVisible(false);
            }
        });
    };
    return (
        <div className={cx('row')}>
            <span>{index}</span>
            <span>{email}</span>
            <span>123******</span>
            <span>{role}</span>
            <span>
                <input type="checkbox" checked={activated} readOnly></input>
            </span>
            <span>{shop && <Link>Shop</Link>}</span>
            <span>
                <button onClick={() => setVisible(true)} className={cx('button', 'delete')}>
                    x
                </button>
            </span>
            {visible && (
                <ConfirmModal
                    setVisible={setVisible}
                    onClick={handleRemove}
                    text="Bạn có chắc muốn xoá danh mục này?"
                />
            )}
        </div>
    );
}

export default ContentItem;
