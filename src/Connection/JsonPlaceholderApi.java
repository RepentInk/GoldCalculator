package Connection;

import Models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 * @author nyark
 */
public interface JsonPlaceholderApi {

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int userId);

}
