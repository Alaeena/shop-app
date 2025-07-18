import Advertise from './Advertise/Advertise';

import classNames from 'classnames/bind';
import Styles from './Advertisement.module.scss';
const cx = classNames.bind(Styles);

function Advertisement() {
    return (
        <div className={cx('banner')}>
            <Advertise />
        </div>
    );
}

export default Advertisement;
