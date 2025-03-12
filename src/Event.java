import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.io.Serializable;

public abstract class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private int id;
    private String title;
    private Date startDate;
    private Date endDate;
    private double ticketPrice;
    private Location location;

    public Event(String title, String startDate, String endDate, double ticketPrice, Location location) throws ParseException {
        this.id = EventManager.generateUniqueId();
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title nie moze byc null lub pusty");
        }
        this.title = title;
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Data nie moze byc null");
        }
        this.startDate = sdf.parse(startDate);
        this.endDate = sdf.parse(endDate);
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
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFormattedStartDate() {
        return sdf.format(startDate);
    }

    public String getFormattedEndDate() {
        return sdf.format(endDate);
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public long getDuration() {
        if (startDate == null || endDate == null) {
            return 0;
        }
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return TimeUnit.MILLISECONDS.toDays(diffInMillis);
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
                ", startDate=" + getFormattedStartDate() +
                ", endDate=" + getFormattedEndDate() +
                ", ticketPrice=" + ticketPrice +
                ", duration=" + getDuration() + " dni" +
                ", location=" + getLocation() +
                ", type='" + getEventType() + '\'' +
                '}';
    }
}