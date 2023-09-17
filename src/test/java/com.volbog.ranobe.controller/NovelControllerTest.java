package com.volbog.ranobe.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.volbog.ranobe.controller.NovelController;
import com.volbog.ranobe.dto.NovelDto;
import com.volbog.ranobe.entity.Novel;
import com.volbog.ranobe.mapper.NovelMapper;
import com.volbog.ranobe.service.NovelService;
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
public class NovelControllerTest {

  private static final String ENDPOINT_URL = "/api/novel";

  @InjectMocks
  private NovelController novelController;

  @Mock
  private NovelService novelService;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(novelController)
        .build();
  }

  @Test
  public void findAllByPage() throws Exception {
    Page<NovelDto> page = new PageImpl<>(Collections.singletonList(NovelBuilder.getDto()));

    Mockito.when(novelService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
        .thenReturn(page);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

    Mockito.verify(novelService, Mockito.times(1))
        .findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
    Mockito.verifyNoMoreInteractions(novelService);
  }

  @Test
  public void getById() throws Exception {
    Mockito.when(novelService.findById(ArgumentMatchers.anyLong()))
        .thenReturn(NovelBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

    Mockito.verify(novelService, Mockito.times(1)).findById("1");
    Mockito.verifyNoMoreInteractions(novelService);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(novelService.save(ArgumentMatchers.any(NovelDto.class)))
        .thenReturn(NovelBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(NovelBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    Mockito.verify(novelService, Mockito.times(1)).save(ArgumentMatchers.any(NovelDto.class));
    Mockito.verifyNoMoreInteractions(novelService);
  }

  @Test
  public void update() throws Exception {
    Mockito.when(novelService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
        .thenReturn(NovelBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(NovelBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(novelService, Mockito.times(1))
        .update(ArgumentMatchers.any(NovelDto.class), ArgumentMatchers.anyLong());
    Mockito.verifyNoMoreInteractions(novelService);
  }

  @Test
  public void delete() throws Exception {
    Mockito.doNothing().when(novelService).deleteById(ArgumentMatchers.anyLong());

    mockMvc.perform(
            MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(NovelBuilder.getIds())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(novelService, Mockito.times(1))
        .deleteById(Mockito.anyLong());
    Mockito.verifyNoMoreInteractions(novelService);
  }
}
