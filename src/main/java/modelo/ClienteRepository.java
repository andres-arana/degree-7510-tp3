package modelo;

import java.util.HashMap;
import java.util.Map;

public class ClienteRepository {
	private Map<String, Cliente> clientes = new HashMap<String, Cliente>();

	public void add(final Cliente cliente) {
		clientes.put(cliente.getDni(), cliente);
	}

	public Cliente getOrCreateByDNI(final String dni) {
		if (clientes.containsKey(dni)) {
			return clientes.get(dni);
		} else {
			Cliente cliente = new Cliente(dni);
			clientes.put(dni, cliente);
			return cliente;
		}
	}
}