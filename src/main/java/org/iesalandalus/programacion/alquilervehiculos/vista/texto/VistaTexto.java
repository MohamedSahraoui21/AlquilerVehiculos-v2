package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

//clase hija de vista
public class VistaTexto extends Vista {

	public VistaTexto() {
		Accion.setVista(this);
	}

	public void comenzar() {
		Accion accion;
		do {
			Consola.mostrarMenu();
			accion = Consola.elegirAccion();
			accion.ejecutar();
			System.out.println("");
		} while (accion != Accion.SALIR);
	}

	public void terminar() {
		System.out.print("gracias, hasta luego.");
		System.exit(0);
	}

	// metodos insertar
	public void insertarCliente() {
		try {
			Consola.mostrarCabecera("  1-Insertar Cliente");
			System.out.println("");
			getControlador().insertar(Consola.leerCliente());
			System.out.print("el cliente se ha insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	public void insertarVehiculo() {
		try {
			Consola.mostrarCabecera(" 2-Insertar Turismo");
			System.out.println("");
			getControlador().insertar(Consola.leerVehiculo());
			System.out.print("el vehiculo se ha insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	public void insertarAlquiler() {
		try {
			Consola.mostrarCabecera(" 3-Insertar Alquiler");
			System.out.println("");
			getControlador().insertar(Consola.leerAlquiler());
			System.out.print("el Alquiler se ha insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	// metodos buscar
	public void buscarCliente() {
		try {
			Consola.mostrarCabecera(" 4-Buscar Cliente");
			System.out.println("");
			System.out.print(getControlador().buscar(Consola.leerClienteDni()));
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	public void buscarVehiculo() {
		try {
			Consola.mostrarCabecera(" 5-Buscar Turismo");
			System.out.println("");
			System.out.print(getControlador().buscar(Consola.leerVehiculoMatricula()));
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}

	}

	public void buscarAlquiler() {
		try {
			Consola.mostrarCabecera(" 6-Buscar Alquiler");
			System.out.println("");
			System.out.print(getControlador().buscar(Consola.leerAlquiler()));
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	// metodo modificar
	public void modificarCliente() {
		try {
			Consola.mostrarCabecera(" 7-Modificar Cliente");
			System.out.println("");
			getControlador().modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
			System.out.print("el cliente se ha modificado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	// metodos devolver
	public void devolverAlquilerCliente() {
		try {
			Consola.mostrarCabecera(" 8-Devolver Alquiler De Cliente");
			System.out.println("");
			getControlador().devolver(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
			System.out.print("el Alquiler del cliente se ha devuelto correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	public void devolverAlquilerVehiculo() {
		try {
			Consola.mostrarCabecera(" 9-Devolver Alquiler De Vehiculo");
			System.out.println("");
			getControlador().devolver(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
			System.out.print("el Alquiler del vehiculo se ha devuelto correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	// metodos borrar
	public void borrarCliente() {
		try {
			Consola.mostrarCabecera(" 10-Borrar Cliente");
			System.out.println("");
			getControlador().borrar(Consola.leerClienteDni());
			System.out.print("el cliente se ha borrado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	public void borrarVehiculo() {
		try {
			Consola.mostrarCabecera(" 11-Borrar Vehiculo");
			System.out.println("");
			getControlador().borrar(Consola.leerVehiculoMatricula());
			System.out.print("el vehiculo se ha borrado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	public void borrarAlquiler() {
		try {
			Consola.mostrarCabecera(" 12-Borrar Alquiler");
			System.out.println("");
			getControlador().borrar(Consola.leerAlquiler());
			System.out.print("el Alquiler se ha borrado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	// metodos listar
	// todos los clientes van a ser listados depende el nombre y si coincide nombres
	// iguales entonces con el dni el listado
	public void listarClientes() {
		Consola.mostrarCabecera(" 13-Listar Clientes");
		System.out.println("");
		List<Cliente> listaClientes = getControlador().getClientes();
		listaClientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
		System.out.print(listaClientes.isEmpty() ? "no hay clientes en esta lista" : listaClientes);
	}
	// todos los vehiculos van a ser listados depende el marca y si coincide marcas
	// iguales entonces con la matricula el listado

	public void listarVehiculos() {
		Consola.mostrarCabecera(" 14-Listar vehiculos");
		System.out.println("");
		List<Vehiculo> listaVehiculos = getControlador().getVehiculos();
		listaVehiculos.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo)
				.thenComparing(Vehiculo::getMatricula));
		System.out.print(listaVehiculos.isEmpty() ? "no hay vehiculos en esta lista" : listaVehiculos);
	}
	// todos los alquileres van a ser listados depende el nombre y si coincide
	// nombres iguales entonces con el dni el listado

	public void listarAlquileres() {
		Consola.mostrarCabecera(" 15-Listar Alquileres");
		System.out.println("");
		List<Alquiler> listaAlquileres = getControlador().getAlquileres();
		Comparator<Cliente> comparator = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
		listaAlquileres
				.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente, comparator));
		System.out.print(listaAlquileres.isEmpty() ? "no hay Alquileres en esta lista" : listaAlquileres);
	}

	public void listarAlquileresCliente() {

		try {
			Consola.mostrarCabecera(" 16-Listar los Alquileres de un Cliente");
			System.out.println("");
			List<Alquiler> listaAlquileresCl = getControlador().getAlquileres(Consola.leerClienteDni());
			Comparator<Cliente> comparator = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
			listaAlquileresCl.sort(
					Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente, comparator));
			System.out.print(listaAlquileresCl.isEmpty() ? "no hay Alquileres para ese cliente" : listaAlquileresCl);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	public void listarAlquileresVehiculo() {
		try {
			Consola.mostrarCabecera(" 17-Listar los Alquileres de un vehiculo");
			System.out.println("");
			List<Alquiler> listaAlquileresV = getControlador().getAlquileres(Consola.leerVehiculoMatricula());
			Comparator<Vehiculo> comparadorVehiculo = Comparator.comparing(Vehiculo::getMarca)
					.thenComparing(Vehiculo::getModelo).thenComparing(Vehiculo::getMatricula);
			listaAlquileresV.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getVehiculo,
					comparadorVehiculo));
			System.out.print(listaAlquileresV.isEmpty() ? "no hay Alquileres para ese Vehiculo" : listaAlquileresV);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.print(e.getMessage());
		}
	}

	// metodo estadisticas
	public void mostrarEstadisticasMensualesTipoVehiculo() {
		Consola.mostrarCabecera(" 18 - mostrar estadisticas mensuales");
		System.out.println(" ");
		try {

		} catch (IllegalArgumentException | NullPointerException e) {

		}
	}

}
