package Mobile_Senac.RideShare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuario_Carro")
public class Usuario_Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 3)
    private String Modelo;

    @NotNull
    @Size(min = 2)
    private String Ano_carro;

    @NotNull
    @Size(min = 2)
    private String Cor_carro;

    @NotNull
    @ManyToOne
	@JsonIgnoreProperties({"usuario_Endereco", "usuario_Carro"})
    private Usuario idUsuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getAno_carro() {
        return Ano_carro;
    }

    public void setAno_carro(String ano_carro) {
        Ano_carro = ano_carro;
    }

    public String getCor_carro() {
        return Cor_carro;
    }

    public void setCor_carro(String cor_carro) {
        Cor_carro = cor_carro;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    
}
