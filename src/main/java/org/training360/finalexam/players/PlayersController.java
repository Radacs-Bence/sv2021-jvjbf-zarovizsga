package org.training360.finalexam.players;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayersController {

    private PlayersService playersService;

    public PlayersController(PlayersService playersService) {
        this.playersService = playersService;
    }


    @GetMapping
    public List<PlayerDTO> listAllPlayers(){
        return playersService.listAllPlayers();
    }

    @PostMapping
    public PlayerDTO savePlayer(@RequestBody CreatePlayerCommand command){
        return playersService.savePlayer(command);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id){
        playersService.deletePlayer(id);
    }
}
