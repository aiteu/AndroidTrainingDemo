package com.aiteu.training.base;

public interface Callback<T> {

    void onSuccess(T t);

    void onError();
}
