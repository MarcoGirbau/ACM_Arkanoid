package codigo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.Random;
import acm.graphics.*;
import acm.util.RandomGenerator;
/*
 * Autor: Marco Girbau
 * 
 * El Arkanoid pero orientado a objetos chachis
 * 
 * Cosas a hacer: 
 * - Arreglar rebote con ladrillos de la nueva version (HECHO)
 * - Rebote con el cursor sea mas progresiva (HECHO)
 * - Crear Sistema partida: Vidas + cambiar nivel (HECHO)
 * - Sistema bonus (1 Bonus hecho que sale en todos los niveles, 1 bola extra en el tercer nivel)
 * - Imagenes incluidas (HECHO)
 */
public class Arkanoid extends acm.program.GraphicsProgram
{
	//Aqui aparecen todos los integers que aparecen a lo largo del codigo
	int altopelotaybarra = 12;
	int anchoBarra = 80;
	int anchoLadrillo = 25;
	int altoLadrillo = 15;
	int numeroLadrillos = 14;
	int tamanobonus = 20;
	int anchuramarcadortexto = 120;
	int alturamarcadortexto = 40;
	int espacioBonus = 300;
	int textoaltura = 99;
	int textoaltura2 = 75;

	//Aqui aparecen las especificaciones de tamaño de los diferentes objetos
	Pelota pelota1 = new Pelota(altopelotaybarra,altopelotaybarra, Color.BLUE);
	Pelota pelota2 = new Pelota(altopelotaybarra, altopelotaybarra, Color.MAGENTA);
	Barra barra1 = new Barra(anchoBarra, altopelotaybarra, Color.RED);
	GObject Heart = new GImage("img/Heart.png", 21, 21);
	Bonus bonus1 = new Bonus(20,20);

	//Especificamos una nueva GImage
	BonusImg bonusfoto = new BonusImg ("img/JorgeCisneros.jpg");

	//Aqui aparece el generador Randomizado
	RandomGenerator r = new RandomGenerator();

	//Aqui aparecen las especificaciones de los marcadores
	Marcador marcador = new Marcador (anchuramarcadortexto,alturamarcadortexto);
	MarcadorVidas marcavida = new MarcadorVidas (anchuramarcadortexto,alturamarcadortexto);

	//Aqui aparece el texto de los diferentes GLabels 
	GLabel perder = new GLabel ("TU PIERDES!");
	GLabel ganar = new GLabel ("TU GANAS!");	

