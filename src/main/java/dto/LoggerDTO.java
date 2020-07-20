package dto;


import enums.RequestMapping;

import java.sql.Timestamp;

public class LoggerDTO {

    private String timestamp;

    private String url;

    private RequestMapping method;

    private Integer responseTime;

    private Integer responseCode;


    public LoggerDTO(){

    }

    public LoggerDTO(String timestamp, String url, RequestMapping method, Integer responseTime, Integer responseCode) {
        this.timestamp = timestamp;
        this.url = url;
        this.method = method;
        this.responseTime = responseTime;
        this.responseCode = responseCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestMapping getMethod() {
        return method;
    }

    public void setMethod(RequestMapping method) {
        this.method = method;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
}
