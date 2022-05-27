package com.knoldus.kup.ipl.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.knoldus.kup.ipl.services.CountryService;
import com.knoldus.kup.ipl.services.PlayerService;
import com.knoldus.kup.ipl.services.TeamService;
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
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@ContextConfiguration(classes = {PlayerController.class})
@ExtendWith(SpringExtension.class)
class PlayerControllerTest {
    @MockBean
    private CountryService countryService;
    
    @Autowired
    private PlayerController playerController;
    
    @MockBean
    private PlayerService playerService;
    
    @MockBean
    private TeamService teamService;
    
    @Test
    void testAddPlayer() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/players/add");
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("player"))
                .andExpect(MockMvcResultMatchers.view().name("addPlayer"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("addPlayer"));
    }
    
    @Test
    void testDeletePlayer() throws Exception {
        when(this.playerService.getAlertOnDelete((Long) any(),
                (org.springframework.web.servlet.mvc.support.RedirectAttributes) any()))
                .thenReturn(new RedirectAttributesModelMap());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ipl/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ipl/admin"));
    }
    
    @Test
    void testShowUpdateForm() throws Exception {
        when(this.playerService.getPlayerWithModel((Long) any(), (org.springframework.ui.Model) any()))
                .thenReturn(new ConcurrentModel());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/edit/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("update-player"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("update-player"));
    }
    
    @Test
    void testUpdatePlayer() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/players/update/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("player"))
                .andExpect(MockMvcResultMatchers.view().name("update-player"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("update-player"));
    }
}

