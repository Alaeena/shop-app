import { UserContext } from '@/Config/UserContext';
import { useContext } from 'react';

function useAuth() {
    return useContext(UserContext);
}

export default useAuth;
