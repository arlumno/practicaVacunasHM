package gal.teis.vacunas.practicavacunas;

import ar.csdam.pr.libreriaar.Entradas;
import ar.csdam.pr.libreriaar.Menu;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ar
 */
public class Aplicacion {

    /**
     * @param args the command line arguments
     *
     * 1.Listar todas las vacunas y mostrar todos sus datos 2.Buscar vacuna.
     * 3.Agregar vacuna. 4.Eliminar vacuna. 5.Introducir resultado de las fases
     * de la vacuna. 6.Autorizar/Rechazar vacuna. 7.Ver vacunas autorizadas.
     * 8.Ver vacunas rechazadas. 9.Ver vacunas pendientes de autorizar/rechazar.
     * 10.Ver la última fase investigada de cada vacuna almacenada.
     */
    public static void main(String[] args) {
        boolean continuar = true;
        Scanner lector = new Scanner(System.in);
        Menu menu = construirMenuPrincipal(lector);
        VacAlmacen almacen = new VacAlmacen();

        do {
            try {
                continuar = menuAcciones(menu, almacen, lector);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } while (continuar);

        lector.close();
    }

    /**
     * Construye y configura el menú principal de la aplicación.
     *
     * @param lector
     * @return devuelve objeto Menu configurado.
     */
    private static Menu construirMenuPrincipal(Scanner lector) {
        Menu menu = new Menu(lector);
        menu.setTituloMenu(Salidas.TITULO_MENU);
        menu.addOpcion(Salidas.MENU_OPC_01); //1
        menu.addOpcion(Salidas.MENU_OPC_02); //2 
        menu.addOpcion(Salidas.MENU_OPC_03); //3
        menu.addOpcion(Salidas.MENU_OPC_04); //4
        menu.addOpcion(Salidas.MENU_OPC_05); //5
        menu.addOpcion(Salidas.MENU_OPC_06); //6
        menu.addOpcion(Salidas.MENU_OPC_07); //7
        menu.addOpcion(Salidas.MENU_OPC_08); //8
        menu.addOpcion(Salidas.MENU_OPC_09); //9
        menu.addOpcion(Salidas.MENU_OPC_10); //10
        menu.addOpcion(Salidas.MENU_OPC_11); //11
        menu.addOpcion(Salidas.MENU_OPC_12); //12
        menu.addOpcion(Salidas.MENU_OPC_13); //13
        return menu;
    }

    private static boolean menuAcciones(Menu menu, VacAlmacen almacen, Scanner lector) throws Exception {
        boolean continuar = true;
        menu.mostrar();
        switch (menu.getSeleccion()) {
            case 0:
                //salir
                continuar = false;
                break;
            case 1:
                //1.Listar todas las vacunas y mostrar todos sus datos
                System.out.println(almacen.verVacunasDetalladas());
                break;
            case 2:
                //2.Buscar vacuna.
                opcion02(lector, almacen);
                break;
            case 3:
                //3.Agregar vacuna.
                opcion03(lector, almacen);
                break;
            case 4:
                //4.Eliminar vacuna.
                opcion04(lector, almacen);
                break;
            case 5:
                //5.Introducir resultado de las fases de la vacuna.
                opcion05(lector, almacen);
                break;
            case 6:
                //6.Autorizar/Rechazar vacuna.
                opcion06(lector, almacen);
                break;
            case 7:
                //7.Ver vacunas autorizadas.
                System.out.println(almacen.verVacunasAutorizadas());
                break;
            case 8:
                //8.Ver vacunas rechazadas.
                System.out.println(almacen.verVacunasRechazadas());
                break;
            case 9:
                //9.Ver vacunas pendientes de autorizar/rechazar.
                System.out.println(almacen.verVacunasPendientes());
                break;
            case 10:
                //10.Ver la última fase investigada de cada vacuna almacenada.
                System.out.println(almacen.verVacunasUltimaFase());
                break;
            case 11:
                //EXTRA- cargar conjunto de vacunas de ejemplo.
                opcion11extra(almacen);
                break;
            case 12:
                //EXTRA- comprobar los codigos con expresiones regulares
                opcion12extra(lector);
                break;
            case 13:
                //EXTRA- Instruciones de ayuda.
                opcion13extra();
                break;
        }
        return continuar;
    }
    
    /**
     * 2.Buscar vacuna.
     * @param lector
     * @param almacen 
     */
    private static void opcion02(Scanner lector, VacAlmacen almacen) {
        
        System.out.println(Salidas.INFO_OPC_02);
        String codigo = Entradas.pedirString(lector);
        System.out.println(almacen.verVacuna(codigo));
    }
    
    /**
     * 3.Agregar vacuna.
     * @param lector
     * @param almacen 
     */
    private static void opcion03(Scanner lector, VacAlmacen almacen) {
        
        Vacuna vacuna;
        String codigo;
        String nombre;
        String princioActivo;
        String farmaceutica;
        double precio;
        System.out.println(Salidas.INFO_OPC_03);
        boolean codigoValido = false;
        
        do {
            System.out.println(Salidas.PEDIR_CODIGO);
            codigo = Entradas.pedirString(lector);
            if (Vacuna.validarCodigo(codigo)) {
                codigoValido = true;
            } else {
                System.out.println(Salidas.ERROR_CODIGO);
            }
        } while (!codigoValido);
        
        System.out.println(Salidas.PEDIR_NOMBRE);
        nombre = Entradas.pedirString(lector);
        
        System.out.println(Salidas.PEDIR_PRINCIPIO_ACTIVO);
        princioActivo = Entradas.pedirString(lector);
        
        System.out.println(Salidas.PEDIR_FARMACEUTICA);
        farmaceutica = Entradas.pedirString(lector);
        
        System.out.println(Salidas.PEDIR_PVP);
        precio = Entradas.pedirDouble(lector);
        
        vacuna = new Vacuna(codigo, nombre, princioActivo, farmaceutica, precio);
        
        if (almacen.agregarVacuna(vacuna)) {
            System.out.println(Salidas.EXITO_OPC_03);
        } else {
            System.out.println(Salidas.ERROR_OPC_03);
        }
        
    }

    /**
     * 4.Eliminar vacuna.
     * @param lector
     * @param almacen 
     */
    private static void opcion04(Scanner lector, VacAlmacen almacen) {
        String codigo;

        System.out.println(Salidas.INFO_OPC_04);
        System.out.println(Salidas.PEDIR_CODIGO);
        codigo = Entradas.pedirString(lector);
        if (almacen.eliminarVacuna(codigo)) {
            System.out.println(Salidas.EXITO_OPC_04);
        } else {
            System.out.println(Salidas.ERROR_OPC_04);
        }
        return;
    }

    /**
     * 5.Introducir resultado de las fases de la vacuna.
     * @param lector
     * @param almacen 
     */
    private static void opcion05(Scanner lector, VacAlmacen almacen) {
        String codigo;
        byte faseActual;
        boolean resultadoFase;

        System.out.println(Salidas.INFO_OPC_05);
        System.out.println(Salidas.PEDIR_CODIGO);
        codigo = Entradas.pedirString(lector);

        if (almacen.existeVacuna(codigo)) {
            if (almacen.aptaPruebas(codigo)){
                faseActual = almacen.faseActual(codigo);
                
                System.out.println(Salidas.Opc5InfoFase(faseActual));
                
                System.out.println(Salidas.Opc5PedirAutorizar(faseActual));               
                resultadoFase = Entradas.pedirBoolean(lector);
                
                if (almacen.grabarResultadoFaseVacuna(codigo, resultadoFase, faseActual)) {
                    if (resultadoFase) {
                        System.out.println(Salidas.Opc5FaseSuperada(faseActual));
                    } else {
                        System.out.println(Salidas.Opc5FaseNoSuperada(faseActual));
                    }
                } else {
                    System.out.println(Salidas.ERROR_OPC_05_01);
                }
            } else {
                System.out.println(Salidas.ERROR_OPC_05_02);
            }
        } else {
            System.out.println(Salidas.ERROR_OPC_05_03);
        }
    }

    /**
     * 6.Autorizar/Rechazar vacuna.
     * @param lector
     * @param almacen 
     */
    private static void opcion06(Scanner lector, VacAlmacen almacen) {
        String codigo;
        boolean resultado = false;

        System.out.println(Salidas.INFO_OPC_06);
        System.out.println(Salidas.PEDIR_CODIGO);
        codigo = Entradas.pedirString(lector);

        if (almacen.existeVacuna(codigo)) {
            if (almacen.aptaAutorizar(codigo)) {
                System.out.println(Salidas.PEDIR_OPC_06_AUTORIZAR_RECHAZAR);
                resultado = Entradas.pedirBoolean(lector, Salidas.INFO_OPC_06_AUTORIZAR, Salidas.INFO_OPC_06_RECHAZAR);
               
                if (almacen.grabarResultadoFinalVacuna(codigo, resultado)) {
                    if (resultado) {
                        System.out.println(Salidas.EXITO_OPC_06_AUTORIZADA);
                    } else {
                        System.out.println(Salidas.EXITO_OPC_06_RECHAZADA);
                    }
                } else {
                    System.out.println(Salidas.ERROR_OPC_06_01);
                }
                
            } else {
                System.out.println(Salidas.PEDIR_OPC_06_RECHAZAR);
                resultado = Entradas.pedirBoolean(lector, Salidas.BOOLEAN_SI, Salidas.BOOLEAN_NO);
                
                if (resultado) { //ahora resultado indicas si se quiere Rechazar
                    if (almacen.grabarResultadoFinalVacuna(codigo, false)) {
                        System.out.println(Salidas.EXITO_OPC_06_RECHAZADA);
                    } else {
                        System.out.println(Salidas.ERROR_OPC_06_01);
                    }
                } else {
                    System.out.println(Salidas.EXITO_OPC_06_ANULAR);
                }
                
            }
        } else {
            System.out.println(Salidas.ERROR_OPC_06_02);
        }
    }

    /**
     * Inserta un conjunto de vacunas de ejemplo en el almacen.
     *
     * @param almacen
     */
    private static void opcion11extra(VacAlmacen almacen) {
        //EXTRA- cargar conjunto de vacunas de ejemplo.
        Vacuna vacuna;

        vacuna = new Vacuna("VAedf45", "COVID-19 AstraZeneca", "Adenovirus de chimpacé", "AstraZeneca", 12.44);
        almacen.agregarVacuna(vacuna);
        almacen.grabarResultadoFaseVacuna("VAedf45", true, (byte) 1);
        almacen.grabarResultadoFaseVacuna("VAedf45", true, (byte) 2);
        almacen.grabarResultadoFaseVacuna("VAedf45", true, (byte) 3);

        vacuna = new Vacuna("VIhdf67", "mRNA-1273", "ARNm", "ModernaTX, Inc.", 9.95);
        almacen.agregarVacuna(vacuna);
        almacen.grabarResultadoFaseVacuna("VIhdf67", true, (byte) 1);
        almacen.grabarResultadoFaseVacuna("VIhdf67", false, (byte) 2);
        almacen.grabarResultadoFaseVacuna("VIhdf67", true, (byte) 3);

        vacuna = new Vacuna("VOhef46", "ejemplo", "Tiene cosas", "ACME, Inc.", 999.95);
        almacen.agregarVacuna(vacuna);
        almacen.grabarResultadoFaseVacuna("VOhef46", true, (byte) 1);
        almacen.grabarResultadoFinalVacuna("VOhef46", false);

        vacuna = new Vacuna("VEfff8", "JNJ-78436735", " vector viral ", "Janssen Pharmaceuticals", 19.95);
        almacen.agregarVacuna(vacuna);

        System.out.println(Salidas.EXITO_OPC_11);
    }

    /**
     * Pide un codigo por consola y verifica si es correcto según los requisitos
     * de escritura.
     *
     * @param lector
     */
    private static void opcion12extra(Scanner lector) {
        String codigo;

        System.out.println(Salidas.INFO_OPC_12);
        System.out.println(Salidas.PEDIR_CODIGO);
        codigo = Entradas.pedirString(lector);
        //comprobar codigo       
        if (Vacuna.validarCodigo(codigo)) {
            System.out.println(Salidas.EXITO_CODIGO);
        } else {
            System.out.println(Salidas.ERROR_CODIGO);
        }
    }
    
    /**
     * Instrucciones de uso.
     */
    private static void opcion13extra() { 
        try{
            FileReader archivo = new FileReader(Salidas.FILE_OPC_13,Charset.forName("utf-8"));            
            BufferedReader br = new BufferedReader(archivo);
            String linea;
            while((linea = br.readLine()) !=null){
                System.out.println(linea);                
            }
        }catch(FileNotFoundException e){
            System.out.println(Salidas.ERROR_OPC_13);
        }catch(Exception e){
            System.out.println(e.toString());
        }
           // Long lFlags= (long) json.get("Flags");
           // String flags = Long.toBinaryString((long) json.get("Flags"));        

    }

}
