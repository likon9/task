package com.khrushchev.postcard.parser.sax;

import com.khrushchev.postcard.entity.AbstractCards;
import com.khrushchev.postcard.exception.PostCardException;

import java.util.Set;

public interface XmlParser {
    public Set<AbstractCards> parserXml(String fileAddress) throws PostCardException;
}