package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @MockBean
    private TaskController taskController;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "test", "test content"));
        taskDtoList.add(new TaskDto(2L, "test 2", "test content 2"));

        when(taskController.getTasks()).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))  // or isOk()
                .andExpect(jsonPath("$", hasSize(2)));
    }

//    @Test
//    public void testGetTask() throws Exception {
//        //Given
//        TaskDto taskDto = new TaskDto(33L, "test", "test content");
//        when(taskController.getTask(taskDto.getId())).thenReturn(taskDto);
//
//        //When & Then
//        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200));  // or isOk()
//    }
//
//
//    @Test
//    public void testUpdateTaskTest() throws Exception {
//        //Given
//        TaskDto taskDto = new TaskDto(22L, "Test title", "test content");
//
//        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);
//
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(taskDto);
//
//        //When & Then
//        mockMvc.perform(put("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(22L)))
//                .andExpect(jsonPath("$.title", is("Test title")))
//                .andExpect(jsonPath("$.content", is("test content")));
//    }
//
//    @Test
//    public void testCreateTask() throws Exception {
//        //Given
//        TaskDto taskDto = new TaskDto(16L, "test", "test_content");
//        Task task = new Task(16L, "test", "content test");
//
//        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
//
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(taskDto);
//
//        //When & Then
//        mockMvc.perform(post("/v1/task/createTask")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(jsonPath("$.id", is(16L)))
//                .andExpect(jsonPath("$.name", is("test")))
//                .andExpect(jsonPath("$.shortUrl", is("test_content")));
//    }

}