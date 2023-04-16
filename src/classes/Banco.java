package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe Banco
 *
 * @author Lucas Furriel Rodrigues
 */

public class Banco {
    private int numero; //Número do banco
    private String nome; //Nome do banco
    private String cnpj; //CNPJ do banco
    private String endereco; //Endereço do banco
    private Conta contaLogada; //Conta que será realizada as operações
    private List<Agencia> agencias = new ArrayList<>(); //Lista das agências de banco

    /**
     * Método construtor da classe Banco
     *
     * @param numero   Número do banco
     * @param nome     Nome do banco
     * @param cnpj     CNPJ do banco
     * @param endereco Endereço do banco
     */
    public Banco(int numero, String nome, String cnpj, String endereco) {
        this.numero = numero;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    /**
     * @return Número cadastrado do banco
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @return Nome cadastrado do banco
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return CNPJ cadastrado do banco
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @return Endereço cadastrado do banco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @return Conta que está atualmente logada no sistema || null caso não há conta logada
     */
    public Conta getContaLogada() {
        return contaLogada;
    }

    /**
     * @return Lista de agências do banco
     */
    public List<Agencia> getAgencias() {
        return agencias;
    }

    /**
     * Método usado para setar o atributo 'contaLogada'. Usa inicialmente o método buscarAgencia para encontrar a agência
     * correspondente com o parâmetro 'numAgencia' e em seguida buscarConta com os parâmetros 'numConta' e 'senha'
     *
     * @param numAgencia Número da agência
     * @param numConta   Número da conta
     * @param senha      Senha do titular da conta
     */
    public void logarCliente(int numAgencia, int numConta, String senha) {
        Agencia agencia = buscarAgencia(numAgencia);
        if (agencia != null) {
            contaLogada = agencia.buscarConta(numConta, senha);
        } else {
            System.out.println("Agência não encontrada");
        }
    }

    /**
     * Seta o atributo 'contaLogada' para null
     */
    public void deslogarConta() {
        this.contaLogada = null;
    }

    /**
     * Adiciona na lista de agências do objeto Banco uma nova agência criada
     *
     * @param agencia Objeto instânciado de Agencia
     */
    public void cadastrarAgencia(Agencia agencia) {
        agencias.add(agencia);
    }

    /**
     * Instancia um novo objeto de Agencia e adiciona na lista de agências do objeto Banco
     *
     * @param numAgencia Número da nova agência
     * @param nome       Nome da nova agência
     * @param endereco   Endereço da nova agência
     */
    public void cadastrarAgencia(int numAgencia, String nome, String endereco) {
        Agencia agencia = new Agencia(numAgencia, nome, endereco);
        agencias.add(agencia);
    }

    /**
     * Percorre a lista de agências do objeto Banco buscando uma agência que corresponde com o parâmetro 'codigo'
     *
     * @param codigo Numero da agência
     * @return Objeto de Agencia caso encontrado || null caso contrário
     */
    public Agencia buscarAgencia(int codigo) {
        for (Agencia a : agencias) {
            if (a.getCodigo() == codigo)
                return a;
        }
        return null;
    }

    /**
     * Método intermediário para realizar um saque partindo do atributo 'contaLogada'
     *
     * @param valor Valor a ser sacado
     */
    public void realizarSaque(double valor) {
        contaLogada.adicionarExtrato((valor * -1), "SAQUE", contaLogada.getNome());
        contaLogada.sacar(valor);
    }

    /**
     * Método intermediário para realizar um depósito partindo do atributo 'contaLogada'
     *
     * @param valor Valor a ser depositado
     */
    public void realizarDeposito(double valor) {
        contaLogada.adicionarExtrato(valor, "DEPÓSITO", contaLogada.getNome());
        contaLogada.despositar(valor);
    }

    /**
     * Buscamos a agência e conta que irá receber a tranferência, realiza um saque do atributo 'contaLogada' e, caso
     * foi possível realizar o saque, deposita o mesmo valor na conta antes encontrada
     *
     * @param numAgencia Agencia da conta que receberá a transferêcnia
     * @param numConta   Número da conta que receberá a transferêcnia
     */
    public void transferencia(int numAgencia, int numConta) {

        Scanner sc = new Scanner(System.in);

        Agencia agencia = buscarAgencia(numAgencia);
        if (agencia != null) {
            Conta conta = agencia.buscarConta(numConta);
            if (conta != null) {
                System.out.println("Digite o valor a ser transferido para " + conta.getNome());
                System.out.print("Valor: R$");
                double valor = Math.abs(sc.nextDouble());
                if (contaLogada.sacar(valor)) {
                    contaLogada.adicionarExtrato((valor * -1), "TRANFERÊNCIA", conta.getNome()); // Adicionamos extrato na conta que esta TRANSFERINDO
                    conta.adicionarExtrato(valor, "TRANFERÊNCIA", contaLogada.getNome()); // Adicionamos extrato na conta que esta RECEBENDO a transferência
                    conta.despositar(valor);
                } else
                    System.out.println("Saldo insuficiente para transferência");
            } else
                System.out.println("Conta não encontrada");
        } else
            System.out.println("Agência não encontrada");

    }

    /**
     * Busca uma conta partindo do parâmetro cpf e realiza o depósito na mesma caso a operação 'realizarSaque'
     * foi possível partindo de 'contaLoada'
     *
     * @param cpf CPF (chave pix) da conta que irá receber a transferência
     */
    public void pix(String cpf) {
        Scanner sc = new Scanner(System.in);
        Conta c = buscarChavePix(cpf);
        if (c != null) {
            System.out.println("Digite o valor a ser transferido para " + c.getNome());
            System.out.print("Valor: R$");
            double valor = Math.abs(sc.nextDouble());
            if (contaLogada.sacar(valor)) {
                contaLogada.adicionarExtrato((valor * -1), "PIX", c.getNome()); // Adicionamos extrato na conta que esta TRANSFERINDO o pix
                c.adicionarExtrato(valor, "PIX", contaLogada.getNome()); // Adicionamos extrato na conta que esta RECEBENDO o pix
                c.despositar(valor);
            } else
                System.out.println("Saldo insuficiente para transferência");
        }
    }

    /**
     * Busca dentre as agências e contas de Banco uma conta cujo cpf corresponda ao parâmetro passado
     *
     * @param cpf CPF (chave pix)
     * @return Objeto da classe conta caso encontrada || null caso contrário
     */
    private Conta buscarChavePix(String cpf) {
        for (Agencia a : agencias) {
            for (Conta c : a.getContas()) {
                if (c.getCpf().equals(cpf))
                    return c;
            }
        }
        return null;
    }

    /**
     * Passa por toda a lista de extratos da conta e imprime os valores
     */
    public void imprimirExtrato() {
        for (Extrato e : contaLogada.getExtratos()) {
            System.out.println(e.getValor() + "- " + e.getData() + " - " + e.getOperacao() + " - " + e.getNome());
        }
    }
}
