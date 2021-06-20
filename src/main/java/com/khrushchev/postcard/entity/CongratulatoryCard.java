package com.khrushchev.postcard.entity;

import java.time.LocalDate;

public class CongratulatoryCard extends AbstractCards{
    private String typeOfHoliday;

    public CongratulatoryCard() {
    }


    public CongratulatoryCard(String theme, String country, int year, String author, String valuable, LocalDate dateOfSell, String typeOfHoliday) {
        super(theme, country, year, author, valuable, dateOfSell);
        this.typeOfHoliday = typeOfHoliday;
    }

    public String getTypeOfHoliday() {
        return typeOfHoliday;
    }

    public void setTypeOfHoliday(String typeOfHoliday) {
        this.typeOfHoliday = typeOfHoliday;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder("CongratulatoryCard{");
        stringBuilder.append("type of holiday - ").append(typeOfHoliday+"\n");
        stringBuilder.append(super.toString());
    return stringBuilder.toString();
    }

    public static CongratulatoryCard.Builder builder() {
        return new CongratulatoryCard.Builder();
    }
    public static class Builder {

        private CongratulatoryCard congratulatoryCard;

        private Builder() {
            congratulatoryCard = new CongratulatoryCard();
        }

        public CongratulatoryCard.Builder themeSet(String theme) {
            congratulatoryCard.setTheme(theme);
            return this;
        }

        public CongratulatoryCard.Builder countrySet(String country) {
            congratulatoryCard.setCountry(country);
            return this;
        }

        public CongratulatoryCard.Builder yearSet(int year) {
            congratulatoryCard.setYear(year);
            return this;
        }

        public CongratulatoryCard.Builder authorSet(String author) {
            congratulatoryCard.setAuthor(author);
            return this;
        }

        public CongratulatoryCard.Builder valuableSet(String valuable) {
            congratulatoryCard.setValuable(valuable);
            return this;
        }

        public CongratulatoryCard.Builder dateOfSellSet(LocalDate dateOfSell) {
            congratulatoryCard.setDateOfSell(dateOfSell);
            return this;
        }

        public CongratulatoryCard.Builder typeOfHolidayset(String typeOfHoliday) {
            congratulatoryCard.setTypeOfHoliday(typeOfHoliday);
            return this;
        }

        public CongratulatoryCard build() {
            return congratulatoryCard;
        }
    }
}
