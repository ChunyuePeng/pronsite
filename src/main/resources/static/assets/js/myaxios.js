var my_axios_config = {
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET,POST,PUT',
        'Access-Control-Allow-Headers': 'x-requested-with,content-type,token'
    },
    baseURL: host,
    transformRequest: [function (data) {
        // 对 data 进行任意转换处理
        return JSON.stringify(data);
    }],
    responseType: 'json',
    withCredentials: false

}
var my_axios = axios.create(my_axios_config);
my_axios.interceptors.request.use(function (config) {
    return config;
}, function (error) {
    return Promise.reject(error);
})
my_axios.interceptors.response.use(function (response) {
    console.log(response)
    if (response.data.code!=200){
        Promise.reject(response.data);
    }
    // 对响应数据做点什么
    return response.data;
}, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
});

function getToken() {
    var cookies = document.cookie.split(";");
    for (let i = 0; i < cookies.length; i++) {
        var kv = cookies[i].split("=");
        if (kv[0] == 'token') {
            return kv[1];
        }
    }
    return null;
}