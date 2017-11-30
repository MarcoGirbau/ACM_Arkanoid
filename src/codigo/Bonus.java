package codigo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import acm.graphics.GImage;
/*
 * Autor: Marco Girbau 
 * Aqui añadiremos (o intentaremos) el sistema de Bonus
 */
public class Bonus extends GRect
{
	//Constructor Bonus
	public Bonus(double _x, double _y) 
	{
		super(_x, _y);
		setVisible(false);
	}
	
	//Especificamos una nueva GImage
	GImage bonus = new GImage("img/JorgeCisneros.jpg");
}
