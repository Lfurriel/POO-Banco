package classes;

import java.time.LocalDateTime;

/**
 * Classe Extrao
 *
 * @author Lucas Furriel Rodrigues
 */


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

    /**
     * @return Valor do extrato
     */
    public double getValor() {
        return valor;
    }

    /**
     * @return Data que foi realizada a operação
     */
    public LocalDateTime getData() {
        return data;
    }

    /**
     * @return Operação que foi realizada "SAQUE", "DEPOSITO", "TRANSFERÊNCIA", "PIX"
     */
    public String getOperacao() {
        return operacao;
    }

    /**
     * @return Nome para quem foi transferido
     */
    public String getNome() {
        return nome;
    }
}
