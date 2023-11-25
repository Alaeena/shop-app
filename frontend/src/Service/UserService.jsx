import axios from 'axios';
import { baseURL } from '@/Util';

export const getAllUser = async () => {
    try {
        const res = await axios.get(`${baseURL}/user/all`);
        return res.data;
    } catch (err) {
        console.log(err);
    }
};
export const deleteUser = async (id) => {
    try {
        const res = await axios.delete(`${baseURL}/user/${id}`);
        return res.data;
    } catch (err) {
        console.log(err);
    }
};
