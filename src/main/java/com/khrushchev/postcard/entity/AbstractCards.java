package com.khrushchev.postcard.entity;

import java.time.LocalDate;

public abstract class AbstractCards {
    private String theme;
    private String country;
    private int year;
    private String author;
    private String valuable;
    private LocalDate dateOfSell;

    public AbstractCards() {
    }

    public AbstractCards(String theme, String country, int year, String author, String valuable, LocalDate dateOfSell) {
        this.theme = theme;
        this.country = country;
        this.year = year;
        this.author = author;
        this.valuable = valuable;
        this.dateOfSell = dateOfSell;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getValuable() {
        return valuable;
    }

    public void setValuable(String valuable) {
        this.valuable = valuable;
    }

    public LocalDate getDateOfSell() {
        return dateOfSell;
    }

    public void setDateOfSell(LocalDate dateOfSell) {
        this.dateOfSell = dateOfSell;
    }

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(" date of sell - ").append(dateOfSell+"\n");
        stringBuilder.append(" theme - ").append(theme+"\n");
        stringBuilder.append(" country - ").append(country+"\n");
        stringBuilder.append(" year - ").append(year+"\n");
        stringBuilder.append(" author - ").append(author+"\n");
        stringBuilder.append(" valuable - ").append(valuable +"}");
        return  stringBuilder.toString();

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
