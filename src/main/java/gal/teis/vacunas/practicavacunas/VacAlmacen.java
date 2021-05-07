package gal.teis.vacunas.practicavacunas;

import java.util.HashMap;

/**
 *
 * @author Ar
 */
public class VacAlmacen {

    private HashMap<String, Vacuna> almacen = new HashMap<String, Vacuna>();
    
    /*** INICIO METODOS EXTRA ***/
    
    /**
     * Comprueba si existe la vacuna con el codigo dado.
     * @param codigo ref de la vacuna
     * @return devuelve True si encuentra el codigo en el almacen.
     */
    public boolean existeVacuna(String codigo){
        boolean resultado = false;
        if(almacen.containsKey(codigo)){
            resultado = true;
        }
        return resultado;
    }
    
    /**
     *  Comprueba si una vacuna es apta para ser autorizada
     * @param codigo
     * @return 
     */    
    public boolean aptaAutorizar(String codigo) {
        boolean resultado = false;
        if (almacen.containsKey(codigo)) {
            resultado = almacen.get(codigo).aptaAutorizar();
        }
        return resultado;
    }
    /**
     *  Comprueba si una vacuna es viable para seguir realizando pruebas. 
     * @param codigo
     * @return 
     */    
    public boolean aptaPruebas(String codigo) {
        boolean resultado = false;
        if (almacen.containsKey(codigo)) {
            resultado = almacen.get(codigo).aptaPruebas();
        }
        return resultado;
    }
    
    /**
     *  Indica en que fase se encuentra actualmente.
     * @param codigo
     * @return devuelve tipo Int. La última fase pendiente de evaluar, o <b>"0"</b>  si no existe la vacuna. <b>"4"</b>  si ya ha realizado las 3 fases.
     */    
    public byte faseActual(String codigo) {
        byte resultado = 0;
        if (almacen.containsKey(codigo)) {
            resultado = (byte) (almacen.get(codigo).getFasesCompletadas() + 1);
        }
        return resultado;
    }

    /*** FIN METODOS EXTRA ***/
    
    
    /**
     * 1.Listar todas las vacunas y mostrar todos sus datos
     *
     * @return String
     */
    public String verVacunasDetalladas() {
        StringBuilder resultado = new StringBuilder(Salidas.TITULO_V_DETALLADAS+"\n\n");
        for (Vacuna vacuna : almacen.values()) {
            resultado.append(vacuna.toString() + "\n");
        }
        resultado.append(Salidas.finResultados(almacen.size()));
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
        String resultado = Salidas.V_NO_ENCONTRADA;
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
     * @param codigo de la vacuna a inscribir
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
                    resultado = almacen.get(codigo).setFase1Superada(resultadoFase);
                    break;
                case 2:                    
                    resultado = almacen.get(codigo).setFase2Superada(resultadoFase);
                    break;
                case 3:                    
                    resultado = almacen.get(codigo).setFase3Superada(resultadoFase);
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
        StringBuilder resultado = new StringBuilder(Salidas.TITULO_V_AUTORIZADAS + "\n");
        for (Vacuna vacuna : almacen.values()) {                  
            if (vacuna.isAutorizada()) {
                resultado.append(vacuna.getCodigo() + " (" + vacuna.getFechaResultado() + ")\n");
                contador++;
            }
        }
        resultado.append(Salidas.finResultados(contador));
        return resultado.toString();
    }

    /**
     * 8. Ver vacunas rechazadas.
     *
     * @return
     */
    public String verVacunasRechazadas() {
        int contador = 0;
        StringBuilder resultado = new StringBuilder(Salidas.TITULO_V_RECHAZADAS + "\n");
        for (Vacuna vacuna : almacen.values()) {                  
            if (vacuna.isRechadaza()) {
                resultado.append(vacuna.getCodigo() + " (" + vacuna.getFechaResultado() + ")\n");
                contador++;
            }
        }
        resultado.append(Salidas.finResultados(contador));
        return resultado.toString();
    }

    /**
     * 9.Ver vacunas pendientes de autorizar/rechazar.
     *
     * @return
     */
    public String verVacunasPendientes() {
        int contador = 0;
        StringBuilder resultado = new StringBuilder(Salidas.TITULO_V_PENDIENTES + "\n");
        for (Vacuna vacuna : almacen.values()) {                  
            if (!vacuna.isRechadaza() && !vacuna.isAutorizada()) {
                resultado.append(vacuna.getCodigo() + "\n");
                contador++;
            }
        }
        resultado.append(Salidas.finResultados(contador));
        return resultado.toString();
    }

    /**
     * 10.Ver la última fase investigada de cada vacuna almacenada.
     *
     * @return String
     */
    public String verVacunasUltimaFase() {
        int contador = 0;

        StringBuilder resultado = new StringBuilder(Salidas.TITULO_V_ESTADO + "\n\n");
        for (Vacuna vacuna : almacen.values()) {                  
            resultado.append(vacuna.getCodigo() + " : ");
            resultado.append(vacuna.investigacionActual() + "\n");
            contador++;
        }
        resultado.append(Salidas.finResultados(contador));
        return resultado.toString();
    }
}
