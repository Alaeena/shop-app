import { get } from '@/Util';

export const getProductDetail = async (productId) => {
    try {
        const res = await get(`/product/${productId}`);

        return res.data;
    } catch (error) {
        return null;
    }
};
export const getProductComment = async (productId, query) => {
    try {
        const res = await get(`/product/${productId}/comment`, {
            params: query,
        });

        return res.data;
    } catch (error) {
        return null;
    }
};
export const getProductList = async (categoryId, params) => {
    try {
        const res = await get(`/category/${categoryId}/product`, {
            params,
        });

        return res.data;
    } catch (error) {
        return null;
    }
};
export const searchProduct = async (query, params) => {
    try {
        const res = await get(`/product/search`, {
            params: { ...params, query },
        });

        return res.data;
    } catch (error) {
        return null;
    }
};
export const getAllProduct = async (pageNumber) => {
    try {
        const res = await get(`/product/all`, {
            params: { pageNumber },
        });

        return res.data;
    } catch (error) {
        return null;
    }
};
export const getDiscountList = async () => {
    try {
        const res = await get(`/product/query`, {
            params: { pageSize: 8, sortBy: 'discount' },
        });

        return res.data;
    } catch (error) {
        return null;
    }
};
export const getSuggestedList = async () => {
    try {
        const res = await get(`/product/query`, {
            params: { pageSize: 18, sortBy: 'sold' },
        });

        return res.data;
    } catch (error) {
        return null;
    }
};
