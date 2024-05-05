import { createRouter, createWebHistory } from 'vue-router';
import axios from "axios";

const routes = [
    {
        name: 'dashboardContainer',
        path: '/dashboard',
        component: () => import('./views/DashboardContainer'),
        meta: { requiresAuth: true },
        redirect: () => {
            return { name: 'dashboard' };
        },
        children: [
            {
                name: 'dashboard',
                path: ' ',
                component: () => import('./views/DashboardPage.vue')
            },
            {
                name: 'person',
                path: 'person',
                component: () => import('./views/PersonPage.vue')
            },
            {
                name: 'about',
                path: 'about',
                component: () => import('./views/AboutPage.vue')
            }
        ]
    },
    {
        name: 'login',
        path: '/login',
        component: () => import('./views/LoginPage')
    },
    {
        name: 'home',
        path: '/',
        component: () => import('./views/MainPage')
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});


router.beforeEach((to, from, next) => {
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth);

    // Check if the route requires authentication
    if (requiresAuth) {
        ch(next)
    } else {
        // Route does not require authentication
        next();
    }
});

async function ch(next){
        // Check if the user is logged in (replace with your own authentication check)
        const isLoggedIn = await checkUserLoggedIn();
        if (!isLoggedIn) {
            // User is not logged in, redirect to the login page
            next('/login');
        } else {
            // User is logged in, proceed with navigation
            next();
        }
}

async function checkUserLoggedIn() {
    return axios
        .get('/rest/common/is-logged') // Replace with your authentication endpoint URL
        .then(response => {
            if (response.status === 200) {
                // User is logged in
                return true;
            } else if (response.status === 401) {
                // User is not logged in
                return false;
            } else {
                // Handle other status codes if necessary
                return false;
            }
        })
        .catch(() => {
            // Handle any error that occurred during the request
            //console.error('Error checking user authentication:', error);
            return false;
        });
}

export default router;