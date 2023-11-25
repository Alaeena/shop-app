import axios from 'axios';
import { baseURL } from '@/Util';

export const getComment = async (orderId) => {
    try {
        const res = await axios.get(`${baseURL}/comment`, {
            params: { orderId },
        });
        return res.data;
    } catch (err) {
        console.log(err);
    }
};
export const addComment = async (list) => {
    try {
        const res = await axios.post(`${baseURL}/comment`, list);
        return res.data;
    } catch (err) {
        return null;
    }
};
export const updateComment = async (list) => {
    try {
        const res = await axios.put(`${baseURL}/comment`, list);
        return res.data;
    } catch (err) {
        return null;
    }
};
