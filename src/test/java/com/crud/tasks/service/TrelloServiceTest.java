package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;


    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("new card", "tested", "top", "new list");
        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto("5", "new card", "test url");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(newCard);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");

        //When
        CreatedTrelloCardDto trelloCard = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("5", trelloCard.getId());
        assertEquals("new card", trelloCard.getName());
        assertEquals("test url", trelloCard.getShortUrl());
    }

    @Test
    public void testFetchTrelloBoardsEmpty() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> emptyListOfBoards = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(0, emptyListOfBoards.size());
    }
}
