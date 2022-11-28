package ir.largesize.OnlineShop.helper.uimodels;

import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.entities.people.User;
import ir.largesize.OnlineShop.enums.UserRole;

public class CustomerVM {
    private long id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String tel;
    private String address;
    private String postalCode;
    private String email;
    private String userName;
    private String password;

    public CustomerVM() {
    }

    public CustomerVM(Customer customer) {
        setId(customer.getId());
        setFirstName(customer.getFirstName());
        setLastName(customer.getLastName());
        setMobile(customer.getMobile());
        setEmail(customer.getEmail());
        setAddress(customer.getAddress());
        setPostalCode(customer.getPostalCode());
        setTel(customer.getTel());
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    public Customer getCustomerInfo() {
        Customer customer = new Customer();
        customer.setId(getId());
        customer.setFirstName(getFirstName());
        customer.setLastName(getLastName());
        customer.setMobile(getMobile());
        customer.setEmail(getEmail());
        customer.setAddress(getAddress());
        customer.setPostalCode(getPostalCode());
        customer.setTel(getTel());

                return customer;
    }

    public User getUserInfo() {
        User user = new User();
        user.setId(getId());
        user.setUserName(getUserName());
        user.setPassword(getPassword());
        user.setEnable(true);
        user.setEmail(getEmail());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setRole(UserRole.USER);

        return user;
    }
}
