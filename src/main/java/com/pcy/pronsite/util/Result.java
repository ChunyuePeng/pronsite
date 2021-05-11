package com.pcy.pronsite.util;

/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/5/11 16:36
 */
public class Result {
    private int code;
    private String msg;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result ok(){
        return ok(null);
    }

    public static Result ok(String msg) {
        return new Result(200,msg);
    }
    public static Result fail(){
        return fail(null);
    }

    public static Result fail(String msg) {
        return new Result(500,msg);
    }
    public static Result unlogin(){
        return new Result(600,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
