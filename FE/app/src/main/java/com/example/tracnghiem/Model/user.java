package com.example.tracnghiem.Model;

import java.io.Serializable;
import java.util.List;

public class user implements Serializable {
    private int id;
    private String ten;
    private List<String> anh;
    private int diem;

    public user(String ten, List<String> anh, int diem,int id) {
        this.ten = ten;
        this.anh = anh;
        this.diem = diem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<String> getAnh() {
        return anh;
    }

    public void setAnh(List<String> anh) {
        this.anh = anh;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
