package com.example.inviattorepartidor.Model;

public class RepartidorModel {

    private String Name;
    private String Phone;
    private String Password;
    private String Direccion;
    private String Image;

    public RepartidorModel() {
    }

    public RepartidorModel(String name, String phone, String password, String direccion, String image) {
        Name = name;
        Phone = phone;
        Password = password;
        Direccion = direccion;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}//RepartidorModel
