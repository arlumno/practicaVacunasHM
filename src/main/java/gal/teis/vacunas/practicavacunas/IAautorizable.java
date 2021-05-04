package gal.teis.vacunas.practicavacunas;

/**
 * La interfaz IAutorizable se usará para autorizar o no los objetos de la clase Vacuna.
 * @author Ar
 */
public interface IAautorizable {   
    /**
     * Autorizada una vacuna para su uso.
     */
    public boolean autorizar();
    
    /**
     * Rechaza una vacuna;
     */
    public boolean rechazar();    
    
}
