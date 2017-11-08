package entity;

import java.util.ArrayList;
import java.util.List;

public class PageBean {

    private int pageRecord;
    private int totalRecord;
    private int currentPage;
    private int totalPage;
    private int previousPage;
    private int nextPage;
    private List<Reader> readerlist;

    public void setPageRecord(int pageRecord) {
        this.pageRecord = pageRecord;
    }

    public int getPageRecord() {
        return pageRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setReaderlist(List<Reader> readerlist) {
        this.readerlist = readerlist;
    }

    public List<Reader> getReaderlist() {
        return readerlist;
    }

    public int getPreviousPage()
    {
        if((currentPage - 1) < 1)
            return 1;
        else
            return (currentPage - 1);
    }

    public int getNextPage()
    {
        if((currentPage + 1) > totalPage)
            return totalPage;
        else
            return currentPage + 1;
    }
}
