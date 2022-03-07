public class Physiotherapy extends Event{
    public Physiotherapy(char type, double arrivalTime, Player player, double duration,boolean isArrival) {
        super(type, arrivalTime, player, duration,isArrival);
    }

    @Override
    public int compareTo(Event o) {
        if(this.player.getLastTrainingTime() > o.player.getLastTrainingTime()){
            return -1;
        }else if(this.player.getLastTrainingTime() < o.player.getLastTrainingTime()){
            return 1;
        }else{
            return idCheck(o);
        }
    }
    public int idCheck(Event o){
        if (this.arrivalTime == o.arrivalTime) {
            if(this.player.getId() < o.player.getId()){
                return -1;
            }else{
                return 1;
            }
        } else if (this.arrivalTime < o.arrivalTime) {
            return -1;
        } else {
            return 1;
        }
    }
}
