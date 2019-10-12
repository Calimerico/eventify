package com.eventify.place.api.rest;

import com.eventify.place.domain.Place;
import com.eventify.place.domain.PlaceBuilder;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.config.auth.MockKafkaConfig;
import com.eventify.shared.config.auth.TestSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static com.eventify.shared.config.auth.TestSecurityConfig.*;
import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {TestSecurityConfig.class, MockKafkaConfig.class})
@AutoConfigureMockMvc
@Transactional
public class PlaceControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceBuilder placeBuilder;

    @Test
    @WithUserDetails(ADMIN_USER)
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
        HashSet<String> names = new HashSet<>();
        names.add("Stadion Karadjordje");
        Place place = placeBuilder
                .city("Novi sad")
                .longitude(45.8153216)
                .latitude(46.8153216)
                .names(names)
                .build();
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
