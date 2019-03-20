package lesson3.auth;

import lesson3.dbconn.JdbcExample;

public class AuthServiceImpl implements AuthService {

    @Override
    public boolean authUser(String username, String password) {
        String pwd = JdbcExample.readFromDB(username);

        return pwd != null && pwd.equals(password);
    }
}
