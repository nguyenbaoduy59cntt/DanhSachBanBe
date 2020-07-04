package vn.edu.ntu.nbd.bai1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
// làm thằng này đầu tiên
public class DSMHtFragment extends Fragment
{   //[0] khai báo
    RecyclerView rvListMH;
    FloatingActionButton fab;

    // [5] khai báo controller vào bên đây để dùng ở bước 6
    NavController controller;

    @Override
    public View onCreateView
            (
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
            )
    {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_first, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


        //[1] add view và add even ngay khi khai báo nhé
        rvListMH = view.findViewById(R.id.rvListMH);
        fab = view.findViewById(R.id.fab);

        //[6] phải làm như này thì khi ấn mũi tên trên thanh bar nó mới trở về được trang trước( nếu không làm bước này
        // mà chỉ làm đến bước 4 thì vẫn hoạt đông bình thương, nhưng đến khi bấm mũi tên trả về thì bị lỗi nha /
        controller =NavHostFragment.findNavController(DSMHtFragment.this);
        ((MainActivity)getActivity()).controller = controller; // cái này phải ép kiểu controller thì thiếu context
        //[/6]

        //set sự kiện cho nút thêm
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_DSMHtFragment_to_themMHFragment);
            }
        });
        // [/1] Xong cái này thì qua MainActivity nhé!!!
    }


    // [7] + [8] Thêm biểu tượng giỏ hàng vào thanh bar ( Trước khi làm bước này, phải tạo menu trong RES đã)
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // đây nha
        inflater.inflate(R.menu.menu,menu);
    }

    //[8]
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    // [9] Tạo sự kiện khi nhấp vào giỏ hàng trên bar thì nó nhảy qua page kia
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Lấy id
        int id = item.getItemId();
        if(id == R.id.menu)
        {
            CallShoppingFragment();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CallShoppingFragment()
    {
        NavHostFragment.findNavController(DSMHtFragment.this).navigate(R.id.action_DSMHtFragment_to_DSGHFragment);
    }
    // [/9]
}