package lesson4.auth;

import lesson4.dbconn.JdbcExample;

public class AuthServiceImpl implements AuthService {

    @Override
    public boolean authUser(String username, String password) {
        String pwd = JdbcExample.readFromDB(username);

        return pwd != null && pwd.equals(password);
    }
}
