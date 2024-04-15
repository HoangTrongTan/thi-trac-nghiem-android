package com.example.tracnghiem.Model;

public class model_DanhSach_ngChoi {
    private String ten,diem;
    private byte[] anh;

    public model_DanhSach_ngChoi(String ten, String diem, byte[] anh) {
        this.ten = ten;
        this.diem = diem;
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
}
