package com.mydemoprojects.marvelcharactersapi.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CharacterControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterController characterController;

    @Test
    public void getAllCharacters() throws Exception {
        Integer[] characters = {1, 2, 3, 4, 5};
        given(characterController.getAllCharacters()).willReturn(characters);

        mockMvc.perform(get("/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json("[1,2,3,4,5]"));
    }

}
