package main;

public class HtmlExporter implements Exporter{
   @Override
   public String export(final SummaryStatistic summaryStatistic){

       String result = "<!doctype html>"+"\n";
       result += "<html lang='en'>"+"\n";
       result += "<head><title>Bank Transaction Report</title></head>"+"\n";
       result += "<body>"+"\n";
       result += "<ul>"+"\n";
       result += "<li><strong>The sum is</strong>: " + summaryStatistic.getSum() +"</li>"+"\n";
       result += "<li><strong>The max is</strong>: " + summaryStatistic.getMax() +"</li>"+"\n";
       result += "<li><strong>The min is</strong>: " + summaryStatistic.getMin() +"</li>"+"\n";
       result += "<li><strong>The average is</strong>: " + summaryStatistic.getAverage() +"</li>"+"\n";
       result += "<li><strong>The count is</strong>: " + summaryStatistic.getCount() +"</li>"+"\n";
       result += "</ul>"+"\n";
       result += "</body>"+"\n";
       result += "</html>"+"\n";
       return result;

   }
}
