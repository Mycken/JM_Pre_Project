package main.java.hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
public class RaU {
    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idrau")
    private String idRaU;

    @Column (name = "user_id")
    private Long idUser;

    @Column (name = "role")
    private String uRole;

    public RaU(Long idUser, String role) {
        this.idUser = idUser;
        this.uRole = role;
    }

    public RaU(Long idUser, String role, String idRaU) {
        this.idUser = idUser;
        this.uRole = role;
        this.idRaU = idRaU;
    }

    public RaU() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getRole() {
        return uRole;
    }

    public void setRole(String role) {
        this.uRole = role;
    }

    @Override
    public String toString() {
        return "RaU{" +
                "idRaU='" + idRaU + '\'' +
                ", idUser=" + idUser +
                ", uRole='" + uRole + '\'' +
                '}';
    }

    public void setIdRau(String s) {
        this.idRaU = s;
    }
}
