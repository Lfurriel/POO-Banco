package classes;

import java.time.LocalDateTime;

/**
 * Classe Extrao
 *
 * @author Lucas Furriel Rodrigues
 */


public class Extrato {

    /**
     * Valor da transação
     */
    private final double valor;

    /**
     * Data quando a transação foi realizada
     */
    private final LocalDateTime data;

    /**
     * Operação realizada
     */
    private final String operacao;

    /**
     * Nome pra onde foi o dinheiro
     */
    private final String nome;

    /**
     * Método construtor da classe Extrato
     *
     * @param valor    Valor da transação
     * @param data     Data da transação
     * @param operacao Operação realizada
     * @param nome     Nome para quem foi transferido o dinheiro
     */
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
