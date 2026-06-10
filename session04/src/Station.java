public class Station implements Describable{
    private String name;
    private String location;
    public Station(String name, String location) {
        this.name = name;
        this.location = location;
    }
    @Override
    public String describe() {
        return "Station " + name + " in " + location;
    }
}