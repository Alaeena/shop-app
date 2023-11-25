import useCart from '@/Util/useCart';

import { Link } from 'react-router-dom';
import { routes } from '@/routes';
import ShopItem from './ShopItem';

import classNames from 'classnames/bind';
import Styles from './Cart.module.scss';
const cx = classNames.bind(Styles);

function ShopList({ data }) {
    const { action, state } = useCart();
    const { shop, cart } = data;
    const render = (list) => list.map((item, key) => <ShopItem key={key} data={item} />);

    const shopId = shop.id;
    const value = state.shop[shopId].value;
    const handleChange = () => {
        action.selectShop(shopId);
    };
    return (
        <div className={cx('shop')}>
            <div className={cx('shop-info')}>
                {value ? (
                    <input checked onChange={handleChange} type="checkbox" />
                ) : (
                    <input onChange={handleChange} type="checkbox" />
                )}
                <img src={shop.url} alt="shop avatar" />
                <Link to={`${routes}/${shop.id}`}>{shop.name}</Link>
            </div>
            <div>{render(cart)}</div>
        </div>
    );
}

export default ShopList;
