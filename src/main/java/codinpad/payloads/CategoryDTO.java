package codinpad.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO
{
    private Integer categoryId;

    @NotBlank
    @Size(min = 4, message= "Min size of category title is 4 characters")
    private String categoryTitle;

    @NotBlank
    @Size(MIN = 4, message = "Min size of category title is 10 characters")
    private String categoryDescription;

    public String getCategoryTitle() {
      return categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryTitle(String title)
    {
      this.categoryTitle = title;
    }

    public void setCategoryDescription(String description)
    {
      this.categoryDescription = description;
    }
}