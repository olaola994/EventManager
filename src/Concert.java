import java.text.ParseException;
import java.util.List;
import java.util.Collections;

public class Concert extends Event {
    private List<String> performers;

    public Concert(int id, String title, String startDate, String endDate, double ticketPrice,  Location location, List<String> performers) throws ParseException {
        super(id, title, startDate, endDate, ticketPrice, location);
        if (performers == null || performers.isEmpty()) {
            throw new IllegalArgumentException("Performers list nie moze byc null lub empty.");
        }
        this.performers = performers;
    }

    @Override
    public String getEventType() {
        return "Concert";
    }

    public List<String> getPerformers() {
        return Collections.unmodifiableList(performers);
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
