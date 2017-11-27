package codigo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import acm.graphics.*;
/*
* Autor: Marco Girbau
 * 
 * El Arkanoid pero orientado a objetos chachis
 * 
 * Cosas a hacer: 
 * - Arreglar rebote con ladrillos de la nueva version
 * - Rebote con el cursor sea mas progresiva
 * - Crear Sistema partida: Vidas + cambiar nivel
 * - Sistema bonus
 */

public class Arkanoid extends acm.program.GraphicsProgram
{
	Pelota pelota1 = new Pelota(15,15, Color.BLUE);
	//Pelota pelota2 = new Pelota(20,20, Color.GREEN);
	Barra barra1 = new Barra(400, 15, Color.RED);
	
	int anchoLadrillo = 27;
	int altoLadrillo = 15;
	int numeroLadrillos = 14;
	
	Marcador marcador = new Marcador (120,40);
	MarcadorVidas marcavida = new MarcadorVidas (120,40);
	
	GLabel perder = new GLabel ("TU PIERDES!");
	GLabel ganar = new GLabel ("TU GANAS!");	
	public void init()
	{
		addMouseListeners();
		setSize(400, 600);
		
		add(pelota1, 0, getHeight()*0.65 - pelota1.getHeight());
		//add(pelota2, 0, getHeight()*0.75 - pelota2.getHeight());
		add(barra1, 0, getHeight()*0.80);
		
		dibujaNivel01();
		//dibujaNivel02();
		add(marcavida, getWidth() - 410 , getHeight() - 100);
		add(marcador, getWidth() - 130, getHeight() - 100);
		//marcador.dibuja(this);
		add(marcador.texto, getWidth() - 125, getHeight() - 75);
		add(marcavida.textovida, getWidth() - 370 , getHeight() - 75);
		}
	public void run()
	{
		while(MarcadorVidas.vidas >= 1 && MarcadorVidas.vidas <= 3 && Marcador.puntuacion < 105)
		{
			pelota1.muevete(this);
			//pelota2.muevete(this);
			pause(4);
		}
		if(MarcadorVidas.vidas <= 0)
		{
			add(perder, getWidth()/2.5, getHeight()/2);
		}
		//if(Marcador.puntuacion >=105)
	//	{
			//add(ganar, getWidth()/2.5, getHeight()/2);
	//	}
		if(Marcador.puntuacion >=105)
		{
			dibujaNivel02();
		}	
	}
	
	public void mouseMoved (MouseEvent _evento)
	{
		barra1.mueveBarra(_evento.getX(), getWidth());
	}
	
	private void dibujaNivel01()
	{
		for(int j=0; j<14; j++)
		{
			for(int i=j; i<14; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i - anchoLadrillo*j/2, altoLadrillo*j + altoLadrillo, anchoLadrillo, altoLadrillo, Color.ORANGE);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	private void dibujaNivel02()
	{
		for(int j=0; j<14; j++)
		{
			for(int i=j; i<14; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i + altoLadrillo*j, altoLadrillo*j + anchoLadrillo, anchoLadrillo, altoLadrillo, Color.GREEN);
				add(miLadrillo);
				pause(7);
			}
		}
	}
}
