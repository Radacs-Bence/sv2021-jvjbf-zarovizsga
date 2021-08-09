package org.training360.finalexam.teams;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.finalexam.players.CreatePlayerCommand;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayersRepository;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class TeamsService {

    private ModelMapper modelMapper;

    private TeamsRepository teamsRepository;

    private PlayersRepository playersRepository;

    public TeamsService(ModelMapper modelMapper, TeamsRepository teamsRepository, PlayersRepository playersRepository) {
        this.modelMapper = modelMapper;
        this.teamsRepository = teamsRepository;
        this.playersRepository = playersRepository;
    }

    public List<TeamDTO> listAllTeams() {
        List<Team> teams = teamsRepository.findAll();

        Type targetListType = new TypeToken<List<TeamDTO>>() {
        }.getType();
        return modelMapper.map(teams, targetListType);
    }

    public TeamDTO saveTeam(CreateTeamCommand command) {
        Team team = teamsRepository.save(new Team(command.getName()));

        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO addNewPlayer(Long id, CreatePlayerCommand command) {
        Team team = teamsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found: " + id));

        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition());

        team.addPlayer(player);

        return modelMapper.map(team, TeamDTO.class);
    }

    public TeamDTO addFreePlayer(Long id, UpdateWithExistingPlayerCommand command) {
        Team team = teamsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found: " + id));

        Player player = playersRepository.findById(command.getPlayer_id()).orElseThrow(() -> new IllegalArgumentException("Player not found: " + id));

        if(player.getTeam() != null){
            throw new IllegalArgumentException("Player already has a team");
        }

        if (team.getPlayers().stream().filter(p -> p.getPosition() == player.getPosition()).count() >= 2){
            throw new IllegalArgumentException("Team already has a enough players in that position");
        }

        team.addPlayer(player);

        return modelMapper.map(team, TeamDTO.class);
    }
}
