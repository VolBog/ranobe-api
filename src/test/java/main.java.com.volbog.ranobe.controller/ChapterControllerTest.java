package main.java.com.volbog.ranobe.controller;

import com.volbog.ranobe.controller.ChapterController;
import com.volbog.ranobe.dto.ChapterDto;
import com.volbog.ranobe.service.ChapterService;
import java.util.Collections;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ChapterControllerTest {

  private static final String ENDPOINT_URL = "/api/chapter";

  @InjectMocks
  private ChapterController chapterController;

  @Mock
  private ChapterService chapterService;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(chapterController)
        .build();
  }

  @Test
  public void findAllByPage() throws Exception {
    Page<ChapterDto> page = new PageImpl<>(Collections.singletonList(ChapterBuilder.getDto()));

    Mockito.when(chapterService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
        .thenReturn(page);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

    Mockito.verify(chapterService, Mockito.times(1))
        .findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
    Mockito.verifyNoMoreInteractions(chapterService);
  }

  @Test
  public void getById() throws Exception {
    Mockito.when(chapterService.findById(ArgumentMatchers.anyLong()))
        .thenReturn(ChapterBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

    Mockito.verify(chapterService, Mockito.times(1)).findById("1");
    Mockito.verifyNoMoreInteractions(chapterService);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(chapterService.save(ArgumentMatchers.any(ChapterDto.class)))
        .thenReturn(ChapterBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(ChapterBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    Mockito.verify(chapterService, Mockito.times(1)).save(ArgumentMatchers.any(ChapterDto.class));
    Mockito.verifyNoMoreInteractions(chapterService);
  }

  @Test
  public void update() throws Exception {
    Mockito.when(chapterService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
        .thenReturn(ChapterBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(ChapterBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(chapterService, Mockito.times(1))
        .update(ArgumentMatchers.any(ChapterDto.class), ArgumentMatchers.anyLong());
    Mockito.verifyNoMoreInteractions(chapterService);
  }

  @Test
  public void delete() throws Exception {
    Mockito.doNothing().when(chapterService).deleteById(ArgumentMatchers.anyLong());

    mockMvc.perform(
            MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(ChapterBuilder.getIds())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(chapterService, Mockito.times(1))
        .deleteById(Mockito.anyLong());
    Mockito.verifyNoMoreInteractions(chapterService);
  }
}
