/*
 * NYCU Software Testing 2022 Lab 2
 */

import java.util.ArrayList;
import java.util.Calendar;

interface paypalService{
    public String doDonate();
}

// Game Database
interface GAMEDb {
    public int getScore(String playerid);
}

class Hour{
    // Return current hour (0-23)
    public int getHour(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
}

class Prison{
    private ArrayList prisonerLog;

    Prison(){prisonerLog = new ArrayList<>();}

    public ArrayList getLog(){
        return this.prisonerLog;
    }

    private void recordLog(String id){
        this.prisonerLog.add(id);
    }

    public void imprisonment (Player player) throws InterruptedException {
        Thread.sleep(604800000); // 7 days
    }

    public String crime(Player player){
        try{
            imprisonment(player);
        }catch (Exception e){}

        String id = player.getPlayerId();
        recordLog(id);

        return "After a long period of punishment, the player can leave! :)";
    }



}

class Player{
    private String PlayerId;
    private int Reputation;

    Player(){
        this.PlayerId = "9527";
        this.Reputation = -1;
    }

    Player(String PlayerId,int Reputation){
        this.PlayerId = PlayerId;
        this.Reputation = Reputation;
    }

    public String getPlayerId(){
        return this.PlayerId;
    }

    public int getReputation(){
        return this.Reputation;
    }
}

public class StrangeGame {
    Prison prison;
    GAMEDb db;
    Hour hour;
    StrangeGame(){
        this.hour = new Hour();
        this.prison = new Prison();
    }
    public void setPrison(Prison prison){
        this.prison = prison;
    }

    private int getHour(){
        return hour.getHour();
    }

    public String enterGame(Player player) throws InterruptedException{

        // playable time: 12:00-23:59
        if(getHour() >= 12){
            System.out.println("Welcome!");
        }else{
            return "The game is not yet open!";
        }

        // check reputation
        if(player.getReputation() < 0){
            System.out.println("Oops! The player ID: " + player.getPlayerId() + " should be arrested!");
            return this.prison.crime(player);
        }else{
            // ...
            return "Have a nice day!";
        }
    }

    // Get score from GAME Database.
    public int getScore(String playerId){
        return db.getScore(playerId);
    }

    public String donate(paypalService Paypal){
        String donateResult = Paypal.doDonate();
        if (donateResult.equals("Success")){
            return "Thank you";
        }else {
            return "Some errors occurred";
        }
    }

}