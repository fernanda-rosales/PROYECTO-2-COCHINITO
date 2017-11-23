/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cochinodigital;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MABY
 */
public class Alcancia {

    private double valorMoneda;
    private double total;
    Scanner s = null;
    Moneda moneda;
    File archivo;
    File archivoF;

    /**
     *
     */
    public Alcancia() {
    }

    /**
     *
     * @param Cochinito
     * @param total
     * @return
     */
    public double ContarMonedas(ArrayList<Moneda> Cochinito, double total) {
        this.total = total;
        Iterator<Moneda> monedaSigui = Cochinito.iterator();
        while (monedaSigui.hasNext()) {
            moneda = monedaSigui.next();
            total = total + moneda.getValor();
        }
        return total;
    }

    public void GuardarDatos(ArrayList<Moneda> Cochinito) {
        try {
            archivo = new File("Cochinito.txt");
            FileWriter escribir = new FileWriter(archivo, true);
            Iterator<Moneda> monedaSigui = Cochinito.iterator();
            while (monedaSigui.hasNext()) {
                Moneda moneda = monedaSigui.next();
                escribir.write(" " + moneda.getValor());
            }
            escribir.close();
        } //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }
    }

    public ArrayList<Moneda> AbrirDatos() {
        ArrayList<Moneda> Cochinito;
        try {
//             Leemos el contenido del fichero
            FileReader lector = new FileReader("Cochinito.txt");
            s = new Scanner(lector);
            // Obtengo los datos del archivo 
            Cochinito = new ArrayList<Moneda>();
            while (s.hasNextLine()) {
                String linea = s.nextLine(); // Obtengo una linea del fichero con los numeros
                String[] cortarString = linea.split(" "); // Obtengo los numeros de la linea en un array
                // Pongo los numeros de la fila en el ArrayList
                for (int i = 1; i < cortarString.length; i++) {
                    valorMoneda = Double.parseDouble(cortarString[i]);
                    moneda = new Moneda(valorMoneda);
                    // Añado el ArrayList de enteros al ArratList de ArrayList
                    Cochinito.add(moneda);
                }
            }
            lector.close();
            return Cochinito;
        } //Si se causa un error al leer cae aqui
        catch (IOException e) {
            System.out.println("Error al leer" + e);
        }
        return null;
    }

    public void GuardarFecha(String fecha) {
        try {
            archivoF = new File("fecha.txt");
            FileWriter escribirF = new FileWriter(archivoF, true);

            escribirF.write(fecha + " ");

            escribirF.close();
        } //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }
    }

    public String AbrirFecha() throws IOException {
        String fecha = "";
//             Leemos el contenido del fichero
        FileReader lectorF;
        try {
            lectorF = new FileReader("fecha.txt");
            s = new Scanner(lectorF);
            while (s.hasNextLine()) {
                fecha = s.nextLine();
            }
            lectorF.close();
            return fecha;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Alcancia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void RomperAlcancia() {
        try {
            archivoF = new File("fecha.txt");
            archivo = new File("Cochinito.txt");
            
            archivo.delete();
            archivoF.delete();
            if (archivo.delete() && archivoF.delete()) {
                throw new Exception("El archivo no se pudo borrar");
            } else {
                archivo.createNewFile();
                archivoF.createNewFile();
            }
        } //Si se causa un error al leer cae aqui
        catch (Exception e) {
        }
    }

}
