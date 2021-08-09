package org.training360.finalexam.teams;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.players.CreatePlayerCommand;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;


import javax.validation.Valid;
import java.net.URI;
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
    public TeamDTO saveTeam(@Valid @RequestBody CreateTeamCommand command){
        return teamsService.saveTeam(command);
    }

    @PostMapping("/{id}/players")
    public TeamDTO addNewPlayer(@PathVariable Long id,  @Valid @RequestBody CreatePlayerCommand command){
        return teamsService.addNewPlayer(id, command);
    }

    @PutMapping("/{id}/players")
    public TeamDTO addFreePlayer(@PathVariable Long id, @RequestBody UpdateWithExistingPlayerCommand command){
        return teamsService.addFreePlayer(id, command);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> notEnoughSpaces(IllegalArgumentException e){
        Problem problem = Problem.builder()
                .withType(URI.create("teams/not-found"))
                .withTitle("No team found with id")
                .withStatus(Status.NOT_FOUND)
                .withDetail(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
