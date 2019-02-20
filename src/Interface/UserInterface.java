package Interface;

import Classess.User;
import java.util.List;

public interface UserInterface {

    public void save(User user);

    public void update(User user, int id);

    public void delete(int id);

    public List<User> findOne(String id);

    public List<User> findAll();

    public boolean userLogin(String username, String password);

    public int userID(String username, String password);

    public boolean userExit(String username, String password);

}
