package codigo;
import java.awt.Color;

import acm.graphics.GImage;
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

	public Pelota(double _ancho, double _alto) //Constructor 1
	{
		super(_ancho, _alto);
	}
	/**
	 * Este es el constructor dabuti que permite pasar el color del parametro
	 * @param _ancho
	 * @param _alto
	 */
	public Pelota(double _ancho, double _alto, Color _color) //Constructor 2
	{
		super(_ancho, _alto);
		if(_ancho <=0)
		{
			this.setSize(1, 1);
		}
		this.setFillColor(_color);
		this.setFilled(true);
	}
	/*
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
		if(this.getY() >= _arkanoid.getHeight() && MarcadorVidas .vidas >=3)//Si sucede lo que indica el if ocurrira lo del interior 
		{
			setLocation(_arkanoid.getWidth()/2, _arkanoid.getHeight()*0.65 - this.getHeight());//Cambiamos la posicion de la bola al perder vidas
			_arkanoid.marcavida.actualizaMarcadorVidas(-1);//Restamos 1 vida
		}
		if(this.getY() >= _arkanoid.getHeight() && MarcadorVidas .vidas >=2)//Si sucede lo que indica el if ocurrira lo del interior 
		{
			setLocation(_arkanoid.getWidth()/2, _arkanoid.getHeight()*0.65 - this.getHeight());//Cambiamos la posicion de la bola al perder vidas
			_arkanoid.marcavida.actualizaMarcadorVidas(-1);//Restamos 1 vida
		}
		if(this.getY() >= _arkanoid.getHeight() && MarcadorVidas .vidas >=1)//Si sucede lo que indica el if ocurrira lo del interior 
		{
			setLocation(_arkanoid.getWidth()/2, _arkanoid.getHeight()*0.65 - this.getHeight());//Cambiamos la posicion de la bola al perder vidas
			_arkanoid.marcavida.actualizaMarcadorVidas(-1);//Restamos 1 vida
		}
		if(Marcador.puntuacion >349 && Marcador.puntuacion <351)//Si sucede lo que indica el if ocurrira lo del interior 
		{
			setLocation(_arkanoid.getWidth()/2, _arkanoid.getHeight()*0.65 - this.getHeight());//Colocamos la bola en el centro porque has ganado
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
		//los choques entre la bola y los ladrillos y la bola y el cursor y el bonus
		move(xVelocidad, yVelocidad);
	}
	private boolean chequeacolision(double posX, double posY, Arkanoid _arkanoid)
	{
		boolean noHaChocado = true;
		GObject auxiliar;
		auxiliar = _arkanoid.getElementAt(posX, posY);

		if(auxiliar instanceof Ladrillo)
			//Especificamos lo que ocurre cuando tocamos un ladrillo
		{
			if(auxiliar.getY() == posY || auxiliar.getY() + auxiliar.getHeight() == posY || auxiliar.getY() + auxiliar.getWidth() == posY)
			{
				yVelocidad *= -1;
			}
			if(auxiliar.getX() == posX || auxiliar.getX() + auxiliar.getWidth() == posX ||  auxiliar.getX() + auxiliar.getHeight() == posX)
			{
				xVelocidad *= -1;
			}
			_arkanoid.remove(auxiliar);//Quitamos un ladrillo
			_arkanoid.marcador.actualizaMarcador(+1);//Añadimos un punto 
			noHaChocado = false;
		}
		else if(auxiliar instanceof Barra)
			//Especificamos lo que ocurre cuando tocas la barra
		{
			//Se va a modificar el rebote de la bola con el cursor para que no sea siempre igual
			//Calculo la posicion X del punto central de la bola
			double centroBola =  getX() + getWidth()/2;
			if(centroBola > auxiliar.getX() + auxiliar.getWidth()/3 && centroBola < auxiliar.getX() + 2 * auxiliar.getWidth()/3)//Centro
			{
				yVelocidad *= -1;
			}
			else if(centroBola > auxiliar.getX()/2 + auxiliar.getWidth()/3 && centroBola < auxiliar.getX()/2 + 2 * auxiliar.getWidth()/3)//No tan centro
			{
				yVelocidad = -0.75;
			}
			else //Esquinas
			{
				yVelocidad = -0.5;
			}
			noHaChocado = false;
		}
		else if(auxiliar instanceof Bonus)
			//Especificamos que ocurre cuando tocas el Bonus
		{
			_arkanoid.anchoBarra += 40; //Cambiamos el integer anchoBarra
			_arkanoid.barra1.setSize(_arkanoid.anchoBarra, _arkanoid.barra1.getHeight()); //Cambiamos el tamaño de la barra
			_arkanoid.remove(auxiliar);//Quitamos el bonus ya que se ha chocado 
			_arkanoid.remove(((Bonus) auxiliar).bonus);
			noHaChocado = false;
		}
		return noHaChocado;
	}
}
