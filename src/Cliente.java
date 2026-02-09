import com.google.gson.annotations.JsonAdapter;

import java.util.UUID;

/**
 * Classe de dados que representa os clientes
 */
public class Cliente {

    // Definindo campos
    @JsonAdapter(AdaptadorIdCliente.class)
    private UUID id;
    private String nome;

    @JsonAdapter(AdaptadorCPF.class)
    private String cpf;

    // Definindo construtores
    Cliente(){}

    Cliente(Cliente cliente){

        this.id = cliente.id;
        this.nome = cliente.nome;
        this.cpf = cliente.cpf;
    }


    // Definindo setters
    public void definirID(UUID id) {

        this.id = id;
    }

    public void definirNome(String nome) {

        this.nome = nome;
    }

    public void definirCPF(String cpf) {

        this.cpf = cpf;
    }


    // Definindo getters
    public UUID obterID() {
        return id;
    }

    public String obterNome() {
        return nome;
    }

    public String obterCPF() {
        return cpf;
    }
}
