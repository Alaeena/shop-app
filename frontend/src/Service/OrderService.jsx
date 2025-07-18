import axios from 'axios';
import { baseURL } from '@/Util';

export const getOrder = async (pageNumber, orderState) => {
    try {
        const res = await axios.get(`${baseURL}/order`, {
            params: { pageNumber, orderState },
        });
        return res.data;
    } catch (err) {
        console.log(err);
        return null;
    }
};
export const createOrder = async (addressId, items, checkoutType) => {
    try {
        const res = await axios.post(`${baseURL}/order`, { addressId, items, checkoutType });
        return res.data.data;
    } catch (err) {
        console.log(err);
        return null;
    }
};
export const cancelOrder = async (orderId) => {
    try {
        const res = await axios.get(`${baseURL}/order/cancel`, {
            params: { id: orderId },
        });
        return res.data;
    } catch (err) {
        console.log(err);
        return null;
    }
};
export const payOrder = async (orderId) => {
    try {
        const res = await axios.get(`${baseURL}/order/pay`, {
            params: {
                id: orderId,
            },
        });
        return res.data;
    } catch (err) {
        console.log(err);
        return null;
    }
};
