package rocks.vivek275.unochamps.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.vivek275.unochamps.model.GameWrapper;
import rocks.vivek275.unochamps.model.Games;
import rocks.vivek275.unochamps.model.Seasons;
import rocks.vivek275.unochamps.service.GamesService;

import java.util.List;

@RestController
@RequestMapping("/api/season/games")
@CrossOrigin
public class GamesController {
    @Autowired
    private GamesService gamesService;
    @GetMapping("/get-games")
    public ResponseEntity<List<Games>> getAllGamesOfSeason(String season) {
        try {
            Seasons seasons = new Seasons();
            seasons.setSeasonName(season);
            return gamesService.getAllGamesOfSeason(seasons);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/set-game")
    public ResponseEntity<List<Games>> setGame(@RequestBody GameWrapper game, @RequestParam("season") String season) {

        try {
            Seasons seasons = new Seasons();
            seasons.setSeasonName(season);
            return gamesService.setGame(game,seasons);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update-game")
    public ResponseEntity<List<Games>> updateGame(@RequestBody GameWrapper game, @RequestParam("season") String season) {
        try{
            Seasons seasons = new Seasons();
            seasons.setSeasonName(season);
            return gamesService.updateGame(game,seasons);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete-game")
    public ResponseEntity<List<Games>> deleteGame(@RequestBody GameWrapper game,@RequestParam("season") String season) {
        try {
            Seasons seasons = new Seasons();
            seasons.setSeasonName(season);
            return gamesService.deleteGame(game,seasons);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
