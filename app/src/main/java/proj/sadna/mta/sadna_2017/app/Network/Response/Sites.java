package proj.sadna.mta.sadna_2017.app.Network.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Einav on 01/09/2017.
 */
public class Sites
{
    @SerializedName("place_id")
    public String place_id;

    @SerializedName("name")
    public String name;

    @SerializedName("formatted_address")
    public String formatted_address;

    @SerializedName("formatted_phone_number")
    public String formatted_phone_number;

    @SerializedName("international_phone_number")
    public String international_phone_number;

    @SerializedName("rating")
    public double rating;

    @SerializedName("google_id")
    public String google_id;

    @SerializedName("location_lat")
    public double location_lat;

    @SerializedName("location_lng")
    public double location_lng;

    @SerializedName("types")
    public String types;

    @SerializedName("id")
    public long id;

    @SerializedName("profile_photo")
    public String profile_photo;

    @SerializedName("album_photos")
    public String album_photos;

    @SerializedName("city_id")
    public int city_id;

    @SerializedName("description")
    public String description;

    @SerializedName("seasons")
    public String seasons;

    @SerializedName("compositions")
    public String compositions;
}
