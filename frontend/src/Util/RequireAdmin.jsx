import { Navigate, useLocation, Outlet } from 'react-router-dom';
import { useAuth } from '.';
import { routes } from '@/routes';

function RequireAdmin() {
    const { state } = useAuth();
    const location = useLocation();

    if (state.isLogin) {
        return state.data?.role === 'ADMIN' ? (
            <Outlet />
        ) : (
            <Navigate to={routes.login} state={{ from: location }} replace />
        );
    } else {
        return <Navigate to={routes.root} state={{ from: location }} replace />;
    }
}

export default RequireAdmin;
