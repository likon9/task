package com.khrushchev.postcard.parser.sax;

public enum PostCardTypesTags {
    CONGRATULATORY_CARD,
    NORMAL_CARD,
    ADVERTISING_CARD,
    THEME,
    COUNTRY,
    YEAR,
    AUTHOR,
    VALUABLE,
    DATE_OF_SELL,
    TYPE_OF_HOLIDAY,
    OLD_CARDS,
    RECIPIENT,
    TARGETS_FOR_ADVERTISING_BY_AGE,
    TARGETS_FOR_ADVERTISING_BY_GENDER;
    public String tagName() {
        return name().replace("_", "-").toLowerCase();
    }
}
