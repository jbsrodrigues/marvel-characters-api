package com.mydemoprojects.marvelcharactersapi.controllers;

import com.mydemoprojects.marvelcharactersapi.services.CharacterService;
import com.mydemoprojects.marvelcharactersapi.models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private CharacterService characterService;

    @GetMapping
    public Integer[] getAllCharacters() {
        return characterService.getCharactersIds();
    }

    @GetMapping("/{id}")
    public Character getCharacter(@PathVariable("id") int id, @QueryParam("language") String language) {
        return characterService.getCharacter(id, language);
    }

    @Autowired
    public void setCharacterService(CharacterService characterService) {
        this.characterService = characterService;
    }
}
