package org.specVisualizer.Model.MathUtil;

public class FastFourierTransform {

    public static Complex[] FFT(Complex[] x){
        int n = x.length;
        if( n == 1) return new Complex[]{x[0]};
        if (n % 2 != 0) {
            throw new IllegalArgumentException("n is not a power of 2");
        }

        Complex [] even = new Complex[n/2];
        for (int k = 0; k < n/2; k++) even[k] = x[2*k];

        Complex [] evenFFT = FFT(even);

        Complex [] odd = even;
        for (int k = 0; k < n/2; k++) odd[k] =  x[2*k + 1];

        Complex [] oddFFT = FFT(odd);
        // combine
        Complex[] y = new Complex[n];
        for (int k = 0; k < n/2; k++) {
            double kth = -2 * k * Math.PI / n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = evenFFT[k].plus (wk.times(oddFFT[k]));
            y[k + n/2] = evenFFT[k].minus(wk.times(oddFFT[k]));
        }
        return y;
    }
}
