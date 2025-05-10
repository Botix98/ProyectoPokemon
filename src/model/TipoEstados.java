package model;

public enum TipoEstados {
	PARALIZADO("PAR"),
	QUEMADO("QUE"),
	ENVENENADO("ENV"),
	GRAVEMENTE_ENVENENADO("GENV"),
	DORMIDO("DOR"),
	CONGELADO("CONG"),
	HELADO("HEL"),
	SOMNOLIENTO("SOM"),
	POKERUS(""),
	DEBILITADO(""),
	CONFUSO("CONF"),
	ENAMORADO("ENM"),
	ATRAPADO("ATR"),
	MALDITO("MAL"),
	DRENADORAS(""),
	CANTO_MORTAL("CM"),
	CENTRO_DE_ATENCION("CA"),
	AMEDRENTADO(""),
	SIN_ESTADO("");
	
	private String pseudonimo;

	/**
	 * @param pseudonimo
	 */
	private TipoEstados(String pseudonimo) {
		this.pseudonimo = pseudonimo;
	}

	/**
	 * @return the pseudonimo
	 */
	public String getPseudonimo() {
		return pseudonimo;
	}
}
