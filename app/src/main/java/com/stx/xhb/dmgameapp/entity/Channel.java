package com.stx.xhb.dmgameapp.entity;

import java.io.Serializable;

/**
 * Created by Teewon on 2016/11/30.
 */

public class Channel implements Serializable {

    int id;
    String name;
    int grade;
    int parent_id;
    int order;
    Channel children;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Channel getChildren() {
        return children;
    }

    public void setChildren(Channel children) {
        this.children = children;
    }
}
