package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    private static final String RESOURCES = "C:\\Users\\demon\\IdeaProjects\\BankSystemJavaAksyuk\\src\\";
    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
        final Path path = Paths.get(RESOURCES+fileName);

        final List<String> lines = Files.readAllLines(path);


        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);


        collectSummary(bankStatementProcessor);

        final List<BankTransaction> transactions =
                bankStatementProcessor.findTransactionGreaterThanEqualStreamAPI(10);

        final BankStatementProcessor bankStatementProcessor1
                = new BankStatementProcessor(transactions);

        collectSummary(bankStatementProcessor1);

        SummaryStatistic summaryStatistic = new SummaryStatistic(bankStatementProcessor.calculateTotalAmount(),
                bankStatementProcessor.getMaxTransactionFromTo(LocalDate.MIN, LocalDate.MAX).getAmount(),
                bankStatementProcessor.getMaxTransactionFromTo(LocalDate.MIN, LocalDate.MAX).getAmount(),
                bankStatementProcessor.getAverage(),
                bankStatementProcessor.getCount());
        HtmlExporter htmlExporter = new HtmlExporter();
        String htmlFile = htmlExporter.export(summaryStatistic);
        BufferedWriter writer = new BufferedWriter(new FileWriter("HtmlFile.html"));
        writer.write(htmlFile);

        writer.close();
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor){
        System.out.println("Total: "+bankStatementProcessor.calculateTotalAmount());
        //System.out.println();
        //System.out.println("Total April: "+bankStatementProcessor.calculateTotalInMonth(Month.APRIL));
        //System.out.println("Total January: "+bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        //System.out.println("Total Category 1: "+bankStatementProcessor.calculateTotalForCategory(1));
        System.out.println("Total in January: "+ bankStatementProcessor.calculateTotalInMonth(Month.JANUARY) );
        System.out.println("Max Bank Transaction from 01-01-2000 to 01-01-2023: \n"+
                bankStatementProcessor.getMaxTransactionFromTo(
                        LocalDate.of(2000,1,1),
                        LocalDate.of(2023,1,1)));
        System.out.println("Min Bank Transaction from 01-01-2000 to 01-01-2023: \n"+
                bankStatementProcessor.getMinTransactionFromTo(
                        LocalDate.of(2000,1,1),
                        LocalDate.of(2023,1,1)));
        System.out.println();
    }
}
