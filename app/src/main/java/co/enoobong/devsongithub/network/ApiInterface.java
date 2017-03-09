package co.enoobong.devsongithub.network;

import co.enoobong.devsongithub.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by owner on 5/3/2017.
 */

public interface ApiInterface {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users")
    Call<ApiResponse> getJavaDevsInLagos(@Query(value = "q", encoded = true) String searchTerms);
}
