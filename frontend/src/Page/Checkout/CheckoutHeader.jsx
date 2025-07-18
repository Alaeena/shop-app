import { useState, useEffect } from 'react';
import { Location } from '@/components/Icon/Fill';
import { AddressList } from '@/components/Modal';

import { getAddress } from '@/Service/AddressService';

import classNames from 'classnames/bind';
import Styles from './Checkout.module.scss';
const cx = classNames.bind(Styles);

function CheckoutHeader({ address = {}, setAddress }) {
    const [list, setList] = useState([]);
    const [visible, setVisible] = useState(false);

    useEffect(() => {
        getAddress().then((res) => {
            const data = res.data;
            setAddress(data[0]);
            setList(data);
        });
    }, []);
    return (
        <div className={cx('header')}>
            <div className={cx('line')}></div>
            <h2>
                <Location />
                Địa Chỉ Nhận Hàng
            </h2>
            <div className={cx('address')}>
                <div>
                    <strong>
                        {address.name} ( {address.phone} )
                    </strong>
                    <span>{address.address}</span>
                </div>
                <button onClick={() => setVisible(true)}>Thay Đổi</button>
            </div>
            {visible && <AddressList data={list} address={address} setAddress={setAddress} setVisible={setVisible} />}
        </div>
    );
}

export default CheckoutHeader;
