/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.vacunas.practicavacunas;

/**
 * Textos para salidas por consola.
 * @author a20armandocb
 */
public class Salidas {
    /** VacunaAutorizacion.java **/
    public static final String  F_RESULTADOS = "Aún no se ha completado la primera fase";
    
    
    /** VacAlmacen.java **/
    public static final String  TITULO_V_DETALLADAS = "**Vacunas Detalladas:**";
    public static final String  TITULO_V_AUTORIZADAS = "**Vacunas Autorizadas:**";
    public static final String  TITULO_V_RECHAZADAS = "**Vacunas Rechazadas:**";
    public static final String  TITULO_V_PENDIENTES = "**Vacunas Pendientes:**";
    public static final String  TITULO_V_ESTADO = "**Vacunas Estado:**";
    public static final String  V_NO_ENCONTRADA = "Vacuna no encontrada.";
    
    public static String finResultados(int resultados){
        return "**Fin. (" + resultados + ") Resultados**";
    }
    
    /** Aplicacion.java -- Menu Principal **/
    public static final String  TITULO_MENU = "Menú Vacunas";
    public static final String  MENU_OPC_01 = "Listar todas las vacunas y mostrar todos sus datos";
    public static final String  MENU_OPC_02 = "Buscar vacuna";
    public static final String  MENU_OPC_03 = "Agregar vacuna";
    public static final String  MENU_OPC_04 = "Eliminar vacuna";
    public static final String  MENU_OPC_05 = "Introducir resultado de las fases de la vacuna";
    public static final String  MENU_OPC_06 = "Autorizar/Rechazar vacuna";
    public static final String  MENU_OPC_07 = "Ver vacunas autorizadas";
    public static final String  MENU_OPC_08 = "Ver vacunas rechazadas";
    public static final String  MENU_OPC_09 = "Ver vacunas pendientes de autorizar/rechazar";
    public static final String  MENU_OPC_10 = "Ver la última fase investigada de cada vacuna almacenada";
    public static final String  MENU_OPC_11 = "Cargar Vacunas de ejemplo";
    public static final String  MENU_OPC_12 = "Comprueba si un codigo es Valido";
    
    public static final String  INFO_OPC_02 = "Indíca el codigo a buscar";    
    
    public static final String  INFO_OPC_03 = "Datos para nueva Vacuna:";
    public static final String  EXITO_OPC_03 = "Vacuna agregada al almacen.";
    public static final String  ERROR_OPC_03 = "Error al agregar Vacuna.";
    
    public static final String  INFO_OPC_04 = "Eliminar Vacuna:";
    public static final String  EXITO_OPC_04 = "Vacuna eliminada.";
    public static final String  ERROR_OPC_04 = "No se ha encontrado la vacuna.";
    
    public static final String  INFO_OPC_05 = "Evaluar Fase:";
    public static String infoOpc5Fase(int faseActual){
        return "Vacuna encontrada. Fase Actual: " + faseActual;
    }
    public static String pedirOpc5Autorizar(int faseActual){
        return "¿Es Favorable el resultado de la fase "+ faseActual+ "?, escribe SI o NO";
    }
    
    public static final String  EXITO_OPC_05 = "";
    public static final String  ERROR_OPC_05 = "";
    
    // Mensages para peticiones por consola.
    public static final String  PEDIR_CODIGO = "Indíca el codigo";
    public static final String  ERROR_CODIGO = "El codigo no cumple los requisitos";
    public static final String  PEDIR_NOMBRE= "Indíca el nombre";
    public static final String  PEDIR_PRINCIPIO_ACTIVO = "Indíca el Principio Activo";
    public static final String  PEDIR_FARMACEUTICA = "Indíca el nombre de la Farmacéutica";
    public static final String  PEDIR_PVP = "Indíca el Precio";
    

}
