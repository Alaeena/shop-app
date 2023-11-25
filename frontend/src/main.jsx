import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import { HashRouter } from 'react-router-dom';
import { UserProvider } from './Config/UserContext.jsx';
import './components/index.scss';

ReactDOM.createRoot(document.getElementById('root')).render(
    <UserProvider>
        <HashRouter>
            <App />
        </HashRouter>
    </UserProvider>,
);
