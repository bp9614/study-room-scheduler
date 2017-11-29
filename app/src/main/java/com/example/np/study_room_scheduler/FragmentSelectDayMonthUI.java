package com.example.np.study_room_scheduler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSelectDayMonthUI extends Fragment {


    public FragmentSelectDayMonthUI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_day_month_ui, container, false);
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
