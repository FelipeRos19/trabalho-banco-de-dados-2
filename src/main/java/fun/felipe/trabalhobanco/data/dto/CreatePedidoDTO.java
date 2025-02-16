package fun.felipe.trabalhobanco.data.dto;

public record CreatePedidoDTO(
        String descricao,
        float peso,
        float tamanho,
        long idCliente,
        long idEntregador) {
}
