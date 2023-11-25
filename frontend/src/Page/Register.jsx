import { useState, useLayoutEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { routes } from '@/routes';

import { useAuth } from '@/Util';
import { RegisterUser } from '@/Service/AuthService';

import { EmailInput, TextInput, PasswordInput } from '@/components/Auth';
import { Facebook, Google } from '@/components/Icon/Fill';
import { Loading } from '@/components/Icon/regular';

import classNames from 'classnames/bind';
import Styles from './Auth.module.scss';
const cx = classNames.bind(Styles);

function Register() {
    const navigate = useNavigate();
    const { action } = useAuth();

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password1, setPassword1] = useState('');
    const [password2, setPassword2] = useState('');

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const disabled = !firstName || !lastName || !email || !password1 || !password2;

    useLayoutEffect(() => {
        if (error) {
            setError('');
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [email, password1, password2]);
    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        const payload = {
            firstName,
            lastName,
            email,
            password: password1,
            rePassword: password2,
        };

        RegisterUser(payload)
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
    const validate1 = (value) => {
        const regx = /^\w{3,}$/;
        const msg = !!value && value.match(regx) ? '' : 'Vui lòng nhập ít nhất 3 ký tự';
        return msg;
    };

    const validate2 = (value) => {
        const msg = password1 === value ? '' : 'Mật khẩu không trùng nhau';
        return msg;
    };
    return (
        <div className={cx('container')}>
            <div className={cx('content')}>
                <div className={cx('info')}>Đăng ký</div>
                <form className={cx('form')}>
                    <div className={cx('name-box')}>
                        <TextInput placeholder={'Họ'} onChange={setFirstName} />
                        <TextInput placeholder={'Tên'} onChange={setLastName} />
                    </div>
                    <EmailInput onChange={setEmail} />
                    <PasswordInput validate={validate1} placeholder="Mật khẩu" onChange={setPassword1} />
                    <PasswordInput validate={validate2} placeholder="Nhập lại mật khẩu" onChange={setPassword2} />

                    {error && <div className={cx('error-container')}>{error}</div>}
                    <button onClick={handleSubmit} className={cx('button', { disabled, loading })}>
                        {loading ? <Loading /> : 'Đăng ký'}
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
                    <span>Bạn đã có tài khoản?</span>
                    <Link to={routes.register}>Đăng nhập</Link>
                </div>
            </div>
        </div>
    );
}

export default Register;
