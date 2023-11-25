import { CartContext } from '@/Config/CartContext';
import { useContext } from 'react';

function useCart() {
    return useContext(CartContext);
}

export default useCart;
