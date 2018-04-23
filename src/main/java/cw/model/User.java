package cw.model;

public interface User
{
    int getId();
    UserRole getUserRole();
    void setUserRole(UserRole userRole);
    String getName();
    void setName(String name);
    String getPhone();
    void setPhone(String phone);
    String getEmail();
    void setEmail(String email);
}
