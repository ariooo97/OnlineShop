package ir.largesize.OnlineShop.helper.uimodels.people;

import ir.largesize.OnlineShop.entities.people.User;
import ir.largesize.OnlineShop.enums.UserRoll;
//user view model class
public class UserVm {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String newPassword;
    private String email;
    private boolean enable;
    private UserRoll roll;
    private  String token;
    private String fullName;

    public UserVm(){

    }
    public UserVm(User user){
        setId(user.getId());
        setEmail(user.getEmail());
        setEnable(user.isEnable());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setRoll(user.getRoll());
        setUserName(user.getUserName());
        setFullName(getFirstName()+" "+getLastName());


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public UserRoll getRoll() {
        return roll;
    }

    public void setRoll(UserRoll roll) {
        this.roll = roll;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
