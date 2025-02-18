package fun.felipe.trabalhobanco.models.repositories;

import fun.felipe.trabalhobanco.models.entities.ConfirmacaoEntregaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmacaoEntregaRepository extends MongoRepository<ConfirmacaoEntregaEntity, Long> {
}
