import java.io.Serial;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Concert extends Event {
    @Serial
    private static final long serialVersionUID = -7544500528767035782L;
    private List<String> performers; // atrybut powtarzalny

    public Concert(String title, LocalDate startDate, LocalDate endDate, double ticketPrice,
                   Location location, List<String> performers) throws ParseException {
        super(title, startDate, endDate, ticketPrice, location);
        if (performers == null || performers.isEmpty()) {
            throw new IllegalArgumentException("Performers list nie moze byc null lub empty.");
        }
        this.performers = performers;
    }


    // przesłonięcie
    @Override
    public String getEventType() {
        return "Concert";
    }

    public List<String> getPerformers() {
        return performers;
    }
    public void setPerformers(List<String> performers) {
        if (performers == null || performers.isEmpty()) {
            throw new IllegalArgumentException("Performers list nie moze byc null lub empty.");
        }
        this.performers = performers;
    }

    @Override
    public String toString() {
        return super.toString() + ", performers=" + performers + "}";
    }
    public void addPerformer(String performer) {
        if (performer == null || performer.trim().isEmpty()) {
            throw new IllegalArgumentException("Performer cannot be null or empty.");
        }
        performers.add(performer);
    }
    public void removePerformer(String performer) {
        if (performers.size() == 1) {
            throw new IllegalStateException("Cannot remove the last performer.");
        }
        performers.remove(performer);
    }
}
