import axios from 'axios';

// Use this URL when developing locally and running Vue separately from the service
axios.defaults.baseURL = 'http://localhost:5000';

// Use this URL when bundling the Vue into the application WAR file
//axios.defaults.baseURL = '.';

export default {
    solveBoard(letters) {
        let url = `/words/${letters}`;
        return axios.get(url);
    }
}