package vn.edu.ntu.nbd.bai2;

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

public class ListBBFragment extends Fragment
{
    RecyclerView rvList;

    @Override
    public View onCreateView
            (
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
            )
    {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvList = view.findViewById(R.id.rvList);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.mnufab, menu);
    }

    //
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // lấy id
        int id =item.getItemId();
        if(id == R.id.mnufab)
        {
            CallShoppingFragment();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CallShoppingFragment()
    {
        NavHostFragment.findNavController(ListBBFragment.this).navigate(R.id.action_listBBFragment_to_detailFragment);
    }
}