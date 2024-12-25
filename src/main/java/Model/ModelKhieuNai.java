/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
public class ModelKhieuNai extends Model{
    
    String ID, hoTen, phanLoai, tieuDe, noiDung, hoiDap, xetDuyet;
    Date ngayGui, ngayDuyet;

    public ModelKhieuNai(String ID, String hoTen, String phanLoai, String tieuDe, String noiDung, String hoiDap, String xetDuyet, Date ngayGui, Date ngayDuyet) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.phanLoai = phanLoai;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.hoiDap = hoiDap;
        this.xetDuyet = xetDuyet;
        this.ngayGui = ngayGui;
        this.ngayDuyet = ngayDuyet;
    }

    

    public String getXetDuyet() {
        return xetDuyet;
    }

    public void setXetDuyet(String xetDuyet) {
        this.xetDuyet = xetDuyet;
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

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getHoiDap() {
        return hoiDap;
    }

    public void setHoiDap(String hoiDap) {
        this.hoiDap = hoiDap;
    }

    public Date getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(Date ngayGui) {
        this.ngayGui = ngayGui;
    }

    public Date getNgayDuyet() {
        return ngayDuyet;
    }

    public void setNgayDuyet(Date ngayDuyet) {
        this.ngayDuyet = ngayDuyet;
    }
    
    @Override
    public Object getValue(String columnName) {
        switch (columnName) {
            case "ID":
                return ID;
            case "hoTen":
                return hoTen;
            case "ngayDuyet":
                return ngayDuyet;
            case "ngayGui":
                return ngayGui;
            case "phanLoai":
                return phanLoai;
            case "tieuDe":
                return tieuDe;
            case "noiDung":
                return noiDung;
            case "hoiDap":
                return hoiDap;
            case "xetDuyet":
                return xetDuyet;
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
    public Object[] toTableRow(int rowNum){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return new Object[]{
            false, 
            rowNum, 
            this,  
            phanLoai,
            tieuDe, 
            hoTen,
            ngayGui == null ? "" : df.format(ngayGui), 
            ngayDuyet == null ? "CHỜ DUYỆT" : df.format(ngayDuyet), 
            xetDuyet,
            
        };
    }
}
