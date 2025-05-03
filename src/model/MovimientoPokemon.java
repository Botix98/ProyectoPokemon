package model;

public class MovimientoPokemon {
    private int idPokemon;
    private int idMovimiento;
    private int ppActuales;

    public MovimientoPokemon() {
        this.idPokemon = 0;
        this.idMovimiento = 0;
        this.ppActuales = 0;
    }
    
    public MovimientoPokemon(int idPokemon, int idMovimiento, int ppActuales) {
		super();
		this.idPokemon = idPokemon;
		this.idMovimiento = idMovimiento;
		this.ppActuales = ppActuales;
	}

    public MovimientoPokemon(MovimientoPokemon otro) {
        this.idPokemon = otro.idPokemon;
        this.idMovimiento = otro.idMovimiento;
        this.ppActuales = otro.ppActuales;
    }
    
	public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getPpActuales() {
        return ppActuales;
    }

    public void setPpActuales(int ppActuales) {
        this.ppActuales = ppActuales;
    }
    
    @Override
    public String toString() {
        return "MovimientoPokemon{" +
                "idPokemon=" + idPokemon +
                ", idMovimiento=" + idMovimiento +
                ", ppActuales=" + ppActuales +
                '}';
    }
}