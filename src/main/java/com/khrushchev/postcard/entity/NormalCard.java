package com.khrushchev.postcard.entity;

import java.time.LocalDate;

public class NormalCard extends AbstractCards{
    private String recipient;

    public NormalCard() {
    }

    public NormalCard(String theme, String country, int year, String author, String valuable, LocalDate dateOfSell, String recipient) {
        super(theme, country, year, author, valuable, dateOfSell);
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("NormalCard{\n");
        stringBuilder.append(" recipient - ").append(recipient+"\n");
        stringBuilder.append(super.toString());
        return stringBuilder.toString();
    }
    public static NormalCard.Builder builder() {
        return new NormalCard.Builder();
    }
    public static class Builder {

        private NormalCard normalCard;

        private Builder() {
            normalCard = new NormalCard();
        }

        public NormalCard.Builder themeSet(String theme) {
            normalCard.setTheme(theme);
            return this;
        }

        public NormalCard.Builder countrySet(String country) {
            normalCard.setCountry(country);
            return this;
        }

        public NormalCard.Builder yearSet(int year) {
            normalCard.setYear(year);
            return this;
        }

        public NormalCard.Builder authorSet(String author) {
            normalCard.setAuthor(author);
            return this;
        }

        public NormalCard.Builder valuableSet(String valuable) {
            normalCard.setValuable(valuable);
            return this;
        }

        public NormalCard.Builder dateOfSellSet(LocalDate dateOfSell) {
            normalCard.setDateOfSell(dateOfSell);
            return this;
        }

        public NormalCard.Builder recipientSet(String recipient) {
            normalCard.setRecipient(recipient);
            return this;
        }

        public NormalCard build() {
            return normalCard;
        }
    }

}
