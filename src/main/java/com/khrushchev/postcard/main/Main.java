package com.khrushchev.postcard.main;

import com.khrushchev.postcard.parser.dom.DomXmlParser;
import com.khrushchev.postcard.parser.sax.SaxXmlParser;
import com.khrushchev.postcard.parser.stax.StaxXmlParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.khrushchev.postcard.parser.sax.SaxXmlParser;

public class Main {
    public static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
      /*  CongratulatoryCard congratulatoryCard = new CongratulatoryCard("qwe","qwe",2000,"qwe","qwe",null,"qwe");
        System.out.println(congratulatoryCard.toString());
        AdvertisingCard advertisingCard = new AdvertisingCard("qwe","qwe",2000,"qwe","qwe",null,"65+","all");
        System.out.println(advertisingCard.toString());
        NormalCard normalCard = new NormalCard("qwe","qwe",2000,"qwe","qwe",null,"qwe");
        System.out.println(normalCard.toString());*/

   DomXmlParser domXmlParser = new DomXmlParser();
        domXmlParser.parserXml("data/old_cards.xml");

        SaxXmlParser saxXmlParser = new SaxXmlParser();
        saxXmlParser.parserXml("data/old_cards.xml");

      StaxXmlParser staxXmlParser = new StaxXmlParser();
        staxXmlParser.parserXml("data/old_cards.xml");
    }
}
