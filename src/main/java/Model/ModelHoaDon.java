/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class ModelHoaDon extends Model {
    int ID, thang, tongSoTienThu;
    String soPhong;
    boolean daDong;

    public ModelHoaDon(int ID, int thang, int tongSoTienThu, String soPhong, boolean daDong) {
        this.ID = ID;
        this.thang = thang;
        this.tongSoTienThu = tongSoTienThu;
        this.soPhong = soPhong;
        this.daDong = daDong;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getTongSoTienThu() {
        return tongSoTienThu;
    }

    public void setTongSoTienThu(int tongSoTienThu) {
        this.tongSoTienThu = tongSoTienThu;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public boolean isDaDong() {
        return daDong;
    }

    public void setDaDong(boolean daDong) {
        this.daDong = daDong;
    }

    
    @Override
    public Object getValue(String columnName) {
        switch (columnName) {
            case "ID":
                return ID;
            case "soPhong":
                return soPhong;
            case "thang":
                return thang;
            case "tongSoTienThu":
                return tongSoTienThu;
            case "daDong":
                return daDong;
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
    public Object[] toTableRow(int rowNum) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return new Object[]{
            rowNum,
            soPhong,
            formatter.format(tongSoTienThu) + " đ",
            this,
            daDong?"Đã đóng":"Chưa đóng"
        };
    }
}
