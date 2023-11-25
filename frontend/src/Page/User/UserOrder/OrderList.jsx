import { useEffect, useState } from 'react';
import { getOrder } from '@/Service/OrderService';
import OrderDetail from './OrderDetail';

import classNames from 'classnames/bind';
import Styles from './UserCheckout.module.scss';
const cx = classNames.bind(Styles);

function OrderList({ section }) {
    const [list, setList] = useState([]);
    const [page, setPage] = useState(0);

    const renderList = () =>
        list.map((item, index) => <OrderDetail section={section} setList={setList} key={index} data={item} />);

    useEffect(() => {
        setPage(0);
        getOrder(page, section).then((res) => {
            if (res) {
                setList(res.data);
            }
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [section]);
    return <div className={cx('list')}>{renderList()}</div>;
}

export default OrderList;
