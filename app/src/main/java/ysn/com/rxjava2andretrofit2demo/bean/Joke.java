package ysn.com.rxjava2andretrofit2demo.bean;

import com.google.gson.annotations.SerializedName;

public class Joke {
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("poster")
    private String poster;
    @SerializedName("url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
