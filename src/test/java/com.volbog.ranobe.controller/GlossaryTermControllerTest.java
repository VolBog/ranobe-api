package com.volbog.ranobe.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.volbog.ranobe.controller.GlossaryTermController;
import com.volbog.ranobe.dto.GlossaryTermDto;
import com.volbog.ranobe.entity.GlossaryTerm;
import com.volbog.ranobe.mapper.GlossaryTermMapper;
import com.volbog.ranobe.service.GlossaryTermService;
import java.util.Arrays;
import java.util.Collections;
import main.java.com.volbog.ranobe.controller.CustomUtils;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GlossaryTermControllerTest {

  private static final String ENDPOINT_URL = "/api/glossary-term";

  @InjectMocks
  private GlossaryTermController glossarytermController;

  @Mock
  private GlossaryTermService glossarytermService;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(glossarytermController)
        .build();
  }

  @Test
  public void findAllByPage() throws Exception {
    Page<GlossaryTermDto> page = new PageImpl<>(
        Collections.singletonList(GlossaryTermBuilder.getDto()));

    Mockito.when(
            glossarytermService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
        .thenReturn(page);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

    Mockito.verify(glossarytermService, Mockito.times(1))
        .findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
    Mockito.verifyNoMoreInteractions(glossarytermService);
  }

  @Test
  public void getById() throws Exception {
    Mockito.when(glossarytermService.findById(ArgumentMatchers.anyLong()))
        .thenReturn(GlossaryTermBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

    Mockito.verify(glossarytermService, Mockito.times(1)).findById("1");
    Mockito.verifyNoMoreInteractions(glossarytermService);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(glossarytermService.save(ArgumentMatchers.any(GlossaryTermDto.class)))
        .thenReturn(GlossaryTermBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(GlossaryTermBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    Mockito.verify(glossarytermService, Mockito.times(1))
        .save(ArgumentMatchers.any(GlossaryTermDto.class));
    Mockito.verifyNoMoreInteractions(glossarytermService);
  }

  @Test
  public void update() throws Exception {
    Mockito.when(glossarytermService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
        .thenReturn(GlossaryTermBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(GlossaryTermBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(glossarytermService, Mockito.times(1))
        .update(ArgumentMatchers.any(GlossaryTermDto.class), ArgumentMatchers.anyLong());
    Mockito.verifyNoMoreInteractions(glossarytermService);
  }

  @Test
  public void delete() throws Exception {
    Mockito.doNothing().when(glossarytermService).deleteById(ArgumentMatchers.anyLong());

    mockMvc.perform(
            MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(GlossaryTermBuilder.getIds())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(glossarytermService, Mockito.times(1))
        .deleteById(Mockito.anyLong());
    Mockito.verifyNoMoreInteractions(glossarytermService);
  }
}
