import { createContext, useReducer } from 'react';

const wrapper = (type, payload) => ({ type, payload });
const CartContext = createContext();
const initState = {
    list: [],
    shop: {},
    item: {},
};
const createTree = (list) => {
    const shop = {};
    const item = {};

    list.forEach((element) => {
        const shopId = element.shop.id;
        const shopObject = { value: false, included: false, itemId: [], data: element.shop };

        element.cart.forEach((value) => {
            const itemId = value.id;
            item[itemId] = { value: false, data: value };
            shopObject.itemId.push(itemId);
        });
        shop[shopId] = shopObject;
    });
    return { shop, item };
};

function reducer(state, action) {
    const payload = action.payload;
    let newState;

    switch (action.type) {
        case 'setList':
            newState = {
                ...createTree(payload),
                list: payload,
            };
            return newState;
        case 'selectShop': {
            const shopObject = state.shop[payload];

            const value = !shopObject.value;
            shopObject.value = value;
            shopObject.isIncluded = value;
            shopObject.itemId.forEach((index) => (state.item[index].value = value));
            newState = {
                ...state,
            };
            return newState;
        }
        case 'selectItem': {
            const itemList = state.item;
            const shopList = state.shop;
            const value = !itemList[payload].value;
            itemList[payload].value = value;

            Object.keys(shopList).forEach((shopName) => {
                const shopObject = shopList[shopName];
                let isSelected = true;
                let isIncluded = false;

                shopObject.itemId.forEach((index) => {
                    if (!itemList[index].value) {
                        isSelected = false;
                    } else {
                        isIncluded = true;
                    }
                });
                shopObject.isIncluded = isIncluded;
                shopObject.value = isSelected;
            });
            newState = {
                ...state,
            };
            return newState;
        }
        default:
    }
}
function CartProvider({ children }) {
    const [state, dispatch] = useReducer(reducer, initState);

    const setList = (data) => dispatch(wrapper('setList', data));
    const selectShop = (data) => dispatch(wrapper('selectShop', data));
    const selectItem = (data) => dispatch(wrapper('selectItem', data));

    const action = { setList, selectShop, selectItem };
    return <CartContext.Provider value={{ state, action }}>{children}</CartContext.Provider>;
}

export { CartProvider, CartContext };
