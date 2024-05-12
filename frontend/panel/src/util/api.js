import axios from 'axios';
import {ShowToast} from "@/assets/toastify";
// const api = axios.create({
//     baseURL: 'https://api.example.com', // Replace with your API base URL
//     // other custom configuration options...
// });

const api = axios.create();
//const GO_TO_LOGIN_PAGE = '<tologinpage/>';


api.interceptors.response.use(
/*     response => {
       console.log(response.data)
       return response
/*   if (response.data.toString().startsWith(GO_TO_LOGIN_PAGE)){
           // Get the current host and port
           const origin = window.location.origin;
           // var currentHost = window.location.hostname;
           // var currentPort = window.location.port;

           var newURL = origin + "/login";

           window.location.href = newURL;
       }else {
           return response
       }
    },*/
    response=> {
        if (response.headers.msg){
            ShowToast.success(response.headers.msg)
        }
        return response
    },

    error => {
        // Handle the error globally
        console.error('Axios Error:', error);

        // Show an alert or perform any error handling logic
        //alert('An error occurred. Please try again.');
        //alert(error)
        if (error.response.status === 400){
            ShowToast.error(error.response.data)
        }
        if (error.response.status === 401){
            ShowToast.error(error.response.data)
        }
        if (error.response.status === 404) {
            //ShowToast.error(error.response.data)
            goToLogin();
        }else{
            ShowToast.error(error.message);
        }


        // Return a rejected promise to propagate the error to the caller
        return Promise.reject(error);
    }
);

function goToLogin() {
    const origin = window.location.origin;
    // var currentHost = window.location.hostname;
    // var currentPort = window.location.port;

    var newURL = origin + "/login";

    window.location.href = newURL;
}

export default api;