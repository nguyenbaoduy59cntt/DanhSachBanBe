package vn.edu.ntu.nbd.bai22;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class DetailFragment extends Fragment {
    EditText edt_id, edt_name, edt_birthday, edt_phone, edt_address;
    Button btn_save;

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

        //add view
        edt_id = view.findViewById(R.id.edt_id);
        edt_name = view.findViewById(R.id.edt_name);
        edt_birthday = view.findViewById(R.id.edt_birthday);
        edt_phone = view.findViewById(R.id.edt_phone);
        edt_address=view.findViewById(R.id.edt_address);
        btn_save = view.findViewById(R.id.btn_save);
        //add events
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DetailFragment.this).navigate(R.id.action_detailFragment_to_listFragment);
            }
        });
    }
}