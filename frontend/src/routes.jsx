import { Home, Category, Product, Search, Login, Register, Payment, Error } from './Page';
import { UserAddress, UserProfile, UserOrder, Cart, Checkout } from './Page';
import { AdminCategory, AdminUser } from './admin';
import { RequireAuth, RequireAdmin } from './Util';

import { AdminLayout, UserLayout, DefaultLayout, WithSidebar, MainLayout, DefaultCart } from './Layout';

const routes = {
    root: '/',
    search: '/search',
    login: '/login',
    register: '/register',
    error: '*',

    category: '/category',
    categorySlug: '/category/:categoryId',

    product: '/product',
    productSlug: '/product/:productId',

    // USER ROUTES
    payment: '/payment',
    cart: '/user/cart',
    checkout: '/user/checkout',
    profile: '/user/profile',
    order: '/user/order',
    address: '/user/address',
    user: {
        route: '/user',
        order: 'order',
        profile: 'profile',
        checkout: 'checkout',
        cart: 'cart',
        address: 'address',
    },

    // ADMIN ROUTES
    admin_category: '/admin/category',
    admin_user: '/admin/user',
    admin: { route: '/admin', category: 'category', user: 'user' },
};
const publicRoutes = [
    { path: routes.root, component: Home, layout: MainLayout },
    { path: routes.search, component: Search, layout: DefaultLayout },
    { path: routes.categorySlug, component: Category, layout: WithSidebar },
    { path: routes.productSlug, component: Product, layout: DefaultLayout },
    { path: routes.payment, component: Payment, layout: DefaultLayout },

    { path: routes.login, component: Login, layout: DefaultLayout },
    { path: routes.register, component: Register, layout: DefaultLayout },
    { path: routes.error, component: Error, layout: DefaultLayout },
];
const privateRoutes = [
    {
        path: routes.user.route,
        component: RequireAuth,
        routes: [
            { path: routes.user.profile, component: UserProfile, layout: UserLayout, index: true },
            { path: routes.user.address, component: UserAddress, layout: UserLayout },
            { path: routes.user.order, component: UserOrder, layout: UserLayout },
            { path: routes.user.cart, component: Cart, layout: DefaultCart },
            { path: routes.user.checkout, component: Checkout, layout: DefaultLayout },
        ],
    },
    {
        path: routes.admin.route,
        component: RequireAdmin,
        routes: [
            { path: routes.admin.category, component: AdminCategory, layout: AdminLayout, index: true },
            { path: routes.admin.user, component: AdminUser, layout: AdminLayout },
        ],
    },
];
export { publicRoutes, privateRoutes, routes };
