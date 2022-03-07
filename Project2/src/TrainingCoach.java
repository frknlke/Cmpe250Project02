public class TrainingCoach {
    private boolean isAvailable;
    private int id;
    private double serviceTime;
    private Player client;
    private Event currEvent;
    private double serviceStartTime;

    public double getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(double serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public Event getCurrEvent() {
        return currEvent;
    }

    public void setCurrEvent(Event currEvent) {
        this.currEvent = currEvent;
    }

    public TrainingCoach(boolean isAvailable, int id, double serviceTime) {
        this.isAvailable = isAvailable;
        this.id = id;
        this.serviceTime = serviceTime;
        client=null;
        currEvent=null;
        serviceStartTime=0;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Player getClient() {
        return client;
    }

    public void setClient(Player client) {
        this.client = client;
    }
}
