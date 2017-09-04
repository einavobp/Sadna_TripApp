package proj.sadna.mta.sadna_2017.app.Activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import proj.sadna.mta.sadna_2017.R;
import proj.sadna.mta.sadna_2017.app.Models.SiteModel;

import static proj.sadna.mta.sadna_2017.app.Utils.AppConstants.REQUEST_LOCATION;

public class PathOnMapActivity extends AppCompatActivity implements RoutingListener
{
    private static final int[] COLORS = new int[]{R.color.primary_dark, R.color.primary, R.color.primary_light, R.color.accent, R.color.primary_dark_material_light};
    MapView mMapView;
    private GoogleMap googleMap;
    private String[] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_on_map);
        final List<SiteModel> siteModels = new ArrayList<>();
        if (getIntent().hasExtra("ids"))
        {
            ids = getIntent().getStringExtra("ids").split(",");
            for (String id : ids)
            {
                try
                {
                    long siteID = Long.parseLong(id);
                    siteModels.add(SiteModel.findById(SiteModel.class, siteID));

                } catch (Exception e)
                {
                    Log.d("ex", e.getMessage());
                }
            }

        }
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately

        try
        {
            MapsInitializer.initialize(this.getApplicationContext());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback()
        {
            @Override
            public void onMapReady(GoogleMap mMap)
            {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(PathOnMapActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(PathOnMapActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(PathOnMapActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else
                {
                    Log.e("DB", "PERMISSION GRANTED");
                }

                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng loc = new LatLng(40.731640, -73.996985);
//                LatLng loc2 = new LatLng(40.724612, -74.002543);
//                LatLng loc3 = new LatLng(40.706080, -73.996889);
                List<LatLng> latLngs = new ArrayList<LatLng>();
                for (SiteModel site : siteModels)
                {
                    loc = new LatLng(site.getLat(), site.getLng());
                    latLngs.add(loc);
                    googleMap.addMarker(new MarkerOptions().position(loc).title(site.getFullName()));
                }

                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
                Routing routing = new Routing.Builder().travelMode(AbstractRouting.TravelMode.DRIVING).withListener(PathOnMapActivity.this).alternativeRoutes(true).waypoints().build();
                routing.execute();

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(loc).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    @Override
    public void onRoutingFailure(RouteException e)
    {

    }

    @Override
    public void onRoutingStart()
    {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int len)
    {
        ArrayList<Object> polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i < route.size(); i++)
        {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = googleMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getApplicationContext(), "Route " + (i + 1) + ": distance - " + route.get(i).getDistanceValue() + ": duration - " + route.get(i).getDurationValue(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingCancelled()
    {

    }
}

