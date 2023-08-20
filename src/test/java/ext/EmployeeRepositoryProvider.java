package ext;

import DB.EmployeeRepository;
import DB.EmployeeRepositoryImpl;
import ext.PropertyProvider;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class EmployeeRepositoryProvider implements ParameterResolver, BeforeAllCallback, AfterAllCallback {
    private Connection connection = null;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(EmployeeRepository.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new EmployeeRepositoryImpl(connection);

    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("Open");
        String connectionString = PropertyProvider.getInstance().getProperties("props.properties").getProperty("connectionString");
        String user = PropertyProvider.getInstance().getProperties("props.properties").getProperty("user");
        String pass = PropertyProvider.getInstance().getProperties("props.properties").getProperty("user.pass");
        connection = DriverManager.getConnection(connectionString, user, pass);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("Close");
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
