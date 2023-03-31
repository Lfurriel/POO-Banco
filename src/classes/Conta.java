package classes;

public class Conta {
    private int numero;
    private double saldo;
    private String nome;
    private String endereco;
    private String cpf;
    private String dataNascimento;
    private String senha;

    //Nome # Data Nascimento # Endereço # CPF # Saldo # Núumero da agência # Número da Conta # senha.

    public Conta(int numero, double saldo, String nome, String endereco, String cpf, String dataNascimento, String senha) {
        this.numero = numero;
        this.saldo = saldo;
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void despositar(double valor) {
        this.saldo += valor;
    }

    public boolean sacar (double valor) {
        if(this.saldo < valor) {
            System.out.println("Não é possivel sacar, saldo insuficiente");
            return false;
        } else {
            this.saldo -= valor;
            return true;
        }
    }

    public void setSenha(String senhaAtual, String novaSenha) {
        if(validarSenha(senhaAtual))
            this.senha = novaSenha;
        else
            System.out.println("Senha inválida");
    }

    public boolean validarSenha(String senhaComparar) {
        return senhaComparar.equals(this.senha);
    }
}
