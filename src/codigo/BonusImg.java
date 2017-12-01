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

public class BonusImg extends GImage
{

	public BonusImg(String string) 
	{
		super(string);
	}

	public void mueveBonusImg(int posX, int anchoPantalla)
	{
		if(posX + getWidth() < anchoPantalla)
		{
			setLocation(posX, getY());											//Hace referencia a la imagen Bonus
		}
	}
}
