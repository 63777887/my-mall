package club.banyuan.zgMallMgt.dto;

public class UmsResourceCategoryResp {

    /**
     * id : 1
     * createTime : 2020-02-05T02:21:44.000+0000
     * name : 商品模块
     * sort : 0
     */

    private int id;
    private String createTime;
    private String name;
    private int sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
