package com.ma.valuacion.infra.claims.info.auto.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ma.valuacion.claims.dominio.InfoAutoCodigo;

@FeignClient(name = "InfoAutoFeingClient", url = "${app.client.inspector.url}", path = "${app.client.inspector.path}")
public interface InfoAutoFeingClient {

	/**
	 * Busca el codigo de info auto de un vehiculo
	 *
	 * @param String siniestro
	 * 
	 * @param String patente
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/infoAuto/consultar/codigo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InfoAutoCodigo> buscarCodigoInfoAuto(
			@RequestParam(name = "siniestro", required = true) String siniestro,
			@RequestParam(name = "patente", required = true) String patente);

}
