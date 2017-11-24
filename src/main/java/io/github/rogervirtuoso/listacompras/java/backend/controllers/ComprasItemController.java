package io.github.rogervirtuoso.listacompras.java.backend.controllers;

import io.github.rogervirtuoso.listacompras.java.backend.business.ComprasBO;
import io.github.rogervirtuoso.listacompras.java.backend.model.Compras;
import io.github.rogervirtuoso.listacompras.java.backend.model.dto.ComprasDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Roger
 */
public class ComprasItemController {

    public List<ComprasDTO> listCompras() {
        try {
            ComprasBO comprasBO = new ComprasBO();
            return comprasBO.listar();
        } catch (Exception ex) {
            Logger.getLogger(ComprasItemController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Compras getCompras(long id) {
        try {
            ComprasBO chamadoBus = new ComprasBO();
            return chamadoBus.selecionar(id);
        } catch (Exception ex) {
            Logger.getLogger(ComprasItemController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Response create(Compras compras) {
        try {
            ComprasBO comprasBO = new ComprasBO();
            compras = comprasBO.inserir(compras);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(ComprasItemController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Response update(Compras compras) {
        try {
            compras.setStatus(Compras.Status.ALTERADO.getCodigo());

            ComprasBO comprasBO = new ComprasBO();
            comprasBO.alterar(compras);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(ComprasItemController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Response delete(long id) {
        try {
            ComprasBO comprasBusBO = new ComprasBO();
            comprasBusBO.excluir(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(ComprasItemController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
