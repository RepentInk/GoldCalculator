package Connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author nyark
 */
public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static Retrofit getClient() {
        try {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getLocalizedMessage());
        }

        return retrofit;
    }

}
