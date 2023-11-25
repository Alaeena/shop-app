const KEY = 'SHOP_STORAGE';
const initState = {
    isLogin: false,
    access_token: '',
    refresh_token: '',
    data: {},
};

export default {
    get() {
        return JSON.parse(localStorage.getItem(KEY)) || initState;
    },
    set(value) {
        localStorage.setItem(KEY, JSON.stringify(value));
    },
};
