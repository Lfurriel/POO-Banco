package classes;

import java.util.ArrayList;
import java.util.List;

public class Agencia {
    private int codigo;
    private String nome;
    private String endereco;
    private List<Conta> contas = new ArrayList<>();

    public Agencia(int codigo, String nome, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public List<Conta> getContas() {
        return contas;
    }

    public void cadastrarConta(Conta conta) {
        contas.add(conta);
    }

    /**
     * Método usado para buscar uma conta específica dentre a lista de contas no objeto agência instanciado
     * @param numConta numero da conta a ser procurada
     * @return Objeto Conta caso encontrada | null caso não econtre
     */
    public Conta buscarConta(int numConta) {
        for (Conta c : contas) {
            if(c.getNumero() == numConta)
                return c;

        }
        return null;
    }

    /**
     * Sobrecarga do método buscar conta, dessa vez recebe também como parametro uma String 'senha', esse método será
     * usado para 'logar' uma conta no banco
     * @param numConta numero da conta a ser procurada
     * @param senha senha da conta a ser procurada
     * @return Objeto Conta caso encontrada | null caso não econtre
     */
    public Conta buscarConta(int numConta, String senha) {
        for (Conta c : contas) {
            if(c.getNumero() == numConta && c.validarSenha(senha))
                return c;

        }
        return null;
    }
}
