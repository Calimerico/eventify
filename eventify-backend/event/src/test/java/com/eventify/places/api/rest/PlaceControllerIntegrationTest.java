package com.eventify.places.api.rest;

import com.eventify.events.domain.Event;
import com.eventify.place.api.rest.CreatePlaceRequest;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration
@Commit//todo read this https://stackoverflow.com/questions/43519761/replacement-of-transactionconfiguration
@Transactional
public class PlaceControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlaceRepository placeRepository;

    @After
    public void tearDown() {
        placeRepository.deleteAll();
    }

    @Test
    public void insertPlaceTest() throws Exception {
        //given
        CreatePlaceRequest createPlaceRequest = new CreatePlaceRequest();
        createPlaceRequest.setCity("Beograd");
        createPlaceRequest.setLatitude(44.8153216);
        createPlaceRequest.setLongitude(44.8253216);
        createPlaceRequest.setName("Beogradska arena");

        //when
        this.mvc.perform(post("/places")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createPlaceRequest)));

        //then
        List<Place> places = placeRepository.findByCity("Beograd");
        Assertions.assertThat(places).isNotNull();
        Assertions.assertThat(places.size()).isEqualTo(1);
        Assertions.assertThat(places.get(0).getLatitude()).isEqualTo(44.8153216);
        Assertions.assertThat(places.get(0).getLongitude()).isEqualTo(44.8253216);
        Assertions.assertThat(places.get(0).getNames()).contains("Beogradska arena");
    }

    @Test
    public void getPlacesTest() throws Exception {
        //given
        Place place = new Place();
        place.setCity("Novi sad");
        HashSet<String> names = new HashSet<>();
        names.add("Stadion Karadjordje");
        place.setLongitude(45.8153216);
        place.setLatitude(46.8153216);
        place.setNames(names);
        placeRepository.save(place);

        //when
        this.mvc.perform(get("/places")
                .param("city","Novi sad")
                .contentType(APPLICATION_JSON))

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)));
    }
}