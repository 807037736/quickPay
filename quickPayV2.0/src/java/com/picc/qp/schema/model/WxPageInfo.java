package com.picc.qp.schema.model;

import java.util.List;

@SuppressWarnings("rawtypes")
public class WxPageInfo<T> implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    
    
    private Integer pageCount;
    
    private List<T> items;
    
    

    public Integer getPageCount() {
        return pageCount;
    }


    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }


    public List<T> getItems() {
        return items;
    }


    public void setItems(List<T> items) {
        this.items = items;
    }

}
