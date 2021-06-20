package com.khrushchev.postcard.parser;

import com.khrushchev.postcard.entity.AbstractCards;
import com.khrushchev.postcard.entity.AdvertisingCard;
import com.khrushchev.postcard.entity.CongratulatoryCard;
import com.khrushchev.postcard.exception.PostCardException;
import com.khrushchev.postcard.parser.dom.DomXmlParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ParserTest {
    private Set<AbstractCards> cards;
    private static final String FILE_PATH_TRUE = "src/test/resources/valid.xml";

    @BeforeClass
    public void setUp() {
        /*  <advertising-card date-of-sell="2011-02-02">
        <theme>Nature</theme>
        <country>German</country>
        <year>2019</year>
        <author>Ward</author>
        <valuable>thematic</valuable>
        <targets-for-advertising-by-age>19-30</targets-for-advertising-by-age>
        <targets-for-advertising-by-gender>all</targets-for-advertising-by-gender>
    </advertising-card>

    <congratulatory-card date-of-sell="2021-01-11">
        <theme>City</theme>
        <country>Belarus</country>
        <year>2020</year>
        <author>Aqq</author>
        <valuable>historical</valuable>
        <type-of-holiday>birthday</type-of-holiday>
    </congratulatory-card>

    <normal-card date-of-sell="2020-09-30">
        <theme>Night</theme>
        <country>Poland</country>
        <year>2013</year>
        <author>Johnson</author>
        <valuable>collection</valuable>
        <recipient>friends</recipient>
    </normal-card>

    <congratulatory-card date-of-sell="2000-10-20">
        <theme>Sport</theme>
        <country>Russia</country>
        <year>1999</year>
        <author>Ivanov</author>
        <valuable>thematic</valuable>
        <type-of-holiday>professional</type-of-holiday>
    </congratulatory-card>*/
        cards = new HashSet<>();

        AdvertisingCard advertisingCard= new AdvertisingCard(
                "Nature",
                "German",
                2019,
                "Ward",
                "thematic",
                LocalDate.parse("2011-02-02"),
                "19-30",
                "all");
        CongratulatoryCard congratulatoryCard= new CongratulatoryCard(
                "City",
                "Belarus",
                2020,
                "aqq",
                "historical",
                LocalDate.parse("2021-01-11"),
                "birthday");



        cards.add(advertisingCard);
   //  cards.add(congratulatoryCard);
    }

    @Test
    public void DomParserTest() throws PostCardException {
        DomXmlParser builder = new DomXmlParser();
        builder.parserXml(FILE_PATH_TRUE);
        Set<AbstractCards> actualSet = builder.getPostCards();
        Assert.assertEquals(actualSet, cards);
    }

}
