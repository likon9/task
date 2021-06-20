package com.khrushchev.postcard.parser.sax;

import com.khrushchev.postcard.entity.AbstractCards;
import com.khrushchev.postcard.exception.PostCardException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SaxXmlParser implements XmlParser {

    public static Logger logger = LogManager.getLogger();
    private SAXParserFactory factory;
    private SAXParser parser;
    private Set<AbstractCards> cards;

    public SaxXmlParser() {
        cards = new HashSet<>();
        try {
            factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Parser configuration Exception" + e);

        } catch (SAXException e) {
            logger.log(Level.ERROR, "Parser configuration Exception" + e);
        }
    }

    public Set<AbstractCards> parserXml(String xmlFile) throws PostCardException {

        PostCardHandler postCardHandler = new PostCardHandler();
        postCardHandler.setPostCard(cards);
        try {
            parser.parse(new File(xmlFile), postCardHandler);
        } catch (SAXException | IOException e) {
            logger.log(Level.ERROR, "File can not valid...");
            throw new PostCardException("File can not valid...");
        }
        return cards;
    }
    public Set<AbstractCards> getPostCards() {
        return this.cards;
    }
}
