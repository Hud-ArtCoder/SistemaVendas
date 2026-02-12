package com.example.sistemavendas.controller;

import com.example.sistemavendas.dao.ClienteDAO;
import com.example.sistemavendas.dao.NovaVendaDAO;
import com.example.sistemavendas.dao.ProdutoDAO;
import com.example.sistemavendas.model.Cliente;
import com.example.sistemavendas.model.NovaVenda;
import com.example.sistemavendas.model.Produto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NovaVendaController {
    @FXML private ComboBox cbNome;
    @FXML private ComboBox cbProduto;
    @FXML private Spinner<Integer> spquantidade;

    @FXML private TableView<NovaVenda> tabelanovavenda;
    @FXML private TableColumn<NovaVenda, Integer> colId;
    @FXML private TableColumn<NovaVenda, String> colproduto;
    @FXML private TableColumn <NovaVenda, String> colnome;
    @FXML private TableColumn <NovaVenda, Integer> colquantidade;
    @FXML private TableColumn <NovaVenda, Integer> colvalortotal;

    private NovaVendaDAO nvdao = new NovaVendaDAO();
    private ProdutoDAO prodao = new ProdutoDAO();
    private ClienteDAO clidao = new ClienteDAO();

    private NovaVenda novaVendaSelecionado;

    @FXML
    public void initialize(){

        Spinner<Double> spinner = new Spinner<>();
        spinner.setValueFactory(
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.1, 1.0)
        );

        colId.setCellValueFactory( new PropertyValueFactory<>("id"));
        colproduto.setCellValueFactory( new PropertyValueFactory<>("produto"));
        colquantidade.setCellValueFactory( new PropertyValueFactory<>("quantidade"));
        colnome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        colvalortotal.setCellValueFactory( new PropertyValueFactory<>("valortotal"));
        atualizarTabela();
    }

    public void atualizarTabela(){
        try {
            tabelanovavenda.setItems(FXCollections.observableArrayList(nvdao.listarTodos()));
        } catch (Exception e) { e.printStackTrace();}
    }


    @FXML
    public void excluirCliente(){
        if( novaVendaSelecionado != null){
            try {
                nvdao.deletar(novaVendaSelecionado.getId());
                atualizarTabela();
                limparCampos();
            } catch (Exception e) { exibirAlerta( "erro", e.getMessage());}
        }
    }

    @FXML
    public void selecionarItem(){
        NovaVenda item = tabelanovavenda.getSelectionModel().getSelectedItem();
        if(novaVendaSelecionado != null){
            cbNome.getSelectionModel().select(clidao.buscarCliente(item.getNome()));
            cbProduto.getSelectionModel().select(prodao.buscarProduto(item.getProduto()));
            spquantidade.getValueFactory().setValue(item.getquantidade());
        }
    }

    @FXML
    public void finalizarvenda() {
        Cliente cliente =

        if (novaVendaSelecionado == null) {
            NovaVenda novaVenda = new NovaVenda();
                cbNome.getValue(),
                cbProduto.getValue(),
                prodao.salvar(novaVenda);
            } else {
                novaVendaSelecionado.setNome(cbNome.getValue());
                novaVendaSelecionado.setPreco(Double.parseDouble(txtPreco.getText()));
                prodao.atualizar(novaVendaSelecionado);
            }
            atualizarTabela();
            limparCampos();

    }

    @FXML
    public void limparCampos(){
        cbNome.getSelectionModel().clearSelection();
        cbProduto.getSelectionModel().clearSelection();
        spquantidade.getValueFactory().setValue(1);
        novaVendaSelecionado = null;
        tabelanovavenda.getSelectionModel().clearSelection();
    }

    private void exibirAlerta(String titulo, String msg){
        Alert alert =new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.show();
    }

}
