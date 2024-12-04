/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class ModelHoGiaDinh extends Model {
    
    private String soPhong;
    private int dienTich, soNguoi, soXeMay, soOto;

    public ModelHoGiaDinh(String soPhong, int dienTich, int soNguoi, int soXeMay, int soOto) {
        this.soPhong = soPhong;
        this.dienTich = dienTich;
        this.soNguoi = soNguoi;
        this.soXeMay = soXeMay;
        this.soOto = soOto;
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

    public int getDienTich() {
        return dienTich;
    }

    public void setDienTich(int dienTich) {
        this.dienTich = dienTich;
    }


    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
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
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
    
}
