/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 *
 * @author ADMIN
 */
public class ModelHoGiaDinh extends Model {
    
    private String soPhong;
    private int soNguoi, soXeMay, soOto;
    private double dienTich;
    private String trangThai;
    public ModelHoGiaDinh(String soPhong, double dienTich, int soNguoi, int soXeMay, int soOto, String trangThai) {
        this.soPhong = soPhong;
        this.dienTich = dienTich;
        this.soNguoi = soNguoi;
        this.soXeMay = soXeMay;
        this.soOto = soOto;
        this.trangThai = trangThai;
    }

    
    
    public int getSoXeMay() {
        return soXeMay;
    }

    public void setSoXeMay(int soXeMay) {
        this.soXeMay = soXeMay;
    }

    public int getSoOto() {
        return soOto;
    }

    public void setSoOto(int soOto) {
        this.soOto = soOto;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }


    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }
    
    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    @Override
    public Object getValue(String columnName) {
        switch (columnName) {
            case "soPhong":
                return soPhong;
            case "dienTich":
                return dienTich;
            case "soNguoi":
                return soNguoi;
            case "soXeMay":
                return soXeMay;
            case "soOto":
                return soOto;
            case "trangThai":
                return trangThai;
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
    public Object[] toTableRow() {
        return new Object[] {
            false,
            soPhong,  // Số phòng
            dienTich,
            soNguoi,
            soXeMay,
            soOto,
            trangThai,
            this
        };
    }
    
}
