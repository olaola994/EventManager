import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.io.*;

public class EventManager {
    @Serial
    private static final long serialVersionUID = 2267496187339548359L;
    private static Set<Event> eventSet = new HashSet<>(); // ekstensja
    private static int nextId;
    private static final String fileName = "events.txt";


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

            saveAllExtents();
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
        saveAllExtents();
    }
    // trwałość ekstensji
    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(eventSet);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        eventSet = (HashSet<Event>) stream.readObject();
    }
    public static void loadAllExtents(){
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                new FileInputStream(fileName)))) {
            nextId = in.readInt();
            EventManager.readExtent(in);

        }catch (IOException | ClassNotFoundException e){
            System.err.println("Blad zapisu do pliku: " + e.getMessage());
        }
    }
    public static void saveAllExtents(){
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream(fileName)))) {

            out.writeInt(nextId);
            EventManager.writeExtent(out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getEventSize(){
        return eventSet.size();
    }
    // metoda klasowa
    public static void showEvents(){
        for(Event event : eventSet){
            System.out.println(event);
        }
    }
}
