package xyz.brawl.gamerise.ui.fragments.ranked;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.ui.fragments.brawlers.BrawlersFragment;
import xyz.brawl.gamerise.ui.viewmodels.ranked.RankedViewModel;
import xyz.brawl.gamerise.util.NetworkUtil;

public class RankedFragment extends Fragment {

    private RankedViewModel mViewModel;
    private FrameLayout noInternetView;

    public static RankedFragment newInstance() {
        return new RankedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        String lastUpdate = "0";
        View view = inflater.inflate(R.layout.fragment_ranked, container, false);
        noInternetView = view.findViewById(R.id.no_internet_view);
        if(!NetworkUtil.isInternetAvailable(this.getContext())){
            noInternetView.setVisibility(View.VISIBLE);
            lastUpdate = System.currentTimeMillis() + "";
        }
        TextView coppeProgressText = view.findViewById(R.id.coppe_progress_text);
        coppeProgressText.setText("30/100");
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