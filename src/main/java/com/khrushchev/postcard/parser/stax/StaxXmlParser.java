package com.khrushchev.postcard.parser.stax;

import com.khrushchev.postcard.entity.AbstractCards;
import com.khrushchev.postcard.entity.AdvertisingCard;
import com.khrushchev.postcard.entity.CongratulatoryCard;
import com.khrushchev.postcard.entity.NormalCard;
import com.khrushchev.postcard.exception.PostCardException;
import com.khrushchev.postcard.parser.sax.PostCardTypesTags;
import com.khrushchev.postcard.parser.sax.XmlParser;
import com.khrushchev.postcard.validator.FileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.khrushchev.postcard.parser.sax.PostCardTypesTags.*;

public class StaxXmlParser implements XmlParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";
    private final static boolean POISONOUS_DEFAULT = false;

    private Set<AbstractCards> cards;
    private XMLInputFactory factory;
    private XMLEventReader reader;
    private Map<String, String> elementsMap;
    private PostCardTypesTags makeCards;


    public StaxXmlParser() {
        factory = XMLInputFactory.newFactory();
        elementsMap = new HashMap<>();
        cards = new HashSet<>();
    }

    public Set<AbstractCards> parserXml(String xmlFile) throws PostCardException {
        if (!FileValidator.isFiveExistIsFileisNull(xmlFile)) {
            logger.error("File can not valid...");
            throw new PostCardException("File can not valid ...");
        }

        try {
            try {
                reader = factory.createXMLEventReader(new FileInputStream(xmlFile));
            } catch (XMLStreamException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            logger.info("StAX parser has been started...");
            while (reader.hasNext()) {
                XMLEvent event = null;
                try {
                    event = reader.nextEvent();
                } catch (XMLStreamException e) {
                    logger.error("XMLStreamException...");
                    throw new PostCardException("XMLStreamException ...");
                }
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    PostCardTypesTags postCardType = PostCardTypesTags.valueOf(tagToEnum(startElement.getName().getLocalPart()));
                    switch (postCardType) {
                        case CONGRATULATORY_CARD:
                            makeCards = postCardType;
                            getAttribute(startElement);
                            break;
                        case ADVERTISING_CARD:
                            makeCards = postCardType;
                            getAttribute(startElement);
                            break;
                        case NORMAL_CARD:
                            makeCards = postCardType;
                            getAttribute(startElement);
                            break;
                        default:
                            makeCards = postCardType;
                            event = reader.nextEvent();
                            elementsMap.put(makeCards.tagName(), event.asCharacters().getData());


                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    PostCardTypesTags endPostCard = PostCardTypesTags.valueOf(tagToEnum(endElement.getName().getLocalPart()));
                    switch (endPostCard) {
                        case CONGRATULATORY_CARD:
                            postCardMaker();
                            break;
                        case ADVERTISING_CARD:
                            postCardMaker();
                        case NORMAL_CARD:
                            postCardMaker();
                            break;
                    }
                }
            }
            logger.info("StAX parser has been finished");
            return cards;
        } catch (XMLStreamException e) {
            logger.error("File can not parsing ...");
            throw new PostCardException("File can not parsing ...");
        }
    }

    private void postCardMaker() {
        switch (makeCards) {
            case CONGRATULATORY_CARD: {
                CongratulatoryCard.Builder builder = CongratulatoryCard.builder();
                builder.themeSet(elementsMap.get(THEME.tagName()))
                        .countrySet(elementsMap.get(COUNTRY.tagName()))
                        .yearSet(Integer.valueOf(elementsMap.get(YEAR.tagName())))
                        .authorSet(elementsMap.get(AUTHOR.tagName()))
                        .valuableSet(elementsMap.get(VALUABLE.tagName()))
                        .typeOfHolidayset(elementsMap.get(TYPE_OF_HOLIDAY.tagName()));
                cards.add(builder.build());
                logger.info("Congratulatory card is finished");
            }
            break;
            case ADVERTISING_CARD: {
                AdvertisingCard.Builder builder =AdvertisingCard.builder();
                builder.themeSet(elementsMap.get(THEME.tagName()))
                        .countrySet(elementsMap.get(COUNTRY.tagName()))
                        .yearSet(Integer.valueOf(elementsMap.get(YEAR.tagName())))
                        .authorSet(elementsMap.get(AUTHOR.tagName()))
                        .valuableSet(elementsMap.get(VALUABLE.tagName()))
                        .targetsForAdvertisingByAgeSet(elementsMap.get(TARGETS_FOR_ADVERTISING_BY_AGE.tagName()))
                        .targetsForAdvertisingByGenderSet(elementsMap.get(TARGETS_FOR_ADVERTISING_BY_AGE.tagName()));
                cards.add(builder.build());
                logger.info("Advertising card is finished");
            }
            break;
            case NORMAL_CARD: {
                NormalCard.Builder builder =NormalCard.builder();
                builder.themeSet(elementsMap.get(THEME.tagName()))
                        .countrySet(elementsMap.get(COUNTRY.tagName()))
                        .yearSet(Integer.valueOf(elementsMap.get(YEAR.tagName())))
                        .authorSet(elementsMap.get(AUTHOR.tagName()))
                        .valuableSet(elementsMap.get(VALUABLE.tagName()))
                        .recipientSet(elementsMap.get(RECIPIENT.tagName()));
                cards.add(builder.build());
                logger.info("Normal card is finished");
            }
            break;
        }
    }


    private void getAttribute(StartElement startElement) {
                elementsMap.put(DATE_OF_SELL.tagName(), startElement.getAttributeByName(new QName(DATE_OF_SELL.tagName())).getValue());
        logger.info("Date is finished");

    }

    private String tagToEnum(String qName) {
        return qName.toUpperCase().replaceAll(HYPHEN, UNDERSCORE);
    }
    public Set<AbstractCards> getPostCards() {
        return this.cards;
    }
}
