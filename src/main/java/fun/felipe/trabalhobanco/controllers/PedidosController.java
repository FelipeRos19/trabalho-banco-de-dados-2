package fun.felipe.trabalhobanco.controllers;

import fun.felipe.trabalhobanco.data.dto.CreatePedidoDTO;
import fun.felipe.trabalhobanco.models.entities.ClienteEntity;
import fun.felipe.trabalhobanco.models.entities.FuncionarioEntity;
import fun.felipe.trabalhobanco.models.entities.LogEntity;
import fun.felipe.trabalhobanco.models.entities.PedidoEntity;
import fun.felipe.trabalhobanco.models.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/pedidos")
public class PedidosController {
    private final PedidoRepository pedidoRepository;
    private final LogsRepository logsRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ConfirmacaoEntregaRepository confirmacaoEntregaRepository;

    private final Map<String, String> status = new HashMap<>();

    public PedidosController(PedidoRepository pedidoRepository, LogsRepository logsRepository, ClienteRepository clienteRepository, FuncionarioRepository funcionarioRepository, ConfirmacaoEntregaRepository confirmacaoEntregaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.logsRepository = logsRepository;
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.confirmacaoEntregaRepository = confirmacaoEntregaRepository;

        status.put("Coleta Feita pela Transportadora", "Em Processo");
        status.put("Saindo de Unidade Logística", "Em Processo");
        status.put("Chegando a sua Região de Distribuição", "Em Processo");
        status.put("Em Rota de Entrega", "Em Processo");
        status.put("Entrega Concluída com Sucesso", "Finalizado");
    }

    @PostMapping("/create")
    public ResponseEntity<PedidoEntity> create(@RequestBody CreatePedidoDTO createPedidoDTO) {
        ClienteEntity clienteEntity = this.clienteRepository.findById(createPedidoDTO.idCliente()).get();
        FuncionarioEntity funcionarioEntity = this.funcionarioRepository.findById(createPedidoDTO.idEntregador()).get();

        PedidoEntity pedido = new PedidoEntity();
        pedido.setCliente(clienteEntity);
        pedido.setFuncionario(funcionarioEntity);
        pedido.setDescricao(createPedidoDTO.descricao());
        pedido.setPeso(createPedidoDTO.peso());

        PedidoEntity savedPedido = pedidoRepository.save(pedido);

        LogEntity logEntity = new LogEntity();

        return ResponseEntity.ok(savedPedido);
    }
}
