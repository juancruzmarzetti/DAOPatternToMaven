package dao;
import java.util.List;
public interface IDAO<T>{
    public List<T> buscarTodos();
    public T buscar(Long id);
    public void guardar(T t);
    public void eliminar(Long id);
}
