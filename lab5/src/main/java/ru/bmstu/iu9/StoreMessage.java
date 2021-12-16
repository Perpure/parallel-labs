package ru.bmstu.iu9;

public class StoreMessage {
    private final String url;
    private final Long time;

    public StoreMessage(String url, Long time) {
        this.url = url;
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public Long getTime() {
        return time;
    }

}
