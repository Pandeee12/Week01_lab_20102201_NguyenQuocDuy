package vn.edu.iuh.fit.models;

public class Role {
    private String roleId;
    private String roleName;
    private String description;
    private Integer status; // 1-active, 0-deactive, -1-deleted

    // Constructors
    public Role() {
    }

    public Role(String roleId, String roleName, String description, Integer status) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
