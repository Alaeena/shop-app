import { useState, useEffect } from 'react';

import { getAllCategory } from '@/Service/CategoryService';
import { AddCategory } from '@/components/Modal';
import ContentItem from './ContentItem';

import classNames from 'classnames/bind';
import Styles from './AdminCategory.module.scss';
const cx = classNames.bind(Styles);

function AdminCategory() {
    const [list, setList] = useState([]);
    const [visible, setVisible] = useState(false);
    const render = () => list.map((item, key) => <ContentItem index={key} key={key} data={item} />);

    useEffect(() => {
        getAllCategory().then((res) => {
            if (res) {
                setList(res);
            }
        });
    }, []);
    return (
        <div className={cx('container')}>
            <div className={cx('header')}>
                <strong>Danh</strong>
                <span>mục</span>
            </div>
            <div className={cx('content')}>
                <button onClick={() => setVisible(true)} className={cx('button', 'add')}>
                    + Thêm danh mục mới
                </button>
                <div className={cx('box')}>{render()}</div>
            </div>
            <AddCategory visible={visible} setVisible={setVisible} />
        </div>
    );
}

export default AdminCategory;
