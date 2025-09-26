package app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private long id;
    private String usersName;
    private String userEmail;
    private String hashedPassword;
    private String userRol;

    public User(long id, String usersName, String userEmail, String hashedPassword, String userRol) {
        this.id = id;
        this.usersName = usersName;
        this.userEmail = userEmail;
        this.hashedPassword = hashedPassword;
        this.userRol = userRol;
    }

    public User(){

    }

    public long getId() {
        return id;
    }

    public String getUsersName() {
        return usersName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getUserRol() {
        return userRol;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsersName(String name) {
        this.usersName = name;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setUserRol(String rol) {
        this.userRol = rol;
    }
}
