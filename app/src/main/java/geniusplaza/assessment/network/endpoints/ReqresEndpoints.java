package geniusplaza.assessment.network.endpoints;

import java.util.List;

import geniusplaza.assessment.models.Create;
import geniusplaza.assessment.models.User;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jamarkushodge on 4/3/19.
 */

// This class details mapped endpoints for reqres API.

    public interface ReqresEndpoints {
        // Request method and URL specified in the annotation

        @GET("users")
        Call<ResponseBody> getUsers(@Query("per_page") int per_page);

        @POST("users")
        Call<ResponseBody> createUser(@Body Create user);
    }

