package com.volbog.ranobe.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.volbog.ranobe.controller.RoleController;
import com.volbog.ranobe.dto.RoleDto;
import com.volbog.ranobe.entity.Role;
import com.volbog.ranobe.mapper.RoleMapper;
import com.volbog.ranobe.service.RoleService;
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
public class RoleControllerTest {

  private static final String ENDPOINT_URL = "/api/role";

  @InjectMocks
  private RoleController roleController;

  @Mock
  private RoleService roleService;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(roleController)
        .build();
  }

  @Test
  public void findAllByPage() throws Exception {
    Page<RoleDto> page = new PageImpl<>(Collections.singletonList(RoleBuilder.getDto()));

    Mockito.when(roleService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
        .thenReturn(page);

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

    Mockito.verify(roleService, Mockito.times(1))
        .findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
    Mockito.verifyNoMoreInteractions(roleService);
  }

  @Test
  public void getById() throws Exception {
    Mockito.when(roleService.findById(ArgumentMatchers.anyLong()))
        .thenReturn(RoleBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

    Mockito.verify(roleService, Mockito.times(1)).findById("1");
    Mockito.verifyNoMoreInteractions(roleService);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(roleService.save(ArgumentMatchers.any(RoleDto.class)))
        .thenReturn(RoleBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(RoleBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    Mockito.verify(roleService, Mockito.times(1)).save(ArgumentMatchers.any(RoleDto.class));
    Mockito.verifyNoMoreInteractions(roleService);
  }

  @Test
  public void update() throws Exception {
    Mockito.when(roleService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
        .thenReturn(RoleBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(RoleBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(roleService, Mockito.times(1))
        .update(ArgumentMatchers.any(RoleDto.class), ArgumentMatchers.anyLong());
    Mockito.verifyNoMoreInteractions(roleService);
  }

  @Test
  public void delete() throws Exception {
    Mockito.doNothing().when(roleService).deleteById(ArgumentMatchers.anyLong());

    mockMvc.perform(
            MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(RoleBuilder.getIds())))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(roleService, Mockito.times(1))
        .deleteById(Mockito.anyLong());
    Mockito.verifyNoMoreInteractions(roleService);
  }
}
