/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.vacunas.practicavacunas;

import java.time.LocalDate;

/**
 *
 * @author Ar
 */
public abstract class VacunaAutorizacion implements IAautorizable {

    private boolean fase1Superada;
    private boolean fase2Superada;
    private boolean fase3Superada;
    private byte fasesCompletadas = 0;
    private LocalDate fechaResultado;
    private boolean autorizada;
    private boolean rechazada;

//    public VacunaAutorizacion(String codigo, String nombre, String principioActivo, String farmaceutica, double pvp) {
//        super(codigo, nombre, principioActivo, farmaceutica, pvp);
//    }
    //getters
    public boolean isFase1Superada() {
        return fase1Superada;
    }

    public boolean isFase2Superada() {
        return fase2Superada;
    }

    public boolean isFase3Superada() {
        return fase3Superada;
    }

    public byte getFasesCompletadas() {
        return fasesCompletadas;
    }

    public LocalDate getFechaResultado() {
        return fechaResultado;
    }

    public boolean isAutorizada() {
        return autorizada;
    }

    public boolean isRechadaza() {
        return rechazada;
    }

    //setters
    /**
     * Solo se cambiará el estado de la fase superada si se encuentra en la
     * faseCompletada correspondiente.
     *
     * @param fase1Superada
     */
    public boolean setFase1Superada(boolean fase1Superada) {
        boolean resultado = false;
        if (fasesCompletadas == 0 && setFasesCompletadas()) {
            this.fase1Superada = fase1Superada;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Solo se cambiará el estado de la fase superada si se encuentra en la
     * faseCompletada correspondiente.
     *
     * @param fase2Superada
     */
    public boolean setFase2Superada(boolean fase2Superada) {
        boolean resultado = false;
        if (fasesCompletadas == 1 && setFasesCompletadas()) {
            this.fase2Superada = fase2Superada;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Solo se cambiará el estado de la fase superada si se encuentra en la
     * faseCompletada correspondiente.
     *
     * @param fase3Superada
     */
    public boolean setFase3Superada(boolean fase1Superada) {
        boolean resultado = false;
        if (fasesCompletadas == 2 && setFasesCompletadas()) {
            this.fase3Superada = fase3Superada;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Pasa a la siguiente fase. No puede exceder de la fase 3
     */
    private boolean setFasesCompletadas() {
        boolean resultado = false;
        if (fasesCompletadas < 3) {
            fasesCompletadas++;
            resultado = true;
        }
        return resultado;
    }

    private void setFechaResultado() {
        fechaResultado = LocalDate.now();
    }

    @Override
    public boolean autorizar() {        
        //si no ha sido rechazada o autorizada. y hay completado todas las fases.
        if (!rechazada && !autorizada && fasesCompletadas == 3 && isFase1Superada() && isFase2Superada() && isFase3Superada()) {
            autorizada = true;
            setFechaResultado();
        }
        return autorizada;
    }

    @Override
    public boolean rechazar() {
        if (!rechazada) { //solo podemos cambiarlo si no ha sido cambiado
            rechazada = true;
            autorizada = false; 
            setFechaResultado();
        }
        return rechazada;
    }

    public String investigacionActual() {
        String resultado = "";

        switch (fasesCompletadas) {
            case 0:
                resultado = "Aún no se ha completado la primera fase";
                break;
            case 1:
                resultado = "Fase 1 completada. Resultado: " + (fase1Superada ? "superada" : "no superada");
                break;
            case 2:
                resultado = "Fase 2 completada. Resultado: " + (fase2Superada ? "superada" : "no superada");
                break;
            case 3:
                resultado = "Fase 3 completada. Resultado: " + (fase3Superada ? "superada" : "no superada");
                break;
        }

        return resultado;
    }
}