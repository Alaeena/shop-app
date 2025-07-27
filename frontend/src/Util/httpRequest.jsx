import axios from 'axios';
const devURL = 'https://alaeena.azurewebsites.net';

export const baseURL = devURL;
const request = axios.create({
    baseURL,
    headers: { Authorization: '' },
});

export const get = async (url, config = {}) => {
    const response = await request.get(url, config);
    return response.data;
};
export const post = async (url, config = {}) => {
    const response = await request.post(url, config);
    return response.data;
};
export default request;
