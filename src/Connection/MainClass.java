package Connection;

import Models.User;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

/**
 *
 * @author nyark
 */
public class MainClass {

    public static void main(String[] args) {
        JsonPlaceholderApi api = RetrofitClient.getClient().create(JsonPlaceholderApi.class);

        Call<User> call = api.getUser(1);

        try {
            Response<User> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                System.out.println(response.body());
            } else {
                System.out.println("Request failed: " + response.errorBody());
            }
        } catch (IOException e) {
            System.out.println("Request failed One: " + e.getMessage());
        }
    }
}
