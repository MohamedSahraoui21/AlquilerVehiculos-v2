package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	public static void mostrarCabecera(String dev) {
		System.out.println(dev);
		for (int i = 0; i < dev.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	public static void mostrarMenu() {
		String mensajePrimero = "Gestión de alquiler de vehículos:";
		System.out.println(mensajePrimero);
		for (Accion opcion : Accion.values()) {
			System.out.println(opcion);
		}
	}

	public static Accion elegirAccion() {
		Accion accion = null;
		int opc;
		do {
			opc = leerEntero("elige una opción : ");

			try {
				accion = Accion.get(opc);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());

			}
		} while (opc < 0 || opc >= Accion.values().length);
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
		String nombre = leerCadena("escribe el nombre:");
		String dni = leerCadena("escribe el dni:");
		String telefono = leerCadena("escribe el telefono:");
		return new Cliente(nombre, dni, telefono);
	}

	public static Cliente leerClienteDni() {
		String dni = leerCadena("escribe el dni:");
		return Cliente.getClienteConDni(dni);
	}

	public static String leerNombre() {
		return leerCadena("escribe el nombre:");
	}

	public static String leerTelefono() {
		return leerCadena("escribe el telefono:");
	}

	public static Vehiculo leerVehiculo() {
		mostrarMenuTiposVehiculos();
		return leerVehiculo(elegirTipoVehiculo());
	}

	private static void mostrarMenuTiposVehiculos() {
		mostrarCabecera("menú tipos de vehiculos: ");
		for (TipoVehiculo tipoVehiculo : TipoVehiculo.values()) {
			System.out.println(tipoVehiculo);
		}
	}

	private static TipoVehiculo elegirTipoVehiculo() {
		TipoVehiculo tipoVehiculo = null;
		int opcion;
		do {
			opcion = leerEntero("elige el tipo de vehículo: ");
			try {
				tipoVehiculo = TipoVehiculo.get(opcion);
			} catch (Exception e) {
				mostrarCabecera(e.getMessage());
			}

		} while (opcion < 0 || opcion >= TipoVehiculo.values().length);

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
			vehiculo = new Furgoneta(marca, modelo, leerEntero("escribe los pma: "), leerEntero("escribe las plazas: "),
					matricula);
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
		LocalDate fechaAlquiler = leerFecha("escribe la fecha de Alquiler:", PATRON_FECHA);
		return new Alquiler(cliente, vehiculo, fechaAlquiler);
	}

	public static LocalDate leerFechaDevolucion() {
		return leerFecha("escribe la fecha de Devolucion:", PATRON_FECHA);
	}

	public static LocalDate leerMes() {
		return leerFecha("escribe el mes y el año:", PATRON_MES);
	}

}