package com.example.inviattorepartidor.Model;

import java.util.List;

public class SolicitudModel {

    private String Phone;
    private String Name;
    private String Address;
    private String Total;
    private String Status;
    private String Comment;
    private String Date;
    private List<CarritoModel> carritoModelList;

    public SolicitudModel() {
    }


    public SolicitudModel(String phone, String name, String address, String total, String comment, String date, List<CarritoModel> carritoModelList) {
        Phone = phone;
        Name = name;
        Address = address;
        Total = total;
        Status = "0"; // Default: 0 placed, 1 shipping, 2 shipped
        Date = date;
        Comment = comment;
        this.carritoModelList = carritoModelList;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public List<CarritoModel> getCarritoModelList() {
        return carritoModelList;
    }

    public void setCarritoModelList(List<CarritoModel> carritoModelList) {
        this.carritoModelList = carritoModelList;
    }

}
