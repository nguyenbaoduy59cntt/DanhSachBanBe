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

public class DSGHFragment extends Fragment {
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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
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
    }
}