package yte.intern.project.EventManagementSystem.usecases.managesecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;
import yte.intern.project.EventManagementSystem.usecases.manageevents.entity.Event;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements UserDetails, CredentialsContainer {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @OneToMany
    @JoinColumn(name = "EVENT_ID")
    @JsonIgnoreProperties("applications")
    private Set<Event> events;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_AUTHORITIES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITRY_ID"))
    private Set<Authority> authorities;

    public void addEvent(Event e){
        events.add(e);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        password = null;
    }
}
