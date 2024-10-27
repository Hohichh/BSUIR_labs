package org.example;

public class FFT {

    public static Complex[] fft(double[] sampleSignal){
        int N = sampleSignal.length;

        if(N <= 1) return new Complex[]{new Complex(sampleSignal[0], 0)};

        double[] even = new double[N/2];
        double[] odd = new double[N/2];

        for(int i = 0; i < N/2; i++){
            even[i] = sampleSignal[i*2];
            odd[i] = sampleSignal[i*2 + 1];
        }

        Complex[] fftEven = fft(even);
        Complex[] fftOdd = fft(odd);

        Complex[] result = new Complex[N];

        for(int k = 0; k < N / 2; k++){
            double angle = -2 * Math.PI * k / N;
            Complex wk = new Complex(Math.cos(angle), Math.sin(angle));  // Вращающий фактор W_N^k
            result[k] = fftEven[k].plus(wk.times(fftOdd[k]));           // X[k] = E[k] + W_N^k * O[k]
            result[k + N / 2] = fftEven[k].minus(wk.times(fftOdd[k]));  // X[k+N/2] = E[k] - W_N^k * O[k]
        }

        return result;
    }

    static class Complex{
        public double real;
        public double imag;

        public Complex(double real, double imag){
            this.real = real;
            this.imag = imag;
        }

        public Complex plus(Complex other){
            return new Complex(this.real + other.real, this.imag + other.imag);
        }

        public Complex minus(Complex other){
            return new Complex(this.real - other.real, this.imag - other.imag);
        }

        public Complex times(Complex other){
            return new Complex(
                    this.real * other.real - this.imag * other.imag,
                    this.real * other.imag + this.imag * other.real
            );
        }

    }
}

