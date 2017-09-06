package proj.sadna.mta.sadna_2017.app.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import info.hoang8f.widget.FButton;
import proj.sadna.mta.sadna_2017.R;
import proj.sadna.mta.sadna_2017.app.Activities.PathActivity;
import proj.sadna.mta.sadna_2017.app.Models.PathModel;
import proj.sadna.mta.sadna_2017.app.Network.NetworkManager;
import proj.sadna.mta.sadna_2017.app.Network.Request.RouteRequest;
import proj.sadna.mta.sadna_2017.app.Network.Response.RouteResponse;
import proj.sadna.mta.sadna_2017.app.interfaces.HandlePathSaver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewTripFragment extends Fragment
{

    public static final int PATH = 4455;
    private static String COUPLE_ID = "1";
    private static String SEASON_ID = "1";
    HandlePathSaver handlePathSaver;

    public NewTripFragment()
    {

    }

    public NewTripFragment(HandlePathSaver handlePathSaver)
    {
        this.handlePathSaver = handlePathSaver;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_new_trip, container, false);

        FButton guideMe = (FButton) view.findViewById(R.id.guideMe);
        guideMe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                NetworkManager.getInstance().calculate(new RouteRequest("1", SEASON_ID, COUPLE_ID, "8:00", "18:00"), new Callback<RouteResponse>()
                {
                    @Override
                    public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response)
                    {
                        PathModel pathModel = new PathModel("My Path", 0, response.body().sites);
                        pathModel.save();


                        Intent intent = new Intent(getActivity(), PathActivity.class);
                        intent.putExtra("id", pathModel.getId());
                        startActivityForResult(intent, PATH);
                    }

                    @Override
                    public void onFailure(Call<RouteResponse> call, Throwable t)
                    {
                        Toast.makeText(getActivity(), "Server Failed.", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        Spinner dropdownSession = (Spinner) view.findViewById(R.id.spinner1);
        Spinner dropdownCompo = (Spinner) view.findViewById(R.id.spinner2);
        Spinner dropdownFrom = (Spinner) view.findViewById(R.id.spinner3);
        Spinner dropdownTo = (Spinner) view.findViewById(R.id.spinner4);

        String[] itemsSession = new String[]{"Summer", "Spring", "Winter", "Autumn"};
        ArrayAdapter<String> adapterSession = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_dropdown_item, itemsSession);
        dropdownSession.setAdapter(adapterSession);

        String[] itemsType = new String[]{"Couple", "Family -under 12", "Family -over 12", "Alone"};
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_dropdown_item, itemsType);
        dropdownCompo.setAdapter(adapterType);
        dropdownSession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        ((ImageView) view.findViewById(R.id.weather_ic)).setImageResource(R.drawable.sun);
                        break;
                    case 1:
                        ((ImageView) view.findViewById(R.id.weather_ic)).setImageResource(R.drawable.spring);
                        break;
                    case 2:
                        ((ImageView) view.findViewById(R.id.weather_ic)).setImageResource(R.drawable.umbrella);
                        break;
                    case 3:
                        ((ImageView) view.findViewById(R.id.weather_ic)).setImageResource(R.drawable.maple);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                // your code here
            }

        });

        dropdownCompo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        ((ImageView) view.findViewById(R.id.composition_ic)).setImageResource(R.drawable.couple);
                        break;
                    case 1:
                        ((ImageView) view.findViewById(R.id.composition_ic)).setImageResource(R.drawable.family);
                        break;
                    case 2:
                        ((ImageView) view.findViewById(R.id.composition_ic)).setImageResource(R.drawable.group);
                        break;
                    case 3:
                        ((ImageView) view.findViewById(R.id.composition_ic)).setImageResource(R.drawable.man);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                // your code here
            }

        });
        String[] itemsFrom = new String[]{"08:00", "09:00", "10:00", "11:00", "12:00", "13:00"};
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_dropdown_item, itemsFrom);
        dropdownFrom.setAdapter(adapterFrom);

        String[] itemsTo = new String[]{"13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"};
        ArrayAdapter<String> adapterTo = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_dropdown_item, itemsTo);
        dropdownTo.setAdapter(adapterTo);

        return view;
    }

    public static Fragment newInstance()
    {
        Bundle args = new Bundle();
        NewTripFragment fragment = new NewTripFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
