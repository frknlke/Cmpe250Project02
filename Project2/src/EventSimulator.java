import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class EventSimulator {
    public int invalidAtt;
    public int canceledAtt;
    private double currTime;
    public double totalTrainingTime;
    public double totalTrainingWaiting;
    public int totalTrainingAttempt;
    public double totalPhysioTime;
    public double totalPhysioWaiting;
    public double maxPhysioWaiting;
    public int idOfMaxPhysioWaiting;
    public int totalPhysioAttempt;
    public double totalMassageTime;
    public double totalMassageWaiting;
    public int totalMassageAttempt;
    public int maxLenOfTraining;
    public int maxLenOfPhysioth;
    public int maxLenOfMassage;
    private int maxLenTraining;
    private int maxLenPhysioth;
    private int maxLenMassage;
    PriorityQueue<Event> trainingQueue = new PriorityQueue<>();
    PriorityQueue<Event> massageQueue = new PriorityQueue<>();
    PriorityQueue<Event> physQueue = new PriorityQueue<>();
    PriorityQueue<Event> arrival = new PriorityQueue<>();


    public double getCurrTime() {
        return currTime;
    }

    public EventSimulator() {
        invalidAtt=0;
        canceledAtt=0;
        currTime=0.0000000;
        totalTrainingTime=0.0000000;
        totalTrainingWaiting = 0.0000000;
        totalTrainingAttempt = 0;
        totalPhysioTime = 0.0000000;
        totalPhysioWaiting = 0.0000000;
        maxPhysioWaiting = 0.0000000;
        idOfMaxPhysioWaiting = 0;
        totalPhysioAttempt = 0;
        totalMassageTime = 0.0000000;
        totalMassageWaiting = 0.0000000;
        totalMassageAttempt = 0;
        maxLenTraining = 0;
        maxLenPhysioth = 0;
        maxLenMassage = 0;
        maxLenOfTraining=0;
        maxLenOfPhysioth=0;
        maxLenOfMassage=0;
    }

    public int getMaxLenTraining(ArrayList<TrainingCoach> coaches) {
        int count=0;
        for(int i=0;i< coaches.size();i++){
            if(coaches.get(i).isAvailable()){
                count++;
            }
        }
        if(coaches.size() > count){
            return trainingQueue.size() - count ;
        }else{
            return 0;
        }
    }

    public void setMaxLenTraining(int maxLenTraining) {
        this.maxLenTraining = maxLenTraining;
    }

    public int getMaxLenPhysioth(ArrayList<Physiotherapist> phys) {
        int count=0;
        for(int i=0;i<phys.size();i++){
            if(phys.get(i).isAvailable()){
                count++;
            }
        }
        if(phys.size()>count){
            return physQueue.size() - count ;
        }else{
            return 0;
        }
    }

    public void setMaxLenPhysioth(int maxLenPhysioth) {
        this.maxLenPhysioth = maxLenPhysioth;
    }

    public int getMaxLenMassage(ArrayList<Masseur> masseurs) {
        int count=0;
        for(int i=0;i<masseurs.size();i++){
            if(masseurs.get(i).isAvailable()){
                count++;
            }
        }
        if(masseurs.size()>count){
            return massageQueue.size() - count;
        }else{
            return 0;
        }

    }

    public void setMaxLenMassage(int maxLenMassage) {
        this.maxLenMassage = maxLenMassage;
    }

    public boolean validationCheck(Event event){
        if(event.type == 'm' && event.player.getNoOfMassRequest()>=3){
            invalidAtt++;
            //System.out.println("invalidAtt : " + event.type +  event.player.getId());
            return false;
        }
        else{
            if((event.player.isInProcess())&& (event.type=='t' || event.type=='m') ){
            canceledAtt++;
            //System.out.println("canceledatt : " + event.type + event.arrivalTime);
            return false;
        }
            else if(event.type == 'p' && event.player.getLastTrainingTime()==0){
                return false;
            }else{
                return true;
            }
        }
    }
    public void queueUpdater(ArrayList<Physiotherapist> phys,ArrayList<TrainingCoach> coach, ArrayList<Masseur> masseurs){
        boolean a =true;
        while(!physQueue.isEmpty() && a){
            for(int i=0;i<phys.size();i++){
                if((i==phys.size()-1) && !phys.get(i).isAvailable() ){
                    a=false;
                    break;
                }
                if(phys.get(i).isAvailable()){
                    Event e1 = physQueue.poll();
                    e1.duration = phys.get(i).getServiceTime();
                    phys.get(i).setCurrEvent(e1);
                    phys.get(i).setAvailable(false);
                    phys.get(i).setClient(e1.player);
                    e1.player.setInProcess(true);
                    //burayı sıfırdan e1.durationa değiştirdim
                    e1.player.setLastTrainingTime(0);
                    totalPhysioTime += phys.get(i).getServiceTime();
                    totalPhysioWaiting += (currTime - e1.arrivalTime);
                    double physioWaiting = e1.player.getPhysioWaitingTime() + (currTime -e1.arrivalTime);
                    e1.player.setPhysioWaitingTime(physioWaiting);
                    if(physioWaiting > maxPhysioWaiting){
                        idOfMaxPhysioWaiting=e1.player.getId();
                        maxPhysioWaiting = physioWaiting;
                    }else if((physioWaiting == maxPhysioWaiting) && (e1.player.getId() < idOfMaxPhysioWaiting)){
                        idOfMaxPhysioWaiting=e1.player.getId();
                    }
                    arrival.add(new Event('p',currTime+ phys.get(i).getServiceTime(),e1.player,e1.duration,false));
                    break;
                }
            }
        }
        a=true;
        while(!trainingQueue.isEmpty() && a){
            for(int i=0;i<coach.size();i++){
                if((coach.size()-1 == i) && !coach.get(i).isAvailable() ){
                    a=false;
                    break;
                }if(coach.get(i).isAvailable()){
                    Event e2 = trainingQueue.poll();
                    coach.get(i).setCurrEvent(e2);
                    coach.get(i).setAvailable(false);
                    coach.get(i).setClient(e2.player);
                    coach.get(i).setServiceTime(e2.duration);
                    e2.player.setLastTrainingTime(e2.duration);
                    totalTrainingAttempt++;
                    totalTrainingTime += e2.duration;
                    totalTrainingWaiting += (currTime - e2.arrivalTime);
                    arrival.add(new Event('t',currTime+ e2.duration,e2.player,e2.duration,false));
                    arrival.add(new Event('p',currTime + e2.duration,e2.player,0,true));
                    break;
                }

            }
        }
        a=true;
        while(!massageQueue.isEmpty() && a){
            for(int i=0;i<masseurs.size();i++){
                if((i == masseurs.size()-1) && !masseurs.get(i).isAvailable()){
                    a=false;
                    break;
                }if(masseurs.get(i).isAvailable()){
                    Event e3 = massageQueue.poll();
                    masseurs.get(i).setCurrEvent(e3);
                    masseurs.get(i).setAvailable(false);
                    masseurs.get(i).setClient(e3.player);
                    masseurs.get(i).setServiceTime(e3.duration);
                    e3.player.setNoOfMassRequest(e3.player.getNoOfMassRequest()+1);
                    double massWaiting = e3.player.getMassageWaitingTime();
                    e3.player.setMassageWaitingTime(massWaiting + currTime - e3.arrivalTime);
                    totalMassageAttempt++;
                    totalMassageTime += e3.duration;
                    totalMassageWaiting += (currTime - e3.arrivalTime);
                    arrival.add(new Event('m',currTime+ e3.duration,e3.player, e3.duration, false));
                    break;
                }
            }
        }
    }
    public void desRunner(ArrayList<Physiotherapist> phys,ArrayList<TrainingCoach> coach, ArrayList<Masseur> masseurs) {
        while(!arrival.isEmpty() ) {
            Event tmp = arrival.poll();
            currTime = tmp.arrivalTime;
            if (tmp.isArrival) {
                if (validationCheck(tmp)) {
                    if (tmp.type == 'p') {
                        physQueue.add(tmp);
                        //System.out.println("Fizyoterapi queue girdi Player id :" + tmp.player.getId() + "   arrival time: " + tmp.arrivalTime);
                        maxLenOfPhysioth=Math.max(maxLenOfPhysioth,getMaxLenPhysioth(phys));
                        //System.out.println("fizik quesine ekledi");
                        //totalPhysioAttempt++;
                       // maxLenOfPhysioth = Math.max(getMaxLenPhysioth(), maxLenOfPhysioth);
                    } else if (tmp.type == 't') {
                        trainingQueue.add(tmp);
                        //System.out.println("Training queue girdi Player id :" + tmp.player.getId() + "   arrival time: " + tmp.arrivalTime);
                        //totalTrainingAttempt++;
                        maxLenOfTraining=Math.max(maxLenOfTraining,getMaxLenTraining(coach));
                        //System.out.println("train quesine ekledi");
                        //totalTrainingTime += tmp.duration;
                        //maxLenOfTraining = Math.max(maxLenOfTraining, getMaxLenTraining());
                    } else {
                        massageQueue.add(tmp);
                        //System.out.println("Masaj queue girdi Player id :" + tmp.player.getId() + "   arrival time: " + tmp.arrivalTime);
                        maxLenOfMassage=Math.max(maxLenOfMassage,getMaxLenMassage(masseurs));
                        //System.out.println("masaj quesine ekledi");
                        //tmp.player.setNoOfMassRequest(tmp.player.getNoOfMassRequest() + 1);

                    }
                    tmp.player.setInProcess(true);
                }else{
                    continue;
                }
            } else {

                if (tmp.type == 't') {
                    for (int i = 0; i < coach.size(); i++) {
                        if(!coach.get(i).isAvailable()) {
                            if (coach.get(i).getCurrEvent().player.getId() == tmp.player.getId()) {
                                coach.get(i).setClient(null);
                                coach.get(i).setCurrEvent(null);
                                coach.get(i).setAvailable(true);
                                tmp.player.setInProcess(false);
                                tmp.player.setLastTrainingTime(tmp.duration);
                                maxLenOfPhysioth=Math.max(maxLenOfPhysioth,getMaxLenPhysioth(phys));
                                tmp.player.setInProcess(true);
                                /*
                                double trainingWaitingTime = currTime - coach.get(i).getCurrEvent().arrivalTime - coach.get(i).getCurrEvent().duration;
                                totalTrainingWaiting += trainingWaitingTime;

                                maxLenOfPhysioth = Math.max(maxLenOfPhysioth, getMaxLenPhysioth());


                                 */
                                i=coach.size();
                            }
                        }
                    }
                } else if (tmp.type == 'm') {
                    for (int i = 0; i < masseurs.size(); i++) {
                        if (!masseurs.get(i).isAvailable()) {
                            if (masseurs.get(i).getCurrEvent().player.getId() == tmp.player.getId()) {
                                //double massageWaitingTime = currTime - masseurs.get(i).getCurrEvent().arrivalTime - masseurs.get(i).getCurrEvent().duration;
                                //totalMassageWaiting += massageWaitingTime;
                                //masseurs.get(i).getClient().setMassageWaitingTime(totalMassageWaiting);
                                //totalMassageTime += masseurs.get(i).getCurrEvent().duration;
                                tmp.player.setInProcess(false);
                                masseurs.get(i).setClient(null);
                                masseurs.get(i).setAvailable(true);
                                masseurs.get(i).setCurrEvent(null);
                                maxLenOfMassage = Math.max(maxLenOfMassage, getMaxLenMassage(masseurs));
                                //System.out.println("Masajdan çıkış Player:" + tmp.player.getId() + " Time:" + currTime);
                                i=masseurs.size();
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < phys.size(); i++) {
                        if (!phys.get(i).isAvailable()) {
                            if (phys.get(i).getCurrEvent().player.getId() == tmp.player.getId()) {
                                /*
                                double waitingTime = currTime - phys.get(i).getServiceTime() - phys.get(i).getCurrEvent().arrivalTime;
                                phys.get(i).getClient().setPhysioWaitingTime(phys.get(i).getClient().getPhysioWaitingTime() + waitingTime);
                                if (waitingTime > maxPhysioWaiting) {
                                    maxPhysioWaiting = waitingTime;
                                    idOfMaxPhysioWaiting = phys.get(i).getClient().getId();
                                } else if (waitingTime == maxPhysioWaiting && phys.get(i).getClient().getId() < idOfMaxPhysioWaiting) {
                                    idOfMaxPhysioWaiting = phys.get(i).getClient().getId();
                                }
                                totalPhysioWaiting += waitingTime;
                                totalPhysioTime += phys.get(i).getServiceTime();
                                */
                                tmp.player.setInProcess(false);
                                phys.get(i).setCurrEvent(null);
                                phys.get(i).setClient(null);
                                phys.get(i).setAvailable(true);
                                //System.out.println("Fizyoterapiden çıkış: Player:" + tmp.player.getId() + " Time:" + currTime);
                                i=phys.size();
                            }
                        }
                    }
                }
            }
            if (!arrival.isEmpty()) {
                Event e1 = arrival.peek();
                if (e1.arrivalTime == currTime) {
                    continue;
                } else {
                    queueUpdater(phys, coach, masseurs);
                }
            } else {
                queueUpdater(phys, coach, masseurs);
            }

            //start


//end
        }
    }
    }

