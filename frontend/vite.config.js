import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
    plugins: [react()],
    resolve: {
        alias: {
            '@': '/src',
        },
    },
    base: '/#/',
    server: {
        host: '0.0.0.0',
        port: 3000,
        proxy: 'http://localhost:8080',
    },
});
