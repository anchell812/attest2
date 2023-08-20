package DB;

import java.sql.SQLException;

public interface CompanyRepository {

    int getCompanyId();

    void setCompanyId(int companyId);

    int create() throws SQLException;

    //int create(String name, String description) throws SQLException;

    void delete(int id) throws SQLException;
}
