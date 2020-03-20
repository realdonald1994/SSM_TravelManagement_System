package cn.itcast.ssm.domain;

import java.io.Serializable;
import java.util.List;

/**
 * User javabean
 */
public class UserInfo implements Serializable {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private Integer status;
    private String statusStr;//0:inactivated 1:activated
    private List<Role> roles;


    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if(status!=null){
            if(status==0){
                statusStr="Inactivated";
            }else if(status==1){
                statusStr="Activated";
            }
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
