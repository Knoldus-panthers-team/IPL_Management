package com.knoldus.kup.ipl.controllers;

import com.knoldus.kup.ipl.repository.MatchRepository;
import com.knoldus.kup.ipl.services.MatchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MatchController.class)
@Component
public class MatchControllerTest {

    @MockBean
    MatchRepository matchRepository;

//    @Autowired
//    VenueRepository venueRepository;
//
//    @Autowired
//    VenueService venueService;
//
    @Autowired
    MatchService matchService;
//
//    Match match1,match2,match3;
//
//    List<Match> matchesList;
//    List<Venue> venueList;
//    RedirectAttributes redirectAttributes;
    
    protected MockMvc mvc;
    
    @Autowired
    WebApplicationContext webApplicationContext;
    
//    Venue venue1,venue2;
//    Team team1;
//    Team team2;
    
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
//        Country country = new Country(1L,"India");
//        City city1 = new City(1L,"Channai",country);
//        City city2 = new City(1L,"Kolkata",country);
//        venue1 = new Venue(1L,"Kolkata Stadium",city1);
//        venue2 = new Venue(1L,"Firoz Shah Kotla",city1);
//
//        team1 = new Team(1L,"KKR", city1);
//        team2 = new Team(2L,"CSK", city2);
//        match1= new Match(1L,"1/05/2021",venue1,team1,team2);
//        match2 = new Match(2L,"3/05/2021",venue1,team1,team2);
//        match3 = new Match(3L,"4/05/2021",venue1,team1,team2);
//        matchesList = Arrays.asList(match1,match2,match3);
//        venueList = Arrays.asList(venue1,venue2);
//
//        Mockito.when(matchRepository.findAll()).thenReturn(matchesList);
//        redirectAttributes = new RedirectAttributesModelMap();
    }
    
    @Test
    public void saveMatch() {
    }
    
    @Test
    public void showEditForm() {
        String uri = "/test";
//        Mockito.when(matchRepository.findById(2L)).thenReturn(Optional.ofNullable(match2));
//        Mockito.when(venueRepository.findAll()).thenReturn(venueList);
        MvcResult mvcResult = null;
        try {
            mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    
    @Test
    public void matchUpdate() {
    }
    
    @Test
    public void getMatches() {
    }
    
    @Test
    public void list() {
    }
    
    @Test
    public void deleteMatch() {
    }
}