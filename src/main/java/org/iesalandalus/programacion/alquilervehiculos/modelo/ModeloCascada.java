package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
//modeloCascada es una clase hija de Modelo

public class ModeloCascada extends Modelo {

	public ModeloCascada(FactoriaFuenteDatos factoriaFuenteDatos) {
		super(factoriaFuenteDatos);
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		getClientes().insertar(new Cliente(cliente));
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		getVehiculos().insertar(Vehiculo.copiar(vehiculo));
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		Cliente cliente = buscar(alquiler.getCliente());
		Vehiculo vehiculo = buscar(alquiler.getVehiculo());
		if (cliente == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		if (vehiculo == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		}
		getAlquileres().insertar(new Alquiler(cliente, vehiculo, alquiler.getFechaAlquiler()));
	}

	public Cliente buscar(Cliente cliente) {
		return new Cliente(getClientes().buscar(cliente));
	}

	public Vehiculo buscar(Vehiculo vehiculo) {
		return Vehiculo.copiar(getVehiculos().buscar(vehiculo));
	}

	public Alquiler buscar(Alquiler alquiler) {
		return new Alquiler(getAlquileres().buscar(alquiler));
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		getClientes().modificar(cliente, nombre, telefono);
	}

	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		getAlquileres().devolver(cliente, fechaDevolucion);
	}

	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		getAlquileres().devolver(vehiculo, fechaDevolucion);
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquiler : getAlquileres().get(cliente)) {
			borrar(alquiler);
		}
		getClientes().borrar(cliente);
	}

	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		for (Alquiler alquiler : getAlquileres().get(vehiculo)) {
			borrar(alquiler);
		}
		getVehiculos().borrar(vehiculo);
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		getAlquileres().borrar(alquiler);
	}

	@Override
	public List<Cliente> getListaClientes() {
		List<Cliente> clientesLista = new ArrayList<>();
		for (Cliente cliente : getClientes().get()) {
			clientesLista.add(new Cliente(cliente));
		}
		return clientesLista;
	}

	@Override
	public List<Vehiculo> getListaVehiculos() {
		List<Vehiculo> vehiculosLista = new ArrayList<>();
		for (Vehiculo vehiculo : getVehiculos().get()) {
			vehiculosLista.add(Vehiculo.copiar(vehiculo));
		}
		return vehiculosLista;
	}

	@Override
	public List<Alquiler> getListaAlquileres() {
		List<Alquiler> alquileresLista = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get()) {
			alquileresLista.add(new Alquiler(alquiler));
		}
		return alquileresLista;
	}

	public List<Alquiler> getListaAlquileres(Cliente cliente) {
		List<Alquiler> alquileresLista1 = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(cliente)) {
			alquileresLista1.add(new Alquiler(alquiler));
		}
		return alquileresLista1;
	}

	public List<Alquiler> getListaAlquileres(Vehiculo vehiculo) {
		List<Alquiler> alquileresLista2 = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(vehiculo)) {
			alquileresLista2.add(new Alquiler(alquiler));
		}
		return alquileresLista2;
	}

}