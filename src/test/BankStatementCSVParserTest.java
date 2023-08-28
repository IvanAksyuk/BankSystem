package test;

import main.BankStatementCSVParser;
import main.BankStatementParser;
import main.BankTransaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception{
        final String line = "30-01-2017 -50 1";

        final BankTransaction result = statementParser.parseFrom(line);

        final BankTransaction excepted = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30),
                -50, 1);

        final double tolerance = 0.0d;

        Assert.assertEquals(excepted.getDate(), result.getDate());
        Assert.assertEquals(excepted.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(excepted.getType(), result.getType());
        //Assert.fail("Not yet implemented");
    }

    @Test
    public void shouldParseThreeCorrectLines() throws Exception{
        final List<String> lines = List.of(new String[]{"30-01-2017 -50 1", "30-01-2007 100 2", "20-01-2012 -40 3"});

        final List<BankTransaction> result = statementParser.parseLinesFrom(lines);

        final List<BankTransaction> excepted = List.of(
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30),
                -50, 1),
                new BankTransaction(LocalDate.of(2007, Month.JANUARY, 30),
                        100, 2),
                new BankTransaction(LocalDate.of(2012, Month.JANUARY, 20),
                        -40, 3)
                );

        final double tolerance = 0.0d;

        Assert.assertEquals(excepted, result);
        //Assert.assertEquals(excepted.getAmount(), result.getAmount(), tolerance);
        //Assert.assertEquals(excepted.getType(), result.getType());
        //Assert.fail("Not yet implemented");
    }
}
