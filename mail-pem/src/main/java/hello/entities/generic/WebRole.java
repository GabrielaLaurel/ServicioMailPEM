package hello.entities.generic;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "web_role")
public class WebRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String description;
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<WebUser> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<WebUser> getUsers() {
        return users;
    }

    public void setUsers(Set<WebUser> users) {
        this.users = users;
    }
}
