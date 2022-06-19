package com.sistema.prueba.pino.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sistema.prueba.pino.dto.UsuarioDTO;

public class UsuarioMapper <T> implements RowMapper<UsuarioDTO>{

	@Override
	public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UsuarioDTO asignarvalores =new UsuarioDTO();
		
		asignarvalores.setIdUsuario(rs.getLong("ID_USUARIO"));
		asignarvalores.setNombreCompleto(rs.getString("NOMBRE_USUARIO"));
		asignarvalores.setPeriodo(rs.getString("PERIODO"));
		asignarvalores.setEstatus(rs.getString("ESTATUS"));
		return asignarvalores;
	}
	
	

}
