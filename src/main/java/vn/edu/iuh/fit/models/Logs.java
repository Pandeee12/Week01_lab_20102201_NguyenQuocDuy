package vn.edu.iuh.fit.models;

import java.util.Date;

public class Logs {
    private Integer logId;
    private String accountId; // Thay đổi kiểu dữ liệu thành String
    private Date loginTime;
    private Date logoutTime;
    private String note;

    // Constructors
    public Logs() {
    }

    public Logs(Integer logId, String accountId, Date loginTime, Date logoutTime, String note) {
        this.logId = logId;
        this.accountId = accountId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.note = note;
    }

    // Getters and Setters

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getAccountId() { // Sử dụng kiểu dữ liệu String
        return accountId;
    }

    public void setAccountId(String accountId) { // Sử dụng kiểu dữ liệu String
        this.accountId = accountId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
