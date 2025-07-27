package com.dyma.tennis.web;

import com.dyma.tennis.Player;
import com.dyma.tennis.PlayerList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tennis PLayers API")
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Operation(summary = "Find players", description = "Return a list of players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "PLayers list",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Player.class)))}),
    })
    @GetMapping
    public List<Player> list(){
        return PlayerList.ALL;
    }

    @Operation(summary = "Finds a player", description = "Finds a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Player",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("{lastName}")
    public Player getByLastName(@PathVariable("lastName") String lastName){
        return PlayerList.ALL.stream()
                .filter(player -> player.lastName().equals(lastName))
                .findFirst()
                .orElseThrow();
    }

    @Operation(summary = "Creates a player", description = "Creates a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Created Player",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))}),
    })
    @PostMapping
    public Player create(@Valid @RequestBody Player player){
        return player;
    }

    @Operation(summary = "Updates a player", description = "Updates a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated Player",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))}),
    })
    @PutMapping
    public Player update(@Valid @RequestBody Player player){
        return player;
    }

    @Operation(summary = "Deletes a player", description = "Deletes a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Player has been deleted")
    })
    @DeleteMapping("{lastName}")
    public void deleteByLastName(@PathVariable("lastName") String lastName){
    }
}
