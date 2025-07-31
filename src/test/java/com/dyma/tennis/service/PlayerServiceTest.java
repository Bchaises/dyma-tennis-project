package com.dyma.tennis.service;

import com.dyma.tennis.Player;
import com.dyma.tennis.PlayerEntityList;
import com.dyma.tennis.data.PlayerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    private PlayerService playerService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        playerService = new PlayerService(playerRepository);
    }

    @Test
    void shouldReturnPLayersRanking() {
        // Given
        Mockito.when(playerRepository.findAll()).thenReturn(PlayerEntityList.ALL);

        // When
        List<Player> allPlayers = playerService.getAllPlayers();

        // Then
        Assertions.assertThat(allPlayers)
                .extracting("lastName")
                .contains("Nadal", "Djokovic", "Federer", "Murray");
    }

    @Test
    void shouldRetrievePlayer() {
        // Given
        String playerToRetrieve = "Nadal";
        Mockito.when(playerRepository
                .findOneByLastNameIgnoreCase(playerToRetrieve))
                .thenReturn(Optional.of(PlayerEntityList.NADAL_RAFAEL));

        // When
        Player retrievedPlayer = playerService.getByLastName(playerToRetrieve);

        // Then
        Assertions.assertThat(retrievedPlayer.lastName()).isEqualTo("Nadal");
        Assertions.assertThat(retrievedPlayer.firstName()).isEqualTo("Rafael");
        Assertions.assertThat(retrievedPlayer.rank().position()).isEqualTo(1);
    }

    @Test
    public void shouldFailToRetrievePLayer_WhenPLayerDoesNotExist() {
        // Given
        String playerToRetrieve = "doe";
        Mockito.when(playerRepository.findOneByLastNameIgnoreCase(playerToRetrieve))
                .thenReturn(Optional.empty());

        // When / Then
        Exception exception = assertThrows(PlayerNotFoundException.class, () -> {
            playerService.getByLastName(playerToRetrieve);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo("player not found with name " + playerToRetrieve);
    }
}
