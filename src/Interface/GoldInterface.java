package Interface;

import Classess.Gold;
import java.util.List;

public interface GoldInterface {

    public void save(Gold gold);

    public void update(Gold gold, int id);

    public void delete(int id);

    public List<Gold> findOne(int id);

    public List<Gold> findAll();
}
