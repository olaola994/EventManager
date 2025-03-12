import java.io.Serial;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.Serializable;

public abstract class Event implements Serializable {
    @Serial
    private static final long serialVersionUID = -2990386744991787306L;

    private int id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private static double ticketPrice = 250.00; // atrybut klasowy
    private Location location; // atrybut złożony


    public Event(String title, LocalDate startDate, LocalDate endDate, double ticketPrice, Location location) throws ParseException {
        this.id = EventManager.generateUniqueId();
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title nie moze byc null lub pusty");
        }
        this.title = title;
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Data nie moze byc null");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        if (ticketPrice < 0) {
            throw new IllegalArgumentException("Ticket price nie moze byc ujemny");
        }
        this.ticketPrice = ticketPrice;

        if (location == null) {
            throw new IllegalArgumentException("Location nie moze byc null");
        }
        this.location = location;

        EventManager.addEvent(this);

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title nie moze byc null");
        }
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate != null) {
            this.startDate = startDate;
        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate != null) {
            this.endDate = endDate;
        }
    }


    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        if (ticketPrice > 0) {
            this.ticketPrice = ticketPrice;
        }
    }

    // atrybut pochodny
    public long getDuration() {
        if (startDate == null || endDate == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    public abstract String getEventType();

    public Location getLocation() {
        return new Location(location.getCity(), location.getStreet(),
                location.getStreetNumber(), location.getApartmentNumber(),
                location.getPostalCode(), location.getCountry());
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                ", ticketPrice=" + ticketPrice +
                ", duration=" + getDuration() + " dni" +
                ", location=" + getLocation() +
                ", type='" + getEventType() + '\'' +
                '}';
    }
}