package prolist.logics;

import prolist.dataaccess.UserDAO;
import prolist.model.User;

import java.sql.SQLException;

/**
 * @author Yuan-Fang Li
 * @author yqtian for version 2.0
 * @version $Id: $
 */
public class UserHandler {
    private final UserDAO userDAO;

    public UserHandler(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean updatePassword(Long personId, String password) throws SQLException {
        User user = userDAO.load(personId);

        if (user == null) {
            throw new SQLException("No user found for id: " + personId);
        }

        if (password == null) {
            return false;
        }

        if (password.equals(user.getPassword())) {
            return false;
        }

        user.setPassword(password);
        userDAO.update(user);

        return true;
    }

}
