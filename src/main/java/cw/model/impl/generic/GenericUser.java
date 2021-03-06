package cw.model.impl.generic;

import cw.model.User;
import cw.model.UserRole;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "GenericUser.findAll", query = "SELECT u FROM GenericUser u")
@SessionScoped
@Named("user")
public class GenericUser implements User, Serializable {

    @Id
    @GeneratedValue()
    private int id;
    private UserRole userRole;
    private String name;
    private String phone;
    private String email;
    private String password;

    public GenericUser(int id, UserRole userRole, String name, String phone, String email) {
        this.id = id;
        this.userRole = userRole;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public GenericUser(UserRole userRole, String name, String phone, String email, String password){
        this.userRole = userRole;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public GenericUser(){
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id){
        this.id = id;
    }

    @Override
    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
