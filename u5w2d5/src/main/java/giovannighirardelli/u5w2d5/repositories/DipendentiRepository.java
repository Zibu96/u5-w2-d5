package giovannighirardelli.u5w2d5.repositories;

import giovannighirardelli.u5w2d5.entities.Dipendenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DipendentiRepository extends JpaRepository<Dipendenti, UUID> {

boolean existsByEmail(String email);
boolean existsByUsername(String username);

}
