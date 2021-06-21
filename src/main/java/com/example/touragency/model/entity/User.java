package com.example.touragency.model.entity;

import com.example.touragency.model.entity.enums.Role;
import com.example.touragency.model.entity.enums.UserStatus;

import java.util.Objects;

public class User extends Entity {
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private UserStatus status;
    private String login;
    private String password;
    private Role role;


    private User(){}

    public static User createUser(int id, String firstname, String lastname, String phone, String email,
                                  UserStatus status, String login, String password, Role role) {
        User user = new User();
        user.setId(id);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhone(phone);
        user.setEmail(email);
        user.setStatus(status);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

    public static User createUser(String firstname, String lastname, String phone, String email,
                                  UserStatus status, String login, String password, Role role) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhone(phone);
        user.setEmail(email);
        user.setStatus(status);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id='"+ getId() + '\'' +
//                ", firstname='" + firstname + '\'' +
//                ", lastname='" + lastname + '\'' +
//                ", phone='" + phone + '\'' +
//                ", email='" + email + '\'' +
//                ", status=" + status +
//                ", login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                ", role=" + role +
//                '}';
//    }


    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return getId() == entity.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, phone, email, status, login, password, role);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
