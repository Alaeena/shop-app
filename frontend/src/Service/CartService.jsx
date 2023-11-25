import axios from 'axios';
import { baseURL } from '@/Util';

export const getCart = async () => {
    try {
        const res = await axios.get(`${baseURL}/user/cart`);
        return res.data.data.list;
    } catch (err) {
        console.log(err);
        return null;
    }
};

export const getCartList = async () => {
    try {
        const res = await axios.get(`${baseURL}/user/cart/list`);
        return res.data.data.cart;
    } catch (err) {
        console.log(err);
        return null;
    }
};
export const deleteCart = async (itemId) => {
    try {
        const res = await axios.delete(`${baseURL}/user/cart`, {
            params: { itemId },
        });
        return res.data.data.list;
    } catch (err) {
        console.log(err);
        return null;
    }
};
export const addCartWithOrder = async (orderId) => {
    try {
        const res = await axios.post(`${baseURL}/user/cart/add`, null, {
            params: { orderId },
        });
        return res.data.data.list;
    } catch (err) {
        console.log(err);
        return null;
    }
};
export const addCartWithProductId = async (productId) => {
    try {
        const res = await axios.post(`${baseURL}/user/cart`, null, {
            params: { productId },
        });
        return res.data.data.list;
    } catch (err) {
        console.log(err);
        return null;
    }
};
