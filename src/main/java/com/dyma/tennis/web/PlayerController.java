package com.dyma.tennis.web;

import com.dyma.tennis.Error;
import com.dyma.tennis.Player;
import com.dyma.tennis.PlayerToSave;
import com.dyma.tennis.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tennis PLayers API")
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(summary = "Find players", description = "Return a list of players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "PLayers list",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Player.class)))}),
    })
    @GetMapping
    public List<Player> list(){
        return playerService.getAllPlayers();
    }

    @Operation(summary = "Finds a player", description = "Finds a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Player",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Player with specified last name was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))})
    })
    @GetMapping("{lastName}")
    public Player getByLastName(@PathVariable("lastName") String lastName){
        return playerService.getByLastName(lastName);
    }

    @Operation(summary = "Creates a player", description = "Creates a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Created Player",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerToSave.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Player with specified last name already exists",
                    content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Error.class))})
    })
    @PostMapping
    public Player create(@Valid @RequestBody PlayerToSave playerToSave){
        return playerService.create(playerToSave);
    }

    @Operation(summary = "Updates a player", description = "Updates a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated Player",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerToSave.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Player with specified last name was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))})
    })
    @PutMapping
    public Player update(@Valid @RequestBody PlayerToSave playerToSave){
        return playerService.update(playerToSave);
    }

    @Operation(summary = "Deletes a player", description = "Deletes a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player has been deleted"),
            @ApiResponse(responseCode = "404",
                    description = "Player with specified last name was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))})
    })
    @DeleteMapping("{lastName}")
    public void deleteByLastName(@PathVariable("lastName") String lastName){
        playerService.deleteByLastName(lastName);
    }
}
