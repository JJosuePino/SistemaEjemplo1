package com.sistema.prueba.pino.repository;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sistema.prueba.pino.dto.UsuarioDTO;
import com.sistema.prueba.pino.mapper.UsuarioMapper;

public class RepositorioImpl implements RepositorioDao {
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate =new JdbcTemplate();

	@Override
	public List<UsuarioDTO> listadoInformacion() {
		// TODO Auto-generated method stub
		jdbcTemplate.setDataSource(dataSource);
		
		return jdbcTemplate.query("SELECT * FROM USUARIO", new UsuarioMapper<UsuarioDTO>());
		
	}

	@Override
	public UsuarioDTO obtenerInformacion(UsuarioDTO usuarioDto) {
		// TODO Auto-generated method stub
		jdbcTemplate.setDataSource(dataSource);
		Object[] idusuario= {usuarioDto.getIdUsuario()};
		
		return jdbcTemplate.queryForObject("SELECT * FROM USUARIO WHERE ID_USUARIO=", idusuario,new UsuarioMapper<UsuarioDTO>());
	}

	@Override
	public int eliminarInformacion(UsuarioDTO identificador) {
		jdbcTemplate.setDataSource(dataSource);		         // Contiene la conexion a la DB
		Object [] idusuario = {identificador.getIdUsuario()};      // Se crea un areglo de tipo object
		int[] types = {Types.INTEGER}; 						// 
		return jdbcTemplate.update("DELETE FROM USUARIO WHERE ID_USUARIO=?",idusuario,types); // Query
	}
                                 
	@Override
	public int insertarInformacion(UsuarioDTO nuevaInformacion) {
		
		jdbcTemplate.setDataSource(dataSource);		         // Contiene la conexion a la DB
		return jdbcTemplate.update("INSERT INTO USUARIO(NOMBRE_COMPLETO,PERIODO,ESTATUS) VALUES(?,?,?)", nuevaInformacion.getNombreCompleto(),nuevaInformacion.getPeriodo(),nuevaInformacion.getEstatus());
	}

	@Override
	public int actualizarInformacion(UsuarioDTO nuevo) {
		// TODO Auto-generated method stub
jdbcTemplate.setDataSource(dataSource);		         // Contiene la conexion a la DB
		
		return jdbcTemplate.update("UPDATE USUARIO SET NOMBRE_COMPLETO=?,PERIODO=?,ESTATUS=? WHERE ID_USUARIO=? ", nuevo.getIdUsuario(),nuevo.getNombreCompleto(),nuevo.getPeriodo(),nuevo.getEstatus() );
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
