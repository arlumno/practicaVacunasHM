package gal.teis.vacunas.practicavacunas;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Ar
 */
public class VacAlmacen {

    private HashMap<String, Vacuna> almacen = new HashMap<String, Vacuna>();

    public boolean comprobarExiteVacuna(String codigo){
        boolean resultado = false;
        if(almacen.containsKey(codigo)){
            resultado = true;
        }
        return resultado;
    }
    // public int indiceVacuna(String codigo) 
    // no necesario con HashMap
    public int faseActual(String codigo) {
        int resultado = -1;
        if (almacen.containsKey(codigo)) {
            resultado = (almacen.get(codigo).getFasesCompletadas() + 1);
        }
        return resultado;
    }

    /**
     * 1.Listar todas las vacunas y mostrar todos sus datos
     *
     * @return String
     */
    public String verVacunasDetalladas() {
        StringBuilder resultado = new StringBuilder("**Vacunas Detalladas:**\n\n");
        for (Vacuna vacuna : almacen.values()) {
            resultado.append(vacuna.toString() + "\n");
        }
        resultado.append("**Fin. (" + almacen.size() + ") Resultados**");
        return resultado.toString();
    }

    /**
     * 2.Buscar vacuna.// ver vacuna || No uso almacen.contains, porque para
     * ello necesitaría el objeto vacuna en lugar del codigo
     *
     * @param codigo
     * @return devuelve toString de la vacuna si la encuentra, devuelve "" si no
     * la encuentra.
     */
    public String verVacuna(String codigo) {
        String resultado = "Vacuna no encontrada.";
        if (almacen.containsKey(codigo)) {
            resultado = almacen.get(codigo).toString();
        }
        return resultado;
    }

    /**
     * 3.Agregar vacuna.
     *
     * @param vacuna
     * @return devuelve true si la agrega, si no la agrega porque ya está
     * guardada.
     */
    public boolean agregarVacuna(Vacuna vacuna) {
        boolean resultado = false;
        if (!almacen.containsKey(vacuna.getCodigo())) {
            almacen.put(vacuna.getCodigo(), vacuna);
            resultado = true;
        }
        return resultado;
    }

    /**
     * 4.Eliminar vacuna.
     *
     * @param codigo
     * @return devuelve True si ha eliminado la vacuna del almacen.
     */
    public boolean eliminarVacuna(String codigo) {
        boolean eliminada = false;
        if (almacen.containsKey(codigo)) {
            almacen.remove(codigo);
            eliminada = true;
        }

        return eliminada;
    }

    /**
     * 5.Introducir resultado de las fases de la vacuna.
     *
     * @param Codigo de la vacuna a inscribir
     * @param resultadoFase resultado de la fase especifica
     * @param fase numero de la fase a inscribir
     * @return true si realiza la operación con exito, false si no ha podido
     * realizar la operación
     */
    public boolean grabarResultadoFaseVacuna(String codigo, boolean resultadoFase, byte fase) {
        boolean resultado = false;
        
        if (almacen.containsKey(codigo)) {
            switch (fase) {
                case 1:
                    almacen.get(codigo).setFase1Superada(resultadoFase);
                    resultado = true;
                    break;
                case 2:
                    almacen.get(codigo).setFase2Superada(resultadoFase);
                    resultado = true;
                    break;
                case 3:
                    almacen.get(codigo).setFase3Superada(resultadoFase);
                    resultado = true;
                    break;
            }
        }
        return resultado;
    }

    /**
     * 6.Autorizar/Rechazar vacuna.
     *
     * @param codigo
     * @param autorizar si es <b>TRUE se AUTORIZA</b> la vacuna, si es <b>FALSE
     * se RECHAZA</b>
     * @return
     */
    public boolean grabarResultadoFinalVacuna(String codigo, boolean autorizar) {
        boolean resultado = false;
        if (almacen.containsKey(codigo)) {
            if (autorizar) {
                resultado = almacen.get(codigo).autorizar();
            } else {
                resultado = almacen.get(codigo).rechazar();
            }
            resultado = true;
        }

        return resultado;
    }

    /**
     * 7.Ver vacunas autorizadas.
     *
     * @return
     */
    public String verVacunasAutorizadas() {
        int contador = 0;
        StringBuilder resultado = new StringBuilder("**Vacunas autorizadas:**\n");
        for (int i = 0; i < almacen.size(); i++) {
            if (almacen.get(i).isAutorizada()) {
                resultado.append(almacen.get(i).getCodigo() + " (" + almacen.get(i).getFechaResultado() + ")\n");
                contador++;
            }
        }
        resultado.append("**Fin. (" + contador + ") Resultados**");
        return resultado.toString();
    }

    /**
     * 8. Ver vacunas rechazadas.
     *
     * @return
     */
    public String verVacunasRechazadas() {
        int contador = 0;
        StringBuilder resultado = new StringBuilder("**Vacunas Rechazadas:**\n");
        for (int i = 0; i < almacen.size(); i++) {
            if (almacen.get(i).isRechadaza()) {
                resultado.append(almacen.get(i).getCodigo() + " (" + almacen.get(i).getFechaResultado() + ")\n");
                contador++;
            }
        }
        resultado.append("**Fin. (" + contador + ") Resultados**");
        return resultado.toString();
    }

    /**
     * 9.Ver vacunas pendientes de autorizar/rechazar.
     *
     * @return
     */
    public String verVacunasPendientes() {
        int contador = 0;
        StringBuilder resultado = new StringBuilder("**Vacunas Pendientes:**\n");
        for (int i = 0; i < almacen.size(); i++) {
            if (!almacen.get(i).isRechadaza() && !almacen.get(i).isAutorizada()) {
                resultado.append(almacen.get(i).getCodigo() + "\n");
                contador++;
            }
        }
        resultado.append("**Fin. (" + contador + ") Resultados**");
        return resultado.toString();
    }

    /**
     * 10.Ver la última fase investigada de cada vacuna almacenada.
     *
     * @return String
     */
    public String verVacunasUltimaFase() {
        int contador = 0;

        StringBuilder resultado = new StringBuilder("**Vacunas Estado:**\n\n");
        for (int i = 0; i < almacen.size(); i++) {
            resultado.append(almacen.get(i).getCodigo() + " : ");
            resultado.append(almacen.get(i).investigacionActual() + "\n");
            contador++;
        }
        resultado.append("**Fin. (" + contador + ") Resultados**");
        return resultado.toString();
    }
}
