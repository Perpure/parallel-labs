package ru.bmstu.iu9;

public class StoreMessage {
    private final String url;
    private final Integer time;

    public StoreMessage(String url, Integer time) {
        this.url = url;
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public Integer getTime() {
        return time;
    }

}
