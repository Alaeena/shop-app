import images from '@/assets/img';

import classNames from 'classnames/bind';
import Styles from './User.module.scss';
const cx = classNames.bind(Styles);

function UserProfile() {
    return (
        <div className={cx('content')}>
            <div className={cx('content-header')}>
                <h2>Hồ sơ của tôi</h2>
                <h4>Quản lý thông tin hồ sơ để bảo mật tài khoản</h4>
            </div>
            <div className={cx('content-flex')}>
                <div className={cx('content-info')}>
                    <div className={cx('text')}>
                        <label>Họ:</label>
                        <span>firstName</span>
                    </div>
                    <div className={cx('text')}>
                        <label>Tên:</label>
                        <span>lastName</span>
                    </div>
                    <div className={cx('text')}>
                        <label>Email:</label>
                        <span>ducbk7a1@gmail.com</span>
                        <a>Xác thực email</a>
                    </div>
                    <div className={cx('text')}>
                        <label>Mật khẩu:</label>
                        <span>******</span>
                        <a>Đổi mật khẩu</a>
                    </div>
                </div>
                <div className={cx('content-avatar')}>
                    <img src={images.ads} alt="avatar" />
                    <button>Chọn Ảnh</button>
                    <span>Dụng lượng file tối đa 1 MB</span>
                    <span>Định dạng:.JPEG, .PNG</span>
                </div>
            </div>
        </div>
    );
}

export default UserProfile;
