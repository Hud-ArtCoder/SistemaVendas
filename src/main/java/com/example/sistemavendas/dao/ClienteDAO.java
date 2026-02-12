package com.example.sistemavendas.dao;

import com.example.sistemavendas.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public void salvar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome,cpf,telefone,email,endereco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            stmt.execute();

        }
    }

    public Cliente buscarCliente(String nome){
        String sql = "SELECT * FROM cliente WHERE nome = ?";

        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco")

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                Cliente c = new Cliente();
                c.setCpf(rs.getString("cpf"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));

                clientes.add(c);
            }
        }
        return clientes;
    }

    public void atualizar (Cliente cliente) throws SQLException {
        String sql = " UPDATE cliente SET nome = ?, cpf = ?,email = ?,telefone = ?,endereco = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.execute();

        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (  Connection conn = ConnectionFactory.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}
