package es.fplumara.dam.ad.p07.service;
import es.fplumara.dam.ad.p07.model.Serie;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Lee y escribe el catálogo de series con DOM (analizador del JDK).
 */
public class CatalogoDom {
    // Paso 1
    public Document cargar(String ruta) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabrica.newDocumentBuilder();
        Document documento = constructor.parse(new File(ruta));
        documento.getDocumentElement().normalize();
        return documento;
    }
    // Paso 2
    public List<Serie> leerSeries(Document documento) {
        List<Serie> series = new ArrayList<>();
        NodeList nodos = documento.getElementsByTagName("serie");
        for (int i = 0; i < nodos.getLength(); i++) {
            Element elemento = (Element) nodos.item(i);
            String id = elemento.getAttribute("id");
            String titulo = textoDe(elemento, "titulo");
            String genero = textoDe(elemento, "genero");
            int anio = Integer.parseInt(textoDe(elemento, "anio"));
            int temporadas = Integer.parseInt(textoDe(elemento, "temporadas"));
            double valoracion = Double.parseDouble(textoDe(elemento, "valoracion"));
            series.add(new Serie(id, titulo, genero, anio, temporadas, valoracion));
        }
        return series;
    }
    // Paso 2
    private String textoDe(Element padre, String etiqueta) {
        return padre.getElementsByTagName(etiqueta).item(0).getTextContent().trim();
    }
    // Paso 3
    public void guardarSeries(List<Serie> series, String plataforma, String ruta)
            throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        Document documento = fabrica.newDocumentBuilder().newDocument();
        Element raiz = documento.createElement("catalogo");
        raiz.setAttribute("plataforma", plataforma);
        documento.appendChild(raiz);

        for (Serie serie : series) {
            Element elementoSerie = documento.createElement("serie");
            elementoSerie.setAttribute("id", serie.getId());
            anadirHijo(documento, elementoSerie, "titulo", serie.getTitulo());
            anadirHijo(documento, elementoSerie, "genero", serie.getGenero());
            anadirHijo(documento, elementoSerie, "anio",
                    String.valueOf(serie.getAnio()));
            anadirHijo(documento, elementoSerie, "temporadas",
                    String.valueOf(serie.getTemporadas()));
            anadirHijo(documento, elementoSerie, "valoracion",
                    String.valueOf(serie.getValoracion()));
            raiz.appendChild(elementoSerie);
        }
        Transformer transformador =
                TransformerFactory.newInstance().newTransformer();
        transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformador.setOutputProperty(OutputKeys.INDENT, "yes");
        transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                "2");
        transformador.transform(new DOMSource(documento), new StreamResult(new
                File(ruta)));
    }
    // Paso 3
    private void anadirHijo(Document documento, Element padre, String etiqueta,
                            String texto) {
        Element hijo = documento.createElement(etiqueta);
        hijo.setTextContent(texto);
        padre.appendChild(hijo);
    }
}