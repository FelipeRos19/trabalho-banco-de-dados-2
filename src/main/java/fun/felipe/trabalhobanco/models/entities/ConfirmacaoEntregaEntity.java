package fun.felipe.trabalhobanco.models.entities;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "confirmacao_entrega")
public class ConfirmacaoEntregaEntity {

    @Id
    private long id;

    private long idPedido;

    private long idFuncionario;

    private byte[] imagemConfirmacao;

    public ConfirmacaoEntregaEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public byte[] getImagemConfirmacao() {
        return imagemConfirmacao;
    }

    public void setImagemConfirmacao(byte[] imagemConfirmacao) {
        this.imagemConfirmacao = imagemConfirmacao;
    }
}
