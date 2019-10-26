package org.project.study.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "`user`")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @JsonProperty
    private String email;

    private String pwd;

    @JsonProperty
    private String image;

    @JsonProperty
    private String name;

    @JsonProperty
    private String nickname;

    @JsonProperty
    private Integer birth;

    @JsonProperty
    private String phone;

    @JsonProperty
    private String gender;

    @Column(name = "admin", insertable = false, updatable = false)
    @JsonProperty
    private Boolean admin;

    @Column(name = "active", insertable = false, updatable = false)
    @JsonProperty
    private Boolean active;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date updatedAt;

    @JsonGetter
    public String getImage() {
        if (this.image == null) {
            return "M".equalsIgnoreCase(this.gender) ? "img/male.jpg" : "img/female.jpg";
        }
        return this.image;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        if (active != null && active) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            if (admin != null && admin) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return active != null && active;
    }

}
