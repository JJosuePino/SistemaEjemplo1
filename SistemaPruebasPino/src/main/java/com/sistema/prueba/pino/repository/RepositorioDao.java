package com.sistema.prueba.pino.repository;

import java.util.List;

import com.sistema.prueba.pino.dto.UsuarioDTO;

public interface RepositorioDao {

	public List<UsuarioDTO> listadoInformacion();
	UsuarioDTO obtenerInformacion(UsuarioDTO usuarioDto);
	public int eliminarInformacion(UsuarioDTO identificador);
	public int insertarInformacion(UsuarioDTO nuevaInformacion);
	public  int actualizarInformacion(UsuarioDTO nuevo); 
	
}
