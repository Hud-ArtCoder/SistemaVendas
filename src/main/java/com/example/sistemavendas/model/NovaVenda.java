package com.example.sistemavendas.model;

public class NovaVenda {
    private int id;
    private String nome;
    private String produto;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;

    public NovaVenda(){};

    public NovaVenda(String nome, String produto, int quantidade, double valorUnitario, double valorTotal){
        this.nome = nome;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.valorUnitario = valorUnitario;

    }

    public NovaVenda(int id, String nome, String produto, int quantidade, double valorUnitario, double valorTotal){
        this.id = id;
        this.nome = nome;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.valorUnitario = valorUnitario;

    }


    //  Getter e Setters

    public int getId() {return id;}
    public String getNome(){return nome;}
    public String getProduto() {return produto;}
    public int getquantidade() {return quantidade;}
    public double getvalorUnitario() {return valorUnitario;}
    public double getvalorTotal() {return valorTotal;}

    public void setId(int id) {this.id = id;}
    public void setProduto(String produto) {this.produto = produto;}
    public void setNome(String nome) {this.nome = nome;}
    public void setEmail(int quantidade) {this.quantidade = quantidade;}
    public void setTelefone(double valorUnitario) {this.valorUnitario = valorUnitario;}
    public void setEndereco(double valorTotal) {this.valorTotal = valorTotal;}
}
