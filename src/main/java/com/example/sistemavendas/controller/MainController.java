package com.example.sistemavendas.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane root;

    @FXML
    public void abrirHome(){
        carregarTela( "Home.fxml");
    }

    @FXML
    public void abrirCliente(){
        carregarTela("Cliente1.fxml");
    }

    @FXML
    public void abrirRelatorio(){ carregarTela("Relatório.fxml");}

    @FXML
    public void abrirProduto(){
        carregarTela("Produtos.fxml");
    }

    private void carregarTela(String fxml) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/" + fxml));
            root.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void abrirajuda(ActionEvent event){
        Alert alert =new Alert( AlertType.INFORMATION);
        alert.setTitle("Sobre o Sistema");
        alert.setHeaderText("Informações do Sistema");
        alert.setContentText(
                "Nome Sistema de Vendas\n" +
                        "Versão 1.0\n" +
                        "Desenvolvedor : Hudson Klisman\n" +
                        "Ano:2026"
        );
        alert.showAndWait();

    }

}
