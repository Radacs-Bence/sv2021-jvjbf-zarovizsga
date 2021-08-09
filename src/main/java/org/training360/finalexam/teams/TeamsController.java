package org.training360.finalexam.teams;

import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.players.CreatePlayerCommand;


import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    private TeamsService teamsService;

    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @GetMapping
    public List<TeamDTO> listAllTeams(){
        return teamsService.listAllTeams();
    }

    @PostMapping
    public TeamDTO saveTeam(@RequestBody CreateTeamCommand command){
        return teamsService.saveTeam(command);
    }

    @PostMapping("/{id}/players")
    public TeamDTO addNewPlayer(@PathVariable Long id, @RequestBody CreatePlayerCommand command){
        return teamsService.addNewPlayer(id, command);
    }

    @PutMapping("/{id}/players")
    public TeamDTO addFreePlayer(@PathVariable Long id, @RequestBody UpdateWithExistingPlayerCommand command){
        return teamsService.addFreePlayer(id, command);
    }
}
