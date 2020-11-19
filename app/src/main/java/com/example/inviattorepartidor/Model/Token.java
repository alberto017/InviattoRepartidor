package com.example.inviattorepartidor.Model;

public class Token {

    private String token;
    private boolean isServerToken;

    public Token() {
    }

    public Token(String token, boolean isServerToken) {
        this.token = token;
        this.isServerToken = isServerToken;
    }//Token

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getIsServerToken() {
        return isServerToken;
    }

    public void setServerToken(boolean serverToken) {
        this.isServerToken = serverToken;
    }//setIsServerToken
}
