import { useState, useEffect } from 'react';

import UserItem from './UserItem';
import { AddUser } from '@/components/Modal';
import { getAllUser } from '@/Service/UserService';

import classNames from 'classnames/bind';
import Styles from './AdminUser.module.scss';
const cx = classNames.bind(Styles);

function UserContent() {
    const [list, setList] = useState([]);
    const [visible, setVisible] = useState(false);

    const render = () => list.map((item, key) => <UserItem index={key} key={key} data={item} />);

    useEffect(() => {
        getAllUser().then((res) => {
            if (res) {
                setList(res);
            }
        });
    }, []);
    return (
        <div className={cx('content')}>
            <button onClick={() => setVisible(true)} className={cx('button', 'add')}>
                Tạo tài khoản
            </button>
            <div className={cx('row', 'first-line')}>
                <span>ID</span>
                <span>Email</span>
                <span>Mật khẩu</span>
                <span>Vai trò</span>
                <span>Xác thực</span>
                <span>Shop</span>
                <span></span>
            </div>
            {render()}
            <AddUser visible={visible} setVisible={setVisible} />
        </div>
    );
}

export default UserContent;
