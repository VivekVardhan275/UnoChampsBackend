package rocks.vivek275.unochamps.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rocks.vivek275.unochamps.model.GameWrapper;
import rocks.vivek275.unochamps.model.Games;
import rocks.vivek275.unochamps.model.Seasons;
import rocks.vivek275.unochamps.repo.GamesRepo;
import rocks.vivek275.unochamps.repo.SeasonsRepo;

import java.util.List;

@Service
public class SeasonsService {
    @Autowired
    SeasonsRepo seasonsRepo;
    @Autowired
    GamesService gamesService;
    @Autowired
    private GamesRepo gamesRepo;

    public ResponseEntity<List<Seasons>> getAllSeasons() {
        try{
            List<Seasons> seasons = seasonsRepo.findAll();
            return new ResponseEntity<>(seasons, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<Seasons>> createSeason(Seasons seasons) {
        try {
            seasonsRepo.save(seasons);
            return new ResponseEntity<>(seasonsRepo.findAll(), HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    public ResponseEntity<List<Seasons>> updateSeasons(String before, String after) {
        try {
            // 1. Load the old season from DB (must exist)
            Seasons oldSeason = seasonsRepo.findById(before)
                    .orElseThrow(() -> new RuntimeException("Season not found: " + before));

            // 2. Create and persist the new season
            Seasons newSeason = new Seasons();
            newSeason.setSeasonName(after);
            newSeason = seasonsRepo.save(newSeason); // returns a managed instance

            // 3. Move all games from oldSeason -> newSeason
            List<Games> gamesList = gamesService.getAllGamesOfSeason(oldSeason).getBody();
            if (gamesList != null && !gamesList.isEmpty()) {
                for (Games game : gamesList) {
                    game.setSeason(newSeason); // reassign managed season
                    // save updated game
                    gamesRepo.save(game); // or gamesRepo.save(game)
                }
            }

            // 4. Delete the old season AFTER reassigning games
            seasonsRepo.delete(oldSeason);

            // 5. Return updated list of seasons
            return new ResponseEntity<>(seasonsRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<Seasons>> deleteSeason(Seasons seasons){
        try {
            gamesRepo.removeAllBySeason(seasons);
            seasonsRepo.delete(seasons);
            List<Seasons> seasonList = seasonsRepo.findAll();
            return new ResponseEntity<>(seasonList,HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
