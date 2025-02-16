package fun.felipe.trabalhobanco.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;

    private float peso;

    private float tamanho;

    private long idCliente;

    private long idEntregador;

    public PedidoEntity() {
    }

    public PedidoEntity(String descricao, float peso, float tamanho, long idCliente, long idEntregador) {
        this.descricao = descricao;
        this.peso = peso;
        this.tamanho = tamanho;
        this.idCliente = idCliente;
        this.idEntregador = idEntregador;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(long idEntregador) {
        this.idEntregador = idEntregador;
    }
}
