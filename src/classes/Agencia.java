package classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Agência
 *
 * @author Lucas Furriel Rodrigues
 */


public class Agencia {
    private int codigo; //Código da agência
    private String nome; //Nome da agência
    private String endereco; //Endereço da agência
    private List<Conta> contas = new ArrayList<>(); //Lista de contas da agência

    /**
     * Método construtor da classe Agencia
     *
     * @param codigo   Número da agência
     * @param nome     Nome da agência
     * @param endereco Endereço da agência
     */
    public Agencia(int codigo, String nome, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
    }

    /**
     * @return Código cadastrado da agência
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @return Nome cadastrado da agência
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return Endereço cadastrado da agência
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @return Lista de contas cadastradas da agência
     */
    public List<Conta> getContas() {
        return contas;
    }

    /**
     * Recebe uma conta a ser adicionada na lista de contas da agência
     *
     * @param conta Conta a ser adicionada na lista
     */
    public void cadastrarConta(Conta conta) {
        contas.add(conta);
    }

    /**
     * Método usado para buscar uma conta específica dentre a lista de contas no objeto agência instanciado
     *
     * @param numConta numero da conta a ser procurada
     * @return Objeto Conta caso encontrada || null caso não econtre
     */
    public Conta buscarConta(int numConta) {
        for (Conta c : contas) {
            if (c.getNumero() == numConta)
                return c;

        }
        return null;
    }

    /**
     * Sobrecarga do método buscar conta, dessa vez recebe também como parametro uma String 'senha', esse método será
     * usado para 'logar' uma conta no banco
     *
     * @param numConta numero da conta a ser procurada
     * @param senha    senha da conta a ser procurada
     * @return Objeto Conta caso encontrada || null caso não econtre
     */
    public Conta buscarConta(int numConta, String senha) {
        boolean encontrada = false;

        for (Conta c : contas) {
            if (c.getNumero() == numConta) {
                encontrada = true;
                if (c.validarSenha(senha)) {
                    if (!c.isBloqueada()) {
                        c.zeraErros();
                        return c;
                    } else
                        System.out.println("Essa conta está bloqueada, por favor contate o seu gerente");
                } else {
                    System.out.println("SENHA INCORRETA!");
                    c.senhaIncorreta();
                }
            }
        }
        if (!encontrada)
            System.out.println("Conta não encontrada");

        return null;
    }
}
