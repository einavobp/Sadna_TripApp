package proj.sadna.mta.sadna_2017.app;

import android.provider.Settings;

import com.orm.SugarApp;

import proj.sadna.mta.sadna_2017.app.Utils.Utils;

/**
 * Created by Einav on 26/08/2017.
 */

public class TripApplication extends SugarApp
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        //get data from server

        //configure if new user or returns
        Utils.init(this);
        TripAppPreferences.init(this);
        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        TripAppPreferences.getInstance().setDeviceToken(android_id);
    }
}
