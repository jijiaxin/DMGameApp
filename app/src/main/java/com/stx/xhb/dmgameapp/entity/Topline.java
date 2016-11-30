package com.stx.xhb.dmgameapp.entity;

import java.io.Serializable;

/**
 * Created by Teewon on 2016/11/30.
 */

public class Topline implements Serializable {

    /**
     * id : 15
     * state : 2
     * link_id : 14
     * type : 0
     * title : 余生很长，何必慌张-1
     * author_id : 1
     * auditor_id : 1
     * channel_id : 10
     * title_bold : 0
     * title_color : #333333
     * subtitle :
     * cover_pic : http://yun.app/upload/cover_pic/cover_583d1b400be13.jpeg
     * top_grade : 0
     * description :
     * source :
     * original_url :
     * is_soft : 0
     * is_political : 0
     * is_international : 0
     * is_important : 0
     * published_at : null
     * created_at : 2016-11-28 14:14:20
     * updated_at : 2016-11-28 15:48:52
     * deleted_at : null
     * url : http://59.110.23.172/articles/15
     */

    private int id;
    private int state;
    private int link_id;
    private int type;
    private String title;
    private int author_id;
    private int auditor_id;
    private int channel_id;
    private int title_bold;
    private String title_color;
    private String subtitle;
    private String cover_pic;
    private int top_grade;
    private String description;
    private String source;
    private String original_url;
    private int is_soft;
    private int is_political;
    private int is_international;
    private int is_important;
    private Object published_at;
    private String created_at;
    private String updated_at;
    private Object deleted_at;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLink_id() {
        return link_id;
    }

    public void setLink_id(int link_id) {
        this.link_id = link_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getAuditor_id() {
        return auditor_id;
    }

    public void setAuditor_id(int auditor_id) {
        this.auditor_id = auditor_id;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public int getTitle_bold() {
        return title_bold;
    }

    public void setTitle_bold(int title_bold) {
        this.title_bold = title_bold;
    }

    public String getTitle_color() {
        return title_color;
    }

    public void setTitle_color(String title_color) {
        this.title_color = title_color;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCover_pic() {
        return cover_pic;
    }

    public void setCover_pic(String cover_pic) {
        this.cover_pic = cover_pic;
    }

    public int getTop_grade() {
        return top_grade;
    }

    public void setTop_grade(int top_grade) {
        this.top_grade = top_grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOriginal_url() {
        return original_url;
    }

    public void setOriginal_url(String original_url) {
        this.original_url = original_url;
    }

    public int getIs_soft() {
        return is_soft;
    }

    public void setIs_soft(int is_soft) {
        this.is_soft = is_soft;
    }

    public int getIs_political() {
        return is_political;
    }

    public void setIs_political(int is_political) {
        this.is_political = is_political;
    }

    public int getIs_international() {
        return is_international;
    }

    public void setIs_international(int is_international) {
        this.is_international = is_international;
    }

    public int getIs_important() {
        return is_important;
    }

    public void setIs_important(int is_important) {
        this.is_important = is_important;
    }

    public Object getPublished_at() {
        return published_at;
    }

    public void setPublished_at(Object published_at) {
        this.published_at = published_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Object getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Object deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
