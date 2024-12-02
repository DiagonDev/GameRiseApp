package xyz.brawl.gamerise.ui.fragments.ranked;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.ui.fragments.brawlers.BrawlersFragment;
import xyz.brawl.gamerise.ui.viewmodels.ranked.RankedViewModel;

public class RankedFragment extends Fragment {

    private RankedViewModel mViewModel;

    public static RankedFragment newInstance() {
        return new RankedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranked, container, false);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view, new BrawlersFragment())
                .commit();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RankedViewModel.class);
        // TODO: Use the ViewModel
    }

}