import axios from 'axios';
const devURL = 'http://localhost:8080';
// const productionURL = 'http://shop-app-api-env.eba-au48hmby.ap-southeast-1.elasticbeanstalk.com';
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
