import Toastify from 'toastify-js';
export const ShowToast = {
    default: function (message, options = {}) {
        Toastify({
            text: message,
            duration: options.duration || 3000,
            close: options.close || true,
            gravity: options.gravity || 'top',
            // Add other configuration options as needed
            className: options.className || '',
            // style:{'background':'red'}
        }).showToast();
    },
    error: function (message, options = {}) {
        this.default(message, { ...options, className: 'toastify-error' });
    },
    warning: function (message, options = {}) {
        this.default(message, { ...options, className: 'toastify-warning' });
    },
    success: function (message, options = {}) {
        this.default(message, { ...options, className: 'toastify-success' });
    }
};