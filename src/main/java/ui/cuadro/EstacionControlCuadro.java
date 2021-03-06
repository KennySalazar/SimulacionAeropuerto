/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.cuadro;

import Interfaces.Posicionable;
import backend.instalaciones.EstacionControl;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kenny
 */
public class EstacionControlCuadro extends Cuadro implements Posicionable {

    private JLabel avionesContactadoLabel;
    private EstacionControl estacionCon;

    public EstacionControlCuadro(EstacionControl estacionCon) {
        super("/imagenes/estacionControl.png");
        this.estacionCon = estacionCon;
        avionesContactadoLabel = new JLabel();
    }

    @Override
    public void posicionarElementos(int ancho, int alto) {
        ponerFuente(alto, 12, 150);
        iniciarCuadro(ancho, alto);

        setBackground(new Color(252, 170, 126));
        desplegarTextoID(estacionCon.getID());
        desplegarTextoAvionesContactados();

        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(ID);
        textos.add(avionesContactadoLabel);

        Dimension tamaño = textos.getPreferredSize();
        textos.setBounds(3, 3, tamaño.width, tamaño.height);
        textos.setOpaque(false);

        add(textos);
        add(imagen);
        imagen.setBounds(1, tamaño.height, ancho, alto - tamaño.height-3);
        cambiarTamaño();

        ponerToolTips();
        setToolTipText(toolTipTexto);
    }

    @Override
    public void actualizarElementos() {
        estacionCon.armarTexto(estacionCon.getAvionesContactados().obtenerLongitud());
        desplegarTextoAvionesContactados();
        ponerToolTips();
        setToolTipText(toolTipTexto);
        revalidate();
        repaint();
    }

    public void ponerToolTips() {
        super.ponerToolTips(estacionCon.getID());
        toolTipTexto += estacionCon.getOcupados();

    }

    public void desplegarTextoAvionesContactados() {
        avionesContactadoLabel.setFont(fuente);
        avionesContactadoLabel.setText("Aviones Contactados: " + estacionCon.getOcupados());
    }
}
