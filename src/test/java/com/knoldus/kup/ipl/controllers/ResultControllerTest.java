package com.knoldus.kup.ipl.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.services.MatchService;
import com.knoldus.kup.ipl.services.ResultService;
import com.knoldus.kup.ipl.services.TeamService;
import com.knoldus.kup.ipl.services.VenueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@ContextConfiguration(classes = {ResultController.class})
@ExtendWith(SpringExtension.class)
class ResultControllerTest {
    @MockBean
    private MatchService matchService;
    
    @Autowired
    private ResultController resultController;
    
    @MockBean
    private ResultService resultService;
    
    @MockBean
    private TeamService teamService;
    
    @MockBean
    private VenueService venueService;
    
    @Test
    void testScoreSave() throws Exception {
        RedirectAttributes attributes = new RedirectAttributesModelMap();
        when(this.resultService.getAlertOnSave(new Match(),
                new RedirectAttributesModelMap()))
                .thenReturn(attributes);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/result/add");
        MockMvcBuilders.standaloneSetup(this.resultController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ipl/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ipl/admin"));
    }
    
    @Test
    void testScoreUpdate() throws Exception {
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        when(this.resultService.getAlertOnUpdate(redirectAttributes,
                new Match())).thenReturn(redirectAttributes);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/result/update/1L").param("id", "42");
        MockMvcBuilders.standaloneSetup(this.resultController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ipl/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ipl/admin"));
    }
    
    @Test
    void testShowAddForm() throws Exception {
        when(this.resultService.getScoreForm(new ExtendedModelMap(), 1L))
                .thenReturn(new ConcurrentModel());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/result/addScore/{match_id}", 1L);
        MockMvcBuilders.standaloneSetup(this.resultController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("add-result"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("add-result"));
    }
    
    @Test
    void testShowUpdateForm() throws Exception {
        when(this.resultService.getEditScoreForm(1L, new ExtendedModelMap()))
                .thenReturn(new ConcurrentModel());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/result/editScore/{match_id}", 1L);
        MockMvcBuilders.standaloneSetup(this.resultController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("update-result"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("update-result"));
    }
}

