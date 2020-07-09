package vn.edu.ntu.nbd.bai1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.edu.ntu.nbd.controller.IController;
import vn.edu.ntu.nbd.model.Product;

// làm thằng này đầu tiên
public class DSMHFragment extends Fragment
{   //[0] khai báo
    RecyclerView rvListMH;
    FloatingActionButton fab;

    // [5] khai báo controller vào bên đây để dùng ở bước 6
    NavController controller;

    //[23] Khai báo IController để đổ mảng bên đó qua đây
    IController iController;
    //[25] tạo ra 1 mảng để chứa data lấy bên IController qua
    ArrayList<Product> listproduct;
    //[27] tạo 1 adapter để bao chưa nội dung lại
    ProductAdapter productAdapter;
    @Override
    public View onCreateView
            (
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
            )
    {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        //[1] add view và add even ngay khi khai báo nhé
        rvListMH = view.findViewById(R.id.rvListMH);
        fab = view.findViewById(R.id.fab);


        //[24]
        iController = (IController) getActivity().getApplication();
        //[26]
        listproduct = iController.listProduct(); // gán listProduct = listproduct; ==> vậy thì hiện tại dữ liệu đang nằm ở listproduct
        //[28] chứa danh sách
        productAdapter =  new ProductAdapter(listproduct);
        //[29] builder data lên
        rvListMH.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListMH.setAdapter(productAdapter);
        //[/29] xong bước này thì chạy CT được rồi
        //(Khi nhấp vô nút thêm vào giỏ hàng, thì hàng được lưu qua bên kia, thế nên phải tạo 1 cái mảng để lưu bên giỏ hàng)=>(tạo tron IController)

        // Inflate the layout for this fragment
       return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //[6] phải làm như này thì khi ấn mũi tên trên thanh bar nó mới trở về được trang trước( nếu không làm bước này
        // mà chỉ làm đến bước 4 thì vẫn hoạt đông bình thương, nhưng đến khi bấm mũi tên trả về thì bị lỗi nha /
        controller =NavHostFragment.findNavController(DSMHFragment.this);
        ((MainActivity)getActivity()).controller = controller; // cái này phải ép kiểu controller thì thiếu context
        //[/6]


        //set sự kiện cho nút thêm
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
    }//[\8]

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
        NavController controller = NavHostFragment.findNavController(DSMHFragment.this);
        controller.navigate(R.id.action_DSMHtFragment_to_DSGHFragment);
    }
    // [/9]


    //[17] + [18]LÀm để hiển thị DS product đã thêm bên controller qua bên đây

    //[17] || [34] cho viewholder kế thừa onclick
    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //Khai báo các biến đặt trong layout_item vào đây
        TextView txt_ten, txt_gia, txt_mota;
        ImageView img_them;
        //[37] khai báo Product p để bước 36 xử lí
        Product p;

        public ProductViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //addview
            txt_ten = itemView.findViewById(R.id.txt_ten);
            txt_gia = itemView.findViewById(R.id.txt_gia);
            txt_mota = itemView.findViewById(R.id.txt_mota);
            img_them = itemView.findViewById(R.id.img_them);

            //[35] tạo sự kiện cho nút thêm này( nhấp vô thì nó sẽ hiển thị ở hàm onClick bên dưới.
            img_them.setOnClickListener(this);
        }

        // Hàm lấy ra vị trí(position) để đưa xuống hàm OnBindViewHolder bên dưới
        public  void bind(Product p)
        {
            this.p = p;
            txt_ten.setText(p.getName());
            txt_gia.setText(new Integer(p.getPrice()).toString());
            txt_mota.setText(p.getDesc());
        }

        //[34] sau khi cho kế thừa Onclick thì có được hàm này
        @Override
        public void onClick(View v)
        {
            //[36]kiểm tra sp
            iController = (IController) getActivity().getApplication();
            if(iController.Check(p)) // p này là lấy p được khai báo ở bước 37. phải khai báo vì nó k biết p này là ở đâu ra
            {
                Toast.makeText(getActivity(),"Đã thêm " + p.getName() + " vào giỏ hàng.", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getActivity(),"Đã có " + p.getName() + " trong giỏ hàng.", Toast.LENGTH_LONG).show();
            }
        }
        // Xong bước này thì làm bước [38] +  khi click vào add thì hàng hiện bên giỏ hàng => phải qua DSGH để làm tiếp theo
    }

    //[18]Tạo cầu nối  để hiển thị, chính là adapter
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>  // RecyclerView.Adapter<ProductViewHolder> : Lấy lại giao diện của view holer
    {
        //[19] Khai báo 1 mảng (trước khi làm thằng này thì cho name vào andoid manifest)
        ArrayList<Product> listProduct; // Khai báo thằng này xong thì xuống return chỗ listitemCount luôn

        //[20] Khai báo constructor
        public ProductAdapter(ArrayList<Product> listProduct) {
            this.listProduct = listProduct;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //[22]
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.sanpham_item, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            //[21]
            holder.bind(listProduct.get(position));
        }

        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }
}