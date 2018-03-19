package com.example.cz17a.quizclient.Src;

/**
 * Created by Hella on 19.03.2018.
 */

public class Jackpot {
    private int amount;
    private boolean isActive;

    public Jackpot(){
        this.amount = 0;
        this.isActive = false;
    }


    public Jackpot(int initialAmount){
        this.amount = initialAmount;
        this.isActive = false;
    }

    public int getAmount(){
        return this.amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public boolean isActive(){
        return this.isActive;
    }

    public void activateJackpot(){
        this.isActive = true;
    }

    public void deactivateJackpot(){
        this.isActive = false;
    }

    public void updatePoints(int amount){
        this.amount += amount;
    }

    public void clear(){
        this.amount = 0;
        this.isActive = false;
    }

    public int payout(double responseTime){
        if(isActive) {
            if (responseTime < 5) {
                return this.amount;
            } else if (responseTime >= 5 && responseTime < 10) {
                return (int) (this.amount * 0.9);
            } else if (responseTime >= 10 && responseTime < 15) {
                return (int) (amount * 0.7);
            } else if (responseTime >= 15 && responseTime < 20) {
                return (int) (amount * 0.5);
            } else if (responseTime >= 20 && responseTime < 25) {
                return (int) (amount * 0.3);
            } else if (responseTime >= 25 && responseTime < 30) {
                return (int) (amount * 0.1);
            } else return 0;
        }
        else return -1;
    }
}
