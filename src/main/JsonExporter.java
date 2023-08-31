package main;

public class JsonExporter implements Exporter{
    @Override
    public String export(final SummaryStatistic summaryStatistic){

        String result = "{"+"\n";

        result += "\"Summary\": ["+"\n";
        result +="{\n";
        result += "\"The sum is\":" + summaryStatistic.getSum()+",\n";
        result += "\"The max is\": " + summaryStatistic.getMax() +",\n";
        result += "\"The min is\": " + summaryStatistic.getMin() +",\n";
        result += "\"The average is\": " + summaryStatistic.getAverage() +",\n";
        result += "\"The count is\": " + summaryStatistic.getCount() +"\n";
        result += "}\n";
        result += "]\n";
        result += "}";
        return result;

    }
}
