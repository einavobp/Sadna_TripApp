package proj.sadna.mta.sadna_2017.app.Network.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Einav on 05/09/2017.
 */
public class RouteResponse
{

    @SerializedName("route")
    public ArrayList<Sites> sites;

//
//    @SerializedName("hours")
//    public List<Sites> hours;
}
