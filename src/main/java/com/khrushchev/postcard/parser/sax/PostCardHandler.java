package com.khrushchev.postcard.parser.sax;

import com.khrushchev.postcard.entity.AbstractCards;
import com.khrushchev.postcard.entity.AdvertisingCard;
import com.khrushchev.postcard.entity.CongratulatoryCard;
import com.khrushchev.postcard.entity.NormalCard;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.Set;

import static com.khrushchev.postcard.parser.sax.PostCardTypesTags.*;

public class PostCardHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";
    private PostCardTypesTags postCardTag;
    private CongratulatoryCard.Builder congratulatoryCardBuilder;
    private AdvertisingCard.Builder advertisingCardBuilder;
    private NormalCard.Builder normalCardBuilder;
    private PostCardTypesTags nextTag;
    Set<AbstractCards> postCard;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        logger.info("Parser sax is started");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        logger.info("Parser sax is ended");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        PostCardTypesTags postCardTypesTags = PostCardTypesTags.valueOf(tagToEnum(qName));

        switch (postCardTypesTags) {
            case CONGRATULATORY_CARD:
                postCardTag = CONGRATULATORY_CARD;
                congratulatoryCardBuilder = CongratulatoryCard.builder();
                congratulatoryCardBuilder.dateOfSellSet(LocalDate.parse(attributes.getValue(DATE_OF_SELL.tagName())));
                break;
            case ADVERTISING_CARD:
                postCardTag = ADVERTISING_CARD;
                advertisingCardBuilder = AdvertisingCard.builder();
                advertisingCardBuilder.dateOfSellSet(LocalDate.parse(attributes.getValue(DATE_OF_SELL.tagName())));
                break;
            case NORMAL_CARD:
                postCardTag = NORMAL_CARD;
                normalCardBuilder = NormalCard.builder();
                normalCardBuilder.dateOfSellSet(LocalDate.parse(attributes.getValue(DATE_OF_SELL.tagName())));
                break;
            default:
                nextTag = postCardTag;
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        PostCardTypesTags enumTag = PostCardTypesTags.valueOf(tagToEnum(qName));
        switch (enumTag) {
            case CONGRATULATORY_CARD:
                postCard.add(congratulatoryCardBuilder.build());
                logger.log(Level.DEBUG, "1");
                break;
            case ADVERTISING_CARD:
                postCard.add(advertisingCardBuilder.build());
                logger.log(Level.DEBUG, "2");
                break;
            case NORMAL_CARD:
                postCard.add(normalCardBuilder.build());
                logger.log(Level.DEBUG, "3");
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String data = new String(ch, start, length).trim();
        if (data != null && data.length() > 0) {
            if (postCardTag == CONGRATULATORY_CARD) {
                switch (nextTag) {
                    case THEME:
                        congratulatoryCardBuilder.themeSet(data);
                        break;
                    case COUNTRY:
                        congratulatoryCardBuilder.countrySet(data);
                        break;
                    case YEAR:
                        congratulatoryCardBuilder.yearSet(Integer.parseInt(data));
                        break;
                    case AUTHOR:
                        congratulatoryCardBuilder.authorSet(data);
                        break;
                    case VALUABLE:
                        congratulatoryCardBuilder.valuableSet(data);
                        break;
                    case TYPE_OF_HOLIDAY:
                        congratulatoryCardBuilder.typeOfHolidayset(data);
                        break;
                }
            }
            if (postCardTag == ADVERTISING_CARD) {
                switch (nextTag) {

                    case THEME:
                        advertisingCardBuilder.themeSet(data);
                        break;
                    case COUNTRY:
                        advertisingCardBuilder.countrySet(data);
                        break;
                    case YEAR:
                        advertisingCardBuilder.yearSet(Integer.parseInt(data));
                        break;
                    case AUTHOR:
                        advertisingCardBuilder.authorSet(data);
                        break;
                    case VALUABLE:
                        advertisingCardBuilder.valuableSet(data);
                        break;
                    case TARGETS_FOR_ADVERTISING_BY_AGE:
                        advertisingCardBuilder.targetsForAdvertisingByAgeSet(data);
                        break;
                    case TARGETS_FOR_ADVERTISING_BY_GENDER:
                        advertisingCardBuilder.targetsForAdvertisingByGenderSet(data);
                        break;
                }
            }
            if (postCardTag == NORMAL_CARD)
                  {
                    switch (nextTag) {
                        case THEME:
                            normalCardBuilder.themeSet(data);
                            break;
                        case COUNTRY:
                            normalCardBuilder.countrySet(data);
                            break;
                        case YEAR:
                            normalCardBuilder.yearSet(Integer.parseInt(data));
                            break;
                        case AUTHOR:
                            normalCardBuilder.authorSet(data);
                            break;
                        case VALUABLE:
                            normalCardBuilder.valuableSet(data);
                            break;
                        case RECIPIENT:
                            normalCardBuilder.recipientSet(data);
                            break;
                    }
                }
        }
    }
    public void setPostCard(Set<AbstractCards> postCard) {
        this.postCard = postCard;
    }


    private String tagToEnum(String qName) {
        return qName.toUpperCase().replaceAll(HYPHEN, UNDERSCORE);
    }

}
