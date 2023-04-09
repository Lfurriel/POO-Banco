package classes;

import java.time.LocalDateTime;

public class Extrato {
    private final double valor; //Valor da transação
    private final LocalDateTime data; //Data quando a transação foi realizada
    private final String operacao; //Operação realizada
    private final String nome; //Nome pra onde foi o dinheiro

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
