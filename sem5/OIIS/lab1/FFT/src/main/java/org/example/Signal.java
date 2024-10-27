package org.example;

public class Signal {
    private double A;
    private int frequency;
    private double phase;
    private double T;

    public Signal (double A, int frequency, double phase, double T) {
        this.A = A;
        this.frequency = frequency;
        this.phase = phase;
        this.T = T;
    }

    public double[] genSamplingSignal(int samplingFreq){
        double[] sampleSignal = new double[samplingFreq];
        double step = T/samplingFreq;
        for(int i = 0; i < samplingFreq; i++){
            double t = i * step;
            sampleSignal[i] = A * Math.sin(2 * Math.PI * frequency * t + phase);
        }
        return sampleSignal;
    }
}
