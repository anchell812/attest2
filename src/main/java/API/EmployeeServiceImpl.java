package API;

import Model.API.EmployeeEntity;
import Model.API.EmployeeFromAPI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final OkHttpClient CLIENT;
    private static String BASE_URL;
    private String PATH = "/employee";
    private MediaType APPLICATION_JSON = MediaType.parse("application/json; charset=utf-8");
    private ObjectMapper MAPPER;

    private String TOKEN;


    public EmployeeServiceImpl(OkHttpClient client, String url) {
        this.CLIENT = client;
        BASE_URL = url;
        MAPPER = new ObjectMapper();
    }


    @Override
    public List<EmployeeFromAPI> getEmployees(int id) throws IOException {
        HttpUrl url = HttpUrl
                .parse(BASE_URL)
                .newBuilder()
                .addPathSegments(PATH)
                .addQueryParameter("company", Integer.toString(id))
                .build();
        Request request = new Request.Builder().get().url(url).build();
        Response response = CLIENT.newCall(request).execute();
        return MAPPER.readValue(response.body().string(), new TypeReference<>() {
        });
    }

    @Override
    public int createEmployee(EmployeeFromAPI employeeFromAPI) throws IOException {
        RequestBody body = RequestBody.create(MAPPER.writeValueAsString(employeeFromAPI), APPLICATION_JSON);
        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder().addPathSegment(PATH).build();
        Request request = new Request.Builder().post(body).url(url).addHeader("x-client-token", TOKEN).build();
        Response response = CLIENT.newCall(request).execute();
        EmployeeFromAPI employee = MAPPER.readValue(response.body().string(), EmployeeFromAPI.class);
        return employee.getId();
    }

    @Override
    public int createEmployeeEnt(EmployeeEntity employeeEntity) throws IOException {
        RequestBody body = RequestBody.create(MAPPER.writeValueAsString(employeeEntity), APPLICATION_JSON);
        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder().addPathSegment(PATH).build();
        Request request = new Request.Builder().post(body).url(url).addHeader("x-client-token", TOKEN).build();
        Response response = CLIENT.newCall(request).execute();
        EmployeeEntity employee = MAPPER.readValue(response.body().string(), EmployeeEntity.class);
        return employee.getId();
    }

    @Override
    public int createEmployeeUnauthorized(EmployeeFromAPI employeeFromAPI) throws IOException {
        RequestBody body = RequestBody.create(MAPPER.writeValueAsString(employeeFromAPI), APPLICATION_JSON);
        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder().addPathSegment(PATH).build();
        Request request = new Request.Builder().post(body).url(url).build();
        Response response = CLIENT.newCall(request).execute();
        EmployeeFromAPI employee = MAPPER.readValue(response.body().string(), EmployeeFromAPI.class);
        return employee.getId();
    }

    public int createEmployeeUnauthorizedEnt(EmployeeEntity employeeEntity) throws IOException {
        RequestBody body = RequestBody.create(MAPPER.writeValueAsString(employeeEntity), APPLICATION_JSON);
        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder().addPathSegment(PATH).build();
        Request request = new Request.Builder().post(body).url(url).build();
        Response response = CLIENT.newCall(request).execute();
        EmployeeFromAPI employee = MAPPER.readValue(response.body().string(), EmployeeFromAPI.class);
        return employee.getId();
    }

    public EmployeeFromAPI getEmployeeById(int id) throws IOException {
        HttpUrl url = HttpUrl
                .parse(BASE_URL)
                .newBuilder()
                .addPathSegments(PATH)
                .addPathSegment(Integer.toString(id))
                .build();
        Request request = new Request.Builder().get().url(url).build();
        Response response = CLIENT.newCall(request).execute();
        return MAPPER.readValue(response.body().string(), EmployeeFromAPI.class);
    }

    @Override
    public void updateEmployeeEnt(int id, EmployeeEntity employeeEntity) throws IOException {
        HttpUrl url = HttpUrl
                .parse(BASE_URL)
                .newBuilder()
                .addPathSegments(PATH)
                .addPathSegment(Integer.toString(id))
                .build();
        RequestBody body = RequestBody.create(MAPPER.writeValueAsString(employeeEntity), APPLICATION_JSON);
        Request request = new Request.Builder().patch(body).url(url).addHeader("x-client-token", TOKEN).build();
        CLIENT.newCall(request).execute();
    }


    public void setToken(String token) {
        this.TOKEN = token;
    }
}
