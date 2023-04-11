//MOHAMED SAHRAOUI 1DAW

package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final String PATRON_MES = "MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);

	private Consola() {

	}

	public static void mostrarCabecera(String mensaje) {
		StringBuilder subrayado = new StringBuilder(mensaje.length());
		for (int i = 0; i < mensaje.length(); i++) {
			subrayado.append("-");
		}
		System.out.printf("%s  %n", mensaje);
		System.out.print(subrayado);
	}

	public static void mostrarMenuAcciones() {
		mostrarCabecera("Gestionar un proyecto de alquiler de vehiculos");
		System.out.printf("%n");
		for (Accion accion : Accion.values()) {
			System.out.printf("%s%n", accion);
		}
	}

	public static Accion elegirAccion() {
		Accion accion = null;
		do {
			try {
				int entero = leerEntero("Introduce la opción:");
				accion = Accion.get(entero);
			} catch (OperationNotSupportedException e) {
				System.out.printf("%s %n", e.getMessage());
				accion = null;
			}
		} while (accion == null);
		return accion;
	}

	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		return Entrada.cadena();
	}

	private static Integer leerEntero(String mensaje) {
		System.out.print(mensaje);
		return Entrada.entero();
	}

	private static LocalDate leerFecha(String mensaje, String patron) {
		LocalDate fecha = null;
		System.out.print(mensaje);
		try {
			if (PATRON_MES.equals(patron)) {
				fecha = LocalDate.parse("01/" + Entrada.cadena(), FORMATO_FECHA);
			} else {
				fecha = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
			}
		} catch (DateTimeException e) {
			System.out.printf("%s", e.getMessage());
		}
		return fecha;
	}

	public static Cliente leerCliente() {
		String nombre = leerCadena("Introduce el nombre:");
		String dni = leerCadena("Introduce el dni:");
		String telefono = leerCadena("Introduce el telefono:");
		return new Cliente(nombre, dni, telefono);
	}

	public static Cliente leerClienteDni() {
		String dni = leerCadena("Introduce el dni:");
		return Cliente.getClienteConDni(dni);
	}

	public static String leerNombre() {
		return leerCadena("Introduce el nombre:");
	}

	public static String leerTelefono() {
		return leerCadena("Introduce el telefono:");
	}

	public static Vehiculo leerVehiculo() {
		mostrarMenuTiposVehiculos();
		return leerVehiculo(elegirTipoVehiculo());
	}

	private static void mostrarMenuTiposVehiculos() {
		mostrarCabecera("Los tipos de vehiculos disponible : ");
		System.out.printf("%n 0-%s%n 1-%s%n 2-%s%n", TipoVehiculo.TURISMO, TipoVehiculo.AUTOBUS, TipoVehiculo.FURGONETA);
	}

	private static TipoVehiculo elegirTipoVehiculo() {
		TipoVehiculo tipoVehiculo = null;
		do {
			try {
				int entero = leerEntero("escribe el tipo de vehiculo: ");
				System.out.printf("%n");
				tipoVehiculo = TipoVehiculo.get(entero);
			} catch (Exception e) {
				System.out.printf("%s %n", e.getMessage());
				tipoVehiculo = null;
			}
		} while (tipoVehiculo == null);
		return tipoVehiculo;
	}

	private static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		Vehiculo vehiculo = null;
		String marca = leerCadena("escribe la marca:");
		String modelo = leerCadena("escribe el modelo:");
		String matricula = leerCadena("escribe la matricula:");

		if (tipoVehiculo == TipoVehiculo.TURISMO) {
			vehiculo = new Turismo(marca, modelo, leerEntero("escribe la cilindrada:"), matricula);
		} else if (tipoVehiculo == TipoVehiculo.AUTOBUS) {
			vehiculo = new Autobus(marca, modelo, leerEntero("escribe las plazas: "), matricula);
		} else if (tipoVehiculo == TipoVehiculo.FURGONETA) {
			vehiculo = new Furgoneta(marca, modelo, leerEntero("escribe los pma: "),
					leerEntero("escribe las plazas: "), matricula);
		}
		return vehiculo;

	}

	public static Vehiculo leerVehiculoMatricula() {
		String matricula = leerCadena("escribe la matricula:");
		return Vehiculo.getVehiculoConMatricula(matricula);
	}

	public static Alquiler leerAlquiler() {
		Cliente cliente = leerClienteDni();
		Vehiculo vehiculo = leerVehiculoMatricula();
		LocalDate fechaAlquiler = leerFecha("escribe la fecha de alquiler:", PATRON_FECHA);
		return new Alquiler(cliente, vehiculo, fechaAlquiler);
	}

	public static LocalDate leerFechaDevolucion() {
		return leerFecha("escribe la fecha de devolucion:", PATRON_FECHA);
	}

	public static LocalDate leerMes() {
		return leerFecha("escribe el mes y el año :", PATRON_MES);
	}
}