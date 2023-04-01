package classes;

import java.util.Scanner;

public class DisplayBanco {
    private final Banco meuBanco;

    public DisplayBanco(int numero, String nome, String cnpj, String endereco) {
        this.meuBanco = new Banco(numero, nome, cnpj, endereco);
    }

    public Banco getMeuBanco() {
        return meuBanco;
    }

    public void login() {
        clearScreen();

        System.out.println("******* BEM-VINDE AO BANQUE UNESP *******\n");

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

    private void telaUsuario() {
        Scanner sc = new Scanner(System.in);
        int op;

        do {

            System.out.println("\n\t1- Consultar saldo");
            System.out.println("\t2- Sacar");
            System.out.println("\t3- Depositar");
            System.out.println("\t4- Transferência");
            System.out.println("\t5- Pix");
            System.out.println("\t6- SAIR");

            op = sc.nextInt();

            if (op < 1 || op > 5)
                System.out.println("OPÇÃO INVÁLIDA");

            switch (op) {
                case 1 -> operacaoSaldo();
                case 2 -> operacaoSaque();
                case 3 -> operacaoDeposito();
                case 4 -> operacaoTransferencia();
                case 5 -> operacaoPix();
                case 6 -> operacaoSair();
            }
        } while (op != 6);

    }

    private void operacaoSaldo() {
        System.out.print("Seu saldo: R$" + meuBanco.getContaLogada().getSaldo());
    }

    private void operacaoSaque() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o valor a ser sacado: ");
        meuBanco.realizarSaque(sc.nextDouble());
    }

    private void operacaoDeposito() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o valor a ser depositado: ");
        meuBanco.realizarDeposito(sc.nextDouble());
    }

    private void operacaoTransferencia() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o numero da agência: ");
        int numAgencia = sc.nextInt();
        System.out.print("Digite o numero da conta: ");
        int numConta = sc.nextInt();

        meuBanco.transferencia(numAgencia, numConta);

    }

    private void operacaoPix() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a chave pix (cpf): ");
        meuBanco.pix(sc.nextLine());
    }

    private void operacaoSair() {
        meuBanco.deslogarConta();
        login();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
