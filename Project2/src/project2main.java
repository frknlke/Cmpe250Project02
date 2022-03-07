import java.io.*;
import java.util.*;

import static java.lang.System.out;

public class project2main {

    public static void main(String[] args)throws IOException {
	// write your code here
        String inputFileName = args[0];
        File myInputFile = new File(inputFileName);

        ArrayList<Physiotherapist> physio = new ArrayList<>();
        ArrayList<TrainingCoach> coaches = new ArrayList<>();
        ArrayList<Masseur> masseurs = new ArrayList<>();
        Map<Integer,Player> players = new HashMap<>();
        EventSimulator des = new EventSimulator();
      try{
          Scanner sc = new Scanner(myInputFile);
          sc.useLocale(Locale.US);

         //input of players' specs
         int numOfPlayers = sc.nextInt();
         for(int i=0;i<numOfPlayers;i++){
             int id = sc.nextInt();
             int skillLevel = sc.nextInt();
             players.put(id,new Player(id,skillLevel));
         }
         int totalArrival = sc.nextInt();
         //out.println(totalArrival);
          char type= 'a';
          int idOfArrival=0;
          double arrivalTime=0.0000;
          double duration=0.0000;
         //arrivals
         for(int i=0;i<totalArrival && sc.hasNext();i++){
             String s = sc.next();
             type = s.charAt(0);
             if(sc.hasNextInt()) {
                 idOfArrival = sc.nextInt();
             }
             if(sc.hasNextDouble()) {
                 arrivalTime = sc.nextDouble();
             }
             if(sc.hasNextDouble()) {
                 duration = sc.nextDouble();
             }

                 des.arrival.add(new Event(type, arrivalTime, players.get(idOfArrival), duration, true));


             //out.println("Eklendi");
             /*
             String arrivalSpecs = sc.nextLine();
             Scanner scanner = new Scanner(arrivalSpecs);
             scanner.useLocale(Locale.US);

                while(scanner.hasNext()) {
                    type = sc.next().charAt(0);
                    idOfArrival = sc.nextInt();
                    arrivalTime = sc.nextDouble();
                    duration = sc.nextDouble();
                    des.arrival.add(new Event(type, arrivalTime, players.get(idOfArrival), duration, true));
                    break;
                    //des.addArrival(new Event(type,arrivalTime,players.get(idOfArrival),duration));

                }
                */
         }

         //physioths
          //line consist of integer N and N many floats that each corr to service time of an physio.
          //String line = sc.nextLine();
         //Scanner scan = new Scanner(line);
         //scan.useLocale(Locale.US);
          String l="";
         if(sc.hasNext()){
             l=sc.next();
         int numOfPhysioths=Integer.parseInt(l);
          for(int i=0;i<numOfPhysioths && sc.hasNext();i++){
              l=sc.next();
            double f = Double.parseDouble(l);
            //out.println("fizyoterapist eklendi");
            physio.add(new Physiotherapist(f,i,true));
          }
         }
          int numOfCoaches=0;
          int numOfMasseurs=0;
         if(sc.hasNextInt()){
          numOfCoaches = sc.nextInt();
         }if(sc.hasNextInt()) {
              numOfMasseurs = sc.nextInt();
          }
         //out.println(numOfCoaches);
         //out.println(numOfMasseurs);
          for(int i=0;i<numOfCoaches;i++){
              //out.println("koç eklendi");
              coaches.add(new TrainingCoach(true,i,0));
          }
          for(int i=0;i<numOfMasseurs;i++){
              //out.println("masör eklendi");
              masseurs.add(new Masseur(true,i,0));
          }
            //out.println("Çalıştı");
            des.desRunner(physio,coaches,masseurs);


      }
      catch (FileNotFoundException e) {
          out.println("Catch - An error occurred.");
          e.printStackTrace();
      }
      /*
      finally {
          try {
              if(fileReader != null){
                  fileReader.close();
              }
          }catch (IOException ioe){
              out.println("Error occured while closing bufferedReader");
          }
      }
      */
        String outputFileName = args[1];
        File myOutputFile = new File(outputFileName);
        try {
            if (myOutputFile.createNewFile()) {
                //System.out.println("File created: " + outputFileName);
            } else {
                out.println("File already exists.");
                out.println("MyFileIO ends with existing output file");
                return;
            }
            FileWriter myWriter = new FileWriter(outputFileName);
            //output
            //out.println("yazmaya başladı");
            myWriter.write(des.maxLenOfTraining + "\n");
            myWriter.write(des.maxLenOfPhysioth + "\n");
            myWriter.write(des.maxLenOfMassage + "\n");
            int totalTrAtt = (des.totalTrainingAttempt==0) ? 1 : des.totalTrainingAttempt;
            double avgWaiting = (double) (des.totalTrainingWaiting) / (double)(totalTrAtt);
            String s= String.format(Locale.US,"%.3f",avgWaiting);
            //avg wait. in the train. queue
            myWriter.write(s + "\n");
            int totalPhyAtt = (des.totalPhysioAttempt==0) ? 1 : des.totalPhysioAttempt;
            avgWaiting = (double) (des.totalPhysioWaiting) / (double)(totalTrAtt);
            s= String.format(Locale.US,"%.3f",avgWaiting);
            //avg wait. in the physiot queue
            myWriter.write(s + "\n");
            int totalMassAtt= (des.totalMassageAttempt==0) ? 1 : des.totalMassageAttempt;
            avgWaiting = (double) (des.totalMassageWaiting) / (double)(totalMassAtt);
            s= String.format(Locale.US,"%.3f",avgWaiting);
            //avg wait. in the massage queue
            myWriter.write(s + "\n");
            //out.println("Total massage attemp: " + des.totalMassageAttempt + "total massage waiting: " + des.totalMassageWaiting);

            double averageTime = (double) (des.totalTrainingTime) / (double) (totalTrAtt);
            s= String.format(Locale.US,"%.3f",averageTime);
            //avg training time
            myWriter.write(s + "\n");

            averageTime = (double) (des.totalPhysioTime) / (double) (totalTrAtt);
            s= String.format(Locale.US,"%.3f",averageTime);
            //avg physiotherapy time
            myWriter.write(s + "\n");

            averageTime = (double) (des.totalMassageTime) / (double) (totalMassAtt);
            s= String.format(Locale.US,"%.3f",averageTime);
            //avg massage time
            myWriter.write(s + "\n");


            averageTime = (double)(des.totalPhysioTime + des.totalTrainingTime + des.totalPhysioWaiting + des.totalTrainingWaiting) / (double) (totalTrAtt);
            s= String.format(Locale.US,"%.3f",averageTime);
            //avg turnaround time
            myWriter.write(s + "\n");

            //max physiot waiting
            s=String.format(Locale.US,"%.3f",des.maxPhysioWaiting);
            myWriter.write(des.idOfMaxPhysioWaiting + " " + s + "\n");

            //min massage waiting
            int maxId=-1;
            Iterator<Integer> i = players.keySet().iterator();
            while(i.hasNext()){
                int a = i.next();
                if(players.get(a).getNoOfMassRequest()==3 && maxId==-1){
                    maxId=a;
                }else if(players.get(a).getNoOfMassRequest()==3 && maxId!=-1){
                    if(players.get(a).getMassageWaitingTime() < players.get(maxId).getMassageWaitingTime()){
                        maxId=a;
                    }else if(players.get(a).getMassageWaitingTime() == players.get(maxId).getMassageWaitingTime()){
                        if(a < maxId){
                            maxId=a;
                        }
                    }
                }
            }
            if(maxId == -1){
                myWriter.write("-1" + " " + "-1");
                myWriter.write("\n");
            }else{
                s=String.format(Locale.US,"%.3f",players.get(maxId).getMassageWaitingTime());
                myWriter.write(maxId + " " + s);
                myWriter.write("\n");
            }

            myWriter.write(des.invalidAtt + "\n");

            myWriter.write(des.canceledAtt + "\n");

            myWriter.write(String.format(Locale.US,"%.3f",des.getCurrTime()));

            myWriter.close();
        } catch (IOException e) {
            out.println("Catch - An error occurred.");
            e.printStackTrace();
        }
    }
}
