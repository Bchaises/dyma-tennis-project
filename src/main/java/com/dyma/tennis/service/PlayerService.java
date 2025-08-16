package com.dyma.tennis.service;

import com.dyma.tennis.model.Player;
import com.dyma.tennis.model.PlayerToSave;
import com.dyma.tennis.model.Rank;
import com.dyma.tennis.data.PlayerEntity;
import com.dyma.tennis.data.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final Logger log = LoggerFactory.getLogger(PlayerService.class);

    @Autowired
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        log.info("Invoking getAllPlayers");
        try {
            return playerRepository.findAll().stream()
                    .map(playerEntity -> new Player(
                            playerEntity.getFirstName(),
                            playerEntity.getLastName(),
                            playerEntity.getBirthDate(),
                            new Rank(playerEntity.getRank(), playerEntity.getPoints())
                    ))
                    .sorted(Comparator.comparing(player -> player.rank().position()))
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            log.error("Could not retrieve players", e);
            throw new PlayerDataRetrievalException(e);
        }
    }

    public Player getByLastName(String lastName) {
        log.info("Invoking getByLastName with lastName={}", lastName);
        try {
            Optional<PlayerEntity> player = playerRepository.findOneByLastNameIgnoreCase(lastName);
            if (player.isEmpty()) {
                log.warn("Could not find player with lastName={}", lastName);
                throw new PlayerNotFoundException(lastName);
            }

            PlayerEntity playerEntity = player.get();

            return new Player(
                    playerEntity.getFirstName(),
                    playerEntity.getLastName(),
                    playerEntity.getBirthDate(),
                    new Rank(playerEntity.getRank(), playerEntity.getPoints()));
        } catch (DataAccessException e) {
            log.error("Could not find player with lastName={}", lastName, e);
            throw new PlayerDataRetrievalException(e);
        }
    }

    public Player create(PlayerToSave playerToSave) {
        log.info("Invoking create with playerToSave={}", playerToSave);
        try {
            Optional<PlayerEntity> existing = playerRepository.findOneByLastNameIgnoreCase(playerToSave.lastName());
            if (existing.isPresent()) {
                log.warn("Player to create with lastName={} already exists", playerToSave.lastName());
                throw new PlayerAlreadyExistsException(playerToSave.lastName());
            }

            PlayerEntity playerEntity = new PlayerEntity(
                    playerToSave.firstName(),
                    playerToSave.lastName(),
                    playerToSave.birthDate(),
                    playerToSave.points(),
                    999999999
            );

            playerRepository.save(playerEntity);

            RankingCalculator rankingCalculator = new RankingCalculator(playerRepository.findAll());
            List<PlayerEntity> updatedPlayers = rankingCalculator.getNewPlayersRanking();
            playerRepository.saveAll(updatedPlayers);

            return getByLastName(playerToSave.lastName());
        } catch (DataAccessException e) {
            log.error("Could not create player {}", playerToSave, e);
            throw new PlayerDataRetrievalException(e);
        }
    }

    public Player update(PlayerToSave playerToSave) {
        log.info("Invoking update with playerToSave={}", playerToSave);
        try {
            Optional<PlayerEntity> existing = playerRepository.findOneByLastNameIgnoreCase(playerToSave.lastName());
            if (existing.isEmpty()) {
                log.warn("Could not find player with lastName={}", playerToSave.lastName());
                throw new PlayerNotFoundException(playerToSave.lastName());
            }

            PlayerEntity playerToUpdate = existing.get();
            playerToUpdate.setFirstName(playerToSave.firstName());
            playerToUpdate.setBirthDate(playerToSave.birthDate());
            playerToUpdate.setPoints(playerToSave.points());

            playerRepository.save(playerToUpdate);

            RankingCalculator rankingCalculator = new RankingCalculator(playerRepository.findAll());
            List<PlayerEntity> updatedPlayers = rankingCalculator.getNewPlayersRanking();
            playerRepository.saveAll(updatedPlayers);

            return getByLastName(playerToSave.lastName());
        } catch (DataAccessException e) {
            log.error("Could not update player {}", playerToSave, e);
            throw new PlayerDataRetrievalException(e);
        }
    }

    public void deleteByLastName(String lastName) {
        log.info("Invoking deleteByLastName with lastName={}", lastName);
        try {
            Optional<PlayerEntity> playerDelete = playerRepository.findOneByLastNameIgnoreCase(lastName);
            if (playerDelete.isEmpty()) {
                log.warn("Could not find player with lastName={}", lastName);
                throw new PlayerNotFoundException(lastName);
            }

            playerRepository.delete(playerDelete.get());

            RankingCalculator rankingCalculator = new RankingCalculator(playerRepository.findAll());
            List<PlayerEntity> updatedPlayers = rankingCalculator.getNewPlayersRanking();
            playerRepository.saveAll(updatedPlayers);
        } catch (DataAccessException e) {
            log.error("Could not delete player with lastName={}", lastName, e);
            throw new PlayerDataRetrievalException(e);
        }
    }
}
