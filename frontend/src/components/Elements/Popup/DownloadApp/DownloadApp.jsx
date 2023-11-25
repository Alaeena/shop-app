import CustomTippy from '@tippyjs/react/headless';
import images from '@/assets/img';

import classNames from 'classnames/bind';
import Styles from './DownloadApp.module.scss';
const cx = classNames.bind(Styles);

function DownloadApp({ children }) {
    const render = (attrs) => (
        <div className={cx('tippy')} {...attrs}>
            <img src={images.qrImage} alt="download_qr_code" className={cx('qr')} />
            <div className={cx('content')}>
                <div className={cx('item')}>
                    <img src={images.appStore} alt="app store" />
                </div>
                <div className={cx('item')}>
                    <img src={images.googlePlay} alt="app store" />
                </div>
            </div>
        </div>
    );
    return (
        <CustomTippy
            appendTo={document.body}
            delay={[100]}
            trigger="mouseenter"
            placement="bottom-start"
            interactive
            render={render}
        >
            {children}
        </CustomTippy>
    );
}

export default DownloadApp;
