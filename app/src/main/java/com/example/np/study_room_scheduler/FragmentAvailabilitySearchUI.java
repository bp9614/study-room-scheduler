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
public class FragmentAvailabilitySearchUI extends Fragment {


    public FragmentAvailabilitySearchUI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_availability_search_ui, container, false);
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
