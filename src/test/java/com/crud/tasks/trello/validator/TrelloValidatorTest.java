package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
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
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateCardTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("testing", "Create new tests", "top", "To Do");
        //When
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    public void testValidateCardTestProper() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Cooking", "Cook a dinner", "top", "To Do");
        //When
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    public void testValidateTrelloBoards() {

        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "Home", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2", "test", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("3", "TesT", new ArrayList<>()));
        //When
        List<TrelloBoard> filteredListofBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assert.assertEquals(1, filteredListofBoards.size());
        Assert.assertEquals("Home", filteredListofBoards.get(0).getName());
        Assert.assertEquals("1", filteredListofBoards.get(0).getId());
    }
}
