/**
 * Created by spasoje on 01-Nov-18.
 */
import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8762/',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': localStorage.getItem("token")
    },
    data: {}
    // Here is reason why I added this data:{} https://github.com/axios/axios/issues/86
});

export default instance;