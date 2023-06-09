package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe Conta
 *
 * @author Lucas Furriel Rodrigues
 */

public class Conta {

    /**
     * Número da conta
     */
    private final int numero;

    /**
     * Saldo da conta
     */
    private double saldo;

    /**
     * Nome do cliente
     */
    private final String nome;

    /**
     * Nome do cliente
     */
    private final String endereco;

    /**
     * CPF do cliente
     */
    private final String cpf;

    /**
     * Data de nascimento do cliente
     */
    private final String dataNascimento;

    /**
     * Senha para acessar a conta
     */
    private String senha;

    /**
     * Lista de extrato da conta
     */
    private List<Extrato> extratos;

    /**
     * Status da conta
     */
    private boolean bloqueada;

    /**
     * Contagem de tentativas erradas ao entrar na conta
     */
    private int erros;

    /**
     * Método construtor da classe Conta
     *
     * @param numero         Número da conta
     * @param saldo          Saldo da conta
     * @param nome           Nome do cliente
     * @param endereco       Endereço do cliente
     * @param cpf            CPF do cliente
     * @param dataNascimento Data de nascimento do cliente
     * @param senha          Senha da conta
     */
    public Conta(int numero, double saldo, String nome, String endereco, String cpf, String dataNascimento, String senha) {
        this.numero = numero;
        this.saldo = saldo;
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.extratos = new ArrayList<>();
        this.bloqueada = false;
        this.erros = 0;
    }

    /**
     * @return Número cadastrado da conta
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @return Saldo disponível na conta
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @return Nome cadastrado da conta
     */
    public String getNome() {
        return nome;
    }


    /**
     * @return Endereço cadastrado da conta
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @return CPF cadastrado da conta
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @return lista de extratos da conta
     */
    public List<Extrato> getExtratos() {
        return extratos;
    }

    /**
     * @return Data de nascimento cadastrada da conta
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @return Se a conta está bloqueada ou não
     */
    public boolean isBloqueada() {
        return bloqueada;
    }

    /**
     * Quando o usuário acertar a sua senha zeramos a contagem de erros para bloqueio
     */
    public void zeraErros() {
        this.erros = 0;
    }

    /**
     * Adiciona valor ao saldo da conta
     *
     * @param valor Quantia a ser depositada
     */
    public void despositar(double valor) {
        this.saldo += valor;
    }

    /**
     * Verifica se é possível fazer uma retirada de saldo e a efetua caso sim
     *
     * @param valor Quantia a ser sacada
     * @return TRUE caso foi possível realizar o saque || FALSE caso contrário
     */
    public boolean sacar(double valor) {
        if (this.saldo < valor) {
            System.out.println("Não é possivel sacar, saldo insuficiente");
            return false;
        } else {
            this.saldo -= valor;
            return true;
        }
    }

    /**
     * Método utilizado para alterar a senha da conta. Senha só é substituída caso o usuário digite a senha
     * antiga corretamente
     *
     * @param senhaAtual Senha já cadastrada
     * @param novaSenha  Senha para qual será alterada
     */
    public void setSenha(String senhaAtual, String novaSenha) {
        if (validarSenha(senhaAtual))
            this.senha = novaSenha;
        else
            System.out.println("Senha inválida");
    }

    /**
     * Utilizado para confirmar se a senha digitada é a mesma já cadastrada
     *
     * @param senhaComparar Senha digitada
     * @return TRUE caso as senhas batem || FALSE caso a senha digitada esteja errada
     */
    public boolean validarSenha(String senhaComparar) {
        return senhaComparar.equals(this.senha);
    }

    /**
     * Adiciona um novo Extrato a lista de extratos da conta
     *
     * @param valor    Valor da transação
     * @param operacao Operação que foi realizada  "SAQUE", "DEPOSITO", "TRANSFERÊNCIA", "PIX"
     * @param nome     Nome para quem foi realizado a operação
     */
    public void adicionarExtrato(double valor, String operacao, String nome) {
        extratos.add(new Extrato(valor, LocalDateTime.now(), operacao, nome));
    }

    /**
     * Quando a senha é digitada incorretamente aumentamos a quantidade de erros em 1
     * ao atingir 3 erros a conta é bloqueada
     */
    public void senhaIncorreta() {
        this.erros++;
        if (erros == 3)
            this.bloqueada = true;
    }

}
