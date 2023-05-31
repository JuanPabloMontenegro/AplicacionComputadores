/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juanc
 */
public class Computador {

    private String procesador;
    private String grafica;
    private int ram;

    public Computador(String procesador, String grafica, int ram) {
        this.procesador = procesador;
        this.grafica = grafica;
        this.ram = ram;
    }

    public String getProcesador() {
        return procesador;
    }

    public String getGrafica() {
        return grafica;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}
