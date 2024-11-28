package xyz.brawl.gamerise.ui.fragments.brawlers;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.ui.viewmodel.brawlers.BrawlersViewModel;

public class BrawlersFragment extends Fragment {

    private BrawlersViewModel mViewModel;

    public static BrawlersFragment newInstance() {
        return new BrawlersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brawlers, container, false);


        //provvisorio
        Button dropdownButton = view.findViewById(R.id.dropdown_button);
        dropdownButton.setOnClickListener(view2 -> {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), dropdownButton);
            popupMenu.getMenuInflater().inflate(R.menu.menu_dropdown, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.item1) {
                    Toast.makeText(getContext(), "Item 1 selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.item2) {
                    Toast.makeText(getContext(), "Item 2 selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });
            popupMenu.show();
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BrawlersViewModel.class);
        // TODO: Use the ViewModel
    }

}