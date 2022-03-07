public class Physiotherapist{
    private double serviceTime;
    private int id;
    private boolean isAvailable;
    private Player client;
    private Event currEvent;
    private double serviceStartTime;

    public Physiotherapist(double serviceTime, int id, boolean isAvailable) {
        this.serviceTime = serviceTime;
        this.id = id;
        this.isAvailable = isAvailable;
        client=null;
        currEvent=null;
        serviceStartTime=0;
    }

    public double getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(double serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Player getClient() {
        return client;
    }

    public void setClient(Player client) {
        this.client = client;
    }

    public Event getCurrEvent() {
        return currEvent;
    }

    public void setCurrEvent(Event currEvent) {
        this.currEvent = currEvent;
    }
}
