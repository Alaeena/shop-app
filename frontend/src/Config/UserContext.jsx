import { createContext, useReducer } from 'react';
import axios from 'axios';
import Storage from '@/Util/Storage';

// Reducer Function
const UserContext = createContext();
const initState = Storage.get();
const UNAUTHORIZED = 403;

const wrapper = (type, payload) => ({ type, payload });
function reducer(state, action) {
    const payload = action.payload;
    let newState;

    switch (action.type) {
        case 'Login':
            newState = {
                access_token: payload.access_token,
                refresh_token: payload.refresh_token,
                isLogin: true,
                data: payload.userDto,
            };
            Storage.set(newState);
            return newState;
        case 'Logout':
            newState = {
                isLogin: false,
                access_token: '',
                refresh_token: '',
                data: {},
            };
            Storage.set(newState);
            return newState;
        default:
    }
}
function UserProvider({ children }) {
    const [state, dispatch] = useReducer(reducer, initState);

    const Login = (data) => dispatch(wrapper('Login', data));
    const Logout = () => dispatch({ type: 'Logout' });

    axios.interceptors.response.use(
        (response) => response,
        (error) => {
            const response = error.response || {};
            const { status } = response;
            if (status === UNAUTHORIZED) {
                Logout();
            }
            return Promise.reject(error);
        },
    );

    const action = { Login, Logout };
    return <UserContext.Provider value={{ state, action }}>{children}</UserContext.Provider>;
}

if (initState.isLogin === true) {
    axios.defaults.headers.common.Authorization = `Bearer ${initState.access_token}`;
}

export { UserContext, UserProvider };
