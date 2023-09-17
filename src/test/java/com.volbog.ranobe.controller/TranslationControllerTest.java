package com.volbog.ranobe.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.volbog.ranobe.controller.TranslationController;
import com.volbog.ranobe.dto.TranslationDto;
import com.volbog.ranobe.entity.Translation;
import com.volbog.ranobe.mapper.TranslationMapper;
import com.volbog.ranobe.service.TranslationService;
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
public class TranslationControllerTest {

  private static final String ENDPOINT_URL = "/api/translation";

  @InjectMocks
  private TranslationController translationController;

  @Mock
  private TranslationService translationService;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(translationController)
        .build();
  }

  @Test
  public void findAllByPage() throws Exception {
    Page<TranslationDto> page = new PageImpl<>(
        Collections.singletonList(TranslationBuilder.getDto()));

    Mockito.when(translationService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
        .thenReturn(page);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

    Mockito.verify(translationService, Mockito.times(1))
        .findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
    Mockito.verifyNoMoreInteractions(translationService);
  }

  @Test
  public void getById() throws Exception {
    Mockito.when(translationService.findById(ArgumentMatchers.anyLong()))
        .thenReturn(TranslationBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

    Mockito.verify(translationService, Mockito.times(1)).findById("1");
    Mockito.verifyNoMoreInteractions(translationService);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(translationService.save(ArgumentMatchers.any(TranslationDto.class)))
        .thenReturn(TranslationBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(TranslationBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    Mockito.verify(translationService, Mockito.times(1))
        .save(ArgumentMatchers.any(TranslationDto.class));
    Mockito.verifyNoMoreInteractions(translationService);
  }

  @Test
  public void update() throws Exception {
    Mockito.when(translationService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
        .thenReturn(TranslationBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(TranslationBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(translationService, Mockito.times(1))
        .update(ArgumentMatchers.any(TranslationDto.class), ArgumentMatchers.anyLong());
    Mockito.verifyNoMoreInteractions(translationService);
  }

  @Test
  public void delete() throws Exception {
    Mockito.doNothing().when(translationService).deleteById(ArgumentMatchers.anyLong());

    mockMvc.perform(
            MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(TranslationBuilder.getIds())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(translationService, Mockito.times(1))
        .deleteById(Mockito.anyLong());
    Mockito.verifyNoMoreInteractions(translationService);
  }
}
