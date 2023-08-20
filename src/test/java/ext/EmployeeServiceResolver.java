package ext;

import API.*;
import Model.API.User;
import ext.PropertyProvider;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.IOException;

public class EmployeeServiceResolver implements ParameterResolver {
    private final static String URL = PropertyProvider.getInstance().getProperties("props.properties").getProperty("baseURI");
    private final static String USER = PropertyProvider.getInstance().getProperties("props.properties").getProperty("login");
    private final static String PASS = PropertyProvider.getInstance().getProperties("props.properties").getProperty("password");

    private final static String ADMIN = PropertyProvider.getInstance().getProperties("props.properties").getProperty("login");
    private final static String ADMIN_PASS = PropertyProvider.getInstance().getProperties("props.properties").getProperty("password");

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(EmployeeService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new LogInterceptor()).build();
        EmployeeService service = new EmployeeServiceImpl(client, URL);

        if (parameterContext.isAnnotated(AdminAuthorized.class)) {
            AuthorizeService authorizedService = new AuthorizeServiceImpl(client, URL);
            User user;
            try {
                user = authorizedService.auth(ADMIN, ADMIN_PASS);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            service.setToken(user.getToken());
        }
        if (parameterContext.isAnnotated(Authorized.class)) {
            AuthorizeService authorizedService = new AuthorizeServiceImpl(client, URL);
            User user;
            try {
                user = authorizedService.auth(USER, PASS);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            service.setToken(user.getToken());
        }

        return service;
    }
}
