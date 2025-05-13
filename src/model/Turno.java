package model;

public class Turno {
	private int numeroCombate;
	private int numeroTurno;
	private String accionEntrenador;
	private String efectoAccionEntrenador;
	private String actualizacionEstadoEntrenador;
	private String accionRival;
	private String efectoAccionRival;
	private String actualizacionEstadoRival;
	
	/**
	 * @param numeroCombate
	 * @param numeroTurno
	 * @param accionEntrenador
	 * @param accionRival
	 */
	public Turno(int numeroCombate, int numeroTurno, String accionEntrenador, String accionRival) {
		this.numeroCombate = numeroCombate;
		this.numeroTurno = numeroTurno;
		this.accionEntrenador = accionEntrenador;
		this.accionRival = accionRival;
	}

	/**
	 * @return the numeroCombate
	 */
	public int getNumeroCombate() {
		return numeroCombate;
	}

	/**
	 * @param numeroCombate the numeroCombate to set
	 */
	public void setNumeroCombate(int numeroCombate) {
		this.numeroCombate = numeroCombate;
	}

	/**
	 * @return the numeroTurno
	 */
	public int getNumeroTurno() {
		return numeroTurno;
	}

	/**
	 * @param numeroTurno the numeroTurno to set
	 */
	public void setNumeroTurno(int numeroTurno) {
		this.numeroTurno = numeroTurno;
	}

	/**
	 * @return the accionEntrenador
	 */
	public String getAccionEntrenador() {
		return accionEntrenador;
	}

	/**
	 * @param accionEntrenador the accionEntrenador to set
	 */
	public void setAccionEntrenador(String accionEntrenador) {
		this.accionEntrenador = accionEntrenador;
	}

	/**
	 * @return the accionRival
	 */
	public String getAccionRival() {
		return accionRival;
	}

	/**
	 * @param accionRival the accionRival to set
	 */
	public void setAccionRival(String accionRival) {
		this.accionRival = accionRival;
	}

	@Override
	public String toString() {
		return "Turno [numeroCombate=" + numeroCombate + ", numeroTurno=" + numeroTurno + ", accionEntrenador="
				+ accionEntrenador + ", accionRival=" + accionRival + "]";
	}
}