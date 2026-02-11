package com.example.sistemavendas.model;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String endereco;

    public Cliente(){};

    public Cliente(String nome, String email, String cpf, String telefone, String endereco){
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;

    }

    public Cliente(int id, String nome, String email, String cpf, String telefone, String endereco){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;

    }


    //  Getter e Setters

    public int getId() {return id;}
    public String getNome(){return nome;}
    public String getCpf() {return cpf;}
    public String getEmail() {return email;}
    public String getTelefone() {return telefone;}
    public String getEndereco() {return endereco;}

    public void setId(int id) {this.id = id;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public void setNome(String nome) {this.nome = nome;}
    public void setEmail(String email) {this.email = email;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
}
