package io.github.rogervirtuoso.listacompras.java.backend.controllers;

import io.github.rogervirtuoso.listacompras.java.backend.business.ComprasItemBO;
import io.github.rogervirtuoso.listacompras.java.backend.model.ComprasItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Roger
 */
@Path("total")
public class TotalController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Double getTotal() {
        try {
            ComprasItemBO comprasItemBO = new ComprasItemBO();
            List<ComprasItem> comprasItemList = comprasItemBO.listar();
            return comprasItemList.stream().mapToDouble(list -> list.getQuantidade() * list.getValor()).sum();
        } catch (Exception ex) {
            Logger.getLogger(TotalController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
