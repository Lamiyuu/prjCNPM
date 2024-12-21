/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
public class ModelThuPhi extends Model{
    
    private String ID, soPhong, nguoiDong, ghiChu, IDKhoanThu;
    private int soTienThu;
    private Date ngayDong;
    private int thang;
    private ModelKhoanThu modelKhoanThu;
    private ModelHoGiaDinh modelHoGiaDinh;
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public String getNguoiDong() {
        return nguoiDong;
    }

    public void setNguoiDong(String nguoiDong) {
        this.nguoiDong = nguoiDong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getIDKhoanThu() {
        return IDKhoanThu;
    }

    public void setIDKhoanThu(String IDKhoanThu) {
        this.IDKhoanThu = IDKhoanThu;
    }

    public int getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(int soTienThu) {
        this.soTienThu = soTienThu;
    }

    public ModelThuPhi(String ID, String soPhong, String nguoiDong, String ghiChu, String IDKhoanThu, int soTienThu, Date ngayDong, int thang, ModelKhoanThu modelKhoanThu, ModelHoGiaDinh modelHoGiaDinh) {
        this.ID = ID;
        this.soPhong = soPhong;
        this.nguoiDong = nguoiDong;
        this.ghiChu = ghiChu;
        this.IDKhoanThu = IDKhoanThu;
        this.soTienThu = soTienThu;
        this.ngayDong = ngayDong;
        this.thang = thang;
        this.modelKhoanThu = modelKhoanThu;
        this.modelHoGiaDinh = modelHoGiaDinh;
    }


    public Date getNgayDong() {
        return ngayDong;
    }

    public void setNgayDong(Date ngayDong) {
        this.ngayDong = ngayDong;
    }

    
    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    

    public ModelKhoanThu getModelKhoanThu() {
        return modelKhoanThu;
    }

    public void setModelKhoanThu(ModelKhoanThu modelKhoanThu) {
        this.modelKhoanThu = modelKhoanThu;
    }

    public ModelHoGiaDinh getModelHoGiaDinh() {
        return modelHoGiaDinh;
    }

    public void setModelHoGiaDinh(ModelHoGiaDinh modelHoGiaDinh) {
        this.modelHoGiaDinh = modelHoGiaDinh;
    }

    
    
    @Override
    public Object getValue(String columnName) {
        switch (columnName) {
            case "ID":
                return ID;
            case "soPhong":
                return soPhong;
            case "ngayDong":
                return ngayDong;
            case "nguoiDong":
                return nguoiDong;
            case "ghiChu":
                return ghiChu;
            case "IDKhoanThu":
                return IDKhoanThu;  
            case "soTienThu":
                return soTienThu;
            case "thang":
                return thang;
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
    public Object[] toTableRow(int rowNum) {
        String donVi;
        switch (modelKhoanThu.getDonVi()) {
            case "Hộ":
                donVi = 1 + " hộ";
                break;
            case "Đầu người":
                donVi = modelHoGiaDinh.getSoNguoi() + " người";
                break;
            case "Số ô tô":
                donVi = modelHoGiaDinh.getSoOto() + " xe";
                break;
            case "Số xe máy":
                donVi = modelHoGiaDinh.getSoXeMay() + " xe";
                break;
            case "Diện tích":
                donVi = modelHoGiaDinh.getDienTich() + " mét vuông";
                break;
            default:
                donVi = 0 + ""; // Giá trị mặc định nếu không khớp
                break;
        }
        DecimalFormat formatter = new DecimalFormat("#,###");

        return new Object[]{
            rowNum,
            modelKhoanThu.getTenKhoanThu(),
            modelKhoanThu.getSoTienThu(),
            donVi,
            this,
            formatter.format(soTienThu) + " đ"
        };
    }
    
    public Object[] toTableRowCharity(int rowNum){
        DecimalFormat formatter = new DecimalFormat("#,###");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return new Object[]{
            false,
            rowNum,
            nguoiDong,
            soPhong,
            formatter.format(soTienThu) + " đ",
            this,
            ngayDong == null ? "" : df.format(ngayDong),
            ghiChu
        };
    }
    
    @Override
    public String toString(){
        return modelKhoanThu.getTenKhoanThu();
    }
}
