package main;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


import static java.util.stream.Collectors.toList;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions){
        this.bankTransactions = bankTransactions;
    }
    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer){
        double result = 0;
        for(final BankTransaction bankTransaction: bankTransactions){
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }
    public double calculateTotalAmount(){
        double total=0;
        for(final BankTransaction bankTransaction: bankTransactions){
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month){
        return summarizeTransactions((acc, bankTransactions)->
                bankTransactions.getDate().getMonth() == month ? acc+ bankTransactions.getAmount() : acc);
    }

    public double calculateTotalForCategory(final int category){
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions){
            if(bankTransaction.getType()==category){
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public BankTransaction getMaxTransactionFromTo(final LocalDate dateFrom, final LocalDate dateTo){
        boolean valueIsExist = false;
        BankTransaction maxBankTransaction = new BankTransaction();
        for(final BankTransaction bankTransaction: bankTransactions){
            if(bankTransaction.getDate().isAfter(dateFrom) && bankTransaction.getDate().isBefore(dateTo)){
               if(!valueIsExist || maxBankTransaction.getAmount()<bankTransaction.getAmount()){
                 valueIsExist = true;
                 maxBankTransaction = bankTransaction;

               }
            }
        }
        return maxBankTransaction;
    }

    public BankTransaction getMinTransactionFromTo(final LocalDate dateFrom, final LocalDate dateTo){
        boolean valueIsExist = false;
        BankTransaction minBankTransaction = new BankTransaction();
        for(final BankTransaction bankTransaction: bankTransactions){
            if(bankTransaction.getDate().isAfter(dateFrom) && bankTransaction.getDate().isBefore(dateTo)){
                if(!valueIsExist || minBankTransaction.getAmount()>bankTransaction.getAmount()){
                    valueIsExist = true;
                    minBankTransaction = bankTransaction;

                }
            }
        }
        return minBankTransaction;
    }

    public List<BankTransaction> findTransaction(final BankTransactionFilter bankTransactionFilter){
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction: bankTransactions){
            if(bankTransactionFilter.test(bankTransaction)){
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionGreaterThanEqual(final int amount){
        return findTransaction(bankTransaction -> bankTransaction.getAmount()>=amount);
    }
    public List<BankTransaction> findTransactionStreamAPI(final BankTransactionFilter bankTransactionFilter){
        return bankTransactions
                .stream()
                .filter(bankTransactionFilter::test).
                collect(toList());
    }

    public List<BankTransaction> findTransactionGreaterThanEqualStreamAPI(final int amount){
        return findTransactionStreamAPI(bankTransaction -> bankTransaction.getAmount()>=amount);
    }
}
