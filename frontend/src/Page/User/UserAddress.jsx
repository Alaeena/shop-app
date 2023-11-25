import { useState, useEffect } from 'react';
import { getAddress, removeAddress } from '@/Service/AddressService';

import { AddAddress, ConfirmModal } from '@/components/Modal';

import classNames from 'classnames/bind';
import Styles from './User.module.scss';
const cx = classNames.bind(Styles);

function UserAddress() {
    const [list, setList] = useState([]);
    const [visible1, setVisible1] = useState(false);
    const [visible2, setVisible2] = useState(false);

    const [removeId, setRemoveId] = useState();
    const [item, setItem] = useState({});

    const handleResponse = (res) => {
        if (res) {
            setList(res.data);
        }
    };
    const buttonEdit = (item) => {
        setItem(item);
        setVisible1(true);
    };
    const handleRemove = () => {
        removeAddress(removeId).then(handleResponse);
        setRemoveId(null);
        setVisible2(false);
    };
    const buttonRemove = (addressId) => {
        setRemoveId(addressId);
        setVisible2(true);
    };
    const render = () =>
        list.map((item, index) => (
            <div key={index} className={cx('address-item')}>
                <p>{item.name}</p>
                <span>{item.phone}</span>
                <span>{item.address}</span>
                <strong>{item.addressType === 'HOME' ? 'Nhà riêng' : 'Nơi làm việc'}</strong>
                <div className={cx('address-option')}>
                    <button onClick={() => buttonRemove(item.id)} className={cx('button-del')}>
                        Xóa
                    </button>
                    <button onClick={() => buttonEdit(item)} className={cx('button-update')}>
                        Sửa
                    </button>
                </div>
            </div>
        ));
    useEffect(() => {
        getAddress().then(handleResponse);
    }, []);

    return (
        <div className={cx('content')}>
            <div className={cx('content-header')}>
                <h2>Địa chỉ của tôi</h2>
                <button onClick={() => setVisible1(true)}>+ Thêm địa chỉ mới</button>
            </div>
            <div className={cx('content-column')}>{render()}</div>
            {visible1 && <AddAddress setVisible={setVisible1} setList={setList} item={item} setItem={setItem} />}
            {visible2 && (
                <ConfirmModal
                    setVisible={setVisible2}
                    onClick={handleRemove}
                    text="Bạn có chắc muốn xoá địa chỉ này?"
                />
            )}
        </div>
    );
}

export default UserAddress;
