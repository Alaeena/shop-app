import { useState, useLayoutEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { routes } from '@/routes';

import { useAuth } from '@/Util';
import { LoginUser } from '@/Service/AuthService';

import { EmailInput, PasswordInput } from '@/components/Auth';
import { Facebook, Google } from '@/components/Icon/Fill';
import { Loading } from '@/components/Icon/regular';

import classNames from 'classnames/bind';
import Styles from './Auth.module.scss';
const cx = classNames.bind(Styles);

function Login() {
    const navigate = useNavigate();
    const { action } = useAuth();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);

    const [error, setError] = useState('');
    const disabled = !email || !password;

    useLayoutEffect(() => {
        if (error) {
            setError('');
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [email, password]);
    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);

        LoginUser(email, password)
            .then((res) => {
                const { data, message } = res;
                if (message) {
                    setError(message);
                } else {
                    action.Login(data);
                    navigate('/');
                }
            })
            .finally(() => setLoading(false));
    };
    const validate = (value) => {
        const regx = /^(?=.*?[^\w\s]).{8,}$/;
        const msg = !!value && value.match(regx) ? '' : 'Vui lòng nhập ít nhất 8 ký tự và ít nhất có 1 ký tự đặc biệt';
        return msg;
    };

    return (
        <div className={cx('container')}>
            <div className={cx('content')}>
                <div className={cx('info')}>Đăng nhập</div>
                <form className={cx('form')}>
                    <EmailInput onChange={setEmail} />
                    <PasswordInput validate={validate} placeholder="Mật khẩu" onChange={setPassword} />

                    <span className={cx('link')}>Quên mật khẩu?</span>
                    {error && <div className={cx('error-container')}>{error}</div>}
                    <button onClick={handleSubmit} className={cx('button', { disabled, loading })}>
                        {loading ? <Loading /> : 'Đăng nhập'}
                    </button>
                </form>
                <div className={cx('line')}>
                    <span />
                    <p>Hoặc</p>
                </div>
                <div className={cx('social-login')}>
                    <button>
                        <Facebook />
                        <span>Facebook</span>
                    </button>
                    <button>
                        <Google />
                        <span>Google</span>
                    </button>
                </div>
                <div className={cx('register')}>
                    <span>Bạn mới biết đến app?</span>
                    <Link to={routes.register}>Đăng ký</Link>
                </div>
            </div>
        </div>
    );
}

export default Login;
