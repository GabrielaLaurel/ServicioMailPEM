package hello.entities.generic;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "web_user")
public class WebUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String username;
    private Integer blocked;

    @ManyToOne
    @JoinColumn
    private WebRole role;

    @OneToMany(mappedBy = "webUser", cascade = CascadeType.ALL)
    private Set<Empleador> empleadores;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    public WebRole getRole() {
        return role;
    }

    public void setRole(WebRole role) {
        this.role = role;
    }

    public Set<Empleador> getEmpleadores() {
        return empleadores;
    }

    public void setEmpleadores(Set<Empleador> empleadores) {
        this.empleadores = empleadores;
    }
}
