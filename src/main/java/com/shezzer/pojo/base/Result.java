package com.shezzer.pojo.base;

public class Result<T> {
    private String msg;
    private T data;
    private Integer code;

    private Result(String msg, T data, Integer code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>("Success", data, 0);
    }

    public static Result success(int code, String data) {
        return new Result<>("Success", data, code);
    }

    public static Result error(String data) {
        return new Result<>("Error", data, -1);
    }

    public static Result failed(int code, String data) {return new Result<>("Failed", data, code);}

//    public static Result error(ExceptionEnum ex) {
//        return error(ex.getCode(), ex.getMsg());
//    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
