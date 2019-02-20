package Interface;

import Classess.Price;
import java.util.List;

public interface PriceInterface {

    public void save(Price price);

    public void update(Price price, int id);

    public void delete(int id);

    public List<Price> findOne(int id);

    public List<Price> findAll();

}
