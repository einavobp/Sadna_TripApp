package proj.sadna.mta.sadna_2017.app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

import proj.sadna.mta.sadna_2017.R;
import proj.sadna.mta.sadna_2017.app.Adapters.SiteAdapter;
import proj.sadna.mta.sadna_2017.app.Models.SiteModel;

public class SearchSiteActivity extends AppCompatActivity
{
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_site);

        ArrayList<SiteModel> data = new ArrayList<>();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.sites_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setHasFixedSize(true);

        data.add(new SiteModel("The Metropolitan Museum of Art", "Museum of Art", "1000 5th Ave, NY 10028-0198", "http://bionics.seas.ucla.edu/people/rosen/panorama/NY_NYC_Metropolitan_Museum_02_LR.jpg", 1, new ArrayList<String>(), 1.3, 1.3, "972-009988884", "bla bla bla", "eiav@gga.com"));
        data.add(new SiteModel("The Metropolitan Museum of Art", "Museum of Art", "1000 5th Ave, NY 10028-0198", "http://bionics.seas.ucla.edu/people/rosen/panorama/NY_NYC_Metropolitan_Museum_02_LR.jpg", 1, new ArrayList<String>(), 1.3, 1.3, "972-009988884", "bla bla bla", "eiav@gga.com"));
        data.add(new SiteModel("The Metropolitan Museum of Art", "Museum of Art", "1000 5th Ave, NY 10028-0198", "http://bionics.seas.ucla.edu/people/rosen/panorama/NY_NYC_Metropolitan_Museum_02_LR.jpg", 1, new ArrayList<String>(), 1.3, 1.3, "972-009988884", "bla bla bla", "eiav@gga.com"));
        data.add(new SiteModel("The Metropolitan Museum of Art", "Museum of Art", "1000 5th Ave, NY 10028-0198", "http://bionics.seas.ucla.edu/people/rosen/panorama/NY_NYC_Metropolitan_Museum_02_LR.jpg", 1, new ArrayList<String>(), 1.3, 1.3, "972-009988884", "bla bla bla", "eiav@gga.com"));
        data.add(new SiteModel("The Metropolitan Museum of Art", "Museum of Art", "1000 5th Ave, NY 10028-0198", "http://bionics.seas.ucla.edu/people/rosen/panorama/NY_NYC_Metropolitan_Museum_02_LR.jpg", 1, new ArrayList<String>(), 1.3, 1.3, "972-009988884", "bla bla bla", "eiav@gga.com"));
        data.add(new SiteModel("The Metropolitan Museum of Art", "Museum of Art", "1000 5th Ave, NY 10028-0198", "http://bionics.seas.ucla.edu/people/rosen/panorama/NY_NYC_Metropolitan_Museum_02_LR.jpg", 1, new ArrayList<String>(), 1.3, 1.3, "972-009988884", "bla bla bla", "eiav@gga.com"));
        data.add(new SiteModel("The Metropolitan Museum of Art", "Museum of Art", "1000 5th Ave, NY 10028-0198", "http://bionics.seas.ucla.edu/people/rosen/panorama/NY_NYC_Metropolitan_Museum_02_LR.jpg", 1, new ArrayList<String>(), 1.3, 1.3, "972-009988884", "bla bla bla", "eiav@gga.com"));
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_search);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(SearchSiteActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e)
                {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e)
                {
                    // TODO: Handle the error.
                }
            }
        });

        SiteAdapter mAdapter = new SiteAdapter(this, data);
        mRecyclerView.setAdapter(mAdapter);
    }

}
