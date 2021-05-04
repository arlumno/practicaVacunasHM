/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.csdam.pr.libreriaar;

/**
 * Conjunto de metodos (Utils varias) para para trabajar en clase de DAM.
 *
 * @author Ar
 */
public class Utils {

    /**
     * Imprime saltos de linea para simular el efecto de limpiar la consola"cls"
     *
     * @param saltos tipo int, cantidad de saltos de linea
     */
    public static void limpiarConsola(int saltos) {
        for (int i = 0; i < saltos; i++) {
            Salidas.saltoLinea();
        }
    }

    /**
     * Imprime 50 saltos de linea para simular el efecto de limpiar la
     * consola"cls"
     */
    public static void limpiarConsola() {
        limpiarConsola(50);
    }

    /**
     * Redondea un numero double a la cantidad de decimales especificados.
     * Multiplica por 10^decimales, redondea con Math.pow y despues divide.
     * <b>limitada por valores de long. Si es superior NO redondea </b>
     *
     * @param numero numero a redondear
     * @param decimales cantidad de decimales, por defecto 2
     * @return tipo double redondeado
     */
    public static double redondearDouble(double numero, byte decimales) {
        double resultado;
        if (decimales > 9) {
            decimales = 9;
        } else if (decimales < 1) {
            decimales = 1;
        }
        int multi = (int) Math.pow(10, decimales);
        resultado = (double) Math.round(numero * multi);
        resultado /= multi;
        return resultado;
    }

    /**
     * Redondea un numero double a la dos decimales. Multiplica por 100,
     * redondea con Math.pow y despues divide.
     * <b>limitada por valores de INT </b>
     *
     * @param numero numero a redondear
     * @return tipo double redondeado a dos decimales
     */
    public static double redondearDouble(double numero) {
        return redondearDouble(numero, (byte) 2);
    }
}
