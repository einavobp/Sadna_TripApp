package proj.sadna.mta.sadna_2017.app.Network.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Einav on 01/09/2017.
 */
public class Cities
{
    @SerializedName("name")
    public String name;

    @SerializedName("id")
    public int id;

    @SerializedName("location_lat")
    public double location_lat;

    @SerializedName("location_lng")
    public double location_lng;

    @SerializedName("country")
    public String country;

    @SerializedName("image_url")
    public String image_url;

    @SerializedName("description")
    public String description;
}
