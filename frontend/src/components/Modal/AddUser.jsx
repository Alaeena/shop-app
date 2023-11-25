import { createPortal } from 'react-dom';
import { useState } from 'react';

import classNames from 'classnames/bind';
import Styles from './Modal.module.scss';
const cx = classNames.bind(Styles);

const modalRoot = document.getElementById('root');

function AddUser({ visible, setVisible }) {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('USER');
    if (!visible) {
        return;
    }

    const disabled = !firstName || !lastName || !password || !email;
    const onClick = () => {
        setVisible(false);
        // const payload = {
        //     firstName,
        //     lastName,
        //     email,
        //     password,
        //     role,
        // };
    };

    return createPortal(
        <div className={cx('overlay')}>
            <div className={cx('content')}>
                <h2>{'Tạo tài khoản mới'}</h2>
                <div>
                    <input
                        name="firstName"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        placeholder="Họ"
                    />
                    <input
                        name="lastName"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        placeholder="Tên"
                    />
                </div>
                <input
                    name="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Email người dùng"
                />
                <input
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Mật khẩu"
                />
                <select value={role} onChange={(e) => setRole(e.target.value)}>
                    <option value="USER">USER - Người dùng</option>
                    <option value="SHOP">SHOP - Người bán hàng</option>
                </select>
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

export default AddUser;
