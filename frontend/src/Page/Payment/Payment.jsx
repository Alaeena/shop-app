import { useLayoutEffect, useState } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { routes } from '@/routes';

import classNames from 'classnames/bind';
import Styles from './Payment.module.scss';
const cx = classNames.bind(Styles);

function Payment() {
    const [searchParams] = useSearchParams();
    const navigate = useNavigate();

    const [paymentInfo, setPaymentInfo] = useState({});

    useLayoutEffect(() => {
        const result = {
            amount: searchParams.get('vnp_Amount'),
            orderInfo: searchParams.get('vnp_OrderInfo'),
            payDate: searchParams.get('vnp_PayDate'),
            responseCode: searchParams.get('vnp_ResponseCode'),
            transactionNo: searchParams.get('vnp_TransactionNo'),
            txnRef: searchParams.get('vnp_TxnRef'),
            status: searchParams.get('vnp_TransactionStatus'),
        };
        setPaymentInfo(result);
    }, [searchParams]);

    const isSuccess = paymentInfo.responseCode === '00' && paymentInfo.status === '00';

    return (
        <div className={cx('container')}>
            <div className={cx('content')}>
                <div className={cx('info')}>{isSuccess ? 'Thanh toán thành công' : 'Thanh toán thất bại'}</div>

                <div className={cx('form')}>
                    <p>
                        Mã giao dịch: <span>{paymentInfo.transactionNo}</span>
                    </p>
                    <p>
                        Mã đơn hàng: <span>{paymentInfo.txnRef}</span>
                    </p>
                    <p>
                        Số tiền: <span>{(paymentInfo.amount / 100).toLocaleString()} đ</span>
                    </p>
                    <p>
                        Thời gian thanh toán: <span>{paymentInfo.payDate}</span>
                    </p>
                    <p>
                        Thông tin đơn hàng: <span>{decodeURIComponent(paymentInfo.orderInfo || '')}</span>
                    </p>
                </div>

                <button className={cx('button')} onClick={() => navigate(routes.order)}>
                    Quay lại đơn hàng
                </button>
            </div>
        </div>
    );
}

export default Payment;
