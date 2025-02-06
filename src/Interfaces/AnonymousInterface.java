package Interfaces;

import java.util.List;

/**
 *
 * @author nyark
 * @param <T>
 */
public interface AnonymousInterface<T> {

    public List<T> list(String year);
    
    public List<T> list();

    public T find(int id);

    public int save(T t);

    public void update(T t, int id);

    public void delete(int id);

    public void clear();

    public int count();
}
