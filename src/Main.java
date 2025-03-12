import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException{
        File file = new File("events.txt");
        if(file.exists() && file.length() > 0){
            EventManager.loadAllExtents();
        }
        Location loc1 = new Location("Warszawa", "Marszałkowska", 10, null, "00-001", "Polska");
        Location loc2 = new Location("Kraków", "Długa", 15, 3, "31-100", "Polska");
        List<String> list1 = new ArrayList<>();
        list1.add("Miles Davis2");
        list1.add("John Coltrane");
        List<String> list2 = new ArrayList<>();
        list2.add("AC/DC");
        list2.add("Queen");
        list2.add("Metallica2");
            Concert concert1 = new Concert("Rock Festival", LocalDate.of(2025,3,13), LocalDate.of(2025,3,13), 250.0, loc1,
                   list1);
            Concert concert2 = new Concert( "Jazz Night", LocalDate.of(2025,5,20), LocalDate.of(2025,5,21), 180.0, loc2,
                    list2);

//        System.out.println(EventManager.getEventSet().size());

        EventManager.showEvents();
    }
}