package com.hirechart.hiredin.Dto;

public class JobStatusDto {

    private String status; // ACTIVE, INACTIVE, EXPIRED
    private String lastDateToApply;

    public JobStatusDto() {
        super();
    }

    public JobStatusDto(String status, String lastDateToApply) {
        super();
        this.status = status;
        this.lastDateToApply = lastDateToApply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastDateToApply() {
        return lastDateToApply;
    }

    public void setLastDateToApply(String lastDateToApply) {
        this.lastDateToApply = lastDateToApply;
    }
}
