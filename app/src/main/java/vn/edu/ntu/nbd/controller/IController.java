package vn.edu.ntu.nbd.controller;

import java.util.ArrayList;

import vn.edu.ntu.nbd.model.Product;

public interface IController {
    //Mảng của DSMH
    public ArrayList<Product> listProduct();//mảng chứa danh sách các sp để show


    //[30] Mảng của DSGH
    public ArrayList<Product> listProductGH();

    //[31] Tạo Hàm kiểm tra sp có trong giỏ hàng chưa
    // (tiếp tục: trong interface có thêm hàng thì giao diện của nó phải thực thi)=>nhảy qua controller
    public boolean Check(Product p);

    //[42] khai báo hàm xóa ds
    public void Clear();
}
