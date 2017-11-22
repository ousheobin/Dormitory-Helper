package cn.edu.gcu.dormitory.helper.pojo;

/**
 * Created by ousheobin on 2017/9/12.
 */

public class NoteBean {

    private String id;
    private String date;
    private String title;
    private String content;

    public NoteBean(){

    }

    public NoteBean(String id, String date, String title, String content) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    @Override
    public String toString() {
        return "NoteBean{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
