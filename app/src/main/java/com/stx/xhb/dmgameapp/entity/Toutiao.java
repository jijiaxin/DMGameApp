package com.stx.xhb.dmgameapp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 头条实体类 包括列表数据和banner
 * Created by Teewon on 2016/11/30.
 */

public class Toutiao implements Serializable {
    List<Topline> data;
    Meta meta;

    public List<Topline> getData() {
        return data;
    }

    public void setData(List<Topline> data) {
        this.data = data;
    }


    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public class Meta{
        List<Banner> photos;

        public List<Banner> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Banner> photos) {
            this.photos = photos;
        }
    }

}
