package de.hsh.capstoneris.data.dto;

public class MessageDTO {
    private long id = -1;
    private long sent_by;
    private long sent_in;
    private long time;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSent_by() {
        return sent_by;
    }

    public void setSent_by(long sent_by) {
        this.sent_by = sent_by;
    }

    public long getSent_in() {
        return sent_in;
    }

    public void setSent_in(long sent_in) {
        this.sent_in = sent_in;
    }

    public long getTime() {
        return time;
    }
    public void setTime(long time){
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
