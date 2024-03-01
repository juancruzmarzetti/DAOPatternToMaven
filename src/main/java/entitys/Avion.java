package entitys;
import java.time.LocalDate;
public class Avion {
    private Long id;
    private String marca;
    private String modelo;
    private Integer matricula;
    private LocalDate fechaDeEntradaServicio;
    public Avion(){};
    public Avion(Long id, String marca, String modelo, Integer matricula, LocalDate fechaDeEntradaServicio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.fechaDeEntradaServicio = fechaDeEntradaServicio;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public Integer getMatricula() {
        return matricula;
    }
    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
    public LocalDate getFechaDeEntradaServicio() {
        return fechaDeEntradaServicio;
    }
    public void setFechaDeEntradaServicio(LocalDate fechaDeEntradaServicio) {
        this.fechaDeEntradaServicio = fechaDeEntradaServicio;
    }
}
