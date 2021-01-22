package model;

public class Procedimento {
    private int id;
    private String titulo;
    private String descricao;
    private double valor;
    private int comissaoPorcentual;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getComissaoPorcentual() {
        return comissaoPorcentual;
    }

    public void setComissaoPorcentual(int comissaoPorcentual) {
        this.comissaoPorcentual = comissaoPorcentual;
    }
}
