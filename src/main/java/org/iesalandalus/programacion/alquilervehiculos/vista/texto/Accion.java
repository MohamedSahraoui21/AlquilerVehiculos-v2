package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

public enum Accion {
	SALIR("salir.") {
		@Override
		public void ejecutar() {
			vista.terminar();
		}
	},
	INSERTAR_CLIENTE("insertar cliente") {
		@Override
		public void ejecutar() {
			vista.insertarCliente();
		}
	},
	INSERTAR_VEHICULO("insertar vehiculo") {
		@Override
		public void ejecutar() {
			vista.insertarVehiculo();
		}
	},
	INSERTAR_ALQUILER("insertar alquiler") {
		@Override
		public void ejecutar() {
			vista.insertarAlquiler();
		}
	},
	BUSCAR_CLIENTE("buscar cliente") {
		@Override
		public void ejecutar() {
			vista.buscarCliente();
		}
	},
	BUSCAR_VEHICULO("buscar vehiculo") {
		@Override
		public void ejecutar() {
			vista.buscarVehiculo();
		}
	},
	BUSCAR_ALQUILER("buscar alquiler") {
		@Override
		public void ejecutar() {
			vista.buscarAlquiler();
		}
	},
	MODIFICAR_CLIENTE("modificar cliente") {
		@Override
		public void ejecutar() {
			vista.modificarCliente();
		}
	},
	DEVOLVER_ALQUILER_CLIENTE("devolver alquiler cliente") {
		@Override
		public void ejecutar() {
			vista.devolverAlquilerCliente();
		}
	},
	DEVOLVER_ALQUILER_VEHICULO("devolver alquiler vehiculo") {
		@Override
		public void ejecutar() {
			vista.devolverAlquilerVehiculo();
		}
	},
	BORRAR_CLIENTE("borrar cliente") {
		@Override
		public void ejecutar() {
			vista.borrarCliente();

		}
	},
	BORRAR_VEHICULO("borrar vehículo") {
		@Override
		public void ejecutar() {
			vista.borrarVehiculo();
		}
	},
	BORRAR_ALQUILER("borrar alquiler") {
		@Override
		public void ejecutar() {
			vista.borrarAlquiler();
		}
	},
	LISTAR_CLIENTES("listar clientes") {
		@Override
		public void ejecutar() {
			vista.listarClientes();
		}
	},
	LISTAR_VEHICULOS("listar vehículos") {
		@Override
		public void ejecutar() {
			vista.listarVehiculos();
		}
	},
	LISTAR_ALQUILERES("listar alquileres") {
		@Override
		public void ejecutar() {
			vista.listarAlquileres();
		}
	},
	LISTAR_ALQUILERES_CLIENTE("listar alquileres cliente") {
		@Override
		public void ejecutar() {
			vista.listarAlquileresCliente();
		}
	},
	LISTAR_ALQUILERES_VEHICULO("listar alquileres vehículo") {
		@Override
		public void ejecutar() {
			vista.listarAlquileresVehiculo();

		}
	},
	MOSTRAR_ESTADISTICAS_MENSUALES("mostrar estadísticas mensuales") {
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

	private static boolean esOrdinalValido(int num) {
		return num >= 0 && num <= values().length - 1;
	}

	public static Accion get(int num) {
		if (!esOrdinalValido(num)) {
			throw new ArrayIndexOutOfBoundsException("el ordinal no es correcto");

		}
		return values()[num];
	}

	@Override
	public String toString() {
		return String.format("%d - %s", ordinal(), texto);
	}

}
