package com.mkao.newsgo.api.models;

public class Wrapper<T> {
    private T data;
    private Throwable mError;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Throwable getmError() {
        return mError;
    }

    public void setmError(Throwable mError) {
        this.mError = mError;
    }
}
