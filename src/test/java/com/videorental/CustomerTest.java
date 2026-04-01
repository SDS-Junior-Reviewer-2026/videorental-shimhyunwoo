package com.videorental;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    public static final String NAME = "NAME_NOT_IMPORTANT";
    public static final String TITLE = "TITLE_NOT_IMPORTANT";

    Customer customer = new Customer(NAME);

    @Test
    public void returnNewCustomer() {
        assertThat(customer).isNotNull();
    }

    @Test
    public void statementForNoRental() {
        // arrange

        // act

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "Amount owed is 0.0\n"
                + "You earned 0 frequent renter pointers");
    }

    @Test
    public void statementForRegularMovieRentalForLessThan3Days() {
        // arrange
        customer.addRental(createRentalFor(Movie.REGULAR, 2));

        // act

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 2.0\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForRegularMovieRentalForLessThan2Days() {
        // arrange
        customer.addRental(createRentalFor(Movie.REGULAR, 3));

        // act

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.5\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForNewReleaseMovie() {
        // arrange
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 1));

        // act

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForChildrenMovieRentalMoreThan3Days() {
        // arrange
        customer.addRental(createRentalFor(Movie.CHILDRENS, 4));

        // act

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForChildrenMovieRentalMoreThan4Days() {
        // arrange
        customer.addRental(createRentalFor(Movie.CHILDRENS, 3));

        // act

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t1.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent renter pointers");
    }

    @Test
    public void statementForNewReleaseMovieRentalMoreThan1Day() {
        // arrange
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 2));

        // act

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t6.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 6.0\n"
                + "You earned 2 frequent renter pointers");
    }

    @Test
    public void statementForFewMoviesRental() {
        // arrange
        customer.addRental(createRentalFor(Movie.REGULAR, 1));
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 4));
        customer.addRental(createRentalFor(Movie.CHILDRENS, 4));

        // act

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "\t12.0(TITLE_NOT_IMPORTANT)\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 17.0\n"
                + "You earned 4 frequent renter pointers");
    }

    // 하나 추가함 -> Movie 클래스의 메서드 활용
    @Test
    public void movieTitleAndPriceCode() {
        Movie movie = getMovie(Movie.REGULAR);
        assertThat(movie.getTitle()).isEqualTo(TITLE);
        assertThat(movie.getPriceCode()).isEqualTo(Movie.REGULAR);
        movie.setPriceCode(Movie.NEW_RELEASE);
        assertThat(movie.getPriceCode()).isEqualTo(Movie.NEW_RELEASE);
    }

    //** 헬퍼 메서드 **//

    private static Rental createRentalFor(int priceCode, int daysRented) {
        Movie movie = getMovie(priceCode);
        Rental rental = new Rental(movie, daysRented);
        return rental;
    }

    private static Movie getMovie(int priceCode) {
        switch (priceCode) {
            case Movie.REGULAR:
                return new RegularMovie(TITLE);
            case Movie.NEW_RELEASE:
                return new NewReleaseMovie(TITLE);
            case Movie.CHILDRENS:
                return new ChildrenMovie(TITLE);
            default:
                return null;
        }
    }

}
