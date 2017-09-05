package proj.sadna.mta.sadna_2017.app.Network;

import proj.sadna.mta.sadna_2017.app.Network.Request.RouteRequest;
import proj.sadna.mta.sadna_2017.app.Network.Request.User;
import proj.sadna.mta.sadna_2017.app.Network.Response.LoginResponse;
import proj.sadna.mta.sadna_2017.app.Network.Response.RouteResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Einav on 01/09/2017.
 */

public interface TripApi
{

    @POST(ApiConstants.LOGIN_USER)
    Call<LoginResponse> loginUser(@Body User user);

    @POST(ApiConstants.CALCULATE)
    Call<RouteResponse> calculateRoute(@Body RouteRequest route);
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_USER)
//    Call<GetUserResponse> getUser(@Field(User.APP_USER_ID) int userId);
//
//    @POST(ApiConstants.FIND_ALL_USERS)
//    Call<FindAllUsersResponse> findAllUsers();


}
