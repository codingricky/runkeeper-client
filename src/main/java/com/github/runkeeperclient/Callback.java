package com.github.runkeeperclient;

public interface Callback<T> {

    void success(T result, String json);
}
