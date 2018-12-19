package com.eventify.webscraper;

/**
 * Created by spasoje on 19-Nov-18.
 */
public class Price {
    private int priceAmmount;
    private String seatDescription;

    public int getPriceAmmount() {
        return priceAmmount;
    }

    public void setPriceAmmount(int priceAmmount) {
        this.priceAmmount = priceAmmount;
    }

    public String getSeatDescription() {
        return seatDescription;
    }

    public void setSeatDescription(String seatDescription) {
        this.seatDescription = seatDescription;
    }

    @Override
    public String toString() {
        return "Price{" +
                "priceAmmount=" + priceAmmount +
                ", seatDescription='" + seatDescription + '\'' +
                '}';
    }
}
