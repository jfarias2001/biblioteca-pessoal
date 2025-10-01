package br.com.bibliotecapessoal.model;


import jakarta.persistence.*;


@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String titulo;
    private String autor;
    private int anoPublicacao;
    private boolean lido;


    // Construtores
    public Livro() {}


    public Livro(String titulo, String autor, int anoPublicacao, boolean lido) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.lido = lido;
    }


    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public boolean isLido() { return lido; }
    public void setLido(boolean lido) { this.lido = lido; }
}