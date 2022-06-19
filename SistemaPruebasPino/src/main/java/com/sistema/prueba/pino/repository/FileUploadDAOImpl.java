package com.sistema.prueba.pino.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.sistema.prueba.pino.UploadFile;



@Repository
public class FileUploadDAOImpl implements FileUploadDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public FileUploadDAOImpl() {
	}
	public FileUploadDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(UploadFile uploadFile) {
System.out.println("Saving file---uploadFile-->: " + uploadFile.getFileName());
System.out.println("Saving file---uploadFile-->: " + uploadFile.getData());
		sessionFactory.getCurrentSession().save(uploadFile);
	}
}
