package vn.edu.iuh.fit.models;

public class GrantAccess {
    private String accountId;
    private String roleId;
    private Integer isGrant; // 0-disable, 1-enable
    private String note;

    // Constructors
    public GrantAccess() {
    }

    public GrantAccess(String accountId, String roleId, Integer isGrant, String note) {
        this.accountId = accountId;
        this.isGrant = isGrant;
        this.roleId = roleId;
        this.note = note;
    }

    // Getters and Setters

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(Integer isGrant) {
        this.isGrant = isGrant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
