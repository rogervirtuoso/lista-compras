package io.github.rogervirtuoso.listacompras.java.backend.business;

import io.github.rogervirtuoso.listacompras.java.backend.controllers.TotalController;
import io.github.rogervirtuoso.listacompras.java.backend.core.HibernateUtil;
import io.github.rogervirtuoso.listacompras.java.backend.model.Compras;
import io.github.rogervirtuoso.listacompras.java.backend.model.ComprasItem;
import io.github.rogervirtuoso.listacompras.java.backend.model.dto.ComprasDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Roger
 */
public class ComprasBO {

    public Compras inserir(Compras compras) {
        compras.setDataCadastro(new Date());
        compras.setDataAlteracao(new Date());
        compras.setStatus(Compras.Status.NOVO.getCodigo());

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(compras);
        t.commit();
        return compras;
    }

    public Compras alterar(Compras compras) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        compras.setDataAlteracao(new Date());
        compras.setStatus(Compras.Status.ALTERADO.getCodigo());
        session.update(compras);
        transaction.commit();
        return compras;
    }

    public void excluir(long codigo) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Compras compras = selecionar(codigo);

        Transaction t = s.beginTransaction();
        s.delete(compras);
        t.commit();
    }

    public Compras selecionar(long id) {
        return HibernateUtil.getSessionFactory()
                .openSession()
                .get(Compras.class, id);
    }

    public List<ComprasDTO> listar() {
        ArrayList<ComprasDTO> dtoList = new ArrayList<>();

        List<Compras> comprasList = HibernateUtil.getSessionFactory()
                .openSession()
                .createQuery("from Compras order by 1 DESC")
                .list();
        for (Compras compras : comprasList) {
            ComprasDTO comprasDTO = new ComprasDTO();
            compras.setTotalCompras(getTotalCompras(compras.getId()));
            comprasDTO.setCompras(compras);
            dtoList.add(comprasDTO);
        }
        return dtoList;
    }

    public Double getTotalCompras(Long id) {
        try {
            ComprasItemBO comprasItemBO = new ComprasItemBO();
            List<ComprasItem> comprasItemList = comprasItemBO.listar(id);
            return comprasItemList.stream().mapToDouble(list -> list.getQuantidade() * list.getValor()).sum();
        } catch (Exception ex) {
            Logger.getLogger(TotalController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
