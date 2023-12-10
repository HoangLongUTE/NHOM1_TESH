package com.example.tesh.User;

import java.util.HashMap;
import java.util.Map;

public class User {

    String username;
    String password;

    public User() {
    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Map<String, Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("email",username);
        return result;
    }
}
