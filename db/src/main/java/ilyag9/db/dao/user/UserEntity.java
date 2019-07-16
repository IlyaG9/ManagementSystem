package ilyag9.db.dao.user;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "LOGIN",nullable = false)
    private String login;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_LOGIN")
    private Date lastLogin;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE",nullable = false)
    private Date createDate;

    @ManyToMany
    @JoinColumn(name = "ROLE_ID")
    private List<UserRoleEntity> roleList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<UserRoleEntity> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRoleEntity> roleList) {
        this.roleList = roleList;
    }
}

