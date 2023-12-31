package codinpad.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
import org.springframework.security.config.core.userdetails.UserDetailsResourceFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User  implements UserDetails{


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;
  private String email;
  private String password;
  private String about;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Post> posts = new ArrayList<>();

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name="user_role",
  joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),
  inverseJoinColumns = @JoinColumn(name="role", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();

  public void setId(int id ) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getAbout() {
    return about;
  }

  public int getId() {
    return id;
  }

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
  // TODO Auto-generated method stub
    List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
  return authorities;
}

@Override
public String getUsername() {
  // TODO Auto-generated method stub
  return this.email;
}

@Override
public boolean isAccountNonExpired() {
  // TODO Auto-generated method stub
  return true;
}

@Override
public boolean isAccountNonLocked() {
  // TODO Auto-generated method stub
  return true;
}

@Override
public boolean isCredentialsNonExpired() {
  // TODO Auto-generated method stub
  return true;
}

@Override
public boolean isEnabled() {
  // TODO Auto-generated method stub
  return true;
}
}