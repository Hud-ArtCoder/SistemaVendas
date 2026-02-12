package com.example.sistemavendas.dao;

import com.example.sistemavendas.model.NovaVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NovaVendaDAO {
    private Connection connection;

    public NovaVendaDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public void salvar(NovaVenda novavenda) throws SQLException {
        String sql = "INSERT INTO novavenda (nome,produto,quantidade,valorunitario,valortotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novavenda.getNome());
            stmt.setString(2, novavenda.getProduto());
            stmt.setInt(3, novavenda.getquantidade());
            stmt.setDouble(4, novavenda.getvalorUnitario());
            stmt.setDouble(5, novavenda.getvalorTotal());
            stmt.execute();

        }
    }

    public List<NovaVenda> listarTodos() throws SQLException {
        List<NovaVenda> novavenda = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                NovaVenda nv = new NovaVenda();
                nv.setProduto(rs.getString("produto"));
                nv.setNome(rs.getString("nome"));
                nv.setEmail(rs.getInt("quantidade"));
                nv.setTelefone(rs.getDouble("valorunitario"));
                nv.setEndereco(rs.getDouble("valortotal"));

                novavenda.add(nv);
            }
        }
        return novavenda;
    }

    public void atualizar (NovaVenda novavenda) throws SQLException {
        String sql = " UPDATE novavenda SET nome = ?, produto = ?, quantidade = ?, valorunitario = ?, valortotal = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, novavenda.getNome());
            stmt.setString(2, novavenda.getProduto());
            stmt.setInt(3, novavenda.getquantidade());
            stmt.setDouble(4, novavenda.getvalorUnitario());
            stmt.setDouble(5, novavenda.getvalorTotal());
            stmt.execute();

        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM novavenda WHERE id = ?";
        try (  Connection conn = ConnectionFactory.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao Excluir Nova Venda: " + e.getMessage());
        }
    }

}
