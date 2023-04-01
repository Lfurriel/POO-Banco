import classes.Agencia;
import classes.Banco;
import classes.Conta;
import classes.DisplayBanco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DisplayBanco displayBanco = lerBanco();
        imprimir(displayBanco);
    }

    public static DisplayBanco lerBanco() throws Exception {
        DisplayBanco displayBanco = null;
        File arquivo = new File("C:\\Users\\Lucas Furriel\\Downloads\\banco.txt ");
        Scanner scanner;
        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            throw new Exception("sem arquivo banco");
        }

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split("#");

            int numero = Integer.parseInt(campos[1]);
            displayBanco = new DisplayBanco(numero, campos[0], campos[1], campos[2]);
        }

        lerAgencias(displayBanco.getMeuBanco());
        return displayBanco;
    }

    private static void lerAgencias(Banco banco) throws Exception {

        /*  Jardins # 0 # Ladeira de Ribeiro, 27, Araraquara-SP
            NOME    #   NUMERO  #   ENDERECO */

        File arquivo = new File("C:\\Users\\Lucas Furriel\\Downloads\\agencias.txt ");
        Scanner scanner;
        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            throw new Exception("sem arquivo agencias");
        }

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split("#");

            String nome = campos[0];
            int numero = Integer.parseInt(campos[1]);
            String endereco = campos[2];

            banco.cadastrarAgencia(numero, nome, endereco);
        }

        lerContas(banco);
    }

    private static void lerContas(Banco banco) throws Exception {

        /*Juan Peixoto#17/05/1933#Residencial Moraes, 956,Carvalho-AC#66933855163#4338.0#1#46246#190705
         * nome # nascimento # endereco # cpf # saldo # agencia # conta # senha*/

        File arquivo = new File("C:\\Users\\Lucas Furriel\\Downloads\\contas.txt ");
        Scanner scanner;
        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            throw new Exception("sem arquivo contas");
        }

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split("#");

            String nome = campos[0];
            String nascimento = campos[1];
            String endereco = campos[2];
            String cpf = campos[3];
            double saldo = Double.parseDouble(campos[4]);
            int agencia = Integer.parseInt(campos[5]);
            int numConta = Integer.parseInt(campos[6]);
            String senha = campos[7];

            Conta conta = new Conta(numConta, saldo, nome, endereco, cpf, nascimento, senha);
            for (Agencia a : banco.getAgencias()) {
                if (a.getCodigo() == agencia)
                    a.cadastrarConta(conta);
            }
        }
    }

    private static void imprimir(DisplayBanco displayBanco) {

        Banco banco = displayBanco.getMeuBanco();
        System.out.println("***************** " + banco.getNome() + " *****************");
        System.out.println("  Numero: " + banco.getCnpj() + "\tEndereco: " + banco.getEndereco());
        System.out.println("-----------------------------------------------");

        for (Agencia a : banco.getAgencias()) {
            System.out.println("\nAgencia: " + a.getNome() + " " + a.getCodigo());
            System.out.println(a.getEndereco());

            for (Conta c : a.getContas())
                System.out.println(c.getNome() + " | " + c.getCpf() + " | " + c.getEndereco());
        }

    }
}