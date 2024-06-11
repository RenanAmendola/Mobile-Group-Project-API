package Mobile_Senac.RideShare.model;

import java.time.LocalTime;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Rotas")
public class Rotas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
	@JsonIgnoreProperties({"usuario_Endereco", "usuario_Carro"})
    private Usuario idMotorista;

    @NotNull
    @Size(min = 3)
    private String inicio;

    @NotNull
    @Size(min = 3)
    private String destino;

    @Size(min = 3)
    private String parada1;

    @Size(min = 3)
    private String parada2;

    @Size(min = 3)
    private String parada3;

    @Size(min = 3)
    private String parada4;

    @NotNull
    @Column(columnDefinition = "TIME")
    private LocalTime horario;



    public Usuario getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Usuario idMotorista) {
        this.idMotorista = idMotorista;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getParada1() {
        return parada1;
    }

    public void setParada1(String parada1) {
        this.parada1 = parada1;
    }

    public String getParada2() {
        return parada2;
    }

    public void setParada2(String parada2) {
        this.parada2 = parada2;
    }

    public String getParada3() {
        return parada3;
    }

    public void setParada3(String parada3) {
        this.parada3 = parada3;
    }

    public String getParada4() {
        return parada4;
    }

    public void setParada4(String parada4) {
        this.parada4 = parada4;
    }

    public LocalTime  getHorario() {
        return horario;
    }

    public void setHorario(LocalTime  horario) {
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    
    
}
