package estacionamiento.dominio;

public class Parqueadero {

	private static final int LIMITE_CARROS = 20;
	private static final int LIMITE_MOTOS = 10;
	private static final int COSTO_MOTOS_HORA = 500;
	private static final int COSTO_MOTOS_DIA = 4000;
	private static final int COSTO_CARROS_HORA = 1000;
	private static final int COSTO_CARROS_DIA = 8000;
	private static final int EXTRA_MOTOS = 2000;
	
	
	public Parqueadero () {
		
	}

	public static int getLimiteMotos() {
		return LIMITE_MOTOS;
	}

	public static int getLimiteCarros() {
		return LIMITE_CARROS;
	}

	public static int getCostoMotosHora() {
		return COSTO_MOTOS_HORA;
	}

	public static int getCostoMotosDia() {
		return COSTO_MOTOS_DIA;
	}

	public static int getCostoCarrosHora() {
		return COSTO_CARROS_HORA;
	}

	public static int getCostoCarrosDia() {
		return COSTO_CARROS_DIA;
	}

	public static int getExtraMotos() {
		return EXTRA_MOTOS;
	}
	
	
}
