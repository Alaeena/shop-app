package app.model;


import app.httpDto.RegisterRequest;
import app.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private Role role;

    private String firstName;
    private String lastName;

    private Boolean activated;
    private Boolean tfaEnabled;
    private Boolean disabled;

    @OneToOne(cascade = {MERGE, PERSIST})
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = {MERGE}, fetch = EAGER, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {MERGE, PERSIST})
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {MERGE, PERSIST, DETACH})
    private Set<Comment> comments = new HashSet<>();

    public UserEntity(RegisterRequest registerRequest) {
        this.email = registerRequest.getEmail();
        this.password = registerRequest.getPassword();
        this.role = Role.USER;
        this.firstName = registerRequest.getFirstName();
        this.lastName = registerRequest.getLastName();

        this.activated = false;
        this.tfaEnabled = false;
        this.disabled = false;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public String getUsername() {
        return email;
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
}
