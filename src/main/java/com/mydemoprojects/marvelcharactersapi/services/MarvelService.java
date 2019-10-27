package com.mydemoprojects.marvelcharactersapi.services;

import com.mydemoprojects.marvelcharactersapi.models.Character;
import com.mydemoprojects.marvelcharactersapi.models.CharacterDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class MarvelService {

    @Value("${marvel.comics.api.public-key}")
    private String apiPublicKey;

    @Value("${marvel.comics.api.private-key}")
    private String apiPrivateKey;

    @Value("${marvel.comics.api.base.url}")
    private String baseUrl;

    private TranslationService translationService;

    public ArrayList<Character> getCharacterList() {
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<Character> characters = new ArrayList<>();

        int count = 0;
        int total;

        do {
            ResponseEntity<CharacterDataWrapper> response = restTemplate.exchange(
                    buildUrl("/characters", 100, count),
                    HttpMethod.GET,
                    buildHeaders(),
                    new ParameterizedTypeReference<CharacterDataWrapper>() {});
            count += response.getBody().getData().getCount();
            total = response.getBody().getData().getTotal();
            characters.addAll(response.getBody().getData().getResults());
            System.out.println("Loading Character " + count + " of " + total + "...");
        } while (count < total);

        return characters;
    }

    public Character getCharacterById(int id, String language) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CharacterDataWrapper> response = restTemplate.exchange(
                buildUrl("/characters/" + id, 1, 0),
                HttpMethod.GET,
                buildHeaders(),
                new ParameterizedTypeReference<CharacterDataWrapper>() {});

        Character character = response.getBody().getData().getResults().get(0);

        if (language != null && !character.getDescription().isEmpty()) {
            String translatedDescription = translationService.translate(language, character.getDescription());
            if (translatedDescription != null) {
                character.setDescription(translatedDescription);
            }
        }
        return character;
    }

    private String buildUrl(String path, int limit, int offset) {
        String ts = String.valueOf(new Date().getTime());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(path)
                .queryParam("ts", ts)
                .queryParam("apikey", apiPublicKey)
                .queryParam("hash", createHash(ts))
                .queryParam("offset", offset);

        if (limit > 0) {
            builder.queryParam("limit", limit);
        }

        return builder.toUriString();
    }

    private HttpEntity<?> buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<>(headers);
    }

    private String createHash(String ts) {
        String toBeHashed = ts + apiPrivateKey + apiPublicKey;

        byte[] bytesOfMessage;
        MessageDigest md;
        try {
            bytesOfMessage = toBeHashed.getBytes("UTF-8");
            md = MessageDigest.getInstance("MD5");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Invalid Hash");
        }

        byte[] hash = md.digest(bytesOfMessage);
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Autowired
    public void setTranslationService(TranslationService translationService) {
        this.translationService = translationService;
    }

}
