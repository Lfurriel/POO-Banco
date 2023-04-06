package classes;

import java.time.LocalDateTime;

public class Extrato {
    private double valor; //Valor da transação
    private LocalDateTime data; //Data quando a transação foi realizada
    private String operacao; //Operação realizada
    private String nome; //Nome pra onde foi o dinheiro

    public Extrato(double valor, LocalDateTime data, String operacao, String nome) {
        this.valor = valor;
        this.data = data;
        this.operacao = operacao;
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getOperacao() {
        return operacao;
    }

    public String getNome() {
        return nome;
    }
}
