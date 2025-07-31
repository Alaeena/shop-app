import { useLayoutEffect, useState } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { routes } from '@/routes';
import classNames from 'classnames/bind';
import Styles from './Payment.module.scss';

const cx = classNames.bind(Styles);

function Payment() {
    const [searchParams] = useSearchParams();
    const navigate = useNavigate();

    const [paymentInfo, setPaymentInfo] = useState({
        amount: '',
        orderInfo: '',
        payDate: '',
        responseCode: '',
        transactionNo: '',
        txnRef: '',
        status: '',
    });

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

    const formatPayDate = (raw) => {
        if (!raw || raw.length !== 14) return raw;
        const year = raw.substring(0, 4);
        const month = raw.substring(4, 6);
        const day = raw.substring(6, 8);
        const hour = raw.substring(8, 10);
        const minute = raw.substring(10, 12);
        const second = raw.substring(12, 14);
        return `${day}/${month}/${year} ${hour}:${minute}:${second}`;
    };

    const formattedAmount = paymentInfo.amount ? `${(Number(paymentInfo.amount) / 100).toLocaleString('vi-VN')} đ` : '';
    const safeDecodeURIComponent = (str) => {
        if (!str || !/%[0-9A-Fa-f]{2}/.test(str)) return str;
        try {
            return decodeURIComponent(str);
        } catch {
            return str;
        }
    };
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
                        Số tiền: <span>{formattedAmount}</span>
                    </p>
                    <p>
                        Thời gian thanh toán: <span>{formatPayDate(paymentInfo.payDate)}</span>
                    </p>
                    <p>
                        Thông tin đơn hàng: <span>{safeDecodeURIComponent(paymentInfo.orderInfo || '')}</span>
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
