import axios from 'axios';
import { baseURL } from '@/Util';

export const getAddress = async () => {
    try {
        const res = await axios.get(`${baseURL}/user/address`);
        return res.data;
    } catch (err) {
        console.log(err);
    }
};
export const addAddress = async (list) => {
    try {
        const res = await axios.post(`${baseURL}/user/address`, list);
        return res.data;
    } catch (err) {
        return null;
    }
};
export const updateAddress = async (list) => {
    try {
        const res = await axios.put(`${baseURL}/user/address`, list);
        return res.data;
    } catch (err) {
        return null;
    }
};
export const removeAddress = async (addressId) => {
    try {
        const res = await axios.delete(`${baseURL}/user/address`, { params: { addressId } });
        return res.data;
    } catch (err) {
        return null;
    }
};
