package fun.felipe.trabalhobanco.controllers;

import fun.felipe.trabalhobanco.data.dto.CreatePedidoDTO;
import fun.felipe.trabalhobanco.models.entities.ClienteEntity;
import fun.felipe.trabalhobanco.models.entities.FuncionarioEntity;
import fun.felipe.trabalhobanco.models.entities.LogEntity;
import fun.felipe.trabalhobanco.models.entities.PedidoEntity;
import fun.felipe.trabalhobanco.models.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController()
@RequestMapping("/pedidos")
public class PedidosController {
    private static final Logger log = LoggerFactory.getLogger(PedidosController.class);
    private final PedidoRepository pedidoRepository;
    private final LogsRepository logsRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ConfirmacaoEntregaRepository confirmacaoEntregaRepository;

    private final Map<String, Map<String, String>> status = new HashMap<>();
    private final List<String> endereco = new ArrayList<>();

    public PedidosController(PedidoRepository pedidoRepository, LogsRepository logsRepository, ClienteRepository clienteRepository, FuncionarioRepository funcionarioRepository, ConfirmacaoEntregaRepository confirmacaoEntregaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.logsRepository = logsRepository;
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.confirmacaoEntregaRepository = confirmacaoEntregaRepository;

        status.put("Coleta Feita pela Transportadora", Map.of("Iniciado", "Rua Lojinha do Seu Zé"));
        status.put("Entrando na Unidade Logística", Map.of("Em Processo 2", "Rua da Unidade Logística"));
        status.put("Saindo de Unidade Logística", Map.of("Em Processo 2", "Rua da Unidade Logística"));
        status.put("Chegando a sua Região de Distribuição", Map.of("Em Processo 3", "Rua da Unidade Logística da sua Região"));
        status.put("Em Rota de Entrega", Map.of("Em Processo 4", "Rua Perto da sua Casa"));
        status.put("Entrega Concluída com Sucesso", Map.of("Finalizado", "Rua Obviamente na sua Casa"));

        endereco.add("Rua Lojinha do Seu Zé");
        endereco.add("Rua da Unidade Logística");
        endereco.add("Rua da Unidade Logística");
        endereco.add("Rua da Unidade Logística da sua Região");
        endereco.add("Rua Perto da sua Casa");
        endereco.add("Rua Obviamente na sua Casa\"");
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

        for (int i = 0; i < 6; i++) {
            LogEntity logEntity = new LogEntity();
            logEntity.setId(new Random().nextInt(0, 100000000));
            logEntity.setTimestamp(LocalDateTime.now().plusHours(i));
            logEntity.setLocation(endereco.get(i));
            logEntity.setIdOrder(savedPedido.getId());
            String status = (i != 5) ? "Em processo" : "Finalizado";
            logEntity.setStatus(status);
            logEntity.setIdCar(savedPedido.getFuncionario().getId());
            this.logsRepository.save(logEntity);
        }

        return ResponseEntity.ok(savedPedido);
    }
}
