package fun.felipe.trabalhobanco.models.repositories;

import fun.felipe.trabalhobanco.models.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
