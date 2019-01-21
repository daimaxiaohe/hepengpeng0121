package com.example.a0121.bean;

import java.util.List;

/**
 * はすてすゃの
 * 2019-01-21.
 */
public class ShowCarBean {
    public String msg;
    public String code;
    public List<Datas> data;
    public static class Datas{
        public List<ListBean> list;
        public String sellerName;
        public boolean ischecked=false;
       public static class ListBean{
           public String images;
           public double price;
           public String title;
           public boolean ischildchecked=false;
       }
    }
}
