package wendydeluca.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wendydeluca.capstone.entities.Host;
import wendydeluca.capstone.entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HostDAO extends JpaRepository<Host, UUID> {
    public Host findByUser(User user);


}
