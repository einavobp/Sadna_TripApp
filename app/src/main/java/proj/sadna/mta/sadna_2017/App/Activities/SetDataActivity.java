package proj.sadna.mta.sadna_2017.app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import proj.sadna.mta.sadna_2017.R;

public class SetDataActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_data);

        RelativeLayout buildMyown = (RelativeLayout) findViewById(R.id.buildMyOwn);
        buildMyown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SetDataActivity.this, PathActivity.class);
                startActivity(intent);
            }
        });

        RelativeLayout guideMe = (RelativeLayout) findViewById(R.id.guideMe);
        guideMe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SetDataActivity.this, SitesActivity.class);
                startActivity(intent);
            }
        });


        Spinner dropdownSession = (Spinner) findViewById(R.id.spinner1);
        Spinner dropdownCompo = (Spinner) findViewById(R.id.spinner2);
        Spinner dropdownFrom = (Spinner) findViewById(R.id.spinner3);
        Spinner dropdownTo = (Spinner) findViewById(R.id.spinner4);

        String[] itemsSession = new String[]{"Summer", "Spring", "Winter", "Autumn"};
        ArrayAdapter<String> adapterSession = new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, itemsSession);
        dropdownSession.setAdapter(adapterSession);

        String[] itemsType = new String[]{"Couple", "Family -under 12", "Family -over 12", "Alone"};
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, itemsType);
        dropdownCompo.setAdapter(adapterType);
        dropdownSession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        ((ImageView) findViewById(R.id.weather_ic)).setImageResource(R.drawable.sun);
                        break;
                    case 1:
                        ((ImageView) findViewById(R.id.weather_ic)).setImageResource(R.drawable.spring);
                        break;
                    case 2:
                        ((ImageView) findViewById(R.id.weather_ic)).setImageResource(R.drawable.umbrella);
                        break;
                    case 3:
                        ((ImageView) findViewById(R.id.weather_ic)).setImageResource(R.drawable.maple);
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
                        ((ImageView) findViewById(R.id.composition_ic)).setImageResource(R.drawable.couple);
                        break;
                    case 1:
                        ((ImageView) findViewById(R.id.composition_ic)).setImageResource(R.drawable.family);
                        break;
                    case 2:
                        ((ImageView) findViewById(R.id.composition_ic)).setImageResource(R.drawable.group);
                        break;
                    case 3:
                        ((ImageView) findViewById(R.id.composition_ic)).setImageResource(R.drawable.man);
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
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, itemsFrom);
        dropdownFrom.setAdapter(adapterFrom);

        String[] itemsTo = new String[]{"13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"};
        ArrayAdapter<String> adapterTo = new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, itemsTo);
        dropdownTo.setAdapter(adapterTo);
    }
}
