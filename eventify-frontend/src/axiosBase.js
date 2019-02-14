/**
 * Created by spasoje on 01-Nov-18.
 */
import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8762/'
});

export default instance;