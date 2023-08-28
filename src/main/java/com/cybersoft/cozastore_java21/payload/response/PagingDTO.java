package com.cybersoft.cozastore_java21.payload.response;

import java.util.List;

public class PagingDTO {
    List<?> content;
    boolean isLast;
    long numberOfPages;
    long currentPage;
    public PagingDTO(List<?> content, boolean isLast, long numberOfPages, long currentPage) {
        this.content = content;
        this.isLast = isLast;
        this.numberOfPages = numberOfPages;
        this.currentPage = currentPage;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }
}
