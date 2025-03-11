import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.io.*;

public class EventManager {
    private static final Set<Event> eventSet = new HashSet<>();;
    private static int eventCounter = 0;
    private static final String fileName = "events.ser";

    public static int getEventCounter() {
        return eventCounter;
    }
    public static Set<Event> getEventSet() {
        return Collections.unmodifiableSet(eventSet);
    }
    protected static void addEvent(Event event) {
        eventSet.add(event);
        eventCounter++;
        saveEventsToFile();
    }
    protected static void removeEvent(Event event) {
        eventSet.remove(event);
        eventCounter--;
        saveEventsToFile();
    }
    public static void saveEventsToFile(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
            out.writeObject(eventSet);
        }catch (IOException e){
            System.err.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }
    public static void loadEventsFromFile(){
        File file = new File(fileName);
        if(!file.exists()){
            saveEventsToFile();
            return;
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
            Set<Event> loadedEvents = (Set<Event>) in.readObject();
            eventSet.clear();
            eventSet.addAll(loadedEvents);
            eventCounter = loadedEvents.size();
        }catch (IOException | ClassNotFoundException e){
            System.err.println("Błąd odczytu pliku: " + e.getMessage());
        }
    }
}
