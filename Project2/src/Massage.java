public class Massage extends Event{
    public Massage(char type, double arrivalTime, Player player, double duration,boolean isArrival) {
        super(type, arrivalTime, player, duration,isArrival);
    }

    @Override
    public int compareTo(Event o) {
        if(this.player.getSkillLevel() > o.player.getSkillLevel()){
            return -1;
        }else if(this.player.getSkillLevel() < o.player.getSkillLevel()){
            return 1;
        }else{
            return idCheck(o);
        }
    }
    public int idCheck(Event o){
        if(this.arrivalTime < o.arrivalTime){
            return  -1;
        }else if(this.arrivalTime > o.arrivalTime){
            return 1;
        }else{
            if(this.player.getId() < o.player.getId()){
                return -1;
            }else{
                return 1;
            }
        }
    }
}
