package com.xinlizz.oh.results;

import java.io.Serializable;

/**
 * ActionResult 请求处理返回数据实体类 
 *
 * @Author: xinlizz
 * @Date: 2018/7/15
 */
public class ActionResult implements Serializable {

    private static final long serialVersionUID = 1782438195164585559L;

    private boolean success = true;

    private String message;

    private Object retValue;

    public ActionResult() {
        success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorMsg(String errorMsg){
        this.success = false;
        this.message = errorMsg;
    }

    public void setSuccessMsg(String successMsg){
        this.success = true;
        this.message = successMsg;
    }

    public Object getRetValue() {
        return retValue;
    }

    public void setRetValue(Object retValue) {
        this.retValue = retValue;
    }

    @Override
    public String toString() {
        return "ActionResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", retValue=" + retValue +
                '}';
    }
}
