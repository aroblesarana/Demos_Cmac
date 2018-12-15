package pe.cmacica.labs.labs03.dominio;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente {
    private int id;
    @NotNull
    @Size(min=5,max=50)
    private String nombres;

    @NotNull
    @Size(min=5,max=50)
    private String ape_pat;

    @NotNull
    @Size(min=5,max=50)
    private String ape_mat;

    @NotNull
    @Min(18)
    @Max(100)
    private int edad;


    @Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApe_pat() {
        return ape_pat;
    }

    public void setApe_pat(String ape_pat) {
        this.ape_pat = ape_pat;
    }

    public String getApe_mat() {
        return ape_mat;
    }

    public void setApe_mat(String ape_mat) {
        this.ape_mat = ape_mat;
    }
}
