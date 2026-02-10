package com.example.sistemavendas.controller;

import com.example.sistemavendas.dao.ProdutoDAO;
import com.example.sistemavendas.model.Produto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProdutosController {

    @FXML private TextField txtNome;
    @FXML private TextField txtPreco;

    @FXML private TableView <Produto> tabelaProdutos;
    @FXML private TableColumn <Produto, Integer> colId;
    @FXML private TableColumn <Produto, String> colNome;
    @FXML private TableColumn <Produto, Double> colPreco;

    private ProdutoDAO dao = new ProdutoDAO();

    private Produto produtoSelecionado;

    @FXML
    public void initialize(){
        colId.setCellValueFactory( new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        colPreco.setCellValueFactory( new PropertyValueFactory<>("preco"));
        atualizarTabela();
    }

    public void atualizarTabela(){
        try {
            tabelaProdutos.setItems(FXCollections.observableArrayList(dao.listarTodos()));
        } catch (Exception e) { e.printStackTrace();}
    }

    @FXML
    public void salvarProduto() {
        try {
            if (produtoSelecionado == null) {
                Produto novo = new Produto(
                        txtNome.getText(),
                        Double.parseDouble(txtPreco.getText()));
                dao.salvar(novo);
            } else {
                produtoSelecionado.setNome(txtNome.getText());
                produtoSelecionado.setPreco(Double.parseDouble(txtPreco.getText()));
                dao.atualizar(produtoSelecionado);
            }
            atualizarTabela();
            limparCampos();

        } catch (Exception e) { exibirAlerta( "erro", e.getMessage());}
    }

    @FXML
    public void excluirProdutos(){
        if( produtoSelecionado != null){
            try {
                dao.deletar(produtoSelecionado.getId());
                atualizarTabela();
                limparCampos();
            } catch (Exception e) { exibirAlerta( "erro", e.getMessage());}
        }
    }

    @FXML
    public void selecionarItem(){
        produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if(produtoSelecionado != null){
            txtNome.setText(produtoSelecionado.getNome());
            txtPreco.setText(String.valueOf(produtoSelecionado.getPreco()));
        }
    }

    @FXML
    public void limparCampos(){
        txtNome.clear();
        txtPreco.clear();
        produtoSelecionado = null;
        tabelaProdutos.getSelectionModel().clearSelection();
    }

    private void exibirAlerta(String titulo, String msg){
        Alert alert =new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.show();
    }
}