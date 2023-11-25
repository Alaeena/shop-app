import { useState } from 'react';
import OrderList from './OrderList';

import classNames from 'classnames/bind';
import Styles from './UserCheckout.module.scss';
const cx = classNames.bind(Styles);

const list = [
    { name: 'Tất cả', map: 'ALL' },
    { name: 'Chờ thanh toán', map: 'PAYING' },
    { name: 'Chờ xác nhận', map: 'PENDING' },
    { name: 'Đã xác nhận', map: 'CONFIRMED' },
    { name: 'Đang giao', map: 'DELIVERING' },
    { name: 'Đã nhận', map: 'RECEIVED' },
    { name: 'Đã hủy', map: 'CANCELLED' },
];

function UserOrder() {
    const [section, setSection] = useState('ALL');

    const renderHeader = () =>
        list.map((item, index) => (
            <div
                key={index}
                onClick={() => setSection(item.map)}
                className={cx('header-item', {
                    active: section === item.map,
                })}
            >
                {item.name}
            </div>
        ));
    return (
        <div className={cx('content')}>
            <div className={cx('header')}>{renderHeader()}</div>
            <OrderList section={section} />
        </div>
    );
}

export default UserOrder;
