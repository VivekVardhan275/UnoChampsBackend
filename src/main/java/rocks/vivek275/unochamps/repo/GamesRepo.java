package rocks.vivek275.unochamps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rocks.vivek275.unochamps.model.Games;
import rocks.vivek275.unochamps.model.Seasons;

import java.util.List;

public interface GamesRepo extends JpaRepository<Games,String> {
    List<Games> getAllBySeason(Seasons season);

    Games getGamesByGameNameAndSeason(String gameName, Seasons season);

    void deleteAllBySeason(Seasons season);

    void removeAllBySeason(Seasons season);
}
