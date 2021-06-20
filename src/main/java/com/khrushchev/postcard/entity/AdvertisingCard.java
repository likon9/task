package com.khrushchev.postcard.entity;

import java.time.LocalDate;

public class AdvertisingCard extends AbstractCards{
    private String targetsForAdvertisingByAge;
    private String targetsForAdvertisingByGender;

    public AdvertisingCard() {
    }

    public AdvertisingCard(String theme, String country, int year, String author, String valuable, LocalDate dateOfSell, String targetsForAdvertisingByAge, String targetsForAdvertisingByGender) {
        super(theme, country, year, author, valuable, dateOfSell);
        this.targetsForAdvertisingByAge = targetsForAdvertisingByAge;
        this.targetsForAdvertisingByGender = targetsForAdvertisingByGender;
    }



    public String getTargetsForAdvertisingByAge() {
        return targetsForAdvertisingByAge;
    }

    public void setTargetsForAdvertisingByAge(String targetsForAdvertisingByAge) {
        this.targetsForAdvertisingByAge = targetsForAdvertisingByAge;
    }

    public String getTargetsForAdvertisingByGender() {
        return targetsForAdvertisingByGender;
    }

    public void setTargetsForAdvertisingByGender(String targetsForAdvertisingByGender) {
        this.targetsForAdvertisingByGender = targetsForAdvertisingByGender;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("AdvertisingCard{\n");
        stringBuilder.append(" targets for advertising by age - ").append(targetsForAdvertisingByAge+"\n");
        stringBuilder.append(" targets for advertising by gender - ").append(targetsForAdvertisingByGender+"\n");
        stringBuilder.append(super.toString());
        return stringBuilder.toString();
    }

    public static Builder builder() {
        return new Builder();
    }
        public static class Builder {

        private AdvertisingCard advertisingCard;

        private Builder() {
            advertisingCard = new AdvertisingCard();
        }

            public Builder themeSet(String theme){
                advertisingCard.setTheme(theme);
                return  this;
            }
            public Builder countrySet(String country){
                advertisingCard.setCountry(country);
                return  this;
            }
            public Builder yearSet(int year){
                advertisingCard.setYear(year);
                return  this;
            }
            public Builder authorSet(String author){
                advertisingCard.setAuthor(author);
                return  this;
            }
            public Builder valuableSet(String valuable){
                advertisingCard.setValuable(valuable);
                return  this;
            }
            public Builder dateOfSellSet(LocalDate dateOfSell){
                advertisingCard.setDateOfSell(dateOfSell);
                return  this;
            }
            public Builder targetsForAdvertisingByAgeSet(String targetsForAdvertisingByAge){
                advertisingCard.setTargetsForAdvertisingByAge(targetsForAdvertisingByAge);
                return  this;
            }
            public Builder targetsForAdvertisingByGenderSet(String targetsForAdvertisingByGender){
                advertisingCard.setTargetsForAdvertisingByGender(targetsForAdvertisingByGender);
                return  this;
            }
            public AdvertisingCard build() {
                return advertisingCard;
            }
    }
    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = result * prime + getDateOfSell().hashCode();
        result = result * prime + getTheme().hashCode();
        result = result * prime + getCountry().hashCode();
        result = result * prime + Integer.hashCode(getYear());
        result = result * prime + getAuthor().hashCode();
        result = result * prime + getValuable().hashCode();
        result = result * prime + getTargetsForAdvertisingByAge().hashCode();
        result = result * prime + getValuable().hashCode();

        return result;
    }

}
