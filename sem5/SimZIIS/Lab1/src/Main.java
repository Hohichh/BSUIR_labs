import java.util.*;
import java.util.stream.*;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите длину строки: ");
        var sc = new Scanner(System.in);
        int strLength = sc.nextInt();

        String password = generatedString(strLength);
        System.out.println("Ваш пароль: " + password);
        HashMap<Character, Integer> frequencyTable = frequencyTableInit(password);

        visualize(frequencyTable);
        long time = bruteForceAttackTime(password);
        System.out.printf("\nВремя подбора пароля: %d часов %d минут %d секунд", time/3600, time%3600/60, time%60);

        timeLengthDepency();
    }

    public static String generatedString(int strLength) {
        var random = new Random();
        var genStr = new StringBuilder();
        for(int i = 0; i < strLength; i++){
            genStr.append((char)(random.nextInt((int)'а', (int)'я'+1)));
        }
        return genStr.toString();
    }

    public static HashMap<Character, Integer> frequencyTableInit(String password){
        var charFreqMap = new HashMap<Character, Integer>();
        for(char c : password.toCharArray()){
            charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);
        }
        return charFreqMap;
    }

    public static long bruteForceAttackTime(String password){
        long P = (long)Math.pow(33, password.length()); //количество возможных паролей
        long pasPerTime = (long)Math.pow(10,9); //скорость перебора
        return P/pasPerTime;
    }

    public static void visualize(HashMap<Character, Integer> frequencyTable){
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title("Char destribution")
                .xAxisTitle("Chars")
                .yAxisTitle("Frequency")
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setAvailableSpaceFill(0.99);
        chart.getStyler().setOverlapped(true);

        var xData = new ArrayList(frequencyTable.keySet().stream().map(c -> Character.toString(c)).sorted().toList());
        var yData = new ArrayList(frequencyTable.values());
        chart.addSeries("char", xData, yData);

        new SwingWrapper<>(chart).displayChart();
    }

    public static void timeLengthDepency(){
        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Time/Length Dependency")
                .xAxisTitle("Length")
                .yAxisTitle("Time")
                .build();

        var timeData = new ArrayList<Long>(); //время
        var lengthData = new ArrayList<Long>(); //длина

        for(int i = 1; i <= 7; i++){
            lengthData.add((long)i*2);
            timeData.add(bruteForceAttackTime(generatedString(i*2)));
        }
        chart.addSeries("time/length", lengthData, timeData);

        new SwingWrapper<>(chart).displayChart();
    }
}
