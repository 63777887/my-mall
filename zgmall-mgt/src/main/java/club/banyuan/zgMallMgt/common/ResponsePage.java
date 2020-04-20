package club.banyuan.zgMallMgt.common;

import java.util.List;

public class ResponsePage<T> {


    /**
     * pageNum : 1
     * pageSize : 5
     * totalPage : 1
     * total : 3
     * list : [{"id":1,"name":"商品管理员","description":"只能查看及操作商品","adminCount":0,"createTime":"2020-02-03T08:50:37.000+0000","status":1,"sort":0}]
     */

    private int pageNum;
    private int pageSize;
    private int totalPage;
    private long total;
    private List<T> list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
