/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author lamto
 */
public class ModelNhanKhau extends Model {
    private String ID;
    private String hoTen;
    private String CCCD;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String TTTV;
    private String soPhong;
    private String sdt;
    
    public ModelNhanKhau(String ID, String hoTen, String CCCD, Date ngaySinh, boolean gioiTinh, String TTTV, String soPhong, String sdt) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.CCCD = CCCD;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.TTTV = TTTV;
        this.soPhong = soPhong;
        this.sdt = sdt;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getTTTV() {
        return TTTV;
    }

    public void setTTTV(String TTTV) {
        this.TTTV = TTTV;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }
    
    @Override
    public Object getValue(String columnName) {
        switch (columnName) {
            case "ID":
                return ID;
            case "hoTen":
                return hoTen;
            case "CCCD":
                return CCCD;
            case "ngaySinh":
                return ngaySinh;
            case "gioiTinh":
                return gioiTinh;
            case "TTTV":
                return TTTV;
            case "soPhong":
                return soPhong;
            case "sdt":
                return sdt;
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
    
    public Object[] toTableRow(int rowNum){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        NumberFormat nf = new DecimalFormat("#, ##0.##");
        return new Object[]{
            false, 
            rowNum, 
            this,
            soPhong,
            hoTen,
            CCCD,
            sdt,
            gioiTinh? "Nam" : "Nữ",
            ngaySinh == null ? "" : df.format(ngaySinh), 
            TTTV
        };
    }
    public Object[] toTableRow1(){
        return new Object[]{ 
            hoTen,
            TTTV
        };
    }
}
