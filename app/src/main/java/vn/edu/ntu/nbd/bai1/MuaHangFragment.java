package vn.edu.ntu.nbd.bai1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.ntu.nbd.controller.IController;
import vn.edu.ntu.nbd.model.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MuaHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MuaHangFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MuaHangFragment() {
        // Required empty public constructor
    }


    public static MuaHangFragment newInstance(String param1, String param2) {
        MuaHangFragment fragment = new MuaHangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mua_hang, container, false);
    }

    //Tính tiền khi nhấn nút mua hàng
    //[43] Khai báo ra onViewCreated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //khai báo biến
        TextView ThanhToan_txtTongTien;
        //addview
        ThanhToan_txtTongTien = view.findViewById(R.id.ThanhToan_txtTongTien);

        //Lấy cái mảng ds product ra
        IController controller = (IController) getActivity().getApplication();
        ArrayList<Product> products = controller.listProductGH();

        int k= 0;
        for (Product p:products)
        {
            k = k + p.getPrice();
        }
        ThanhToan_txtTongTien.setText(Integer.toString(k) + " VND");
    } // Xongggggggggggggggg nhá
}