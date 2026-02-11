package com.example.sistemavendas.controller;

import com.example.sistemavendas.dao.ClienteDAO;
import com.example.sistemavendas.model.Cliente;
import com.example.sistemavendas.model.Produto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClienteController {

    @FXML private TextField txtNome;
    @FXML private TextField txtCPF;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtEndereco;

    @FXML private TableView<Cliente> tabelaCliente;

    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colCPF;
    @FXML private TableColumn <Cliente, String> colNome;
    @FXML private TableColumn <Cliente, String> colTelefone;
    @FXML private TableColumn <Cliente, String> colEmail;
    @FXML private TableColumn <Cliente, String> colEndereco;

    private final ClienteDAO dao = new ClienteDAO();

    private Cliente clienteSelecionado;

    @FXML
    public void initialize(){
        colId.setCellValueFactory( new PropertyValueFactory<>("id"));
        colCPF.setCellValueFactory( new PropertyValueFactory<>("cpf"));
        colNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory( new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory( new PropertyValueFactory<>("email"));
        colEndereco.setCellValueFactory( new PropertyValueFactory<>("endereco"));
        atualizarTabela();
    }

    public void atualizarTabela(){
        try {
            tabelaCliente.setItems(FXCollections.observableArrayList(dao.listarTodos()));
        } catch (Exception e) { e.printStackTrace();}
    }

    @FXML
    public void salvarCliente() {
        try {
            if (clienteSelecionado == null) {
                Cliente novo = new Cliente(
                        txtNome.getText(),
                        txtCPF.getText(),
                        txtTelefone.getText(),
                        txtEmail.getText(),
                        txtEndereco.getText()
                );
                dao.salvar(novo);
            } else {
                clienteSelecionado.setNome(txtNome.getText());
                clienteSelecionado.setCpf(txtCPF.getText());
                clienteSelecionado.setTelefone(txtTelefone.getText());
                clienteSelecionado.setEmail(txtEmail.getText());
                clienteSelecionado.setEndereco(txtEndereco.getText());
                dao.atualizar(clienteSelecionado);
            }
            atualizarTabela();
            limparCampos();

        } catch (Exception e) { exibirAlerta( "erro", e.getMessage());}
    }

    @FXML
    public void excluirCliente(){
        if( clienteSelecionado != null){
            try {
                dao.deletar(clienteSelecionado.getId());
                atualizarTabela();
                limparCampos();
            } catch (Exception e) { exibirAlerta( "erro", e.getMessage());}
        }
    }

    @FXML
    public void selecionarItem(){
        clienteSelecionado = tabelaCliente.getSelectionModel().getSelectedItem();
        if(clienteSelecionado != null){
            txtNome.setText(clienteSelecionado.getNome());
            txtCPF.setText(clienteSelecionado.getCpf());
        }
    }

    @FXML
    public void limparCampos(){
        txtNome.clear();
        txtEndereco.clear();
        txtCPF.clear();
        txtEmail.clear();
        txtTelefone.clear();
        clienteSelecionado = null;
        tabelaCliente.getSelectionModel().clearSelection();
    }

    private void exibirAlerta(String titulo, String msg){
        Alert alert =new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.show();
    }
}
