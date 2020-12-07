import * as axios from 'axios';

const BASE_URL = 'http://localhost:8080';

function upload(formData) {
    const url = `${BASE_URL}/upload`; //Maybe changer?
    return axios.post(url, formData);
    /*
        // get data
        .then(x => x.data)
        // add url field
        .then(x => x.map(img => Object.assign({},
            img, { url: `${BASE_URL}/images/${img.id}` }))); */
}

function queryf(queryStr) {
    const url = `${BASE_URL}/query?keyword=`+ queryStr;
    //console.log(url);
    return axios.get(url);
        
}

export { upload }
export { queryf }
