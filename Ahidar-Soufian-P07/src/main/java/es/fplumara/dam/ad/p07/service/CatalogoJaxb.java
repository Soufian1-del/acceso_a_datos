package es.fplumara.dam.ad.p07.service;
import es.fplumara.dam.ad.p07.model.Catalogo;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
/**
 * Lee y escribe el catálogo de series con JAXB (vinculación de clases).
 */
public class CatalogoJaxb {
    private JAXBContext contexto;
    // Paso 4
    public CatalogoJaxb() throws JAXBException {
        contexto = JAXBContext.newInstance(Catalogo.class);
    }
    // Paso 4
    public Catalogo leer(String ruta) throws JAXBException {
        Unmarshaller lector = contexto.createUnmarshaller();
        return (Catalogo) lector.unmarshal(new File(ruta));
    }
    // Paso 5
    public void guardar(Catalogo catalogo, String ruta) throws JAXBException {
        Marshaller escritor = contexto.createMarshaller();
        escritor.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        escritor.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        escritor.marshal(catalogo, new File(ruta));
    }
}