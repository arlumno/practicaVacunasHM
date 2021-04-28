/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.vacunas.practicavacunas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ar
 */
public class Vacuna extends VacunaAutorizacion {

    private String codigo;
    private String nombre;
    private String principioActivo;
    private String farmaceutica;
    private double pvp;

    /**
     *
     *
     * @param codigo codigo de la vacuna, debe ser previamente validado por
     * validarCodigo(codigo)
     * @param nombre
     * @param principioActivo
     * @param farmaceutica
     * @param pvp
     */
    public Vacuna(String codigo, String nombre, String principioActivo, String farmaceutica, double pvp) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.principioActivo = principioActivo;
        this.farmaceutica = farmaceutica;
        this.pvp = pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public double getPvp() {
        return pvp;
    }

    public String getCodigo() {
        return codigo;
    }

    //pendiente de terminar o rehacer******Usar expresión regular
    public static boolean validarCodigo(String codigo) {

// letra V + vocal en mayúsculas + tres o cuatro letras minúsculas 
// + dos números del 4 al 7, o bien con el número 8.
//        Pattern patron = Pattern.compile("***");
//        Matcher comparador = patron.matcher(codigo);
//        return comparador.matches();
        //version resumida
        return Pattern.compile("^V[AEIOU][a-z]{3,4}([4-7]{2}|8)").matcher(codigo).matches();
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Vacuna) && (this.hashCode() == obj.hashCode());
    }

    public boolean equals(String codigo) {
        return this.hashCode() == codigo.hashCode();
    }

    @Override
    public String toString() {
        String resultado = "Datos de la vacuna:\n"
                + "   Código          " + codigo + "\n"
                + "   Nombre          " + nombre + "\n"
                + "   P. activo       " + principioActivo + "\n";
        if (isAutorizada()) {
            resultado += "   Farmaceutica    " + farmaceutica + "\n"
                    + "   Precio          " + getPvp() + " €";
        }
        return resultado;
    }

}
