package data;

public class UsersData {
    public String id;
    public String password;
    public String nickname;
    public String name;
    public String phone;
    public String email;

    public UsersData(String id,  String password_s, String nickname,
                     String name, String phone, String email) {
        this.id = id;
        this.password = password_s;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

}
