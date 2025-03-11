import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.io.Serializable;

public abstract class Event implements Serializable {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private int id;
    private String title;
    private Date startDate;
    private Date endDate;
    private double ticketPrice;
    private Location location;

    public Event(int id, String title, String startDate, String endDate, double ticketPrice, Location location) throws ParseException {
        this.id = id;
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Data nie moze byc null");
        }
        this.startDate = sdf.parse(startDate);
        this.endDate = sdf.parse(endDate);
        if (ticketPrice < 0) {
            throw new IllegalArgumentException("Ticket price cannot be negative.");
        }
        this.ticketPrice = ticketPrice;
        this.location = location;

        EventManager.addEvent(this);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
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

    public long getDuration() {
        if (startDate == null || endDate == null) {
            return 0;
        }
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return TimeUnit.MILLISECONDS.toDays(diffInMillis);
    }

    public abstract String getEventType();

    public Location getLocation() {
        return new Location(location.getId(), location.getCity(), location.getStreet(),
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
                ", type='" + getEventType() + '\'' +
                '}';
    }
}