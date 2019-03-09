package lesson2.auth;

import lesson2.dbconn.JdbcExample;

import java.util.HashMap;
import java.util.Map;

public class AuthServiceImpl implements AuthService {

    @Override
    public boolean authUser(String username, String password) {
        String pwd = JdbcExample.readFromDB(username);

        return pwd != null && pwd.equals(password);
    }
}
