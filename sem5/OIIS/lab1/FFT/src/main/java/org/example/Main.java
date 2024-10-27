package org.example;

import org.knowm.xchart.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Signal signal = new Signal(1.0, 5, 0, 1.0);
        int samplingFreq = 1024;
        double[] data = signal.genSamplingSignal(samplingFreq);

        // Выполнение БПФ
        FFT.Complex[] fftData = FFT.fft(data);

        // Построение графика амплитудного спектра
        plotFFT(fftData, samplingFreq);
    }

    // Метод для построения графика с использованием XChart
    public static void plotFFT(FFT.Complex[] fftData, int samplingFreq) {
        int N = fftData.length;
        double[] frequencies = IntStream.range(0, N / 2)
                .mapToDouble(i -> i * (double) samplingFreq / N)
                .toArray();

        double[] amplitudes = Arrays.stream(fftData)
                .mapToDouble(c -> Math.sqrt(c.real * c.real + c.imag * c.imag) / samplingFreq)  // Нормализация на количество выборок
                .toArray();
        amplitudes = Arrays.copyOfRange(amplitudes, 0, N / 2);

        // Создание графика
        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Frequency Domain")
                .xAxisTitle("Frequency (Hz)")
                .yAxisTitle("Amplitude")
                .build();

        // Добавление данных на график
        chart.addSeries("FFT", frequencies, amplitudes);

        // Отображение графика
        new SwingWrapper<>(chart).displayChart();
    }
}
