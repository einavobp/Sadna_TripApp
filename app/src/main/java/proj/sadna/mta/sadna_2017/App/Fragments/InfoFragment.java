package proj.sadna.mta.sadna_2017.App.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import proj.sadna.mta.sadna_2017.App.Models.SiteModel;
import proj.sadna.mta.sadna_2017.R;

/**
 * Created by Einav on 21/07/2017.
 */

public class InfoFragment extends Fragment
{
    private static SiteModel site;

    public InfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_site_general_info, container, false);
        ((TextView) view.findViewById(R.id.site_name)).setText(site.getFullName());
        ((TextView) view.findViewById(R.id.description)).setText(site.getDescription());
        ((TextView) view.findViewById(R.id.address)).setText(site.getAddress());
        ((TextView) view.findViewById(R.id.phone_number)).setText(site.getPhoneNumber());
        //((TextView) view.findViewById(R.id.phone_number)).setText(site.getEmail());

        Glide.with(getActivity()).load(site.getProfilePicture()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(((ImageView) view.findViewById(R.id.image_site)));
        return view;
    }

    public static Fragment newInstance(SiteModel siteModel)
    {
        site = siteModel;
        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
