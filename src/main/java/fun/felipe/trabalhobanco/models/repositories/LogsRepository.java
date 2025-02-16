package fun.felipe.trabalhobanco.models.repositories;

import fun.felipe.trabalhobanco.models.entities.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends MongoRepository<LogEntity, Long> {
}
