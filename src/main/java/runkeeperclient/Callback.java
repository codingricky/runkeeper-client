package runkeeperclient;

public interface Callback<T> {

    void success(T result, String json);
}
