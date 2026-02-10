package com.example.sistemavendas.model;

public class Produto {
    private int id;
    private String nome;
    private double preco;

    public Produto(){}

    public Produto(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // Getter e Setters

    public int getId(){ return id;}
    public String getNome(){ return nome;}
    public double getPreco() {return preco;}

    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setPreco(double preco) {this.preco = preco;}
}
