package rocks.vivek275.unochamps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.vivek275.unochamps.model.Seasons;
import rocks.vivek275.unochamps.service.SeasonsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/seasons")
public class SeasonsController {
    @Autowired
    SeasonsService seasonsService;
    @GetMapping("/get-seasons")
    public ResponseEntity<List<Seasons>> getSeasons() {
        try {
            return seasonsService.getAllSeasons();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/set-season")
    public ResponseEntity<List<Seasons>> setSeason(@RequestParam("season") String season) {
        try {
            Seasons seasons = new Seasons();
            seasons.setSeasonName(season);
            return seasonsService.createSeason(seasons);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update-season")
    public ResponseEntity<List<Seasons>> updateSeason(@RequestParam("current-season") String before, @RequestParam("new-season") String after) {
        try {
            return seasonsService.updateSeasons(before, after);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete-season")
    public ResponseEntity<List<Seasons>> deleteSeason(@RequestParam("season") String season) {
        try {
            Seasons seasons = new Seasons();
            seasons.setSeasonName(season);
            return seasonsService.deleteSeason(seasons);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
