import java.text.ParseException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ParseException{
        try {
            EventManager.loadEventsFromFile();
            Location loc1 = new Location(1, "Warszawa", "Marszałkowska", 10, null, "00-001", "Polska");
            Location loc2 = new Location(2, "Kraków", "Długa", 15, 3, "31-100", "Polska");

            Concert concert1 = new Concert(1, "Rock Festival", "11/03/2025", "13/03/2025", 250.0, loc1,
                    Arrays.asList("Metallica", "AC/DC", "Queen"));
            Concert concert2 = new Concert(2, "Jazz Night", "20/05/2025", "21/05/2025", 180.0, loc2,
                    Arrays.asList("Miles Davis", "John Coltrane"));

            System.out.println("Liczba wydarzeń: " + EventManager.getEventCounter());
            System.out.println("Lista wydarzeń:");
            for (Event e : EventManager.getEventSet()) {
                System.out.println(e);
            }

            EventManager.saveEventsToFile();

        } catch (ParseException e) {
            System.out.println("Błąd parsowania daty: " + e.getMessage());
        }
    }
}