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
    public static final String  MENU_OPC_12 = "Comprueba si un código es Valido";
    public static final String  MENU_OPC_13 = "AYUDA / INSTRUCCIONES";
    
    public static final String  INFO_OPC_02 = "Indíca el código a buscar";    
    
    public static final String  INFO_OPC_03 = "Datos para nueva Vacuna:";
    public static final String  EXITO_OPC_03 = "Vacuna agregada al almacen.";
    public static final String  ERROR_OPC_03 = "Error al agregar Vacuna.";
    
    public static final String  INFO_OPC_04 = "Eliminar Vacuna:";
    public static final String  EXITO_OPC_04 = "Vacuna eliminada.";
    public static final String  ERROR_OPC_04 = "No se ha encontrado la vacuna.";
    
    public static final String  INFO_OPC_05 = "Evaluar Fase:";
    public static String Opc5InfoFase(int faseActual){
        return "Vacuna encontrada. Fase Actual: " + faseActual;
    }
    public static String Opc5PedirAutorizar(int faseActual){
        return "¿Es Favorable el resultado de la fase "+ faseActual+ "?, escribe SI o NO";
    }
    public static String Opc5FaseSuperada(int faseActual){
        return "Vacuna actualizada. Fase: " + faseActual + " Superada";
    }
    public static String Opc5FaseNoSuperada(int faseActual){
        return "Vacuna actualizada. Fase: " + faseActual + " NO Superada";
    }   
    public static final String  ERROR_OPC_05_01 = "Error al actualizar vacuna.";
    public static final String  ERROR_OPC_05_02 = "No se puede evaluar Fases con esta Vacuna";
    public static final String  ERROR_OPC_05_03 = "El código NO corresponde a ninguna vacuna del almacén";
    
    public static final String  BOOLEAN_SI = "SI";
    public static final String  BOOLEAN_NO = "NO";
    
    public static final String  INFO_OPC_06 = "Autorizar/Rechazar Vacuna:";
    public static final String  INFO_OPC_06_AUTORIZAR = "AUTORIZAR";
    public static final String  INFO_OPC_06_RECHAZAR = "RECHAZAR";
    public static final String  EXITO_OPC_06_AUTORIZADA = "Vacuna Autorizada";
    public static final String  EXITO_OPC_06_RECHAZADA = "Vacuna Rechazada";
    public static final String  EXITO_OPC_06_ANULAR = "Proceso Anulado.";
    public static final String  ERROR_OPC_06_01 = "Error al actualizar vacuna.";
    public static final String  ERROR_OPC_06_02 = "El código NO corresponde a ninguna vacuna del almacén";
    public static final String  PEDIR_OPC_06_AUTORIZAR_RECHAZAR = "¿Resultado FINAL?, escribe "+INFO_OPC_06_AUTORIZAR+" o " + INFO_OPC_06_RECHAZAR;
    public static final String  PEDIR_OPC_06_RECHAZAR = "La vacuna no es Apta para ser Autorizada. ¿Desea rechazarla?\n, escribe "+BOOLEAN_SI+" o " + BOOLEAN_NO;

    public static final String  EXITO_OPC_11 = "---Vacunas cargadas---";
    
    public static final String  INFO_OPC_12 = "o El código tendrá el siguiente formato:\n"
                    + "o Comenzará por la letra V seguida de una vocal en mayúsculas.\n"
                    + "o A continuación, tres o cuatro letras minúsculas.\n"
                    + "o Finaliza, o con dos números del 4 al 7, o bien con el número 8";
    
    public static final String  FILE_OPC_13 = "src/main/java/gal/teis/vacunas/practicavacunas/leeme.txt";
    public static final String  ERROR_OPC_13 = "Error al acceder al archivo.";
    
    // Mensages para peticiones genericas por consola.
    public static final String  PEDIR_CODIGO = "Indíca el código";
    public static final String  EXITO_CODIGO = "El código es VÁLIDO";
    public static final String  ERROR_CODIGO = "El código no cumple los requisitos";
    public static final String  PEDIR_NOMBRE= "Indíca el nombre";
    public static final String  PEDIR_PRINCIPIO_ACTIVO = "Indíca el Principio Activo";
    public static final String  PEDIR_FARMACEUTICA = "Indíca el nombre de la Farmacéutica";
    public static final String  PEDIR_PVP = "Indíca el Precio";
    

}
