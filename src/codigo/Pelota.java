package codigo;
import java.awt.Color;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
/*
 * Autor: Marco Girbau
 * 
 * La clave pelota es la que vamos a utilizar 
 * Tiene dos constructores
 */

public class Pelota extends GOval
{
	/**
	 * Este es el constructor basico, que es identico al de la clase GOval
	 * @param _ancho
	 * @param _alto
	 */
	double xVelocidad = 1; //Velocidad de la bola en el eje X
	double yVelocidad = -1; //Velocidad de la bola en el eje Y
	//GLabel ganar = new GLabel ("TU GANAS!");
	//GLabel perder = new GLabel ("TU PIERDES!");
	
	public Pelota(double _ancho, double _alto)
	{
		super(_ancho, _alto);
	}
	/**
	 * Este es el constructor dabuti que permite pasar el color del parametro
	 * @param _ancho
	 * @param _alto
	 */
	public Pelota(double _ancho, double _alto, Color _color)
	{
		super(_ancho, _alto);
		if(_ancho <=0)
		{
			this.setSize(1, 1);
		}
		this.setFillColor(_color);
		this.setFilled(true);
	}
	/**
	 * Se encarga de mover a la pelota y chequear si ha habido colision
	 */
	public void muevete(Arkanoid _arkanoid)
	{
			//chequea si ha chocado con las paredes izq o derecha
		if(this.getX() + this.getWidth() >= _arkanoid.getWidth() || this.getX() < 0)
		{
			xVelocidad *= -1;
		}	
			//chequea si ha chocado con el techo
		if(this.getY() < 0)
		{
			yVelocidad *= -1;
		}
		if (chequeacolision(getX(), getY(), _arkanoid))//chequeo la esquina superior izquierda
		{
			if (chequeacolision(getX() + getWidth(), getY(), _arkanoid))//chequeo la esquina superior derecha
			{
				if (chequeacolision(getX(), getY() + getHeight(), _arkanoid))//chequeo la esquina inferior izquierda
				{
					if (chequeacolision(getX() + getWidth(), getY() + getHeight(), _arkanoid))//chequeo la esquina inferior derecha
					{
						
					}
				}
			}
		}
		//Voy a crear un metodo chequeacolision generico que sirva para comprobar
		//los choques entre la bola y los ladrillos y la bola y el cursor
		move(xVelocidad, yVelocidad);
	}
	private boolean chequeacolision(double posX, double posY, Arkanoid _arkanoid)
	{
		boolean noHaChocado = true;
		GObject auxiliar;
		auxiliar = _arkanoid.getElementAt(posX, posY);
		
		if(auxiliar instanceof Ladrillo)
		{
			if(auxiliar.getY() == posY || auxiliar.getY() + auxiliar.getHeight() == posY)
			{
				yVelocidad *= -1;
				
			}
			else if(auxiliar.getX() == posX || auxiliar.getX() + auxiliar.getWidth() == posX)
			{
				xVelocidad *= -1;
				
			}
			_arkanoid.remove(auxiliar);
			noHaChocado = false;
		}
		else if(auxiliar instanceof Barra)
		{
			yVelocidad *= -1;
			noHaChocado = false;
		}
		//add(ganar, getWidth()/2.5, getHeight()/2);
		//add(perder, getWidth()/2.5, getHeight()/2);
		return noHaChocado;
	}
}
