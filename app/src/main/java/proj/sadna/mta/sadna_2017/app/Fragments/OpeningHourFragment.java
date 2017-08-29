package proj.sadna.mta.sadna_2017.app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import proj.sadna.mta.sadna_2017.R;

/**
 * Created by Einav on 21/07/2017.
 */

public class OpeningHourFragment extends Fragment
{
    public OpeningHourFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_opening_hours, container, false);
    }

    public static Fragment newInstance()
    {
        Bundle args = new Bundle();
        OpeningHourFragment fragment = new OpeningHourFragment();
        fragment.setArguments(args);

        return fragment;
    }

}
