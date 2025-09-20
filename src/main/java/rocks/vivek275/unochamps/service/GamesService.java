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

import java.util.List;

@Service
public class GamesService {
    @Autowired
    GamesRepo gamesRepo;
    public ResponseEntity<List<Games>> getAllGamesOfSeason(Seasons season) {
        try {
            List<Games> games = gamesRepo.getAllBySeason(season);
            if (games.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(games, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<Games>> setGame(GameWrapper game, Seasons season) {
        try {
            Games newGame = new Games();
            newGame.setGameName(game.getGameName());
            newGame.setSeason(season);
            newGame.setMembers(game.getMembers());
            newGame.setRanks(game.getRanks());
            newGame.setPoints(game.getPoints());
            gamesRepo.save(newGame);
            return new ResponseEntity<>(gamesRepo.getAllBySeason(season), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    public ResponseEntity<List<Games>> updateGame(GameWrapper game, Seasons season) {
        try {
            Games updatedGame = gamesRepo.getGamesByGameNameAndSeason(game.getGameName(), season);
            updatedGame.setGameName(game.getGameName());
            updatedGame.setSeason(season);
            updatedGame.setMembers(game.getMembers());
            updatedGame.setRanks(game.getRanks());
            updatedGame.setPoints(game.getPoints());
            gamesRepo.save(updatedGame);
            return new ResponseEntity<>(gamesRepo.getAllBySeason(season), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    public ResponseEntity<List<Games>> deleteGame(GameWrapper game, Seasons season) {
        try{
                Games gameToBeDeleted = new Games();
                gameToBeDeleted.setGameName(game.getGameName());
                gameToBeDeleted.setMembers(game.getMembers());
                gameToBeDeleted.setRanks(game.getRanks());
                gameToBeDeleted.setPoints(game.getPoints());
                gameToBeDeleted.setSeason(season);
                gamesRepo.delete(gameToBeDeleted);
                return new ResponseEntity<>(gamesRepo.getAllBySeason(season), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
