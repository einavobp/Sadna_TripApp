package proj.sadna.mta.sadna_2017.app.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;

import proj.sadna.mta.sadna_2017.R;

/**
 * Created by Einav on 26/08/2017.
 */
public class UserSettingsFragment extends Fragment
{
    Activity context;
    RelativeLayout feedback;
    RelativeLayout notification;
    RelativeLayout profile;
    RelativeLayout logout;
    Switch mSwitch;
    private RelativeLayout share;

    public UserSettingsFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_settings, container, false);
    }

    public static Fragment newInstance()
    {
        Bundle args = new Bundle();
        UserSettingsFragment fragment = new UserSettingsFragment();
        fragment.setArguments(args);

        return fragment;
    }

}
