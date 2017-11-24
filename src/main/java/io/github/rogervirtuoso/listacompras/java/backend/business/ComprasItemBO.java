package io.github.rogervirtuoso.listacompras.java.backend.business;

import io.github.rogervirtuoso.listacompras.java.backend.model.Compras;
import io.github.rogervirtuoso.listacompras.java.backend.model.ComprasItem;
import io.github.rogervirtuoso.listacompras.java.backend.core.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Roger
 */
public class ComprasItemBO {

    public Long inserirItem(ComprasItem comprasItem, Compras compras) {
        comprasItem.setCompras(compras);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(comprasItem);
        transaction.commit();
        return comprasItem.getId();
    }

    public List<ComprasItem> inserirItemList(List<ComprasItem> comprasItemList, Compras compras) {
        if (comprasItemList == null) {
            return null;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (ComprasItem comprasItem : comprasItemList) {
            comprasItem.setCompras(compras);
            session.save(comprasItem);
        }
        transaction.commit();
        return comprasItemList;
    }

    public void alterar(ComprasItem ComprasItem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(ComprasItem);
        transaction.commit();
    }

    public void excluir(Compras compras) {
        if (compras != null) {
            excluir(compras.getId());
        }
    }

    public void excluir(long comprasId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ComprasItem> comprasItemList = listar(comprasId);
        if (comprasItemList != null) {
            Transaction transaction = session.beginTransaction();
            for (ComprasItem comprasItem : comprasItemList) {
                session.delete(comprasItem);
            }
            transaction.commit();
        }
    }

    public ComprasItem selecionar(long id) {
        return HibernateUtil.getSessionFactory()
                .openSession()
                .get(ComprasItem.class, id);
    }

    public List<ComprasItem> listar(long comprasId) {
        return (List<ComprasItem>) HibernateUtil.getSessionFactory()
                .openSession()
                .createQuery("from ComprasItem where compras = " + comprasId)
                .list();
    }

    public List<ComprasItem> listar() {
        return (List<ComprasItem>) HibernateUtil.getSessionFactory()
                .openSession()
                .createQuery("from ComprasItem ")
                .list();
    }
}
