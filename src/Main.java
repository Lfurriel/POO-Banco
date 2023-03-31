import classes.Banco;
import classes.DisplayBanco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DisplayBanco displayBanco = lerBanco();
    }

    public static DisplayBanco lerBanco() throws Exception {
        DisplayBanco displayBanco = null;
        File arquivo = new File("C:\\Users\\lfurr\\Downloads\\banco.txt ");
        Scanner scanner;
        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            throw new Exception("sem arquivo");
        }

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split("#");

            for (String valor : campos) {
                System.out.println(valor + " | ");
            }

            int numero = Integer.parseInt(campos[1]);
            displayBanco = new DisplayBanco(numero, campos[0], campos[1], campos[2]);
            System.out.println();
        }

        lerAgencias(displayBanco.getMeuBanco());
        return displayBanco;
    }

    private static void lerAgencias(Banco banco) throws Exception {

        File arquivo = new File("C:\\Users\\lfurr\\Downloads\\agencias.txt ");
        Scanner scanner;
        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            throw new Exception("sem arquivo");
        }

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split("#");

            for (String valor : campos) {
                System.out.println(valor + " | ");
            }

            System.out.println();
        }

    }
}