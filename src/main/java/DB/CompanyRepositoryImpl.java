package DB;

import Model.API.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyRepositoryImpl implements CompanyRepository {
    private final Connection connection;
    private int companyId;
    private static final String INSERT = "insert into company (name) values ('Name Of Company')";
    private static final String DELETE = "delete from company where \"id\"=?";
    private final static String SELECT_ALL = "select * from company where \"deletedAt\" is null";
    private final static String SELECT_LAST = "select * from company where \"deletedAt\" is null ORDER BY \"id\" DESC LIMIT 1";


    public CompanyRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getCompanyId() {
        return companyId;
    }

    @Override
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public int create() throws SQLException {
        int resultSetInsert = connection.createStatement().executeUpdate(INSERT);
        ResultSet resultSetLast = connection.createStatement().executeQuery(SELECT_LAST);
        Company company = new Company();
        resultSetLast.next();
        company.setId(resultSetLast.getInt("id"));
        return companyId = company.getId();
    }

    @Override
    public void delete(int companyId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        preparedStatement.setInt(1, companyId);
        preparedStatement.executeUpdate();
    }


}
