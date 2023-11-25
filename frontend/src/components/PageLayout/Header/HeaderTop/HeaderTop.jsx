import { Link } from 'react-router-dom';
import { routes } from '@/routes';
import { useAuth } from '@/Util';

import { DownloadApp, Notification, UserInfo } from '@/components/Elements';
import { BellIcon, QuestionIcon } from '@/components/Icon/regular';

import classNames from 'classnames/bind';
import Styles from './HeaderTop.module.scss';
import images from '@/assets/img';
const cx = classNames.bind(Styles);

function HeaderTop() {
    const { state } = useAuth();
    const username = state.isLogin ? state.data.email.split('@')[0] : 'guest';
    return (
        <div className={cx('container')}>
            <div className={cx('left')}>
                <span className={cx('item', 'line', 'text')}>Kênh người bán</span>
                <DownloadApp>
                    <span className={cx('item', 'line', 'text')}>Tải ứng dụng</span>
                </DownloadApp>
                <span className={cx('item', 'text')}>
                    <QuestionIcon />
                    Hỗ trợ
                </span>
            </div>
            <div className={cx('right')}>
                <Notification>
                    <span className={cx('item', 'text')}>
                        <BellIcon />
                        Thông báo
                    </span>
                </Notification>
                {state.isLogin ? (
                    <UserInfo>
                        <Link to={routes.profile} className={cx('avatar')}>
                            <img src={images.ads}></img>
                            <span className={cx('item')}>{username}</span>
                        </Link>
                    </UserInfo>
                ) : (
                    <>
                        <Link to={routes.login} className={cx('item', 'text')}>
                            Đăng nhập
                        </Link>
                        <Link to={routes.register} className={cx('item', 'text')}>
                            Đăng ký
                        </Link>
                    </>
                )}
            </div>
        </div>
    );
}

export default HeaderTop;
