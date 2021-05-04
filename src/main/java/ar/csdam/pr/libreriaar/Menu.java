/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.csdam.pr.libreriaar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Imprime un menún según los parametros proporcionados
 *
 * @author Ar
 */
public class Menu {


    private ArrayList<String> opcionesArray = new ArrayList<String>();
    private ArrayList<String> descripcionOpcionesArray = new ArrayList<String>();
    private Scanner lector;
    private StringBuilder menu = new StringBuilder();
    private String tituloMenu = "";
    private boolean salir = true;
    private boolean descripcion = true;
    private byte nOpciones = 0;
    private byte seleccion;
    private int intentos = 0;

    /**
     * Menú de opciones. Limitado a 127 opciones. Se reserva el 0 para salir.
     *
     * @param lector objeto Scanner para seleccionar opciones
     */
    public Menu(Scanner lector) {
        this.lector = lector;
    }
    /**
     * @return the salir
     */
    public boolean isSalir() {
        return salir;
    }

    /**
     * @return the descripcion
     */
    public boolean isDescripcion() {
        return descripcion;
    }

    /**
     * @param salir Indica si hay la opción de salir del menu.
     */
    public void setSalir(boolean salir) {
        this.salir = salir;
        borrarMenu();
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(boolean descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Cambia el titulo del menu, por defecto no tiene.
     *
     * @param tituloMenu
     */
    public void setTituloMenu(String tituloMenu) {
        this.tituloMenu = tituloMenu;
    }

    /**
     * Añade una opción al menú,
     *
     * @param opcion Nombre o titulo de la opcion
     * @param descripcion descripción de la opcion
     */
    public void addOpcion(String opcion, String descripcion) {
        if (nOpciones == 127) {
            Salidas.errorLimite();
        } else {
            opcionesArray.add(opcion);
            descripcionOpcionesArray.add(descripcion);
            nOpciones = (byte) opcionesArray.size();
            borrarMenu();
        }
    }

    /**
     * Añade una opción al menú,
     *
     * @param opcion Nombre o titulo de la opcion
     */
    public void addOpcion(String opcion) {
        addOpcion(opcion, "");
    }

    /**
     * Carga un conjunto de opciones de tipo Arraylist String
     *
     * @param opciones Arraylist String de opciones
     */
    public void addOpciones(ArrayList<String> opciones) {
        for (int i = 0; i < opciones.size(); i++) {
            addOpcion(opciones.get(i));
        }
    }
    /**
     * Carga un conjunto de opciones de tipo String[]
     *
     * @param opciones Arraylist String de opciones
     */
    public void addOpciones(String[] opciones) {        
        addOpciones(new ArrayList<>(Arrays.asList(opciones)));
    }
   
    /**
     * Carga un conjunto de opciones / descripcion  de tipo String[][]
     * Donde el primer indice indica el conjunto de opciones, en el segundo indice [0] opcion y [1] descripción
     * @param opciones 
     */
    public void addOpciones(String[][] opciones) {
        for(int i = 0; i < opciones.length; i++) {
            addOpcion(opciones[i][0], opciones[i][1]);
        }
    }

    private void delOpcion(byte nOpcion) {
        opcionesArray.remove(nOpcion);
        descripcionOpcionesArray.remove(nOpcion);
        nOpciones = (byte) opcionesArray.size();
        borrarMenu();
    }

    /**
     * Genera el menu en función de los opciones.
     */
    private void construirMenu() {
        //Construimos y guardamos el menú
        menu.append(Textos.cabeceraMenu(tituloMenu));
        for (int i = 0; i < nOpciones; i++) {
            menu.append("\n");
            menu.append(i + 1); //la opcion es = al indice del array + 1
            menu.append(".- ");
            menu.append(opcionesArray.get(i));
            if (isDescripcion() && descripcionOpcionesArray.get(i) != "") {
                menu.append("\n\t");
                menu.append(descripcionOpcionesArray.get(i));
            }
        }
        if (isSalir()) {
            menu.append(Textos.OPCION_SALIR);
        }
        menu.append(Textos.INDICAR_OPCION);
    }

    private void borrarMenu() {
        menu = new StringBuilder();
    }

    /**
     * Muestra el menú por consola y pide seleccionar una opción. El menú
     * empieza en 1 y usa 0 para salir. El Indice del array es (return - 1)
     *
     * @return opcion tipo byte, devuelve opción correspondiente a la lista
     * impresa, empezando en 1. Devuelve 0 para salir. El Indice del array es
     * (return - 1)
     */
    private boolean validarMenu() {
        boolean resultado = true;

        if (opcionesArray.isEmpty()) {
            Salidas.errorMenuVacio();
            resultado = false;
        } else {
            if (menu.length() == 0) {
                construirMenu();
            }
        }
        return resultado;
    }

    /**
     * Imprime el menu, pide que selecciones una opción y guarda la seleccion.     
     *
     * @throws Exception. Ocurre cuando se intenta imprimir un menú vació, con respuesta obligatoria (que no tiene opción de salir)
     */
    public void mostrar() throws Exception {
        //se imprime a partir del primer intento del menú.
        if (validarMenu()) {
            if (intentos > 0) {
                //pausa
                Salidas.repetirMenu();
                //pedimos entrada de texto para que se pause el programa, no es necesario asignar el resultado
                lector.nextLine();
            }
            //imprimimos el menú
            System.out.println(menu);
            seleccion = Entradas.pedirByte(lector, (byte) 0, nOpciones);

            if (seleccion == 0) {
                System.out.println(Textos.finMenu(tituloMenu));
            } else {
                Utils.limpiarConsola(5);                
                System.out.println(Textos.opcionSeleccionada(opcionesArray.get(seleccion - 1)));
            }
            intentos++;
        } else {
            if (isSalir()) {
                seleccion = 0;
            } else {
                // esto ocurre cuando se intenta imprimir un menú vació, con respuesta obligatoria (que no tiene opción de salir)
                throw new Exception(Textos.EXC_SIN_OPCIONES);
            }
        }

    }

    /**
     * Para obtener la opcion seleccionada en el thisl. mostrar()
     * @return tipo byte, tras mostrar el menú. indice array = (selección - 1)
     * @throws Exception, Cuando no se ha seleccionado anteriormente.
     */
    public byte getSeleccion() throws Exception{
        if (intentos == 0){
            throw new Exception(Textos.NO_SELECCIONADO);
        }
        return seleccion;
    }

}
