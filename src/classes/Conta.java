package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Conta {
    private final int numero; //Número da conta
    private double saldo; //Saldo da conta
    private final String nome; //Nome do cliente
    private final String endereco; //Endereço do cliente
    private final String cpf; //CPF do cliente
    private final String dataNascimento; //Data de nascimento do cliente
    private String senha; //Senha para acessar a conta

    private List<Extrato> extratos;

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

    public void adicionarExtrato(double valor, String operacao, String nome) {
        extratos.add(new Extrato(valor, LocalDateTime.now(), operacao, nome));
    }

}
