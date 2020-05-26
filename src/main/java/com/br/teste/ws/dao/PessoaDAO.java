package com.br.teste.ws.dao;

import com.br.teste.ws.factory.bd.FactoryConnection;
import datamodel.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vitcl
 */
public class PessoaDAO {

    public PessoaDAO() {
    }

    public void inserir(Pessoa pessoa) {
        Connection con = FactoryConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO pessoa (data,sexo,idade,cidade) values (?,?,?,?)");
            ps.setString(1, pessoa.getData());
            ps.setString(2, pessoa.getSexo());
            ps.setString(3, pessoa.getIdade());
            ps.setString(4, pessoa.getCidade());
            ps.execute();
            ps.close();
            con.close();
            System.out.println("Uma pessoa cadastrada com sucesso!!");

        } catch (SQLException e) {
            System.out.println("err..." + e.getMessage());
        }

    }

    public void remover(Long index) {
        Connection con = FactoryConnection.getConnection();
        String sql = "DELETE FROM pessoa WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(index.longValue()));
            ps.execute();
            con.close();
            ps.close();
            System.out.println("Uma pessoa removida com sucesso!!");
        } catch (SQLException ex) {
            System.out.println("err..." + ex.getMessage());
        }

    }

    public void atualizar(Long index, Pessoa pessoa) {
        Connection con = FactoryConnection.getConnection();
        String sql = "UPDATE pessoa SET data = ?, sexo = ?, idade = ?, cidade = ?  WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pessoa.getData());
            ps.setString(2, pessoa.getSexo());
            ps.setString(3, pessoa.getIdade());
            ps.setString(4, pessoa.getCidade());
            ps.setString(5, String.valueOf(index.longValue()));
            ps.execute();
            ps.close();
            con.close();

        } catch (SQLException ex) {
            System.out.println("err.." + ex.getMessage());
        }
    }

    public Pessoa buscarPorIndex(Long index) {

        Connection con = FactoryConnection.getConnection();
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(index.intValue()));
            ResultSet rs = ps.executeQuery();

            Pessoa p = new Pessoa();

            while (rs.next()) {
                p.setId(Long.parseLong(rs.getString("id")));
                p.setData(rs.getString("data"));
                p.setSexo(rs.getString("sexo"));
                p.setIdade(rs.getString("idade"));
                p.setCidade(rs.getString("cidade"));
            }

            return p;

        } catch (SQLException ex) {
            System.out.println("err..." + ex.getMessage());
        }

        return null;
    }

    public List<Pessoa> getPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        Connection con = FactoryConnection.getConnection();
        String sql = "SELECT * FROM pessoa";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa novaPessoa = new Pessoa();
                novaPessoa.setId(Long.parseLong(rs.getString("id")));
                novaPessoa.setData(rs.getString("data"));
                novaPessoa.setSexo(rs.getString("sexo"));
                novaPessoa.setIdade(rs.getString("idade"));
                novaPessoa.setCidade(rs.getString("cidade"));
                pessoas.add(novaPessoa);

            }

            ps.close();
            rs.close();
            con.close();

            return pessoas;

        } catch (SQLException ex) {
            System.out.println("err..." + ex.getMessage());
        }

        return null;
    }
}
