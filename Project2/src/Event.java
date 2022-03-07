import java.util.Objects;

public class Event implements Comparable<Event> {
    public char type;
    public double arrivalTime;
    public Player player;
    public double duration;
    public boolean isArrival;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return type == event.type && Double.compare(event.arrivalTime, arrivalTime) == 0 && Double.compare(event.duration, duration) == 0 && player.equals(event.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, arrivalTime, player, duration);
    }

    public Event(char type, double arrivalTime, Player player, double duration, boolean isArrival) {
        this.type = type;
        this.arrivalTime = arrivalTime;
        this.player = player;
        this.duration=duration;
        this.isArrival=isArrival;

    }

    @Override
    public int compareTo(Event o) {
        if((this.type == 'p' && o.type == 'm') && ((this.arrivalTime==o.arrivalTime) && (this.isArrival&&o.isArrival))){
            return -1;
        }else{
            if(this.arrivalTime < o.arrivalTime){
                return -1;
            }else if(this.arrivalTime > o.arrivalTime){
                return 1;
            }else{
                if(this.isArrival && !o.isArrival){
                    return +1;
                }else{
                    return -1;
                }

            }
        }

    }
    /*
    @Override
    public int compareTo(Event o) {
        if(this.type == o.type){
            if(this.type == 'm'){
                if(this.player.getSkillLevel() > o.player.getSkillLevel()){
                    return 1;
                }else if(this.player.getSkillLevel() < o.player.getSkillLevel()){
                    return -1;
                }else{
                    return idCheck(o);
                }
            }
            else if(this.type == 'p'){
                if(this.player.getLastTrainingTime() > o.player.getLastTrainingTime()){
                    return 1;
                }else if(this.player.getLastTrainingTime() < o.player.getLastTrainingTime()){
                    return -1;
                }else{
                    return idCheck(o);
                }
            }else{
                return idCheck(o);
            }
        }else{
            System.out.println("Type Mismatch");
            return 0;
        }

    }
    public int idCheck(Event o){
        if (this.arrivalTime == o.arrivalTime) {
            if(this.player.getId() < o.player.getId()){
                return 1;
            }else{
                return -1;
            }
        } else if (this.arrivalTime < o.arrivalTime) {
            return 1;
        } else {
            return -1;
        }
    }
    */
}

