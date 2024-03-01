import dao.AvionDAOH2;
import entitys.Avion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.AvionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

public class AvionDAOH2Test {
    public static Avion avion1;
    public static Avion avion2;
    public static AvionService avionService;
    @BeforeAll
    public static void initAll(){
        avion1 = new Avion(
                1L,
                "Chevy",
                "A96 2001",
                123465,
                LocalDate.of(2024,2,25));
        avion2 = new Avion(
                2L,
                "Casanova",
                "B55 2010",
                111111,
                LocalDate.of(2010, 7, 15));
        avionService = new AvionService(new AvionDAOH2());
    }
    @Test
    public void DAOTestGuardarYBuscarTodos(){
        avionService.guardarAvion(avion1);
        avionService.guardarAvion(avion2);
        List<Avion> aviones = avionService.buscarTodosLosAviones();
        Assertions.assertEquals(2, aviones.size());
    }

    @Test
    public void DAOTestEliminar(){
        avionService.eliminarAvion(avion1.getId());
        List<Avion> aviones2 = avionService.buscarTodosLosAviones();
        Assertions.assertEquals(1, aviones2.size());
    }

    @Test
    public void DAOTestBuscarUno(){
        Avion avionSet = new Avion();
        Avion avionBuscado = avionService.buscar(avion2.getId());
        avionSet.setId(avionBuscado.getId());
        assertEquals(avionSet.getId(), avionBuscado.getId());
    }
}