package com.co.sgi.utilidades;

public enum NavegationPageURL {

    EMPRESA("Empresa", "/administracion/empresa.xhtml");

    private String page;
    private String url;

    private NavegationPageURL(String page, String url) {
        this.page = page;
        this.url = url;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
