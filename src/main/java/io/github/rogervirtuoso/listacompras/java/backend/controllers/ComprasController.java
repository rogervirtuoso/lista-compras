package io.github.rogervirtuoso.listacompras.java.backend.controllers;

import io.github.rogervirtuoso.listacompras.java.backend.business.ComprasBO;
import io.github.rogervirtuoso.listacompras.java.backend.business.ComprasItemBO;
import io.github.rogervirtuoso.listacompras.java.backend.model.Compras;
import io.github.rogervirtuoso.listacompras.java.backend.model.dto.ComprasDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Roger
 */
@Path("compras")
public class ComprasController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<ComprasDTO> listCompras() {
        try {
            ComprasBO comprasBO = new ComprasBO();
            return comprasBO.listar();
        } catch (Exception ex) {
            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public ComprasDTO getCompras(@PathParam("id") long id) {
        try {
            ComprasBO chamadoBus = new ComprasBO();
            Compras compras = chamadoBus.selecionar(id);
            ComprasDTO comprasDTO = new ComprasDTO();
            comprasDTO.setCompras(compras);

            ComprasItemBO comprasItemBO = new ComprasItemBO();
            comprasDTO.setComprasItemList(comprasItemBO.listar(compras.getId()));
            return comprasDTO;
        } catch (Exception ex) {
            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(ComprasDTO comprasDTO) {
        try {
            ComprasBO comprasBO = new ComprasBO();
            comprasDTO.setCompras(comprasBO.inserir(comprasDTO.getCompras()));
            ComprasItemBO comprasItemBO = new ComprasItemBO();
            if (comprasDTO.getComprasItemList() != null && !comprasDTO.getComprasItemList().isEmpty()) {
                comprasItemBO.inserirItemList(comprasDTO.getComprasItemList(), comprasDTO.getCompras());
            }
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(ComprasDTO comprasDTO) {
        try {
            comprasDTO.getCompras().setStatus(Compras.Status.ALTERADO.getCodigo());
            ComprasBO comprasBO = new ComprasBO();
            comprasDTO.setCompras(comprasBO.alterar(comprasDTO.getCompras()));
            ComprasItemBO comprasItemBO = new ComprasItemBO();
            comprasItemBO.excluir(comprasDTO.getCompras());
            comprasItemBO.inserirItemList(comprasDTO.getComprasItemList(), comprasDTO.getCompras());
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        try {
            ComprasItemBO comprasItemBO = new ComprasItemBO();
            comprasItemBO.excluir(id);
            ComprasBO comprasBusBO = new ComprasBO();
            comprasBusBO.excluir(id);

            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

//    @PUT
//    @Path("{id}/")
//    public Response concluir(@PathParam("id") long id) {
//        try {
//            ComprasBO comprasBus = new ComprasBO();
//
//            Compras compras = comprasBus.selecionar(id);
//            compras.setStatus(Compras.Status.NOVO.getCodigo());
//
//            comprasBus.alterar(compras);
//            return Response.status(Response.Status.OK).build();
//        } catch (Exception ex) {
//            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
//            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
//        }
//    }
}
