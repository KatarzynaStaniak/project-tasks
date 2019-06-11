package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "My Task",
                "My first task",
                "top",
                "ToDo"
        );
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("My Task", trelloCard.getName());
        Assert.assertEquals("My first task", trelloCard.getDescription());
        Assert.assertEquals("top", trelloCard.getPos());
        Assert.assertEquals("ToDo", trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard(
                "My Task 2",
                "My second task",
                "top",
                "Done"
        );
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("My Task 2", trelloCardDto.getName());
        Assert.assertEquals("My second task", trelloCardDto.getDescription());
        Assert.assertEquals("top", trelloCardDto.getPos());
        Assert.assertEquals("Done", trelloCardDto.getListId());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList toDo = new TrelloList("1", "Things to Do", false);
        TrelloList done = new TrelloList("2", "Done", true);
        trelloLists.add(toDo);
        trelloLists.add(done);
        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertEquals("Things to Do", trelloListDtoList.get(0).getName());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        TrelloListDto toDo = new TrelloListDto("1", "Things to Do", false);
        TrelloListDto done = new TrelloListDto("2", "Done", true);
        trelloListsDto.add(toDo);
        trelloListsDto.add(done);
        //When
        List<TrelloList> trelloListList = trelloMapper.mapToList(trelloListsDto);
        //Then
        Assert.assertEquals("Things to Do", trelloListList.get(0).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList toDo = new TrelloList("1", "Things to Do", false);
        TrelloList done = new TrelloList("2", "Done", true);
        trelloLists.add(toDo);
        trelloLists.add(done);
        TrelloBoard myTasks = new TrelloBoard("1", "My Tasks", trelloLists);
        TrelloBoard myHomework = new TrelloBoard("2", "My Homework", trelloLists);
        trelloBoards.add(myTasks);
        trelloBoards.add(myHomework);
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        Assert.assertEquals(false, trelloBoardDtoList.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        TrelloListDto toDo = new TrelloListDto("1", "Things to Do", false);
        TrelloListDto done = new TrelloListDto("2", "Done", true);
        trelloListsDto.add(toDo);
        trelloListsDto.add(done);
        TrelloBoardDto myTasks = new TrelloBoardDto("1", "My Tasks", trelloListsDto);
        TrelloBoardDto myHomework = new TrelloBoardDto("2", "My Homework", trelloListsDto);
        trelloBoardsDto.add(myTasks);
        trelloBoardsDto.add(myHomework);
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        Assert.assertEquals(false, trelloBoardList.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Shopping", "Do shopping");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals("Shopping", task.getTitle());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(3L, "Shopping", "Do shopping");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(new Long(3), taskDto.getId());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "Shopping", "Do shopping");
        Task task2 = new Task(2L, "Cinema", "Go to the cinema");
        taskList.add(task);
        taskList.add(task2);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals("Go to the cinema", taskDtoList.get(1).getContent());
    }
}
