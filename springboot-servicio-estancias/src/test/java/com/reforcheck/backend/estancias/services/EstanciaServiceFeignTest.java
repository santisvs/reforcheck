package com.reforcheck.backend.estancias.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reforcheck.backend.commons.entities.mysql.models.estancia.Estancia;
import com.reforcheck.backend.estancias.clients.InstalacionClientRest;

@SpringBootTest
public class EstanciaServiceFeignTest {
	
	@Autowired
	private InstalacionClientRest clienteInstalacionFeign;

	@Test
	public void setInstalacionNull() {
		Estancia estancia = new Estancia();
		estancia.setInstalacion(clienteInstalacionFeign.listarByIdEstancia("sdcsdsdf"));
		assertEquals(null, estancia.getInstalacion());
	}
}
