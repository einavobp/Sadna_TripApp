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
import android.widget.RelativeLayout;
import android.widget.Spinner;

import proj.sadna.mta.sadna_2017.app.Activities.PathActivity;
import proj.sadna.mta.sadna_2017.R;

public class NewTripFragment extends Fragment
{

    public NewTripFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_new_trip, container, false);

        RelativeLayout guideMe = (RelativeLayout) view.findViewById(R.id.guideMe);
        guideMe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), PathActivity.class);
                startActivity(intent);
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
