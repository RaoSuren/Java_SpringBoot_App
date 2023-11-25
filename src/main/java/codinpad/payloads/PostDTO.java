package codinpad.payloads;

import java.util.Date;

import codinpad.models.Category;
import codinpad.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDTO category;

    private UserDTO user;



}