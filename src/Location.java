import java.io.Serializable;

public class Location implements Serializable{
    private int id;
    private String city;
    private String street;
    private int streetNumber;
    private Integer apartmentNumber;
    private String postalCode;
    private String country;

    public Location(int id, String city, String street, int streetNumber, Integer apartmentNumber, String postalCode, String country) {
        this.id = id;
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City nie moze byc null lub pusty.");
        }
        this.city = city;
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Street nie moze byc null lub pusty.");
        }
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Postal code nie moze byc null lub pusty.");
        }
        this.postalCode = postalCode;
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country nie moze byc null lub pusty.");
        }
        this.country = country;
    }

    public Location(int id, String city, String street, int streetNumber, String postalCode, String country) {
        this(id, city, street, streetNumber, null, postalCode, country);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
    public Integer getApartmentNumber() {
        return apartmentNumber;
    }
    public void setApartmentNumber(Integer apartmentNumber) {
        if (apartmentNumber != null && apartmentNumber < 0) {
            throw new IllegalArgumentException("Apartment number nie moze byc mniejsze niz 0.");
        }
        this.apartmentNumber = apartmentNumber;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

}
