package dao;
import entitys.Avion;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AvionDAOH2 implements IDAO<Avion>{
    //Log4j logger configuration:
    private static final Logger LOGGER = Logger.getLogger(AvionDAOH2.class.getName());

    //Database configuration:
    private static final String DB_CONFIG_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_CONFIG_URL = "jdbc:h2:~/mydaodb";
    private static final String DB_CONFIG_USER = "sa";
    private static final String DB_CONFIG_PASSWORD = "";

    //Statements and PreparedStatements configuration:
    private static final String STMT_BUSCAR_TODOS = "SELECT * FROM AVIONES;";
    private static final String PSTMT_BUSCAR = "SELECT * FROM AVIONES WHERE ID = ?;";
    private static final String PSTMT_ELIMINAR = "DELETE FROM AVIONES WHERE ID = ?";
    private static final String PSTMT_GUARDAR = "INSERT INTO AVIONES " +
            "(ID, MARCA, MODELO, MATRICULA, FECHA_DE_ENTRADA_A_SERVICIO)" +
            "VALUES (?,?,?,?,?)";
    @Override
    public List<Avion> buscarTodos() {
        List<Avion> aviones = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(STMT_BUSCAR_TODOS);
            while(rs.next()){
                Avion avion = new Avion(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5).toLocalDate());
                aviones.add(avion);
            }
            stmt.close();
        }catch (Exception e){
            LOGGER.info("Error al buscar la lista completa de aviones en base de datos: " + e);
        }finally{
            try{
                c.close();
            }catch (Exception ex){
                LOGGER.info("Error al intentar cerrar conexión con base de datos" +
                        "al buscar la lista completa de aviones: " + ex);
            }
        }
        return aviones;
    }
    @Override
    public Avion buscar(Long id) {
        Avion avionBuscado = new Avion();
        Connection c = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            pstmt = c.prepareStatement(PSTMT_BUSCAR);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(rs.getLong(1));
            while(rs.next()){
                avionBuscado.setId(rs.getLong(1));
                avionBuscado.setMarca(rs.getString(2));
                avionBuscado.setModelo(rs.getString(3));
                avionBuscado.setMatricula(rs.getInt(4));
                avionBuscado.setFechaDeEntradaServicio(rs.getDate(5).toLocalDate());
            }
            pstmt.close();
        }catch(Exception e){
            LOGGER.info("Error al comunicarnos con la base de datos para buscar" +
                    "un avion por su id: " + e);
        }finally{
            try{
                c.close();
            }catch (Exception ex){
                LOGGER.info("Error al intentar cerrar la conexión con la base de datos" +
                        "al buscar un avion por su id: " + ex);
            }
        }
        return avionBuscado;
    }
    @Override
    public void guardar(Avion avion) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            pstmt = c.prepareStatement(PSTMT_GUARDAR);
            pstmt.setLong(1, avion.getId());
            pstmt.setString(2, avion.getMarca());
            pstmt.setString(3, avion.getModelo());
            pstmt.setInt(4, avion.getMatricula());
            pstmt.setDate(5, Date.valueOf(avion.getFechaDeEntradaServicio()));
            c.setAutoCommit(false);
            pstmt.executeUpdate();
            c.commit();
            pstmt.close();
        }catch(Exception e){
            LOGGER.info("Error al guardar avion en la base de datos: " + e);
            try{
                c.rollback();
            }catch (Exception ex){
                LOGGER.info("No se pudo ejecutar el rollback en defensa del" +
                        "error debido a no poder guardar el avion en la base de datos: " + ex);
            }
        }finally{
            try{
                c.close();
            }catch(Exception exc){
                LOGGER.info("Error al intentar cerrar la conexión con la base de datos luego" +
                        "de guardar el avion en la misma: " + exc);
            }
        }
    }
    @Override
    public void eliminar(Long id) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(DB_CONFIG_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_CONFIG_URL, DB_CONFIG_USER, DB_CONFIG_PASSWORD);
            pstmt = c.prepareStatement(PSTMT_ELIMINAR);
            pstmt.setLong(1, id);
            //c.setAutoCommit(false);
            pstmt.executeUpdate();
            //c.commit();
        }catch(Exception e){
            LOGGER.info("Error al intentar eliminar un avion de la base de datos: " + e);
            try{
                c.rollback();
            }catch(Exception ex){
                LOGGER.info("Error al intentar rollback luego del error al intentar" +
                        "de eliminar un avion de la base de datos: " + ex);
            }
        }finally{
            try{
                c.close();
            }catch (Exception exc){
                LOGGER.info("Error al intentar cerrar la conexión con la base de datos" +
                        "luego de eliminar un avion de la misma: " + exc);
            }
        }
    }
}