	public void init()
	{
		addMouseListeners();																// Añadimos el control del mouse para el Arkanoid

		setSize(400, 600); 																	//Especificamos el tamaño del mapa

		//Añadimos objetos 
		add(pelota1, 0, (int)(getHeight()*0.65 - pelota1.getHeight()));
		add(barra1, 0, getHeight()*0.80);

		dibujaNivel01();																	//Dibujamos el nivel de ladrillos 1

		int posicionBonusX = r.nextInt(espacioBonus-tamanobonus);
		int posicionBonusY = r.nextInt(espacioBonus-tamanobonus);

		add(bonusfoto,posicionBonusX,posicionBonusY);								//Añadimos el rectangulo en el lugar random
		add(bonus1,posicionBonusX,posicionBonusY);											//Añadimos la imagen bonus en un lugar Random

		setBackground(Color.GRAY);															//Cambiamos el color del fondo

		//Añadimos por ultimo los marcadores tanto de texto como de su respectivo cuadro
		add(marcavida, getWidth() - 410 , getHeight() - textoaltura);
		add(marcador, getWidth() - 130, getHeight() - textoaltura);
		add(marcador.texto, getWidth() - 125, getHeight() - textoaltura2);
		add(marcavida.textovida, getWidth() - 380 , getHeight() - 70);
		add(Heart, getWidth() - 370 , getHeight() - 95);
	}
	public void run()
	{
		while(MarcadorVidas.vidas >= 1 && MarcadorVidas.vidas <= 3)							//Mientras suceda lo que indica el while se hara
		{
			//Movemos los Bonus
			bonus1.mueveBonus((int)barra1.getX(), getWidth());
			bonusfoto.mueveBonusImg((int)barra1.getX(), getWidth());

			pelota1.muevete(this);
			pause(3); 																		//Especifica la velocidad

			if(Marcador.puntuacion == 105)													//Si sucede lo que indica el if ocurrira lo del interior 
			{
				pelota1.setLocation(getHeight()/2, getHeight()/2); 							//Colocamos de nuevo la pelota al cambiar de nivel

				dibujaNivel02();															//Dibujamos el nivel 2

				setBackground(Color.PINK); 													//Cambiamos el color de fondo

				dibujaNivel011(); 															//Añadimos el segundo puesto de ladrillos

				//Añadimos los bonus con sus Random respectivos
				int posicionBonusX = r.nextInt(espacioBonus-tamanobonus);					
				int posicionBonusY = r.nextInt(espacioBonus-tamanobonus);
				add(bonusfoto,posicionBonusX,posicionBonusY);								//Añadimos el rectangulo en el lugar random
				add(bonus1,posicionBonusX,posicionBonusY);									//Añadimos la imagen bonus en un lugar Random

				while(MarcadorVidas.vidas >= 1 && MarcadorVidas.vidas <= 3)					//Si sucede lo que indica el if ocurrira lo del interior 
				{
					//Movemos los Bonus
					bonus1.mueveBonus((int)barra1.getX(), getWidth());
					bonusfoto.mueveBonusImg((int)barra1.getX(), getWidth());

					pelota1.muevete(this);
					pause(3);

					if(Marcador.puntuacion == 259 )											//Si sucede lo que indica el if ocurrira lo del interior
					{
						pelota1.setLocation(getHeight()/2, getHeight()/2); 					//Colocamos de nuevo la pelota al cambiar de nivel

						//Dibujamos los niveles con sus respectivos ladrillos extras
						dibujaNivel03();
						dibujaNivel012();
						dibujaNivel013();

						//Añadimos el bonus y la pelota 2 
						add(bonusfoto,posicionBonusX,posicionBonusY);						//Añadimos el rectangulo en el lugar random
						add(bonus1,posicionBonusX,posicionBonusY);							//Añadimos la imagen bonus en un lugar Random
						add(pelota2, 0, (int)(getHeight()*0.65 - pelota2.getHeight()));			

						setBackground(Color.orange);										//Cambiamos el color de fondo

						while(MarcadorVidas.vidas >= 1 && MarcadorVidas.vidas <= 3)
						{
							//Movemos los Bonus
							bonus1.mueveBonus((int)barra1.getX(), getWidth());
							bonusfoto.mueveBonusImg((int)barra1.getX(), getWidth());

							//Movemos las pelotas
							pelota1.muevete(this);
							pelota2.muevete(this);
							pause(4);

							if(Marcador.puntuacion == 376)									//Si sucede lo que indica el if ocurrira lo del interior
							{
								add(ganar, getWidth()/2.5, getHeight()/2);					//Aparecera un texto en el lugar indicado

								//Colocamos las pelotas en el centro y las borramos, junto a la barra.
								pelota1.setLocation(getHeight()/2, getHeight()/2);
								pelota2.setLocation(getHeight()/2, getHeight()/2);
								remove(pelota1);
								remove(pelota2);
								remove(barra1);
							}
						}
					}
				}
			}	
		}
		if(MarcadorVidas.vidas <= 0)														//Si sucede lo que indica el if ocurrira lo del interior
		{
			add(perder, getWidth()/2.5, getHeight()/2);										//Aparecera un texto en el lugar indicado
		}
	}

	public void mouseMoved (MouseEvent _evento)												//Especificamos el evento del mouse con la barra
	{
		barra1.mueveBarra(_evento.getX(), getWidth());
	}

	public void mueveBonusfoto(int posX, int anchoPantalla)
	{
		if(posX + getWidth() < anchoPantalla)
		{
			setLocation(posX, getY());														//Hace referencia al Bonus
		}
	}

	private void dibujaNivel01()															//105 ladrillos
	{
		for(int j=0; j<14; j++)
		{
			for(int i=j; i<14; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i - anchoLadrillo*j/2 + 17, altoLadrillo*j + altoLadrillo, anchoLadrillo, altoLadrillo, Color.ORANGE);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	private void dibujaNivel02()															//140 + Segundo nivel de ladrillo
	{
		for(int i=0; i<10; i++)
		{
			for(int j=0; j<14; j++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(j * anchoLadrillo + 17, i * altoLadrillo, anchoLadrillo, altoLadrillo, Color.GREEN);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	private void dibujaNivel011()															//Segundo nivel de ladrillo = 14 
	{
		for(int j=0; j<1; j++)
		{
			for(int i=j; i<14; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i + 17 , altoLadrillo, anchoLadrillo, altoLadrillo, Color.RED);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	private void dibujaNivel03()															//Tercer nivel de ladrillo  = 90 + 
	{
		for(int j=0; j<10; j++)
		{
			for(int i=j; i<14; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i - anchoLadrillo*j/2 + 17, j * altoLadrillo, anchoLadrillo, altoLadrillo, Color.CYAN);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	private void dibujaNivel012()															//Segundo nivel de ladrillo 2 = 13
	{
		for(int j=0; j<1; j++)
		{
			for(int i=j; i<13; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i + 30 , altoLadrillo, anchoLadrillo, altoLadrillo, Color.RED);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	private void dibujaNivel013()															//Segundo nivel de ladrillo 3 = 9 
	{
		for(int j=0; j<1; j++)
		{
			for(int i=j; i<9; i++)
			{
				Ladrillo miLadrillo = new Ladrillo
						(anchoLadrillo*i + 80 , altoLadrillo + 60, anchoLadrillo, altoLadrillo, Color.RED);
				add(miLadrillo);
				pause(7);
			}
		}
	}
}
