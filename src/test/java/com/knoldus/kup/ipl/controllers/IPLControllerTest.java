package com.knoldus.kup.ipl.controllers;

import static org.mockito.Mockito.when;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Player;
import com.knoldus.kup.ipl.models.PointTable;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.services.CityService;
import com.knoldus.kup.ipl.services.CountryService;
import com.knoldus.kup.ipl.services.MatchService;
import com.knoldus.kup.ipl.services.PlayerService;
import com.knoldus.kup.ipl.services.PointService;
import com.knoldus.kup.ipl.services.TeamService;
import com.knoldus.kup.ipl.services.VenueService;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {IPLController.class})
@ExtendWith(SpringExtension.class)
class IPLControllerTest {
    @MockBean
    private CityService cityService;
    
    @MockBean
    private CountryService countryService;
    
    @Autowired
    private IPLController iPLController;
    
    @MockBean
    private MatchService matchService;
    
    @MockBean
    private PlayerService playerService;
    
    @MockBean
    private PointService pointService;
    
    @MockBean
    private TeamService teamService;
    
    @MockBean
    private VenueService venueService;
    
    @Test
    void testGetAdminDashboard() throws Exception {
        Country country = new Country(1L,"India");
        City city = new City(1L, "Kolkata",country);
        Team team = new Team(1L,"KKR",city);
        Team team2 = new Team(1L,"CSK",new City(2L,"Chennai",country));
        when(this.venueService.getAllVenues()).thenReturn(new ArrayList<>());
        when(this.teamService.getAllTeams()).thenReturn(new ArrayList<>());
        when(this.teamService.getNewTeamObject()).thenReturn(team);
        when(this.pointService.getAllTables()).thenReturn(new ArrayList<>());
        Player player = new Player(1L,"Hardik Pandya",team, country,"batting");
        when(this.playerService.getAllPlayers()).thenReturn(new ArrayList<>());
        when(this.playerService.getNewPlayerObject()).thenReturn(player);
        when(this.matchService.getAllMatches()).thenReturn(new ArrayList<>());
        when(this.countryService.getAllCountries()).thenReturn(new ArrayList<>());
        when(this.cityService.getAllCities()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ipl/admin");
        MockMvcBuilders.standaloneSetup(this.iPLController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(10))
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("cities", "countries", "match", "matches",
                                "player", "players", "pointTables", "team", "teams", "venues"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }
    
    @Test
    void testGetDashboard() throws Exception {
        when(this.teamService.getAllTeams()).thenReturn(new ArrayList<>());
        when(this.pointService.getAllTables()).thenReturn(new ArrayList<>());
        when(this.playerService.getAllPlayers()).thenReturn(new ArrayList<>());
        when(this.matchService.getAllMatches()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ipl/");
        MockMvcBuilders.standaloneSetup(this.iPLController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(4))
                .andExpect(MockMvcResultMatchers.model().attributeExists("matches", "players", "pointTables", "teams"))
                .andExpect(MockMvcResultMatchers.view().name("dashboard"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("dashboard"));
    }
}

