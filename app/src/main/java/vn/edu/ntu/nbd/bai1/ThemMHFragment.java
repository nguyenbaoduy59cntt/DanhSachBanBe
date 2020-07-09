package vn.edu.ntu.nbd.bai1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.ntu.nbd.controller.IController;
import vn.edu.ntu.nbd.model.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemMHFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemMHFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThemMHFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThemMHFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThemMHFragment newInstance(String param1, String param2) {
        ThemMHFragment fragment = new ThemMHFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_them_m_h, container, false);
    }

    //[41] đưa dữ liệu đổ vào DSMH
    //tạo ra onViewCreated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //đổ biến vào đây
        final EditText Them_edt_ten, Them_edt_gia, Them_edt_mota;
        Button Them_btn;

        //addView
        Them_edt_ten = view.findViewById(R.id.Them_edt_ten);
        Them_edt_gia = view.findViewById(R.id.Them_edt_gia);
        Them_edt_mota = view.findViewById(R.id.Them_edt_mota);
        Them_btn = view.findViewById(R.id.Them_btn);

        //addEvent
        Them_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IController controller= (IController) getActivity().getApplication();
                //Khi click vào nút thêm, thì hệ thống sẽ tạo ra 1 Product mới, và dữ liệu sẽ lấy từ các edittext này.
                Product p = new Product(Them_edt_ten.getText().toString(),new Integer(Them_edt_gia.getText().toString()), Them_edt_mota.getText().toString());
                controller.listProduct().add(p);
                Toast.makeText(getActivity(), "Đã thêm " + p.getName()+ " vào giỏ hàng", Toast.LENGTH_LONG).show();
                NavHostFragment.findNavController(ThemMHFragment.this).navigate(R.id.action_themMHFragment_to_DSMHtFragment);
            }
        });
    }
    //Xong thằng này thì qua bước 42 để làm thêm cái thanh toán khi click vào mua hàng
}