package com.mydemoprojects.marvelcharactersapi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class TranslationService {

    @Value("${marvel.comics.api.translation.app.url}")
    private String translationAppUrl;

    public String translate(String language, String text) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(translationAppUrl)
                    .queryParam("q", text)
                    .queryParam("target", language);

            URL url = new URL(builder.toUriString());
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            //TODO handle exception "Translation for the given languages not supported"
            return null;
        }
    }

}
