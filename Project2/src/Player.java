import java.util.Objects;

public class Player implements Comparable<Player> {
    private int id;
    private int skillLevel;
    private int noOfMassRequest;
    private boolean isInProcess;
    private double lastTrainingTime;
    private double totalTrainingTime;
    private boolean isTrained;
    private double massageWaitingTime;
    private double physioWaitingTime;

    public double getMassageWaitingTime() {
        return massageWaitingTime;
    }

    public void setMassageWaitingTime(double massageWaitingTime) {
        this.massageWaitingTime = massageWaitingTime;
    }

    public double getPhysioWaitingTime() {
        return physioWaitingTime;
    }

    public void setPhysioWaitingTime(double physioWaitingTime) {
        this.physioWaitingTime = physioWaitingTime;
    }

    public boolean isTrained() {
        return isTrained;
    }

    public void setTrained(boolean trained) {
        isTrained = trained;
    }

    public double getTotalTrainingTime() {
        return totalTrainingTime;
    }

    public void setTotalTrainingTime(double totalTrainingTime) {
        this.totalTrainingTime = totalTrainingTime;
    }

    public double getLastTrainingTime() {
        return lastTrainingTime;
    }

    public void setLastTrainingTime(double lastTrainingTime) {
        this.lastTrainingTime = lastTrainingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public int getNoOfMassRequest() {
        return noOfMassRequest;
    }

    public void setNoOfMassRequest(int noOfMassRequest) {
        this.noOfMassRequest = noOfMassRequest;
    }

    public boolean isInProcess() {
        return isInProcess;
    }

    public void setInProcess(boolean inProcess) {
        isInProcess = inProcess;
    }

    public Player(int id, int skillLevel) {
        this.id = id;
        this.skillLevel = skillLevel;
        this.isInProcess = false;
        noOfMassRequest=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getId() == player.getId() && getSkillLevel() == player.getSkillLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSkillLevel());
    }

    @Override
    public int compareTo(Player o) {
        return 0;
    }
}
