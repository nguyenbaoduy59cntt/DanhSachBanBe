package vn.edu.ntu.nbd.bai1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import vn.edu.ntu.nbd.controller.IController;
import vn.edu.ntu.nbd.model.Product;

public class DSGHFragment extends Fragment
{
    //[10] khai báo
    TextView GH_txt;
    Button GH_btn_mua, GH_btn_xoa;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //[11] add view
        GH_txt = view.findViewById(R.id.GH_txt);
        GH_btn_mua = view.findViewById(R.id.GH_btn_mua);
        GH_btn_xoa = view.findViewById(R.id.GH_btn_xoa);

        //[12] add event cho nút mua. Đến bước đây thì đã xong hết chuyển đổi qua lại giữa các trang. Giờ thì đến đổ dữ liệu vào
        GH_btn_mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DSGHFragment.this).navigate(R.id.action_DSGHFragment_to_muaHangFragment);
            }
        });

        //[41] tạo sự kiện cho nút xóa(trước khi tạo sự kiện cho nút xóa thì phải khai báo hàm xóa bên IController)
        GH_btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IController controller = (IController) getActivity().getApplication();
                controller.Clear();
                GH_txt.setText("Không có mặt hàng nào.");
            }
        });

        //[38]Khai báo 1 viewcart để chưa thông tin bên trong giỏ hàng(làm như này cho gọn thôi)
        ViewCartInfo();
    }
    //[39] thực hiện hàm view cart ở đây nhé.
    private void ViewCartInfo()
    {
        //[40] khai báo 1 controller để chứa mảng product khi click bên nút add  product vào giỏ hàng
        IController controller = (IController) getActivity().getApplication();
        ArrayList<Product> listproduct = controller.listProductGH(); // bên kia đã khai báo listproductGH rồi, nên bên này chỉ dùng thôi
        StringBuilder builder = new StringBuilder();
        // Vòng for để chạy biến p trong listProduct để lấy ra từng thằng
        for (Product p:listproduct)
        {
            builder.append(p.getName() + ":  " + p.getPrice() +" VND\n");
        }
        // set nội dung cho biến GH_txt
        GH_txt.setText(builder);
    }
}