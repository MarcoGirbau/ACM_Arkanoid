package codigo;
import java.awt.Color;
import java.awt.Font;
import acm.graphics.GLabel;
import acm.graphics.GRect;
/*
 * Autor: Marco Girbau
 * 
 * La clase Marcador sirve para especificar la puntuacion 
 */
public class Marcador extends GRect
{
	GLabel texto = new GLabel("");

	//Estado base de la puntuacion
	static int puntuacion = 0;													

	//Constructor Marcador con ciertas especificaciones para el texto
	public Marcador(double width, double height) 								
	{
		super(width, height);
		setFilled(true);
		setFillColor(Color.WHITE);
		texto.setLabel("Puntuacion: " + puntuacion);
		texto.setFont(new Font("Times New Roman", Font.BOLD, 15));
	}

	//Esto permite que la puntuacion vaya aumentando o disminuyendo
	public void actualizaMarcador(int puntos)
	{
		puntuacion += puntos;
		texto.setLabel("Puntuacion: " + puntuacion);
	}
}
