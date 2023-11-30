package vn.edu.iuh.fit.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GrantAccess {
    @Id
    private String account_id;
    @Id
    private String role_id;
    @Column(name = "is_grant")
    private boolean isGrant;
    private String note;

    @ManyToOne
    private Role role;
    @ManyToOne
    private Account account;

    public GrantAccess() {
    }

    public GrantAccess(String account_id, String role_id, boolean isGrant, String note) {
        this.account_id = account_id;
        this.role_id = role_id;
        this.isGrant = isGrant;
        this.note = note;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public boolean isGrant() {
        return isGrant;
    }

    public void setGrant(boolean grant) {
        isGrant = grant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String node) {
        this.note = node;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "account_id='" + account_id + '\'' +
                ", role_id='" + role_id + '\'' +
                ", isGrant=" + isGrant +
                ", node='" + note + '\'' +
                '}';
    }
}
