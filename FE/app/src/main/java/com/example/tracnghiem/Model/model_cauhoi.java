package com.example.tracnghiem.Model;

public class model_cauhoi {
    private String cauhoi,da_a,da_b,da_c,da_d;
    private int kq;

    public model_cauhoi(String cauhoi, String da_a, String da_b, String da_c, String da_d, int kq) {
        this.cauhoi = cauhoi;
        this.da_a = da_a;
        this.da_b = da_b;
        this.da_c = da_c;
        this.da_d = da_d;
        this.kq = kq;
    }

    public int getKq() {
        return kq;
    }

    public void setKq(int kq) {
        this.kq = kq;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getDa_a() {
        return da_a;
    }

    public void setDa_a(String da_a) {
        this.da_a = da_a;
    }

    public String getDa_b() {
        return da_b;
    }

    public void setDa_b(String da_b) {
        this.da_b = da_b;
    }

    public String getDa_c() {
        return da_c;
    }

    public void setDa_c(String da_c) {
        this.da_c = da_c;
    }

    public String getDa_d() {
        return da_d;
    }

    public void setDa_d(String da_d) {
        this.da_d = da_d;
    }
}
