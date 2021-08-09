package org.training360.finalexam.players;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class PlayersService {

    private ModelMapper modelMapper;

    private PlayersRepository playersRepository;

    public PlayersService(ModelMapper modelMapper, PlayersRepository playersRepository) {
        this.modelMapper = modelMapper;
        this.playersRepository = playersRepository;
    }


    public List<PlayerDTO> listAllPlayers() {
        List<Player> players = playersRepository.findAll();

        Type targetListType = new TypeToken<List<PlayerDTO>>() {
        }.getType();
        return modelMapper.map(players, targetListType);
    }


    public PlayerDTO savePlayer(CreatePlayerCommand command) {
        Player player = playersRepository.save(new Player(command.getName(), command.getBirthDate(), command.getPosition()));

        return modelMapper.map(player, PlayerDTO.class);
    }

    public void deletePlayer(Long id) {
        playersRepository.deleteById(id);
    }
}
