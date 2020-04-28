package de.hsh.capstoneris.data.dao;

import de.hsh.capstoneris.data.sql.Connection;

public class MessageDAO extends Connection{


    private long id;
    private long sent_by;
    private long sent_in;
    private String time;
    private String content;

    public MessageDAO(long id, long sent_by, long sent_in, String content, String time) {
        this.id = id;
        this.sent_by = sent_by;
        this.sent_in = sent_in;
        this.content = content;
        this.time = time;
    }

    public MessageDAO() {
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
