package main;

import java.time.DateTimeException;
import java.time.LocalDate;

public class BankStatementValidator {

    final private String type;
    final private String date;
    final private String amount;

    public BankStatementValidator(final String type, final String date, final String amount){
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public Notification validate(){

        final Notification notification = new Notification();
        if(this.type.length() > 100){
            notification.addError("The type is too long");
        }

        final LocalDate parseDate;
        try{
            parseDate = LocalDate.parse(this.date);
            if(parseDate.isAfter(LocalDate.now())){
                notification.addError("date cannot be in the future");
            }
        }
        catch (DateTimeException e){
            notification.addError("Invalid format for date");
        }

        final double amount;
        try {
            amount = Double.parseDouble(this.amount);
        }
        catch (NumberFormatException e){
            notification.addError("Invalid format for amount");
        }

        return notification;
    }
}
