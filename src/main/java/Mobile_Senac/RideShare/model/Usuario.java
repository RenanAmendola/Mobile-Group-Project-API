package Mobile_Senac.RideShare.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String nome;

    @NotNull
    @Size(min = 3, max = 100)
    private String sobrenome;

    @NotNull
    private String telefone ;

    /*@Schema(example = "email@email.com.br")*/
    @NotNull
    @Email
    @Size(min = 3, max = 60)
    private String email;

    @NotNull
    private String tipo ;

    @NotNull
    private long CPF ;

    private String matricula ;

    @Size(min = 5)
    private String foto;

    @NotNull
    @Size(min = 5)
    private String senha;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("idUsuario")
    private List<Usuario_Carro> usuario_Carro;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("idUsuario")
    private List<Usuario_Endereco> usuario_Endereco;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Usuario_Carro> getUsuario_Carro() {
        return usuario_Carro;
    }

    public void setUsuario_Carro(List<Usuario_Carro> usuario_Carro) {
        this.usuario_Carro = usuario_Carro;
    }

    public List<Usuario_Endereco> getUsuario_Endereco() {
        return usuario_Endereco;
    }

    public void setUsuario_Endereco(List<Usuario_Endereco> usuario_Endereco) {
        this.usuario_Endereco = usuario_Endereco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
