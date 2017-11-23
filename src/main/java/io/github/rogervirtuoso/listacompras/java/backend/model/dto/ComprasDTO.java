package io.github.rogervirtuoso.listacompras.java.backend.model.dto;

import io.github.rogervirtuoso.listacompras.java.backend.model.Compras;
import io.github.rogervirtuoso.listacompras.java.backend.model.ComprasItem;

import java.io.Serializable;
import java.util.List;

/**
 * @author Roger
 */
public class ComprasDTO implements Serializable {

    private Compras compras;
    private List<ComprasItem> comprasItemList;

    public ComprasDTO() {
    }

    public ComprasDTO(Compras compras, List<ComprasItem> comprasItemList) {
        this.compras = compras;
        this.comprasItemList = comprasItemList;
    }

    public Compras getCompras() {
        return compras;
    }

    public void setCompras(Compras compras) {
        this.compras = compras;
    }

    public List<ComprasItem> getComprasItemList() {
        return comprasItemList;
    }

    public void setComprasItemList(List<ComprasItem> comprasItemList) {
        this.comprasItemList = comprasItemList;
    }
}
