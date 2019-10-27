package com.mydemoprojects.marvelcharactersapi.services;

import com.mydemoprojects.marvelcharactersapi.models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@CacheConfig(cacheNames={"characters"})
public class CharacterService {

    private MarvelService marvelService;

    @Cacheable()
    public Integer[] getCharactersIds() {
        return marvelService.getCharacterList().stream()
                .map(Character::getId)
                .toArray(Integer[]::new);
    }

    public Character getCharacter(int id, String language) {
        return marvelService.getCharacterById(id, language);
    }

    @CachePut(value = "characters")
    @Scheduled(fixedDelayString = "${marvel.comics.api.characters.cache.flush:3600}000")
    public Integer[] updateCharactersCache() {
        System.out.println("Updating Characters cache...");
        return getCharactersIds();
    }

    @Autowired
    public void setMarvelService(MarvelService marvelService) {
        this.marvelService = marvelService;
    }
}
