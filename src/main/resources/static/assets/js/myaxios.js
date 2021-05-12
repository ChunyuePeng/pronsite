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
    var token = getCookie("token");
    config.headers['token'] = token;
    return config;
}, function (error) {
    return Promise.reject(error);
})
my_axios.interceptors.response.use(function (response) {
    if (response.data.errno == 501){
        console.log("未登录");
        window.location.href = "form-login.html";
    }
    if (response.data.errno != 0) {
        return Promise.reject(response.data);
    }
    // 对响应数据做点什么
    return response.data;
}, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error.data);
});

function getCookie(objName) {//获取指定名称的cookie的值
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
        var temp = arrStr[i].split("=");
        if (temp[0] == objName) return unescape(temp[1]);  //解码
    }
    return "";
}