import java.io.Serializable;

public class Location implements Serializable{
    private static final long serialVersionUID = 1L;
    private String city;
    private String street;
    private int streetNumber;
    private Integer apartmentNumber;
    private String postalCode;
    private String country;

    public Location(String city, String street, int streetNumber, Integer apartmentNumber, String postalCode, String country) {
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

    public Location(String city, String street, int streetNumber, String postalCode, String country) {
        this(city, street, streetNumber, null, postalCode, country);
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
    @Override
    public String toString() {
        return "Location{'" +
                "city=" + this.getCity() +
                ", street=" + this.getStreet() +
                ", streetNumber=" + this.getStreetNumber() +
                (apartmentNumber != null ? ", apartmentNumber=" + apartmentNumber : "") +
                ", postalCode=" + this.getPostalCode()  +
                ", country=" + getCountry() +
                '}';
    }
}
