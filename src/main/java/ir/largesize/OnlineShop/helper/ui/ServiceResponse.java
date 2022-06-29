package ir.largesize.OnlineShop.helper.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiceResponse<T> implements Serializable {
    private List<T> dataList;
    private ResponseStatus status;
    private boolean hasError;
    private String message;
    private long totalCount;

    public ServiceResponse(ResponseStatus status, List<T> dataList) {
        this.dataList = dataList;
        this.status = status;
        this.message = "";
        this.hasError = false;
        this.totalCount = 0;
    }

    public ServiceResponse(ResponseStatus status, List<T> dataList, long totalCount) {
        this.dataList = dataList;
        this.status = status;
        this.message = "";
        this.hasError = false;
        this.totalCount = totalCount;
    }

    public ServiceResponse(ResponseStatus status, T data) {
        this.dataList = new ArrayList<T>();
        this.dataList.add(data);
        this.status = status;
        this.message = "";
        this.hasError = false;
        this.totalCount = 1;
    }

    public ServiceResponse(ResponseStatus status, String message) {
        this.dataList = new ArrayList<T>();
        this.status = status;
        this.message = message;
        this.hasError = status==ResponseStatus.FAILED;
        this.totalCount = 0;

    }

    public ServiceResponse(Exception ex) {
        this.dataList = new ArrayList<T>();
        this.status = ResponseStatus.EXCEPTION;
        this.message = ex.getMessage();
        this.hasError = true;
        this.totalCount = 0;

    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }
}
