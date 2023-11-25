import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { deleteCategory } from '@/Service/CategoryService';
import { ConfirmModal, AddCategory, AddSubCategory } from '@/components/Modal';

import classNames from 'classnames/bind';
import Styles from './AdminCategory.module.scss';
const cx = classNames.bind(Styles);

function ContentItem({ index, data = {} }) {
    const navigate = useNavigate();

    const [visible1, setVisible1] = useState(false);
    const [visible2, setVisible2] = useState(false);
    const [visible3, setVisible3] = useState(false);

    const { id, name, subCategories = [] } = data;
    const handleRemove = () => {
        deleteCategory(id).then((res) => {
            if (res) {
                navigate(0);
            } else {
                setVisible1(false);
            }
        });
    };
    const renderSub = () =>
        subCategories.map((item, key) => (
            <div key={key} className={cx('card')}>
                <span>{item.name}</span>
                <ul>
                    {item.attributes.map((value, index) => (
                        <li key={index}>{value.name}</li>
                    ))}
                </ul>
            </div>
        ));

    return (
        <div className={cx('row')}>
            <div className={cx('main-category')}>
                <span>{index + 1}</span>
                <p>{name}</p>
                <button onClick={() => setVisible3(true)} className={cx('button', 'add')}>
                    +
                </button>
                <button onClick={() => setVisible2(true)} className={cx('button', 'edit')}>
                    Chỉnh sửa
                </button>
                <button onClick={() => setVisible1(true)} className={cx('button', 'delete')}>
                    x
                </button>
            </div>
            <div className={cx('sub-category')}>{renderSub()}</div>
            {visible1 && (
                <ConfirmModal
                    setVisible={setVisible1}
                    onClick={handleRemove}
                    text="Bạn có chắc muốn xoá danh mục này?"
                />
            )}
            <AddCategory setVisible={setVisible2} data={data} visible={visible2} />
            <AddSubCategory visible={visible3} setVisible={setVisible3} id={id} />
        </div>
    );
}

export default ContentItem;
