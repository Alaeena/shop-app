import { routes } from '@/routes';
import { useAuth } from '.';
import { Navigate, useLocation, Outlet } from 'react-router-dom';

function RequireAuth() {
    const { state } = useAuth();
    const location = useLocation();

    return <>{state.isLogin ? <Outlet /> : <Navigate to={routes.login} state={{ from: location }} replace />}</>;
}

export default RequireAuth;
