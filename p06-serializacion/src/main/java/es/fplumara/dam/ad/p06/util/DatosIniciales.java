package es.fplumara.dam.ad.p06.util;

import es.fplumara.dam.ad.p06.modelo.Objeto;
import es.fplumara.dam.ad.p06.modelo.Partida;
import es.fplumara.dam.ad.p06.modelo.Personaje;

import java.util.ArrayList;
import java.util.List;

/**
 * Crea en memoria una partida de ejemplo de «Crónicas de Ébano»:
 * 4 personajes con 5 objetos cada uno. Todos los datos son inventados.
 * Esta clase viene hecha: no hace falta modificarla.
 */
public class DatosIniciales {

    public static Partida crearPartida() {
        List<Personaje> grupo = new ArrayList<Personaje>();

        List<Objeto> inventarioAitana = new ArrayList<Objeto>();
        inventarioAitana.add(new Objeto("Espada de cobre", "arma", 120));
        inventarioAitana.add(new Objeto("Escudo de roble", "armadura", 90));
        inventarioAitana.add(new Objeto("Poción de vida", "poción", 25));
        inventarioAitana.add(new Objeto("Poción de vida", "poción", 25));
        inventarioAitana.add(new Objeto("Mapa de las Minas Grises", "misión", 0));
        grupo.add(new Personaje("Aitana", "Guerrera", 12, 340, inventarioAitana));

        List<Objeto> inventarioKwame = new ArrayList<Objeto>();
        inventarioKwame.add(new Objeto("Báculo de ceniza", "arma", 210));
        inventarioKwame.add(new Objeto("Túnica estrellada", "armadura", 75));
        inventarioKwame.add(new Objeto("Poción de maná", "poción", 30));
        inventarioKwame.add(new Objeto("Libro de runas", "misión", 0));
        inventarioKwame.add(new Objeto("Anillo de escarcha", "accesorio", 160));
        grupo.add(new Personaje("Kwame", "Mago", 11, 190, inventarioKwame));

        List<Objeto> inventarioYusuf = new ArrayList<Objeto>();
        inventarioYusuf.add(new Objeto("Dagas gemelas", "arma", 140));
        inventarioYusuf.add(new Objeto("Capa de sombra", "armadura", 110));
        inventarioYusuf.add(new Objeto("Ganzúas", "herramienta", 15));
        inventarioYusuf.add(new Objeto("Bomba de humo", "consumible", 20));
        inventarioYusuf.add(new Objeto("Llave oxidada", "misión", 0));
        grupo.add(new Personaje("Yusuf", "Pícaro", 12, 230, inventarioYusuf));

        List<Objeto> inventarioMei = new ArrayList<Objeto>();
        inventarioMei.add(new Objeto("Maza de luz", "arma", 95));
        inventarioMei.add(new Objeto("Cota de mallas", "armadura", 130));
        inventarioMei.add(new Objeto("Poción de vida", "poción", 25));
        inventarioMei.add(new Objeto("Hierbas curativas", "consumible", 10));
        inventarioMei.add(new Objeto("Amuleto del alba", "accesorio", 180));
        grupo.add(new Personaje("Mei", "Sanadora", 10, 210, inventarioMei));

        return new Partida("nerea_lumbre", 42.5, grupo, "SES-7F3A-91C2");
    }
}
