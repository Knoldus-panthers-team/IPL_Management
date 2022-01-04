package com.knoldus.kup.ipl.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.repository.MatchRepository;
import com.knoldus.kup.ipl.services.MatchService;
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
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@ContextConfiguration(classes = {MatchController.class})
@ExtendWith(SpringExtension.class)
class MatchControllerTest {
    @Autowired
    private MatchController matchController;
    
    @MockBean
    private MatchRepository matchRepository;
    
    @MockBean
    private MatchService matchService;
    
    
    @Test
    void testGetMatches() throws Exception {
        when(this.matchService.getMatchesWithModel(new ExtendedModelMap())).thenReturn(new ExtendedModelMap());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/matches/list");
        MockMvcBuilders.standaloneSetup(this.matchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("match-details"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("match-details"));
    }
    
    @Test
    void testMatchUpdateIfSlotIsBooked() throws Exception {
        when(this.matchService.getAlertIfSlotBooked(new RedirectAttributesModelMap()))
                .thenReturn(new RedirectAttributesModelMap());
        Match match = new Match();
        when(this.matchService.isSlotBooked((com.knoldus.kup.ipl.models.Match) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/matches/update/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.matchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/matches/edit/1"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/matches/edit/1"));
    }
    
    @Test
    void testMatchUpdate2() throws Exception {
        when(this.matchService.getAlertIfTeamSame((new RedirectAttributesModelMap())))
                .thenReturn(new RedirectAttributesModelMap());
        when(this.matchService.isTeamSame((com.knoldus.kup.ipl.models.Match) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/matches/update/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.matchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/matches/edit/1"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/matches/edit/1"));
    }
    
    @Test
    void testMatchUpdateIfSuccess() throws Exception {
        when(this.matchService.getAlertOnUpdate(new RedirectAttributesModelMap(),
                (new Match()))).thenReturn(new RedirectAttributesModelMap());
        when(this.matchService.isSlotBooked(new Match())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/matches/update/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.matchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ipl/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ipl/admin"));
    }
    
    @Test
    void testSaveMatch() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/matches/save");
        MockMvcBuilders.standaloneSetup(this.matchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("match"))
                .andExpect(MockMvcResultMatchers.view().name("add-match"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("add-match"));
    }
    
    @Test
    void testShowEditForm() throws Exception {
        Model model = new ExtendedModelMap();
        when(this.matchService.getMatchDetails(model, 1L))
                .thenReturn(model);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/matches/edit/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.matchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("update-match"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("update-match"));
    }
    
    @Test
    void testDeleteMatch() throws Exception {
        when(this.matchService.getAlertOnDelete(new RedirectAttributesModelMap(),
                1L)).thenReturn(new RedirectAttributesModelMap());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/matches/delete/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.matchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ipl/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ipl/admin"));
    }
}

