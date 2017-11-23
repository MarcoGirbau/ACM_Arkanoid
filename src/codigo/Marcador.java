package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Marcador extends GRect
{
	GLabel texto = new GLabel("");
	public Marcador(double width, double height) 
	{
		super(width, height);
		setFilled(true);
		setFillColor(Color.WHITE);
		texto.setLabel("Puntuacion: " + Arkanoid.puntuacion);
		texto.setFont(new Font("Times New Roman", Font.BOLD, 15));
	}
	//public void dibuja(Arkanoid _arkanoid)
	{
		//_arkanoid.add(this, getX(), getY());
		//_arkanoid.add(texto, getX() + 10, getY() + 30);
	}
}
