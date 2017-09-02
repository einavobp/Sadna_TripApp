package proj.sadna.mta.sadna_2017.app.Network.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Einav on 01/09/2017.
 */
public class LoginResponse
{
    @SerializedName("user")
    public UserBodyResponse user;

    @SerializedName("cities")
    public ArrayList<Cities> cities;

    @SerializedName("sites")
    public List<Sites> sites;

//    @SerializedName("recommended_paths")
    public List<RecommendedPathsResponse> openingTimesResponse;

}

