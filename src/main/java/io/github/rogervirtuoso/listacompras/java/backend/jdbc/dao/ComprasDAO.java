/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.rogervirtuoso.listacompras.java.backend.jdbc.dao;

import io.github.rogervirtuoso.listacompras.java.backend.model.Compras;
import io.github.rogervirtuoso.listacompras.java.backend.core.ConexaoJDBC;
import io.github.rogervirtuoso.listacompras.java.backend.core.ConexaoPostgresJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Roger
 */
public class ComprasDAO {

    private final ConexaoJDBC conexao;

    public ComprasDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoPostgresJDBC();
    }

    public Long inserir(Compras compras) throws SQLException, ClassNotFoundException {
        Long id = null;
        String sqlQuery = "INSERT INTO compras (descricao, status, dt_cadastro, dt_alteracao) VALUES (?, ?, ?, ?) RETURNING i_compras";

        try {
            PreparedStatement sql = this.conexao.getConnection().prepareStatement(sqlQuery);
            sql.setString(1, compras.getNome());
            sql.setLong(2, compras.getStatus());
            sql.setDate(3, new java.sql.Date(compras.getDataCadastro().getTime()));
            sql.setDate(4, new java.sql.Date(compras.getDataAlteracao().getTime()));

            ResultSet resultSet = sql.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }

            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }

        return id;
    }

    public int alterar(Compras compras) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE compras SET descricao = ?, status = ?, dt_cadastro = ?, dt_alteracao = ? WHERE i_compras = ?";
        int linhasAfetadas = 0;

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, compras.getNome());
            stmt.setInt(2, compras.getStatus().intValue());
            stmt.setDate(3, new java.sql.Date(compras.getDataCadastro().getTime()));
            stmt.setDate(4, new java.sql.Date(compras.getDataCadastro().getTime()));
            stmt.setLong(5, compras.getId());

            linhasAfetadas = stmt.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }

        return linhasAfetadas;
    }

    public int excluir(long codigo) throws SQLException, ClassNotFoundException {
        int linhasAlfetadas = 0;
        String sqlQuery = "DELETE FROM compras WHERE i_compras = ?";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setLong(1, codigo);
            linhasAlfetadas = stmt.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }

        return linhasAlfetadas;
    }

    public Compras selecionar(long id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM compras WHERE i_compras = ?";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return parser(resultSet);
            }
        } catch (SQLException e) {
            throw e;
        }

        return null;
    }

    public List<Compras> listar() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM compras ORDER BY i_compras";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();

            List<Compras> chamados = new ArrayList<>();

            while (rs.next()) {
                chamados.add(parser(rs));
            }

            return chamados;
        } catch (SQLException e) {
            throw e;
        }
    }

    private Compras parser(ResultSet resultSet) throws SQLException {
        Compras compras = new Compras();

        compras.setId(resultSet.getLong("i_compras"));
        compras.setNome(resultSet.getString("nome"));
        compras.setStatus(resultSet.getLong("status"));
        Date dataCadastro = new Date();
        dataCadastro.setTime(resultSet.getDate("dt_cadastro").getTime());

        Date dataAlteracao = new Date();
        dataAlteracao.setTime(resultSet.getDate("dt_alteracao").getTime());

        compras.setDataAlteracao(dataAlteracao);

        return compras;
    }
}
