package org.jihed.autofixer;

public class UserModelClass {
    static Integer id;
    static String name;
    static String email;

    public UserModelClass(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserModelClass(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

