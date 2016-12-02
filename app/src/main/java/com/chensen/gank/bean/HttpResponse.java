package com.chensen.gank.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author：chensen on 2016/11/30 16:25
 * desc：
 */

public class HttpResponse   {

    private  boolean error;
    private List<GanHuoBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GanHuoBean> getResults() {
        return results;
    }

    public void setResults(List<GanHuoBean> results) {
        this.results = results;
    }
}
