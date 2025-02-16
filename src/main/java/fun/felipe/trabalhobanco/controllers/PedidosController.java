package fun.felipe.trabalhobanco.controllers;

import fun.felipe.trabalhobanco.data.dto.CreatePedidoDTO;
import fun.felipe.trabalhobanco.models.entities.LogEntity;
import fun.felipe.trabalhobanco.models.entities.PedidoEntity;
import fun.felipe.trabalhobanco.models.repositories.LogsRepository;
import fun.felipe.trabalhobanco.models.repositories.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController()
@RequestMapping("/pedidos")
public class PedidosController {
    private final PedidoRepository pedidoRepository;
    private final LogsRepository logsRepository;

    public PedidosController(PedidoRepository pedidoRepository, LogsRepository logsRepository) {
        this.pedidoRepository = pedidoRepository;
        this.logsRepository = logsRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<PedidoEntity> create(@RequestBody CreatePedidoDTO createPedidoDTO) {
        PedidoEntity pedido = new PedidoEntity(
                createPedidoDTO.descricao(),
                createPedidoDTO.peso(),
                createPedidoDTO.tamanho(),
                createPedidoDTO.idCliente(),
                createPedidoDTO.idEntregador()
        );

        PedidoEntity savedPedido = pedidoRepository.save(pedido);

        for (int i = 0 ; i < 3; i++) {
            LogEntity log = new LogEntity();
            log.setId(i);
            log.setIdCar(i);
            log.setIdOrder(savedPedido.getId());
            log.setTimestamp(LocalDateTime.now().plusHours(1));
            log.setLocation("Esse dado é privado meu amigo por favor evitar ok!");
            this.logsRepository.save(log);
        }

        return ResponseEntity.ok(savedPedido);
    }
}
