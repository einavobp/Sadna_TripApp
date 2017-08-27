package proj.sadna.mta.sadna_2017.app.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import proj.sadna.mta.sadna_2017.R;
import proj.sadna.mta.sadna_2017.app.Activities.LoginActivity;
import proj.sadna.mta.sadna_2017.app.TripAppPreferences;
import proj.sadna.mta.sadna_2017.app.Utils.AppConstants;
import proj.sadna.mta.sadna_2017.app.Utils.Utils;

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
        View view = inflater.inflate(R.layout.fragment_user_settings, container, false);
        context = getActivity();
        feedback = (RelativeLayout) view.findViewById(R.id.feedback);
        profile = (RelativeLayout) view.findViewById(R.id.profile);
        notification = (RelativeLayout) view.findViewById(R.id.notification);
        logout = (RelativeLayout) view.findViewById(R.id.logout);
        share = (RelativeLayout) view.findViewById(R.id.share);
        mSwitch = ((Switch) view.findViewById(R.id.switch_notification));
        mSwitch.setChecked(TripAppPreferences.getInstance().showNotification());
        setOnClicks();
        return view;
    }

    private void setOnClicks()
    {
        feedback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Utils.sendEmail(context, "support@tripapp.com", "trip app issue:", "sent from app");
            }
        });

        profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "tripApp")));
            }
        });

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Log.d("send notification", isChecked + " ");
                TripAppPreferences.setNotification(isChecked);
            }
        });
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TripAppPreferences.logout();
                Intent intent = new Intent(context, LoginActivity.class);
                intent.putExtra(AppConstants.KILL, true);
                context.startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mSwitch.performClick();
            }
        });
        share.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Check us out! - come and plan a great trip with us. http:\\tripapp.com");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }

    public static Fragment newInstance()
    {
        Bundle args = new Bundle();
        UserSettingsFragment fragment = new UserSettingsFragment();
        fragment.setArguments(args);

        return fragment;
    }

}
