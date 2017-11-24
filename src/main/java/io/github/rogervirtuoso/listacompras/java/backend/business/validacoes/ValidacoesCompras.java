package io.github.rogervirtuoso.listacompras.java.backend.business.validacoes;

import io.github.rogervirtuoso.listacompras.java.backend.model.Compras;

public class ValidacoesCompras {

    public static String executarValidacoes(Compras compras) {
        if (compras.getNome() == null || compras.getNome().isEmpty()) {
            return "O nome da lista de compras é obrigatório.";
        }
        if (compras.getDataCadastro() != null && compras.getDataAlteracao() != null
                && compras.getDataCadastro().after(compras.getDataAlteracao())) {
            return "A Data de Cadastro não pode ser maior que a Data de Alteração.";
        }
        return null;
    }
}
