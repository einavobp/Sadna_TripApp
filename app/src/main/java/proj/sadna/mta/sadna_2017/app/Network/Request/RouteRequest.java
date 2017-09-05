package proj.sadna.mta.sadna_2017.app.Network.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Einav on 05/09/2017.
 */
public class RouteRequest
{

    @SerializedName("cityId")
    private String cityId;

    @SerializedName("seasonId")
    private String seasonId;


    @SerializedName("compositionId")
    private String compositionId;


    @SerializedName("startTime")
    private String startTime;


    @SerializedName("endTime")
    private String endTime;

    public RouteRequest(String cityId, String seasonId, String compositionId, String startTime, String endTime)
    {
        this.cityId = cityId;
        this.seasonId = seasonId;
        this.compositionId = compositionId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
