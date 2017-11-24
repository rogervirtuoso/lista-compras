package io.github.rogervirtuoso.listacompras.java.backend.business.validacoes;

import io.github.rogervirtuoso.listacompras.java.backend.model.ComprasItem;

/**
 * @author Roger
 */
public class ValidacoesComprasItem {

    public static String executarValidacoes(ComprasItem comprasItem) {
        if (comprasItem.getDescricaoProduto() == null || comprasItem.getDescricaoProduto().isEmpty()) {
            return "A descrição do produto é obrigatório.";
        }
        if (comprasItem.getQuantidade() == null) {
            StringBuilder sb = new StringBuilder("A Quantidade do produto ");
            sb.append(comprasItem.getDescricaoProduto());
            sb.append(" é obrigatório.");
            return sb.toString();
        }
        if (comprasItem.getQuantidade() <= 0L) {
            StringBuilder sb = new StringBuilder("A Quantidade do produto ");
            sb.append(comprasItem.getDescricaoProduto());
            sb.append(" deve ser maior que 0.");
            return sb.toString();
        }
        if (comprasItem.getValor() != null && comprasItem.getValor() < 0D) {
            StringBuilder sb = new StringBuilder("O Valor do produto ");
            sb.append(comprasItem.getDescricaoProduto());
            sb.append(" deve ser igual ou maior que 0.");
            return sb.toString();
        }
        return null;
    }
}
