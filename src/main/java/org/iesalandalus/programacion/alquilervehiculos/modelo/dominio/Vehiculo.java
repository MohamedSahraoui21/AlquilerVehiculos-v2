package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;
//vehiculo es una clase madre

public abstract class Vehiculo {
	// añadir constantes y atributos

	private static final String ER_MARCA = "[A-Z][a-z]+([ -]?[A-Z][a-z]+)*|[A-Z]+";
	private static final String ER_MATRICULA = "\\d{4}[BCDFGHJKLMNPRSTVWXYZ]{3}";
	private String marca;
	private String modelo;
	private String matricula;
	// crear constructores

	protected Vehiculo(String marca, String modelo, String matricula) {
		setMarca(marca);
		setModelo(modelo);
		setMatricula(matricula);
	}

	protected Vehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un vehículo nulo.");
		}
		marca = vehiculo.getMarca();
		modelo = vehiculo.getModelo();
		matricula = vehiculo.getMatricula();
	}

	public static Vehiculo copiar(Vehiculo vehiculo) {
		Vehiculo tipoVehiculo = null;
		if (vehiculo instanceof Turismo turismo) {
			tipoVehiculo = new Turismo(turismo);
		}
		if (vehiculo instanceof Furgoneta furgoneta) {
			tipoVehiculo = new Furgoneta(furgoneta);
		}
		if (vehiculo instanceof Autobus autobus) {
			tipoVehiculo = new Autobus(autobus);
		}

		return tipoVehiculo;
	}
	// declarar metodo abstarct

	public abstract int getFactorPrecio();

	public static Vehiculo getVehiculoConMatricula(String matricula) {
		return new Turismo("KIA", "Turismo", 90, matricula);
	}

	public String getMarca() {
		return marca;
	}

	private void setMarca(String marca) {
		if (marca == null) {
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		}
		if (!marca.matches(ER_MARCA)) {
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
		}
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	private void setModelo(String modelo) {
		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}
		if (modelo.isBlank()) {
			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
		}
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	private void setMatricula(String matricula) {
		if (matricula == null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		}
		if (!matricula.matches(ER_MATRICULA)) {
			throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
		}
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vehiculo))
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}

}