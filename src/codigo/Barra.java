package codigo;
import java.awt.Color;
import acm.graphics.GRect;

/**
 * @author Marco Girbau
 * 
 * La clase Barra es la que dibuja el cursor del juego 
 */

public class Barra extends GRect
{
	/**
	 * Crea el cursor del juego
	 * @param width - ancho del cursor
	 * @param height - el alto del cursor
	 * @param _color - color del cursor
	 */
	
	//Constructor Barra
	public Barra(double width, double height, Color _color) 
	{
		super(width, height);
		setFilled(true);
		setFillColor(_color);
	}
	/**
	 * muevebarra reposiciona la barra en la coordenada en la que esta el raton
	 * @param posX  -La posicion x del raton. La y la obtienede la propia barra
	 * @param anchoPantalla   -porque asi no tengo que pasar nada mas
	 */
	public void mueveBarra(int posX, int anchoPantalla)
	{
		if(posX + getWidth() < anchoPantalla)
		{
			setLocation(posX, getY());											//Hace referencia a la barra
		}
	}

}
