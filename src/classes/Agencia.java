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

    public Conta buscarConta(int numConta, String senha) {
        for (Conta c : contas) {
            if(c.getNumero() == numConta && c.validarSenha(senha))
                return c;

        }
        return null;
    }

    public Conta buscarConta(int numConta) {
        for (Conta c : contas) {
            if(c.getNumero() == numConta)
                return c;

        }
        return null;
    }
}
