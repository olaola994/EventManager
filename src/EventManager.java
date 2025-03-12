import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.io.*;

public class EventManager {
    private static final long serialVersionUID = 1L;
    private static final Set<Event> eventSet = new HashSet<>();;
    private static int eventCounter;
    private static int nextId;
    private static final String fileName = "events.ser";

    static {
        loadEventsFromFile();
    }
    public static int getEventCounter() {
        return eventCounter;
    }
    public static int generateUniqueId() {
        int newId = nextId;
        setNextId(newId+1);
        return newId;
    }
    protected static void setNextId(int id) {
        nextId = id;
    }
    public static Set<Event> getEventSet() {
        return Collections.unmodifiableSet(eventSet);
    }
    protected static void addEvent(Event event) {
        if (!eventSet.contains(event)) {
            eventSet.add(event);
            eventCounter++;
            saveEventsToFile();
        }
    }
    protected static void removeEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Nie mozna usuwac pustego elemntu");
        }
        if (!eventSet.contains(event)) {
            throw new IllegalArgumentException("Event, ktory probujesz usunac nie istnieje w kolekcji");
        }
        eventSet.remove(event);
        eventCounter--;
        saveEventsToFile();
    }
    public static void saveEventsToFile(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
            out.writeObject(eventSet);
        }catch (IOException e){
            System.err.println("Blad zapisu do pliku: " + e.getMessage());
        }
    }
    public static void loadEventsFromFile(){
        File file = new File(fileName);
        if(!file.exists()){
            eventSet.clear();
            eventCounter = 0;
            nextId = 1;
            return;
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
            Set<Event> loadedEvents = (Set<Event>) in.readObject();
            eventSet.clear();
            eventSet.addAll(loadedEvents);
            eventCounter = eventSet.size();
            int maxId = loadedEvents.stream()
                    .mapToInt(Event::getId)
                    .max()
                    .orElse(0);

            nextId = maxId + 1;

            EventManager.setNextId(nextId);
        }catch (IOException | ClassNotFoundException e){
            System.err.println("Blad odczytu pliku: " + e.getMessage());
        }
    }
}
