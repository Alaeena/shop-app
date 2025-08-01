import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
    plugins: [react()],
    resolve: {
        alias: {
            '@': '/src',
        },
    },
    base: '/shop-app',
    server: {
        host: '0.0.0.0',
        port: 3000,
    },
});
