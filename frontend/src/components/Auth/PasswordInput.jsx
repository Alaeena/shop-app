import { useState, useEffect } from 'react';
import { Error } from '../Icon/regular';
import { EyeClosed, EyeOpen } from '../Icon/Fill';

import classNames from 'classnames/bind';
import Styles from './Auth.module.scss';
const cx = classNames.bind(Styles);

function PasswordInput({ onChange, validate, placeholder }) {
    const [value, setValue] = useState('');
    const [visible, setVisible] = useState(false);
    const [error, setError] = useState('');

    useEffect(() => {
        if (!value) {
            return;
        }
        const timerId = setTimeout(() => {
            const msg = validate(value);
            const sentValue = !msg ? value : '';
            onChange(sentValue);
        }, 500);
        return () => clearTimeout(timerId);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [value]);

    const handleBlur = () => {
        const msg = validate(value);
        if (msg) {
            setError(msg);
        }
    };
    const handleChange = (targetValue) => {
        setValue(targetValue);
        if (error) {
            setError('');
        }
    };

    return (
        <div className={cx('form-group', { error: !!error })}>
            <div className={cx('form-wrapper')}>
                <input
                    onChange={(e) => handleChange(e.target.value)}
                    onBlur={handleBlur}
                    spellCheck={false}
                    placeholder={placeholder}
                    type={visible ? 'text' : 'password'}
                />
                <div className={cx('icon-container')}>
                    {visible ? (
                        <EyeOpen className={cx('icon-eye')} onClick={() => setVisible(false)} />
                    ) : (
                        <EyeClosed className={cx('icon-eye')} onClick={() => setVisible(true)} />
                    )}
                    {!!error && <Error className={cx('icon-error')} />}
                </div>
            </div>
            {!!error && <span>{error}</span>}
        </div>
    );
}

export default PasswordInput;
