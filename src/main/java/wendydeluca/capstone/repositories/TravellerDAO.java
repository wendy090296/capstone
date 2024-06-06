package wendydeluca.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wendydeluca.capstone.entities.Traveller;
import wendydeluca.capstone.entities.User;

import java.util.UUID;

@Repository
public interface TravellerDAO extends JpaRepository<Traveller, UUID> {
    public Traveller findByUser(User user);
}
