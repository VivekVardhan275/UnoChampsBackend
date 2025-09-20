package rocks.vivek275.unochamps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.vivek275.unochamps.model.Seasons;

@Repository
public interface SeasonsRepo extends JpaRepository<Seasons,String> {
    Seasons getSeasonsBySeasonName(String seasonName);
}
