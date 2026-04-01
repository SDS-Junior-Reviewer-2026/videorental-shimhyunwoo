package com.videorental;

public abstract class Movie {

	private String title;
	private MovieType priceCode;

	public Movie(String title, MovieType priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public MovieType getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(MovieType arg) {
		priceCode = arg;
	}

	public String getTitle() {
		return title;
	}

	abstract double getChargeFor(int daysRented);

	abstract int getFrequentRenterPointsFor(int daysRented);
}