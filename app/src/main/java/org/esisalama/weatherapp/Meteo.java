package org.esisalama.weatherapp;

import java.util.List;

public class Meteo {
    private String Product;
    private String init;
    private List<Dataserie> dataseries;

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    public List<Dataserie> getDataseries() {
        return dataseries;
    }

    public void setDataseries(List<Dataserie> dataseries) {
        this.dataseries = dataseries;
    }
}
