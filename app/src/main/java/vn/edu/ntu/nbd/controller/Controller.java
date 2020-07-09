package vn.edu.ntu.nbd.controller;

import android.app.Application;

import java.util.ArrayList;

import vn.edu.ntu.nbd.model.Product;

//[14] tạo controller
public class Controller extends Application implements IController
{
    //[15] tạo mảng chứa sản phẩm
    ArrayList<Product> listProduct = new ArrayList<>();
    //[32]
    ArrayList<Product> listProductGH = new ArrayList<>();

    //[16] tạo constructor cho nó để khởi tạo product (tiếp theo, những sản phẩm này sẽ hiển thị ở đâu, thì mình qua bên đó làm tiếp)
    public Controller() {
        listProduct.add(new Product("Xoài", 20000, "Xoài cát"));
        listProduct.add(new Product("Bưởi", 50000, "Bưởi năm roi"));
        listProduct.add(new Product("Cam", 40000, "Cam sành"));
        listProduct.add(new Product("Mận", 30000, "Mận tím"));
        listProduct.add(new Product("Quýt", 30000, "Quýt thái"));
        listProduct.add(new Product("Mãng Cầu", 50000, "Mãng cầu sim"));
        listProduct.add(new Product("Thơm", 10000, "Thơm Daklak"));
        listProduct.add(new Product("Dừa", 20000, "Dừa bến tre"));
        listProduct.add(new Product("Nho", 90000, "Nho bình thuận"));
        listProduct.add(new Product("Chôm Chôm", 30000, "Chôm chôm cà mau"));
        listProduct.add(new Product("Bơ", 80000, "Bơ sáp"));
    }

    @Override
    public ArrayList<Product> listProduct() {
        return listProduct;
    }

    @Override
    public ArrayList<Product> listProductGH() {
        return listProductGH;
    }

    //[33]
    @Override
    public boolean Check(Product p) {
        if(listProductGH.contains(p))
            return false;
        else
        {
             listProductGH.add(p);
             return true;
        }
    }
    // XOng bước này, thì mình tạo sự kiện nút bấm cho nút thêm vào giỏ => qua DSMH

    //[43] phải thực hiện vì mới khai báo nó bên IController
    @Override
    public void Clear()
    {
        listProductGH.clear();
    }
}
