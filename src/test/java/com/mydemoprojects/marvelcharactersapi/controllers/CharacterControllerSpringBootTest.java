package com.mydemoprojects.marvelcharactersapi.controllers;

import com.mydemoprojects.marvelcharactersapi.MarvelCharactersApiApplication;
import com.mydemoprojects.marvelcharactersapi.models.Character;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarvelCharactersApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CharacterControllerSpringBootTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testGetCharacterByIdShouldReturnCharacter() {
        Character character = restTemplate.getForObject(getRootUrl() + "/characters/1009144", Character.class);
        assertEquals(character.getId(), 1009144);
    }

    @Test(expected = Exception.class)
    public void testGetNonExistingCharacterByIdShouldThrowException() {
        restTemplate.getForObject(getRootUrl() + "/characters/1", Character.class);
    }

}
