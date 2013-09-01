package com.sjtu.onlinelibrary.web.viewmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-1
 * Time: 下午5:54
 */
public class BusinessResult {
    public BusinessResult(ResultStatus status,String message){
        setMessage(message);
        setResultStatus(status);
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public enum ResultStatus{
        OK,
        FAIL
    }

    private ResultStatus resultStatus;
    private String message;
}
