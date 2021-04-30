package gal.teis.vacunas.practicavacunas;

import ar.csdam.pr.libreriaar.Entradas;
import ar.csdam.pr.libreriaar.Menu;
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
                continuar = menuPrincipal(menu, almacen, lector);
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
        menu.setTituloMenu("Menú Vacunas");
        menu.addOpcion("Listar todas las vacunas y mostrar todos sus datos"); //1
        menu.addOpcion("Buscar vacuna"); //2 
        menu.addOpcion("Agregar vacuna"); //3
        menu.addOpcion("Eliminar vacuna"); //4
        menu.addOpcion("Introducir resultado de las fases de la vacuna"); //5
        menu.addOpcion("Autorizar/Rechazar vacuna"); //6
        menu.addOpcion("Ver vacunas autorizadas"); //7
        menu.addOpcion("Ver vacunas rechazadas"); //8
        menu.addOpcion("Ver vacunas pendientes de autorizar/rechazar"); //9
        menu.addOpcion("Ver la última fase investigada de cada vacuna almacenada"); //10
        menu.addOpcion("Cargar Vacunas de ejemplo"); //10
        menu.addOpcion("Comprueba si un codigo es Valido"); //10
        return menu;
    }

    private static boolean menuPrincipal(Menu menu, VacAlmacen almacen, Scanner lector) throws Exception {
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
        }
        return continuar;
    }

    private static void opcion02(Scanner lector, VacAlmacen almacen) {
        //2.Buscar vacuna.
        System.out.println("Indíca el codigo a buscar");
        String codigo = Entradas.pedirString(lector);
        System.out.println(almacen.verVacuna(codigo));
    }

    private static void opcion03(Scanner lector, VacAlmacen almacen) {
        //3.Agregar vacuna.
        Vacuna vacuna;
        String codigo;
        System.out.println("Datos para nueva Vacuna:");
        boolean codigoValido = false;
        do {
            System.out.println("Indíca el codigo");
            codigo = Entradas.pedirString(lector);
            if (Vacuna.validarCodigo(codigo)) {
                codigoValido = true;
            } else {
                System.out.println("El codigo no cumple los requisitos");
            }
        } while (!codigoValido);
        System.out.println("Indíca el nombre");
        String nombre = Entradas.pedirString(lector);
        System.out.println("Indíca el principio activo");
        String princioActivo = Entradas.pedirString(lector);
        System.out.println("Indíca el farmacéutica");
        String farmaceutica = Entradas.pedirString(lector);
        System.out.println("Indíca el precio");
        double precio = Entradas.pedirDouble(lector);
        if (Vacuna.validarCodigo(codigo)) {
            vacuna = new Vacuna(codigo, nombre, princioActivo, farmaceutica, precio);
            if (almacen.agregarVacuna(vacuna)) {
                System.out.println("Vacuna agregada al almacen.");
            } else {
                System.out.println("Error al agregar Vacuna.");
            }
        }
    }

    private static void opcion04(Scanner lector, VacAlmacen almacen) {
        //4.Eliminar vacuna.
        String codigo;
        System.out.println("Indica el codigo a eliminar");
        codigo = Entradas.pedirString(lector);
        if (almacen.eliminarVacuna(codigo)) {
            System.out.println("Vacuna " + codigo + " eliminada");
        } else {
            System.out.println("No se ha encontrado la vacuna " + codigo);
        }
        return;
    }

    private static void opcion05(Scanner lector, VacAlmacen almacen) {
        //5.Introducir resultado de las fases de la vacuna.
        String codigo;
        byte faseActual;
        boolean resultadoFase;
        System.out.println("Indica el codigo de la vacuna");
        codigo = Entradas.pedirString(lector);

        if (almacen.existeVacuna(codigo)) {
            faseActual = almacen.faseActual(codigo);
            if (faseActual != 4) {
                System.out.println("Vacuna encontrada. Fase Actual: " + faseActual);
                System.out.println("¿Es Favorable el resultado de la fase "+ faseActual+ "?, escribe SI o NO");
                resultadoFase = Entradas.pedirBoolean(lector);

                if (almacen.grabarResultadoFaseVacuna(codigo, resultadoFase, faseActual)) {
                    if (resultadoFase) {
                        System.out.println("Vacuna actualizada. Fase: " + faseActual + " Superada");
                    } else {
                        System.out.println("Vacuna actualizada. Fase: " + faseActual + " No Superada");
                    }
                } else {
                    System.out.println("Error al actualizar vacuna.");
                }
            } else {
                System.out.println("La vacuna NO tiene fases pendientes.");
            }
        } else {
            System.out.println("El codigo NO corresponde a ninguna vacuna del almacén");
        }
    }

    private static void opcion06(Scanner lector, VacAlmacen almacen) {
        //6.Autorizar/Rechazar vacuna.
        String codigo;
        boolean resultado = false;

        System.out.println("Indica el codigo de la vacuna");
        codigo = Entradas.pedirString(lector);

        if (almacen.existeVacuna(codigo)) {
            if (almacen.aptaAutorizar(codigo)) {
                System.out.println("¿Resultado FINAL?, escribe AUTORIZAR o RECHAZAR");
                resultado = Entradas.pedirBoolean(lector, "AUTORIZAR", "RECHAZAR");
                if (almacen.grabarResultadoFinalVacuna(codigo, resultado)) {
                    if (resultado) {
                        System.out.println("Vacuna AUTORIZADA.");
                    } else {
                        System.out.println("Vacuna RECHAZADA.");
                    }
                } else {
                    System.out.println("Error al actualizar vacuna.");
                }
            } else {
                System.out.println("La vacuna no es Apta para ser Autorizada. ¿Desea rechazarla?");
                System.out.println("Escribe SI o NO");
                resultado = Entradas.pedirBoolean(lector, "SI", "NO");
                if (resultado) { //ahora resultado indicas si se quiere Rechazar
                    if (almacen.grabarResultadoFinalVacuna(codigo, false)) {
                        System.out.println("Vacuna RECHAZADA.");
                    } else {
                        System.out.println("Error al actualizar vacuna.");
                    }
                } else {
                    System.out.println("Proceso Anulado.");
                }
            }
        } else {
            System.out.println("El codigo NO corresponde a ninguna vacuna del almacén");
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
        
        
        vacuna = new Vacuna("VEfff8", "JNJ-78436735", " vector viral ", "Janssen Pharmaceuticals", 19.95);
        almacen.agregarVacuna(vacuna);
        
        System.out.println("---Vacunas cargadas---");
    }

    /**
     * Pide un codigo por consola y verifica si es correcto según los requisitos
     * de escritura.
     *
     * @param lector
     */
    private static void opcion12extra(Scanner lector) {
        String codigo;

        System.out.println("Indica el codigo de la vacuna");
        codigo = Entradas.pedirString(lector);
        //comprobar codigo       
        if (Vacuna.validarCodigo(codigo)) {
            System.out.println("El codigo es váido");
        } else {
            System.out.println("Codigo incorrecto.");
            System.out.println("• El codigo tendrá el siguiente formato:\n"
                    + "o Comenzará por la letra V seguida de una vocal en mayúsculas.\n"
                    + "o A continuación, tres o cuatro letras minúsculas.\n"
                    + "o Finaliza, o con dos números del 4 al 7, o bien con el número 8");
        }
    }

}
