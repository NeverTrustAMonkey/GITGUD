package edu.tacoma.uw.plsanch.gitgud;


import org.junit.Test;

import java.util.ArrayList;

import edu.tacoma.uw.plsanch.gitgud.guide.Guide;

import static org.junit.Assert.*;

/**
 * Unit Testing for the Guide model class
 *
 * @author Patrick Sanchez
 */
public class GuideTest {

//Constructor tests
    @Test
    public void testGuideConstructor() {
        assertNotNull(new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here"));
    }

//Getter Tests
    @Test
    public void testGetTitle() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        assertEquals("TestGuideTitle", test.getmGuideTitle());
    }

    @Test
    public void testGetAuthor() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        assertEquals("DanTheTesterMan", test.getmGuideAuthor());
    }

    @Test
    public void testGetHero() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        assertEquals("Ana", test.getmGuideHero());
    }

    @Test
    public void testGetContent() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        assertEquals("The guide text goes here", test.getmGuideText());
    }

    @Test
    public void testGetId() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        assertEquals("10", test.getmGuideId());
    }

    //Setter tests
    @Test
    public void testsetTitle() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideTitle("Chupacabra");
        assertEquals("Chupacabra", test.getmGuideTitle());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testsetTitleover20() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideTitle("Thisissomethingjustovertwentycharacters");

    }

    @Test(expected=IllegalArgumentException.class)
    public void testsetTitleoverunder1() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideTitle("");

    }

    @Test
    public void testsetAuthor() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideAuthor("Chupacabra");
        assertEquals("Chupacabra", test.getmGuideAuthor());
    }


    @Test(expected=IllegalArgumentException.class)
    public void testsetAuthorover32() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideAuthor("Thisissomethingjustovertwentycharacters");

    }

    @Test(expected=NullPointerException.class)
    public void testsetAuthornull() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideAuthor(null);

    }

    @Test(expected=IllegalArgumentException.class)
    public void testsetAuthorunder1() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideAuthor("");

    }

    @Test
    public void testsetHero() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideHero("Widowmaker");
        assertEquals("Widowmaker", test.getmGuideHero());
    }


    @Test(expected= IllegalArgumentException.class)
    public void testsetHeroOver10() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideHero("Widowmaker10char");
    }

    @Test(expected= IllegalArgumentException.class)
    public void testsetHerounder3() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideHero("An");

    }

    @Test(expected= NullPointerException.class)
    public void testsetHeroNull() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideHero(null);

    }


    @Test
    public void testsetContent() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideText("Here's some new text!");
        assertEquals("Here's some new text!", test.getmGuideText());

    }


    @Test(expected= IllegalArgumentException.class)
    public void testsetContentUnder1() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideText("");
    }


    @Test(expected= NullPointerException.class)
    public void testsetContentNull() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideText(null);
    }


    @Test
    public void testsetId() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideId("12");
    }

    @Test(expected= IllegalArgumentException.class)
    public void testsetIdNeg() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideId("-1");
    }

    @Test(expected= NumberFormatException.class)
    public void testsetIdNull() {
        Guide test = new Guide("10","TestGuideTitle", "DanTheTesterMan", "Ana", "The guide text goes here");
        test.setmGuideId(null);
    }

    @Test
    public void testParseGuideJSONNull() {

        String result = Guide.parseGuideJSON(null, null);
        assertNull(result);

    }



}//EOF