package kz.tim.samples.dto;

/**
 * Simple model to store results of the crawler.
 *
 * @author Timur Tibeyev.
 */
public class News {
    private String url;
    private String author;
    private String title;
    private String updateDate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Creates new instance of the {@code News} class.
     * @param url news url
     * @param author author name
     * @param title title
     * @param updateDate update date
     */
    public News(String url, String author, String title, String updateDate) {
        this.url = url;
        this.author = author;
        this.title = title;
        this.updateDate = updateDate;
    }
}
