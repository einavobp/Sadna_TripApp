package proj.sadna.mta.sadna_2017.app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

import proj.sadna.mta.sadna_2017.R;
import proj.sadna.mta.sadna_2017.app.Adapters.SiteAdapter;
import proj.sadna.mta.sadna_2017.app.Models.PathModel;
import proj.sadna.mta.sadna_2017.app.Models.SiteModel;

public class SearchSiteActivity extends AppCompatActivity
{
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 100;
    private static final String TAG = "TAG";
    public static boolean changed = false;
    private PathModel pathModel = new PathModel();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_site);

        ArrayList<SiteModel> data = SiteModel.getList();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.sites_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setHasFixedSize(true);

//          FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_search);
//        floatingActionButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                try
//                {
//                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).
//                            build(SearchSiteActivity.this);
//                    SearchSiteActivity.this.startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
//                } catch (GooglePlayServicesRepairableException e)
//                {
//                    Toast.makeText(SearchSiteActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                } catch (GooglePlayServicesNotAvailableException e)
//                {
//                    Toast.makeText(SearchSiteActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        if (getIntent().hasExtra("id"))
        {
            pathModel = PathModel.findById(PathModel.class, getIntent().getLongExtra("id", 1));
        }
        SiteAdapter mAdapter = new SiteAdapter(this, this, pathModel, data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed()
    {
        setResult(144);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        try
        {
            if (resultCode == 123)
            {
                this.setResult(123);
                this.finish();
            }
            if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE)
            {
                if (resultCode == RESULT_OK)
                {
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    Log.i(TAG, "Place: " + place.getName());
                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR)
                {
                    Status status = PlaceAutocomplete.getStatus(this, data);
                    // TODO: Handle the error.
                    Log.i(TAG, status.getStatusMessage());

                } else if (resultCode == RESULT_CANCELED)
                {
                    Toast.makeText(this, "Cancled", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e)
        {
            Toast.makeText(SearchSiteActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
