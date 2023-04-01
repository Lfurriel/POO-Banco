package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {
    private int numero;
    private String nome;
    private String cnpj;
    private String endereco;
    private Conta contaLogada;
    private List<Agencia> agencias = new ArrayList<>();

    public Banco(int numero, String nome, String cnpj, String endereco) {
        this.numero = numero;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Conta getContaLogada() {
        return contaLogada;
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void logarCliente(int numAgencia, int numConta, String senha) {
        Agencia agencia = buscarAgencia(numAgencia);
        if (agencia != null) {
            contaLogada = agencia.buscarConta(numConta, senha);
            if (contaLogada == null) {
                System.out.println("Conta não encontrada ou senha incorreta");
            }
        } else {
            System.out.println("Agência não encontrada");
        }
    }

    public void deslogarConta() {
        this.contaLogada = null;
    }

    public void cadastrarAgencia(Agencia agencia) {
        agencias.add(agencia);
    }

    public void cadastrarAgencia(int numAgencia, String nome, String endereco) {
        Agencia agencia = new Agencia(numAgencia, nome, endereco);
        agencias.add(agencia);
    }

    public Agencia buscarAgencia(int codigo) {
        for (Agencia a : agencias) {
            if (a.getCodigo() == codigo)
                return a;
        }
        return null;
    }

    public boolean realizarSaque(double valor) {
        return contaLogada.sacar(valor);
    }

    public void realizarDeposito(double valor) {
        contaLogada.despositar(valor);
    }

    public void transferencia(int numAgencia, int numConta) {

        Scanner sc = new Scanner(System.in);

        Agencia agencia = buscarAgencia(numAgencia);
        if (agencia != null) {
            Conta conta = agencia.buscarConta(numConta);
            if (conta != null) {
                System.out.println("Digite o valor a ser transferido para " + conta.getNome());
                System.out.print("Valor: R$");
                double valor = sc.nextDouble();
                if (realizarSaque(valor))
                    conta.despositar(valor);
                else
                    System.out.println("Saldo insuficiente para transferência");
            } else
                System.out.println("Conta não encontrada");
        } else
            System.out.println("Agência não encontrada");

    }

    public void pix(String cpf) {
        Scanner sc = new Scanner(System.in);
        Conta c = buscarChavePix(cpf);
        if(c != null) {
            System.out.println("Digite o valor a ser transferido para " + c.getNome());
            System.out.print("Valor: R$");
            double valor = sc.nextDouble();
            if (realizarSaque(valor))
                c.despositar(valor);
            else
                System.out.println("Saldo insuficiente para transferência");
        }
    }

    private Conta buscarChavePix(String cpf) {
        for (Agencia a : agencias) {
            for (Conta c : a.getContas()) {
                if (c.getCpf().equals(cpf))
                    return c;
            }
        }
        return null;
    }
}
