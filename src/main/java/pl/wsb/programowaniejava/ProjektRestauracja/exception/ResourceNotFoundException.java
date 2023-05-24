package pl.wsb.programowaniejava.ProjektRestauracja.exception;



public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found");
    }
}
