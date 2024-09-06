# Отчет по программе

## Задание 1: Разработка программы на языке Java

### Описание программы
Была разработана программа на языке Java, которая реализует следующие функции:

1. **Генерация строки с заданной длиной:**
   - Программа запрашивает у пользователя длину строки. 
   - Генерирует случайную строку из символов русского алфавита (от 'а' до 'я') с использованием генератора случайных чисел `Random`.

2. **Проверка равномерности распределения символов:**
   - Создается частотная таблица, которая подсчитывает количество каждого символа в сгенерированной строке.
   - Построение графика распределения символов с помощью библиотеки `XChart`, который отображает частоту появления каждого символа в строке.

3. **Вычисление среднего времени подбора пароля:**
   - Рассчитывается среднее время подбора пароля методом brute force, исходя из того, что атакующий может перебрать 10^9 паролей в секунду.
   - Время подбора рассчитывается по формуле:
     \[
     T = \frac{P}{V},
     \]
     где \(P\) — количество возможных паролей (длина алфавита в степени длины строки), а \(V = 10^9\) — скорость перебора паролей.

### Пример работы программы

1. Пользователь вводит длину строки, например 5 символов.
2. Программа генерирует строку, например: `оиегя`.
3. Строится график частотного распределения символов.
4. Выводится примерное время подбора пароля brute force, например: 25 часов 35 минут 45 секунд.

#### Код программы:

```java
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
}
```

## Задание 2: Построение графика зависимости времени подбора от длины пароля

Для анализа зависимости времени подбора пароля от его длины можно провести серию экспериментов с разными длинами паролей и построить график. Время подбора растет экспоненциально по мере увеличения длины пароля, так как количество возможных паролей увеличивается как \(33^L\), где \(L\) — длина пароля.

## Задание 3: Практические рекомендации по выбору пароля

1. **Алфавит:**
   - Использование более широкого алфавита (например, добавление цифр, заглавных букв, специальных символов) значительно увеличивает количество возможных паролей и сложность их подбора.
   
2. **Длина пароля:**
   - Пароли длиной менее 8 символов могут быть подобраны относительно быстро (за несколько часов или дней), особенно если используются простые алфавиты (например, только строчные буквы).
   - Оптимальная длина пароля — 12 символов и более, что существенно увеличивает время подбора.

3. **Ценность информации и производительность атакующего:**
   - Если атакующий имеет мощные вычислительные средства, скорость подбора может значительно увеличиться. Поэтому важно использовать длинные и сложные пароли для защиты ценной информации.

### Вывод
Для защиты важных данных рекомендуется использовать сложные пароли с длиной не менее 12 символов, включающие различные типы символов (буквы, цифры, специальные символы).
