/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.instalaciones;

import backend.Avion;
import backend.MotorSimulacion;
import backend.estructuras.lista.Lista;
import backend.estructuras.lista.EstructuraException;
import ui.cuadro.EstacionControlCuadro;

/**
 * @author Kenny
 */
public class EstacionControl extends Instalacion {
    private Lista<Avion> avionesContactados;
    private EstacionControlCuadro cuadro;
    private MotorSimulacion motor;


    public EstacionControl(int ID, int cantidad) {
        super(ID, cantidad);
        avionesContactados = new Lista<Avion>();

    }

    public Lista<Avion> getAvionesContactados() {
        return avionesContactados;
    }

    public void setMotor(MotorSimulacion motor) {
        this.motor = motor;
    }

    @Override
    public void crearLista(Lista lineas, Lista elementos) {
        for (int i = 0; i < lineas.obtenerLongitud(); i++) {
            try {
                String[] separador = ((String) lineas.obtenerElemento(i)).split(",");
                EstacionControl estacionC = new EstacionControl(Integer.parseInt(separador[0]), Integer.parseInt(separador[1]));
                elementos.agregar(estacionC);

            } catch (EstructuraException ex) {

            }
        }

    }

    public void setCuadro(EstacionControlCuadro cuadro) {
        this.cuadro = cuadro;
    }

    public void contactarAvion(Avion avion) throws Exception {
        if (avionesContactados.obtenerLongitud() + 1 <= cantidad) {
            this.avionesContactados.agregar(avion);
            this.avionActivo = avion;
            armarTexto(avionesContactados.obtenerLongitud());
            cuadro.actualizarElementos();
            motor.actualizarCombobox();
            motor.nuevoLog("Estación control", "El avion con id " + avion.getID() +" contactó con la estación de control con id: " + ID);
        } else {
            throw new Exception();
        }
    }

    public void eliminarAvion(Avion avion) {
        try {
            avionesContactados.borrarElemento(avion);
            cuadro.actualizarElementos();
            motor.actualizarCombobox();
            motor.nuevoLog("Estación control", "La estación de control se desvinculó del avión con id: " + avion.getID() );
        } catch (EstructuraException e) {
          
        }

    }

//    public void contactarPista(Avion avion, PistaAterrizaje pista) {
//        eliminarAvion(avion);
//    }
}
