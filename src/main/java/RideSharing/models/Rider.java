package RideSharing.models;

public class Rider {
    String id;
    String name;

    public Rider(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
