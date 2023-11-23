package codinpad.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private Integer postId;

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    @JoinTable(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;
}