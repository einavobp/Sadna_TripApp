package proj.sadna.mta.sadna_2017.app.Network.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Einav on 01/09/2017.
 */
public class RecommendedPathsResponse
{
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("sites")
    public String sites;

    @SerializedName("description")
    public String description;

}
