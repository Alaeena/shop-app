import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import { BrowserRouter } from 'react-router-dom';
import { UserProvider } from './Config/UserContext.jsx';
import { ScrollToTop } from './Util/index.jsx';
import './components/index.scss';

ReactDOM.createRoot(document.getElementById('root')).render(
    <UserProvider>
        <BrowserRouter>
            <ScrollToTop />
            <App />
        </BrowserRouter>
    </UserProvider>,
);
