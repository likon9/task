package com.khrushchev.postcard.parser.dom;

import com.khrushchev.postcard.entity.AbstractCards;
import com.khrushchev.postcard.entity.AdvertisingCard;
import com.khrushchev.postcard.entity.CongratulatoryCard;
import com.khrushchev.postcard.entity.NormalCard;
import com.khrushchev.postcard.exception.PostCardException;
import com.khrushchev.postcard.validator.FileValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.khrushchev.postcard.parser.sax.PostCardTypesTags.*;


public class DomXmlParser {
    private static Logger logger = LogManager.getLogger();
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Set<AbstractCards> postCards;
    private final static int TAG_POSITION = 0;

    public DomXmlParser() {
        postCards = new HashSet<>();
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Parser configuration Exception" + e);
        }

    }
    public Set<AbstractCards> parserXml(String filePath) throws PostCardException {
        Document document = null;
        if (!FileValidator.isFiveExistIsFileisNull(filePath)) {
            logger.error("File can not valid ...");
            throw new PostCardException("File can not valid ...");
        }
        try {
            document = documentBuilder.parse(filePath);
            Element root = document.getDocumentElement();

            NodeList congratulatoryCardList = root.getElementsByTagName(CONGRATULATORY_CARD.tagName());
            parserNodeList(congratulatoryCardList, CONGRATULATORY_CARD.tagName());

            NodeList advertisingCardList = root.getElementsByTagName(ADVERTISING_CARD.tagName());
            parserNodeList(advertisingCardList, ADVERTISING_CARD.tagName());

            NodeList normalCardList = root.getElementsByTagName(NORMAL_CARD.tagName());
            parserNodeList(normalCardList, NORMAL_CARD.tagName());

            logger.info("Parser dom is successful");




        } catch (IOException | SAXException e) {
            logger.log(Level.ERROR, "Parsing failure..." + e);
            throw new PostCardException("Parsing failure...");
        }
        return postCards;
    }

    private void parserNodeList(NodeList nodeList, String type) {

        if (CONGRATULATORY_CARD.tagName().equals(type)) {
            CongratulatoryCardMaker(nodeList);
        }
        if (ADVERTISING_CARD.tagName().equals(type)) {
            AdvertisingCardMaker(nodeList);
        }
        if (NORMAL_CARD.tagName().equals(type)) {
            NormalCardMaker(nodeList);
        }
    }

    private void CongratulatoryCardMaker(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            CongratulatoryCard.Builder builder =  CongratulatoryCard.builder();
            Element element = (Element) nodeList.item(i);

            builder.themeSet(getElement(element,THEME.tagName())).
                    countrySet(getElement(element,COUNTRY.tagName())).
                    yearSet(Integer.parseInt(getElement(element,YEAR.tagName()))).
                    authorSet(getElement(element,AUTHOR.tagName())).
                    valuableSet(getElement(element,VALUABLE.tagName())).
                    dateOfSellSet(LocalDate.parse(nodeList.item(i).getAttributes().getNamedItem(DATE_OF_SELL.tagName()).getNodeValue())).
                    typeOfHolidayset(getElement(element, TYPE_OF_HOLIDAY.tagName())).build();

            postCards.add((CongratulatoryCard) builder.build());

        }
    }

    private void AdvertisingCardMaker(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            AdvertisingCard.Builder builder =  AdvertisingCard.builder();
            Element element = (Element) nodeList.item(i);

            builder.themeSet(getElement(element,THEME.tagName())).
                    countrySet(getElement(element,COUNTRY.tagName())).
                    yearSet(Integer.parseInt(getElement(element,YEAR.tagName()))).
                    authorSet(getElement(element,AUTHOR.tagName())).
                    valuableSet(getElement(element,VALUABLE.tagName())).
                    dateOfSellSet(LocalDate.parse(nodeList.item(i).getAttributes().getNamedItem(DATE_OF_SELL.tagName()).getNodeValue())).
                    targetsForAdvertisingByAgeSet(getElement(element, TARGETS_FOR_ADVERTISING_BY_AGE.tagName())).
                    targetsForAdvertisingByGenderSet(getElement(element, TARGETS_FOR_ADVERTISING_BY_GENDER.tagName())).build();


            postCards.add((AdvertisingCard) builder.build());

        }
    }

    private void NormalCardMaker(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            NormalCard.Builder builder =  NormalCard.builder();
            Element element = (Element) nodeList.item(i);

            builder.themeSet(getElement(element,THEME.tagName())).
                    countrySet(getElement(element,COUNTRY.tagName())).
                    yearSet(Integer.parseInt(getElement(element,YEAR.tagName()))).
                    authorSet(getElement(element,AUTHOR.tagName())).
                    valuableSet(getElement(element,VALUABLE.tagName())).
                    dateOfSellSet(LocalDate.parse(nodeList.item(i).getAttributes().getNamedItem(DATE_OF_SELL.tagName()).getNodeValue())).
                    recipientSet(getElement(element, RECIPIENT.tagName())).build();


            postCards.add((NormalCard) builder.build());

        }
    }

    private String getElement(Element elementByCard, String elementName) {

        NodeList nodeListElement = elementByCard.getElementsByTagName(elementName);
        Node node = nodeListElement.item(TAG_POSITION);
        String result = node.getChildNodes().item(TAG_POSITION).getNodeValue();
        return result;
    }
    public Set<AbstractCards> getPostCards() {
        return this.postCards;
    }

}


