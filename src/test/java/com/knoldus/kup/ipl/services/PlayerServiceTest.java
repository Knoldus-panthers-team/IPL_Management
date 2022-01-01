package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.City;
import com.knoldus.kup.ipl.models.Country;
import com.knoldus.kup.ipl.models.Player;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.repository.CountryRepository;
import com.knoldus.kup.ipl.repository.PlayerRepository;
import com.knoldus.kup.ipl.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PlayerServiceTest {

    @MockBean
    PlayerRepository playerRepository;
    
    @MockBean
    TeamRepository teamRepository;

    @Autowired
    PlayerService playerService;
    
    @Autowired
    TeamService teamService;
    
    @MockBean
    CountryRepository countryRepository;

    Player player1,player2,player3;
    Set<Player> playersList;
    RedirectAttributes redirectAttributes;
    Team team1, team2;
    Country country1,country2;
    List<Team> teamList;
    
    Set<Player> fakeList;
    
    @BeforeEach
    void setUp() {
//        this.playerService = new PlayerService(this.playerRepository);
//        this.teamService = new TeamService(this.teamRepository);
        
        City city1 = new City();
        City city2 = new City();
        city1.setId(1L);
        city1.setCityName("Kolkata");
        city2.setId(2L);
        city2.setCityName("Chennai");
    
        fakeList = new HashSet<>();
        for (int i=0; i<18; i++) {
            fakeList.add(new Player());
        }

      
        team1 = new Team(1L,"KKR", city1);
        team2 = new Team(2L,"CSK", city2);
        teamList = Arrays.asList(team1,team2);

        country1 = new Country(1L,"India");
        country2 = new Country(2L,"Srilanka");
    
        player1 = new Player(1L,"Rohit Sharma",team1,country1,"Batsman");
        player2 = new Player(2L,"Virat Kohli",team1,country1,"Batsman");
        player3 = new Player(3L,"Virat Kohli",team1,country1,"Batsman");
        playersList = new HashSet<>();
        playersList.add(player1);
        playersList.add(player2);
        playersList.add(player3);
    }

    @Test
    void getNewPlayerObject() {
        Player player = new Player();
        Assertions.assertThat(playerService.getNewPlayerObject().getClass()).isEqualTo(player.getClass());
    }

    @Test
    void savePlayer() {
        when(playerRepository.save(player1)).thenReturn(player1);
        Player player = playerService.savePlayer(player1);
        assertNotNull(player);
        assertEquals(player.getTeam(),player1.getTeam());
    }

    @Test
    void getAllPlayers() {
        List<Player> intoList = new ArrayList<>();
        intoList.addAll(playersList);
        when(playerRepository.findAll()).thenReturn(intoList);
        Assertions.assertThat(playerService.getAllPlayers()).isEqualTo(intoList);
        assertTrue(playerService.getAllPlayers().size()>1);
    }

    @Test
    void getPlayerById() {
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));
        Assertions.assertThat(playerService.getPlayerById(1L)).isEqualTo(player1);
    }

    @Test
    void getPlayersByTeamId() {
        when(playerRepository.findByTeamId(1L)).thenReturn(playersList);
        Assertions.assertThat(playerService.getPlayersByTeamId(1L)).isEqualTo(playersList);
    }

    @Test
    void deletePlayer() {
        playerService.deletePlayer(player1.getId());
        Mockito.verify(playerRepository, Mockito.times(1))
                .deleteById(player1.getId());
    }
    
    @Test
    void getAlertOnSave_returnSuccessMessageOnSave() {
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        when(playerRepository.save(player1)).thenReturn(player1);
        Mockito.when(playerRepository.findByTeamId(team1.getId())).thenReturn(playersList);
        RedirectAttributes expectedResult = new RedirectAttributesModelMap();
        RedirectAttributes actualResult = playerService.getAlertOnSave(player1, redirectAttributes);
        String expectedAttributes = "{message=Player added successfully, messageType=player, alertType=success}";
        assertEquals(expectedAttributes,String.valueOf(actualResult.getFlashAttributes()));
    }
    
    @Test
    void getAlertOnSave_returnErrorAlertAttributes() {
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        Mockito.when(playerRepository.findByTeamId(team1.getId())).thenReturn((Set<Player>) fakeList);
        RedirectAttributes actualResult = playerService.getAlertOnSave(player1, redirectAttributes);
        String expectedAttributes = "{message=Players can not be more than 15, messageType=player, alertType=error}";
        assertEquals(expectedAttributes,String.valueOf(actualResult.getFlashAttributes()));
    }
    
    @Test
    void getPlayerWithModel_ReturnModelWithAttributes(){
        Model model = new ExtendedModelMap();
        when(playerRepository.findById(1L)).thenReturn(Optional.ofNullable(player1));
        when(teamRepository.findAll()).thenReturn(teamList);
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country1,country2));
        List<Team> expectedResult = (List<Team>) playerService.getPlayerWithModel(1L,model).getAttribute("teams");
        assertTrue(expectedResult.size() > 1);
    }
    
    @Test
    void getAlertOnUpdate_ReturnSuccessAlertAttributes(){
        RedirectAttributes attributes = new RedirectAttributesModelMap();
        when(playerRepository.save(player1)).thenReturn(player1 );
        attributes = playerService.getAlertOnUpdate(player1, attributes);
        String actualAttributes = String.valueOf(attributes.getFlashAttributes());
        String expectedAttributes = "{message=Player updated successfully, messageType=player, alertType=success}";
        assertEquals(expectedAttributes,actualAttributes);
    }
    
    @Test
    void getAlertOnDelete_ReturnSuccessAlertAttributes(){
        RedirectAttributes attributes = new RedirectAttributesModelMap();
        attributes = playerService.getAlertOnDelete(1L, attributes);
        String actualAttributes = String.valueOf(attributes.getFlashAttributes());
        String expectedAttributes = "{message=Player deleted successfully, messageType=player, alertType=success}";
        assertEquals(expectedAttributes,actualAttributes);
    }
    
    @Test
    void getPlayersByTeamIdWithModel_ReturnModelWithPlayers(){
        Model model = new ExtendedModelMap();
        when(playerRepository.findByTeamId(team1.getId())).thenReturn(playersList );
        HashSet<Player> players = (HashSet<Player>) playerService.getPlayersByTeamIdWithModel(model, team1.getId()).getAttribute("players");
        assertTrue(players.size()>1);
    }
}