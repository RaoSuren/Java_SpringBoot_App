package codinpad.payloads;

import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;
    private String name;
    private String email;
    private String password;
    private String about;

    public int getId() {
      return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
          this.email = email;
    }

    public void setAbout(String about) {
           this.about = about;
    }

    public void setPassword(String password) {
         this.password = password;
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
}