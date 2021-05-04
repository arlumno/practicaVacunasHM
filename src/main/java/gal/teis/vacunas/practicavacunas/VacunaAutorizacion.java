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
     * Solo se cambiará el valor de la fase superada 1 si se encuentra en lafasesCompletadas = 0 (fase inicial)
     *
     * @param fase1Superada
     */
    public boolean setFase1Superada(boolean superada) {
        boolean resultado = false;
        if (fasesCompletadas == 0 && setFasesCompletadas()) {
            this.fase1Superada = superada;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Solo se cambiará el valor de la fase superada 2 si se encuentra en la fasesCompletadas = 1
     *
     * @param fase2Superada
     */
    public boolean setFase2Superada(boolean superada) {
        boolean resultado = false;
        if (fasesCompletadas == 1 && setFasesCompletadas()) {
            this.fase2Superada = superada;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Solo se cambiará el valor de la fase superada 3 si se encuentra en la fasesCompletadas = 2
     *
     * @param fase3Superada
     */
    public boolean setFase3Superada(boolean superada) {
        boolean resultado = false;
        if (fasesCompletadas == 2 && setFasesCompletadas()) {
            this.fase3Superada = superada;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Pasa a la siguiente fase. No puede exceder de la fase 3
     * @return True si realiza la operación con éxito
     */
    private boolean setFasesCompletadas() {
        boolean resultado = false;
        if (fasesCompletadas < 3) {
            fasesCompletadas++;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Graba la fecha actual
     */
    private void setFechaResultado() {
        fechaResultado = LocalDate.now();
    }

    /**
     * Comprueba si cumple los requisitos para ser Autorizada. Todas las fases superadas con éxito.
     * @return 
     */
    public boolean aptaAutorizar() {        
        return (!rechazada && fasesCompletadas == 3 && isFase1Superada() && isFase2Superada() && isFase3Superada());
    }    
    
    @Override
    public boolean autorizar() {        
        //comprueba si no hay sido autorizada anteriormente y si es apta.
        if (!autorizada && aptaAutorizar()) {
            autorizada = true;
            setFechaResultado();
        }
        return autorizada;
    }

    @Override
    public boolean rechazar() {
        //comprueba si no ha sido rechazada anteriormente. Una vacuna autorizada puede ser rechazada.
        if (!rechazada) { 
            rechazada = true;
            autorizada = false; 
            setFechaResultado();
        }        
        return rechazada;
    }
    
    /**
     * Indica la última fase completada y su resultado.
     * @return 
     */
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
