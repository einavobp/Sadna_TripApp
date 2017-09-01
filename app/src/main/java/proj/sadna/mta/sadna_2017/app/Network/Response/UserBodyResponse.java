package proj.sadna.mta.sadna_2017.app.Network.Response;

import com.google.gson.annotations.SerializedName;

import proj.sadna.mta.sadna_2017.app.Network.Request.User;


/**
 * Created by Einav on 01/09/2017.
 */
public class UserBodyResponse
{
    @SerializedName(User.APP_USER_ID)
    public long userId;

    @SerializedName("uname")
    public String email;

}
