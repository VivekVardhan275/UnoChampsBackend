package rocks.vivek275.unochamps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.vivek275.unochamps.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User getUserByEmail(String email);
}
