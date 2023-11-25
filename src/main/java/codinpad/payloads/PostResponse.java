package codinpad.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse
{
    private List<PostDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    private boolean lastPage;

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalElement(long totalElements) {
          this.totalElements = totalElements;
    }

    public void setTotalPages(int totalPages) {
          this.totalPages = totalPages;
    }

    public void setLastPage(boolean lastPage) {
          this.lastPage = lastPage;
    }

    public void setContent(List<PostDTO> postDtos) {
        this.content = postDtos;
    }
}