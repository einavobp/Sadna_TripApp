package proj.sadna.mta.sadna_2017.app.Fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import proj.sadna.mta.sadna_2017.R;
import proj.sadna.mta.sadna_2017.app.Activities.SiteOverviewActivity;
import proj.sadna.mta.sadna_2017.app.Adapters.GalleryAdapter;
import proj.sadna.mta.sadna_2017.app.Models.SiteModel;

/**
 * Created by Einav on 22/07/2017.
 */

public class GalleryFragment extends Fragment
{
    private static SiteOverviewActivity activity;
    private static SiteModel siteModel;
    GalleryAdapter mAdapter;
    public static String IMGS[] = {"https://images.unsplash.com/photo-1444090542259-0af8fa96557e?q=80&fm=jpg&w=1080&fit=max&s=4b703b77b42e067f949d14581f35019b", "https://images.unsplash.com/photo-1439546743462-802cabef8e97?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300", "https://media-cdn.tripadvisor.com/media/photo-s/0e/9a/e3/1d/freedom-tower.jpg", "https://images.unsplash.com/photo-1437651025703-2858c944e3eb?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300", "https://images.unsplash.com/photo-1431538510849-b719825bf08b?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300", "https://images.unsplash.com/photo-1434873740857-1bc5653afda8?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300", "https://images.unsplash.com/photo-1439396087961-98bc12c21176?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300", "https://images.unsplash.com/photo-1433616174899-f847df236857?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300", "https://images.unsplash.com/photo-1438480478735-3234e63615bb?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300", "https://images.unsplash.com/photo-1438027316524-6078d503224b?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300"};
    RecyclerView mRecyclerView;

    ArrayList<String> data = new ArrayList<>();

    public GalleryFragment()
    {

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_site_gallery, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_gallery);

        if (siteModel != null && siteModel.getImageUrl() != null)
        {
            data = siteModel.getImageUrl();
        } else for (int i = 0; i < IMGS.length; i++)
        {
            data.add(IMGS[i]);
        }

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0)
                {
                    activity.ShowHeader();
                } else
                {
                    activity.HideHeader();
                }
            }
        });
        mAdapter = new GalleryAdapter(getActivity(), data);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public static Fragment newInstance(SiteOverviewActivity siteOverviewActivity, SiteModel site)
    {
        activity = siteOverviewActivity;
        Bundle args = new Bundle();
        GalleryFragment fragment = new GalleryFragment();
        fragment.setArguments(args);
        siteModel = site;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}