package com.xinlizz.oh.results;

import java.io.Serializable;
import java.util.List;

/**
 * DataResult 带有结果集的返回数据实体类 
 *
 * @Author: xinlizz
 * @Date: 2018/7/15
 */
public class DataResult<T> implements Serializable {

    private static final long serialVersionUID = 377426992554529717L;

    private boolean success = true;

    private String errorMsg;

    private String message;

    private List<T> datas;

    public DataResult() {
        this.success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg){
        this.success = false;
        this.message = errorMsg;
    }

    public void setSuccessMsg(String successMsg){
        this.success = true;
        this.message = successMsg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "DataResult{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                ", message='" + message + '\'' +
                ", datas=" + datas +
                '}';
    }
}
