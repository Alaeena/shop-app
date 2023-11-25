import { useCallback, useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { routes } from '@/routes';

import SidebarIcon from './SidebarIcon';
import { getCategories } from '@/Service/CategoryService';

import classNames from 'classnames/bind';
import Styles from './Sidebar.module.scss';
const cx = classNames.bind(Styles);

function Sidebar() {
    const [list, setList] = useState([]);
    const [height, setHeight] = useState();
    const [fixed, setFixed] = useState(false);

    const onRefchange = useCallback((element) => {
        if (!element) {
            return;
        }
        setHeight(element.offsetTop);
    }, []);

    useEffect(() => {
        getCategories().then((res) => {
            if (res) {
                setList(res);
            }
        });
    }, []);
    useEffect(() => {
        if (!height) {
            return;
        }
        const handleScroll = () => {
            setFixed(window.scrollY > height);
        };
        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, [height]);

    const render = (arrayList) =>
        arrayList.map((value, index) => (
            <Link key={index} className={cx('item')} to={`${routes.category}/${value.id}`}>
                <img src={SidebarIcon[value.name]} alt="image" />
                <span>{value.name}</span>
            </Link>
        ));

    return (
        <div className={cx('box')}>
            <div ref={onRefchange} className={cx('container', { fixed })}>
                <div className={cx('list')}>
                    <div className={cx('header')}>Danh mục</div>
                    {render(list)}
                </div>
            </div>
        </div>
    );
}

export default Sidebar;
