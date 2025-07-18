import { useState } from 'react';
import { createPortal } from 'react-dom';
import { addAddress, updateAddress } from '@/Service/AddressService';

import classNames from 'classnames/bind';
import Styles from './Modal.module.scss';
const cx = classNames.bind(Styles);

const modalRoot = document.getElementById('root');

function AddAddress({ setVisible, setList, item, setItem }) {
    const [receiver, setReceiver] = useState(item.name);
    const [phone, setPhone] = useState(item.phone);
    const [address, setAddress] = useState(item.address);
    const [addressType, setAddressType] = useState(item.addressType);

    const disabled = !receiver || !phone || !address || !addressType;
    const isEmpty = (obj) => {
        return Object.keys(obj).length === 0;
    };
    const handleSubmit = () => {
        const func = isEmpty(item) ? addAddress : updateAddress;
        const newItem = { ...item, ...{ receiver, phone, address, addressType } };
        func(newItem).then((res) => {
            if (res) {
                setList(res.data);
                setItem({});
                setVisible(false);
            }
        });
    };
    const handleClose = () => {
        setItem({});
        setVisible(false);
    };

    return createPortal(
        <div className={cx('overlay')}>
            <div className={cx('content')}>
                <h2>{isEmpty(item) ? 'Địa chỉ mới' : 'Cập nhật địa chỉ'}</h2>
                <input
                    name="name"
                    value={receiver}
                    onChange={(e) => setReceiver(e.target.value)}
                    placeholder="Họ và tên"
                />
                <input
                    name="phone"
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}
                    placeholder="Số điện thoại"
                />
                <input
                    name="address"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                    placeholder="Địa chỉ"
                />
                <span>Loại địa chỉ</span>
                <div className={cx('option')}>
                    <button onClick={() => setAddressType('HOME')} className={cx({ active: addressType === 'HOME' })}>
                        Nhà riêng
                    </button>
                    <button onClick={() => setAddressType('WORK')} className={cx({ active: addressType === 'WORK' })}>
                        Văn phòng
                    </button>
                </div>
                <div className={cx('bottom')}>
                    <button onClick={handleClose} className={cx('button')}>
                        Trở lại
                    </button>
                    <button onClick={handleSubmit} className={cx('button', 'primary', { disabled })}>
                        Hoàn thành
                    </button>
                </div>
            </div>
        </div>,
        modalRoot,
    );
}

export default AddAddress;
