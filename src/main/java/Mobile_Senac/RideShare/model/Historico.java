package Mobile_Senac.RideShare.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Usuario_Historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
	@JsonIgnoreProperties({"usuario_Endereco", "usuario_Carro"})
    private Usuario idMotorista;

    @NotNull
    @OneToOne
	@JsonIgnoreProperties({"usuario_Endereco", "usuario_Carro"})
    private Usuario idUsuarioPrincipal;

    @OneToOne
	@JsonIgnoreProperties({"usuario_Endereco", "usuario_Carro"})
    private Usuario idpassageiro1;

    @OneToOne
	@JsonIgnoreProperties({"usuario_Endereco", "usuario_Carro"})
    private Usuario idpassageiro2;

    @OneToOne
	@JsonIgnoreProperties({"usuario_Endereco", "usuario_Carro"})
    private Usuario idpassageiro3;

    @NotNull
    private String status;

    @NotNull
    private Timestamp dataHoraCarona;



    public Usuario getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Usuario idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Usuario getidUsuarioPrincipal() {
        return idUsuarioPrincipal;
    }

    public void setidUsuarioPrincipal(Usuario idUsuarioPrincipal) {
        this.idUsuarioPrincipal = idUsuarioPrincipal;
    }

    public Usuario getIdpassageiro1() {
        return idpassageiro1;
    }

    public void setIdpassageiro1(Usuario idpassageiro1) {
        this.idpassageiro1 = idpassageiro1;
    }

    public Usuario getIdpassageiro2() {
        return idpassageiro2;
    }

    public void setIdpassageiro2(Usuario idpassageiro2) {
        this.idpassageiro2 = idpassageiro2;
    }

    public Usuario getIdpassageiro3() {
        return idpassageiro3;
    }

    public void setIdpassageiro3(Usuario idpassageiro3) {
        this.idpassageiro3 = idpassageiro3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDataHoraCarona() {
        return dataHoraCarona;
    }

    public void setDataHoraCarona(Timestamp dataHoraCarona) {
        this.dataHoraCarona = dataHoraCarona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
