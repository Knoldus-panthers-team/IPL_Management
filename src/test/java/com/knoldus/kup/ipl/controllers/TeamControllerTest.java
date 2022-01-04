package com.knoldus.kup.ipl.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.services.CityService;
import com.knoldus.kup.ipl.services.PlayerService;
import com.knoldus.kup.ipl.services.TeamService;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ContextConfiguration(classes = {TeamController.class, Team.class})
@ExtendWith(SpringExtension.class)
class TeamControllerTest {
    @Autowired
    private Team team;
    
    @MockBean
    private CityService cityService;
    
    @MockBean
    private PlayerService playerService;
    
    @Autowired
    private TeamController teamController;
    
    @MockBean
    private TeamService teamService;
    
    @Test
    void testGetPlayers() throws Exception {
        Country country = new Country(1L,"India");
        City city = new City(1L,"Kolkata",country);
        Team team1 = new Team(1L, "KKR", new City());
        Model model1 = new ExtendedModelMap();
        model1.addAttribute("team", team1);
        Optional<Team> ofResult = Optional.of(team1);
        when(this.teamService.getTeamById(1L)).thenReturn(ofResult);
        when(this.playerService.getPlayersByTeamIdWithModel(model1, 1L))
                .thenReturn(new ConcurrentModel());
        when(this.playerService.getPlayersByTeamId(1L)).thenReturn(new HashSet<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teams/players/{team_id}", 1L);
        MockMvcBuilders.standaloneSetup(this.teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("team-details"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("team-details"));
    }
    
    @Test
    void testGetPlayersOnNull() throws Exception {
        Team team1 = new Team(1L, "KKR", new City());
        when(this.teamService.getTeamById(1L)).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teams/players/{team_id}", 1L);
        MockMvcBuilders.standaloneSetup(this.teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ipl"));
    }
    
    @Test
    void testGetTeamPlayers() throws Exception {
        Team team1 = new Team(1L, "KKR", new City());
        when(this.teamService.getTeamById(1L)).thenReturn(Optional.of(team1));
        when(this.playerService.getPlayersByTeamIdWithModel(new ExtendedModelMap(), 1L))
                .thenReturn(new ConcurrentModel());
        when(this.playerService.getPlayersByTeamId(1L)).thenReturn(new HashSet<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teams/team/{team_id}", 1L);
        MockMvcBuilders.standaloneSetup(this.teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("admin-teams"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("admin-teams"));
    }
    
    @Test
    void testGetTeamPlayersPnNull() throws Exception {
        when(this.teamService.getTeamById(1L)).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teams/team/{team_id}", 1L);
        MockMvcBuilders.standaloneSetup(this.teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ipl/admin"));
    }
    
    @Test
    void testSaveTeam() throws Exception {
        Team team1 = new Team(1L, "KKR", new City());
        when(teamService.saveTeam(team1)).thenReturn(team1);
        when(this.teamService.getAlertOnSave(team1,
                new RedirectAttributesModelMap()))
                .thenReturn(new RedirectAttributesModelMap());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/teams/add");
        MockMvcBuilders.standaloneSetup(this.teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ipl/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ipl/admin"));
    }
//
    @Test
    void testUpdateForm() throws Exception {
        Team team1 = new Team(1L, "KKR", new City());
        Model model1 = new ExtendedModelMap();
        model1.addAttribute("team", team1);
        when(this.teamService.getTeamEditForm(model1, 1L))
                .thenReturn(model1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teams/edit/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("update-team"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("update-team"));
    }
    
    @Test
    void testUpdateTeam() throws Exception {
        when(this.teamService.getAlertOnUpdate((Team) any(),
                (org.springframework.web.servlet.mvc.support.RedirectAttributes) any()))
                .thenReturn(new RedirectAttributesModelMap());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/teams/update/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ipl/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ipl/admin"));
    }
    @Test
    void testDeleteTeam() throws Exception {
        when(this.teamService.getAlertOnDelete((Long) any(),
                (RedirectAttributes) any()))
                .thenReturn(new RedirectAttributesModelMap());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teams/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.teamController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ipl/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ipl/admin"));
    }
}

