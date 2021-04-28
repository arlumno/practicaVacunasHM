package gal.teis.vacunas.practicavacunas;

/**
 * La interfaz IAutorizable se usará para autorizar o no los objetos de la clase Vacuna.
 * @author Ar
 */
public interface IAautorizable {   
    /**
     * que hace que una vacuna esté autorizada para su uso.
     */
    public boolean autorizar();
    
    /**
     * que hace que una vacuna no esté autorizada para su uso.
     */
    public boolean rechazar();    
    
}
