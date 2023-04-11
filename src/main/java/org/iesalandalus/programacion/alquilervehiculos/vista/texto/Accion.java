//MOHAMED SAHRAOUI 1DAW

package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import javax.naming.OperationNotSupportedException;

public enum Accion {
	SALIR("Salir") {
		@Override
		public void ejecutar() {
			vista.terminar();
		}
	},
	INSERTAR_CLIENTE("Insertar-cliente") {
		@Override
		public void ejecutar() {
			vista.insertarCliente();
		}
	},
	INSERTAR_VEHICULO("Insertar-el-vehiculo") {
		@Override
		public void ejecutar() {
			vista.insertarVehiculo();
		}
	},
	INSERTAR_ALQUILER("Insertar-el-alquiler") {
		@Override
		public void ejecutar() {
			vista.insertarAlquiler();
		}
	},
	BUSCAR_CLIENTE("Buscar-cliente") {
		@Override
		public void ejecutar() {
			vista.buscarCliente();
		}
	},
	BUSCAR_VEHICULO("Buscar-vehiculo") {
		@Override
		public void ejecutar() {
			vista.buscarVehiculo();
		}
	},
	BUSCAR_ALQUILER("Buscar-alquiler") {
		@Override
		public void ejecutar() {
			vista.buscarAlquiler();
		}
	},
	MODIFICAR_CLIENTE("Modificar-cliente") {
		@Override
		public void ejecutar() {
			vista.modificarCliente();
		}
	},
	DEVOLVER_ALQUILER_CLIENTE("Devolver-alquiler-cliente") {
		@Override
		public void ejecutar() {
			vista.devolverAlquilerCliente();
		}
	},
	DEVOLVER_ALQUILER_VEHICULO("Devolver-alquiler-vehiculo") {
		@Override
		public void ejecutar() {
			vista.devolverAlquilerVehiculo();
		}
	},
	BORRAR_CLIENTE("Borrar-cliente") {
		@Override
		public void ejecutar() {
			vista.borrarCliente();

		}
	},
	BORRAR_VEHICULO("Borrar-vehiculo") {
		@Override
		public void ejecutar() {
			vista.borrarVehiculo();
		}
	},
	BORRAR_ALQUILER("Borrar-alquiler") {
		@Override
		public void ejecutar() {
			vista.borrarAlquiler();
		}
	},
	LISTAR_CLIENTES("Listar-clientes") {
		@Override
		public void ejecutar() {
			vista.listarClientes();
		}
	},
	LISTAR_VEHICULOS("Listar-vehiculos") {
		@Override
		public void ejecutar() {
			vista.listarVehiculos();
		}
	},
	LISTAR_ALQUILERES("Listar-alquileres") {
		@Override
		public void ejecutar() {
			vista.listarAlquileres();
		}
	},
	LISTAR_ALQUILERES_CLIENTE("Listar-alquileres-cliente") {
		@Override
		public void ejecutar() {
			vista.listarAlquileresCliente();
		}
	},
	LISTAR_ALQUILERES_VEHICULO("Listar-alquileres-vehiculo") {
		@Override
		public void ejecutar() {
			vista.listarAlquileresVehiculo();

		}
	},
	MOSTRAR_ESTADISTICAS_MENSUALES("estadisticas-mensuales") {
		@Override
		public void ejecutar() {
			vista.mostrarEstadisticasMensualesTipoVehiculo();
		}
	};

	private String texto;
	private static VistaTexto vista;

	private Accion(String texto) {
		this.texto = texto;
	}

	public abstract void ejecutar();

	static void setVista(VistaTexto vista) {
		Accion.vista = vista;
	}

	private static boolean esOrdinalValido(int ordinal) {
		boolean ordinalCorrecto = true;
		if (ordinal < 0 || ordinal >= values().length) {
			ordinalCorrecto = false;
		}
		return ordinalCorrecto;
	}

	public static Accion get(int ordinal) throws OperationNotSupportedException {
		if (!esOrdinalValido(ordinal)) {
			throw new OperationNotSupportedException("el ordinal no es correcto");
		}
		return values()[ordinal];
	}

	@Override
	public String toString() {
		return String.format("%d-%s", ordinal(), texto);
	}

}
