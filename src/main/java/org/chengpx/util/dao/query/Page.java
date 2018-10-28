package org.chengpx.util.dao.query;

import java.util.List;

/**
 * 分页对象
 *
 * @author chengpx
 * @date 2018/4/4 8:37
 */
public class Page<T> {

    /**
     * 总记录数
     */
    private Integer totalRecord;
    /**
     * 当前页
     */
    private Integer pageNO;
    /**
     * 总页数(不提供 setter)
     */
    private Integer totalPage;
    /**
     * 页数据集合
     */
    private List<T> tList;
    /**
     * 页数据个数
     */
    private Integer tCount;
    /**
     * 页数据最大个数
     */
    private Integer maxResults;

    public Integer getTotalPage() {
        Integer totalRecord = getTotalRecord();
        Integer maxResults = getMaxResults();
        int i = totalRecord / maxResults;
        return totalRecord % maxResults == 0 ? i : i + 1;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public Integer getPageNO() {
        return pageNO;
    }

    public void setPageNO(Integer pageNO) {
        this.pageNO = pageNO;
    }

    public List<T> gettList() {
        return tList;
    }

    public void settList(List<T> tList) {
        this.tList = tList;
    }

    public Integer gettCount() {
        return tCount;
    }

    public void settCount(Integer tCount) {
        this.tCount = tCount;
    }

}