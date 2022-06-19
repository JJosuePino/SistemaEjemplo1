package com.sistema.prueba.pino.service;

import java.util.List;

import com.sistema.prueba.pino.dto.UsuarioDTO;

public interface ServiceInterface {

	public List<UsuarioDTO> listadoInformacion();
	UsuarioDTO obtenerInformacion(UsuarioDTO usuarioDto);
	public int eliminarInformacion(UsuarioDTO identificador);
	public int insertarInformacion(UsuarioDTO nuevaInformacion);
	public  int actualizarInformacion(UsuarioDTO nuevo);
}
