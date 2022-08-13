package main.java.hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
public class RaU {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idRaU")
    private Long idRaU;

    @Column (name = "user_id")
    private Long idUser;

    @Column (name = "role")
    private String uRole;

    public RaU(Long idUser, String role) {
        this.idUser = idUser;
        this.uRole = role;
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
        return "UR{" +
                "idUser=" + idUser +
                ", role='" + uRole + '}';
    }
}
