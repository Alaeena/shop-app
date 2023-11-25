import axios from 'axios';
import { post, get, baseURL } from '@/Util';
export const LoginUser = async (email, password) => {
    try {
        const res = await post('/auth/login', { email, password });
        const token = res.access_token;
        axios.defaults.headers.common.Authorization = `Bearer ${token}`;

        return { data: res };
    } catch (error) {
        const { data } = error.response;
        return { message: data.message };
    }
};
export const RegisterUser = async (payload) => {
    try {
        const res = await get('/auth/register', payload);
        const token = res.access_token;
        axios.defaults.headers.common.Authorization = `Bearer ${token}`;

        return { data: res };
    } catch (error) {
        const { data } = error.response;
        return { message: data.message };
    }
};
export const LogoutUser = async () => {
    try {
        await axios.post(`${baseURL}/auth/logout`);
        delete axios.defaults.headers.common.Authorization;
    } catch (error) {
        console.log(error);
    }
};
