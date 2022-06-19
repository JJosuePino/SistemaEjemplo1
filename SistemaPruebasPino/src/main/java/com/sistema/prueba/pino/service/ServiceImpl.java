package com.sistema.prueba.pino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sistema.prueba.pino.dto.UsuarioDTO;
import com.sistema.prueba.pino.repository.RepositorioDao;

@Service
public class ServiceImpl  implements ServiceInterface{

	@Autowired
	
	RepositorioDao dao;

	@Override
	public List<UsuarioDTO> listadoInformacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO obtenerInformacion(UsuarioDTO usuarioDto) {
		// TODO Auto-generated method stub
		return dao.obtenerInformacion(usuarioDto);
	}

	@Override
	public int eliminarInformacion(UsuarioDTO identificador) {
		// TODO Auto-generated method stub
		return dao.eliminarInformacion(identificador);
	}

	@Override
	public int insertarInformacion(UsuarioDTO nuevaInformacion) {
		// TODO Auto-generated method stub
		return dao.insertarInformacion(nuevaInformacion);
	}

	@Override
	public int actualizarInformacion(UsuarioDTO nuevo) {
		// TODO Auto-generated method stub
	
		return dao.actualizarInformacion(nuevo);
	}
	

}
