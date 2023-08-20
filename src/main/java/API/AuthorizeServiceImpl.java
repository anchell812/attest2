package API;

import Model.API.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class AuthorizeServiceImpl implements AuthorizeService {
    private String BASE_URL;
    private String PATH = "auth/login";
    private OkHttpClient client;
    private ObjectMapper mapper;
    private MediaType APPLICATION_JSON = MediaType.parse("application/json; charset=utf-8");

    public AuthorizeServiceImpl(OkHttpClient client, String url) {
        this.BASE_URL = url;
        this.client = client;
        this.mapper = new ObjectMapper();
    }

    @Override
    public User auth(String username, String password) throws IOException {
        RequestBody bodyAuth = RequestBody.create("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}", APPLICATION_JSON);
        HttpUrl urlAuth = HttpUrl.parse(BASE_URL).newBuilder().addPathSegments(PATH).build();
        Request request = new Request.Builder().post(bodyAuth).url(urlAuth).build();
        Response response = client.newCall(request).execute();
        User user = mapper.readValue(response.body().string(), User.class);
        return user;
    }
}