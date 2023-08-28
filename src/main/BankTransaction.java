package main;

import java.time.LocalDate;
import java.util.Objects;

public class BankTransaction {
    private final LocalDate date;
    private final double amount;
    private final int type;

    public BankTransaction(final LocalDate date, final double amount, final int type){
        this.date = date;
        this.amount = amount;
        this.type = type;
    }
    public BankTransaction(){
        this.date = null;
        this.amount = 0;
        this.type = 0;
    }
    public LocalDate getDate(){
        return date;
    }
    public double getAmount(){
        return amount;
    }
   public int getType(){
        return type;
   }
   @Override
   public String toString(){
        return "Date:"+date+" "+"Amount:"+amount+" "+"Type:"+type;
   }

   @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o == null || getClass()!= o.getClass()) return false;
        BankTransaction that = (BankTransaction) o;
        return  Double.compare(that.amount, amount) ==0 &&
                date.equals(that.date) &&
                that.type == type;
   }

   @Override
    public int hashCode(){
        return Objects.hash(date,amount,type);
   }
}
