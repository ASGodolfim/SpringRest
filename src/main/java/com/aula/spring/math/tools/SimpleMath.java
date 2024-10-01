package com.aula.spring.math.tools;

public class SimpleMath {

    public Double sum(Double nOne, Double nTwo) {
        return nOne + nTwo;
    }

    public Double sub(Double nOne, Double nTwo) {
        return nOne - nTwo;
    }

    public Double times(Double nOne, Double nTwo) {
        return nOne * nTwo;
    }

    public Double div(Double nOne, Double nTwo) {
        return nOne / nTwo;
    }

    public Double avg(Double nOne, Double nTwo){
        return  (nOne + nTwo) / 2;
    }

    public Double square(Double nOne) {
        return Math.sqrt (nOne);
    }

}
