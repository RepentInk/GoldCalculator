package Interface;

import Classess.Customers;
import java.util.List;

public interface CustomerInterface {

    public void save(Customers customer);

    public void update(Customers customer, int id);

    public void delete(int id);

    public int returnID(String fullname);

    public List<Customers> findAll();
    
    public List<Customers> findOne(String id);

}
