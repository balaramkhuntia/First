package myblog6.myblog6.payload;

import lombok.Data;

import java.util.List;
@Data
public class PostResponse {

    private List<PostDto> posts;

    private int pageNo;
    private int pageSize;
    private int totalPage;
    private long totalElement;

    private boolean last;

    public List<PostDto> getPosts() {
        return posts;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public boolean isLast() {
        return last;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
