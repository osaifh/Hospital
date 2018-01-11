package hospital;

import java.util.List;

public interface DAO<T,PK> {
    
   public List<T> getAll();
   
   public T findByID(PK id);
   
   public boolean add(T object);
   
   public boolean update(T object);
   
   public boolean delete (PK id);
   
}
