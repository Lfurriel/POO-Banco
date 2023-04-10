package classes;

import java.util.Scanner;

public class DisplayBanco {
    private final Banco meuBanco;

    /**
     * Método construtor da classe DisplayBanco instanciando um novo obj Banco
     *
     * @param numero   Número do banco
     * @param nome     Nome do banco
     * @param cnpj     CNPJ do banco
     * @param endereco Endereço do banco
     */
    public DisplayBanco(int numero, String nome, String cnpj, String endereco) {
        this.meuBanco = new Banco(numero, nome, cnpj, endereco);
    }

    /**
     * @return Banco cadastrado
     */
    public Banco getMeuBanco() {
        return meuBanco;
    }

    /**
     * Tela inicial do Banco, responsável por ler agencia conta e senha do usuário
     */
    public void login() {
        clearScreen();

        System.out.println("******* BEM-VINDO AO " + meuBanco.getNome() + "*******\n");

        int numAgencia, numConta;
        String senha;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Digite o numero da agência: ");
            numAgencia = sc.nextInt();
            System.out.print("Digite o numero da conta: ");
            numConta = sc.nextInt();
            sc.nextLine();
            System.out.print("Digite a senha da conta: ");
            senha = sc.nextLine();

            meuBanco.logarCliente(numAgencia, numConta, senha);
        } while (meuBanco.getContaLogada() == null);

        telaUsuario();
    }

    /**
     * Tela de usuário, permite escolher dentre as opções a próxima operação a ser feita
     */
    private void telaUsuario() {
        Scanner sc = new Scanner(System.in);
        int op;

        do {

            System.out.println("\n\t1- Consultar saldo");
            System.out.println("\t2- Sacar");
            System.out.println("\t3- Depositar");
            System.out.println("\t4- Transferência");
            System.out.println("\t5- Pix");
            System.out.println("\t6- Consultar extrato");
            System.out.println("\t7- SAIR");

            op = sc.nextInt();

            if (op < 1 || op > 6)
                System.out.println("OPÇÃO INVÁLIDA");

            switch (op) {
                case 1 -> operacaoSaldo();
                case 2 -> operacaoSaque();
                case 3 -> operacaoDeposito();
                case 4 -> operacaoTransferencia();
                case 5 -> operacaoPix();
                case 6 -> operacaoExtrato();
                case 7 -> operacaoSair();
            }
        } while (op != 7);

    }

    /**
     * Opção 1) Imprime na tela o saldo do cliente
     */
    private void operacaoSaldo() {
        System.out.print("Seu saldo: R$" + meuBanco.getContaLogada().getSaldo());
    }

    /**
     * Opção 2) Permite o usuário digitar um valor a ser sacado de sua conta
     */
    private void operacaoSaque() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o valor a ser sacado: ");
        meuBanco.realizarSaque(Math.abs(sc.nextDouble()));
    }

    /**
     * Opção 3) Permite o usuário digitar um valor a ser depositado em sua conta
     */
    private void operacaoDeposito() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o valor a ser depositado: ");
        meuBanco.realizarDeposito(Math.abs(sc.nextDouble()));
    }

    /**
     * Opção 4) Permite o usuário buscar uma conta do banco que receberá uma tranferência
     */
    private void operacaoTransferencia() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o numero da agência: ");
        int numAgencia = Math.abs(sc.nextInt());
        System.out.print("Digite o numero da conta: ");
        int numConta = Math.abs(sc.nextInt());

        meuBanco.transferencia(numAgencia, numConta);

    }

    /**
     * Opção 5) Lê uma chave pix (CPF) do usuário
     */
    private void operacaoPix() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a chave pix (cpf): ");
        meuBanco.pix(sc.nextLine());
    }

    /**
     * Opção 6) Imprime o extrato da conta
     */
    private void operacaoExtrato() {
        meuBanco.imprimirExtrato();
    }

    /**
     * Opção 7) Desloga o cliente de sua conta
     */
    private void operacaoSair() {
        meuBanco.deslogarConta();
        login();
    }

    /**
     * Método útil para limpar o terminal
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
