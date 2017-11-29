package codigo;
import java.awt.Color;
import java.awt.Font;
import acm.graphics.GLabel;
import acm.graphics.GRect;

/*
 * Autor: Marco Girbau 
 * Aqui añadiremos (o intentaremos) el sistema de Bonus
 */
public class Bonus extends GRect
{
	public Bonus(double width, double height, Color _color) //Constructor Bonus
	{
		super(width, height);
		setFilled(true);
		setFillColor(_color);
	}	
}
