package API;

import Model.API.User;

import java.io.IOException;

public interface AuthorizeService {
    User auth(String username, String password) throws IOException;
}
