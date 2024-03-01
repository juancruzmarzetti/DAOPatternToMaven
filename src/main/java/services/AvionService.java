package services;
import dao.IDAO;
import entitys.Avion;
import java.util.List;

public class AvionService {
    private IDAO<Avion> avionDAO;
    public AvionService(){}
    public AvionService(IDAO<Avion> avionDAO) {
        this.avionDAO = avionDAO;
    }
    public void guardarAvion(Avion avion){
        avionDAO.guardar(avion);
    }
    public void eliminarAvion(Long id){
        avionDAO.eliminar(id);
    }
    public List<Avion> buscarTodosLosAviones(){
        return avionDAO.buscarTodos();
    }
    public Avion buscar(Long id){
        return avionDAO.buscar(id);
    }
}
