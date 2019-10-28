package com.mydemoprojects.marvelcharactersapi.controllers;

import com.mydemoprojects.marvelcharactersapi.services.CharacterService;
import com.mydemoprojects.marvelcharactersapi.models.Character;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

@RestController
@RequestMapping("/characters")
@Api(value="Characters API", description="Operations to retrieve Marvel characters in Characters API")
public class CharacterController {

    private CharacterService characterService;

    @ApiOperation(value = "Get all Marvel character ids in a JSON array of numbers", response = Array.class)
    @GetMapping (produces = { MediaType.APPLICATION_JSON_VALUE })
    public Integer[] getAllCharacters() {
        return characterService.getCharactersIds();
    }

    @ApiOperation(value = "Get a Marvel character: id, name, description, thumbnail given an Id", response = Character.class)
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Character getCharacter(@ApiParam(value = "Character Id to be retrieved", required = true) @PathVariable("id") int id,
                                  @ApiParam(name = "language", value = "Language ISO-639-1 code", type = "query", defaultValue = "en")
                                  @RequestParam(value = "language", required = false ) String language) {
        return characterService.getCharacter(id, language);
    }

    @Autowired
    public void setCharacterService(CharacterService characterService) {
        this.characterService = characterService;
    }
}
