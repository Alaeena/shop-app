import { get } from '@/Util';
import { baseURL } from '@/Util';
import axios from 'axios';

export const getCategories = async () => {
    try {
        const res = await get(`/category`);
        return res.data;
    } catch (error) {
        return null;
    }
};
export const getAllCategory = async () => {
    try {
        const res = await get(`/category/all`);
        return res.data;
    } catch (error) {
        return null;
    }
};
export const addCategory = async (name) => {
    try {
        const res = await axios.post(`${baseURL}/category`, { name });
        return res.data;
    } catch (error) {
        return null;
    }
};
export const updateCategory = async (id, name) => {
    try {
        const res = await axios.put(`${baseURL}/category`, { id, name });
        return res.data;
    } catch (error) {
        return null;
    }
};
export const deleteCategory = async (id) => {
    try {
        const res = await axios.delete(`${baseURL}/category/${id}`);
        return res.data;
    } catch (error) {
        return null;
    }
};
export const getCategoryList = async (categoryId) => {
    try {
        const res = await get(`/category/${categoryId}/list`);
        return res.data;
    } catch (error) {
        return null;
    }
};
export const addSubCategory = async (id, name) => {
    try {
        const res = await axios.post(`${baseURL}/category/${id}`, { name });
        return res.data;
    } catch (error) {
        return null;
    }
};
