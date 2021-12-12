package com.practica.integracion;

import com.practica.integracion.DAO.AuthDAO;
import com.practica.integracion.DAO.GenericDAO;
import com.practica.integracion.DAO.User;
import com.practica.integracion.manager.SystemManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestInvalidUser {
	@Mock
	private static AuthDAO mockAuthDao;
	@Mock
	private static GenericDAO mockGenericDao;

	@Test
	public void testStartRemoteSystemWithInvalidUserAndSystem() throws Exception {

		User nonValidUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
		when(mockAuthDao.getAuthData(nonValidUser.getId())).thenReturn(null);

		String nonValidId = "06654";
		when(mockGenericDao.getSomeData(null, "where id=" + nonValidId)).thenReturn(null);

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);

		Collection<Object> retorno = manager.startRemoteSystem(nonValidUser.getId(), nonValidId);

		assertNull(retorno);
		ordered.verify(mockAuthDao).getAuthData(nonValidUser.getId());
		ordered.verify(mockGenericDao).getSomeData(null, "where id=" + nonValidId);
	}
	@Test
	public void testStartRemoteSystemWithInvalidUserAndValidSystem() throws Exception {

		User nonValidUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
		when(mockAuthDao.getAuthData(nonValidUser.getId())).thenReturn(null);

		String validId = "12345";
		when(mockGenericDao.getSomeData(null, "where id=" + validId)).thenThrow(javax.naming.OperationNotSupportedException.class);

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);

		assertThrows(com.practica.integracion.manager.SystemManagerException.class, ()->{
			manager.startRemoteSystem(nonValidUser.getId(), validId);
		});
		ordered.verify(mockAuthDao).getAuthData(nonValidUser.getId());
		ordered.verify(mockGenericDao).getSomeData(null, "where id=" + validId);
	}
	@Test
	public void testStopRemoteSystemWithInvalidUserAndSystem() throws Exception {

		User nonValidUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
		when(mockAuthDao.getAuthData(nonValidUser.getId())).thenReturn(null);

		String nonValidId = "06654";
		when(mockGenericDao.getSomeData(null, "where id=" + nonValidId)).thenReturn(null);

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);

		Collection<Object> retorno = manager.stopRemoteSystem(nonValidUser.getId(), nonValidId);

		assertNull(retorno);
		ordered.verify(mockAuthDao).getAuthData(nonValidUser.getId());
		ordered.verify(mockGenericDao).getSomeData(null, "where id=" + nonValidId);
	}
	@Test
	public void testStopRemoteSystemWithInvalidUserAndValidSystem() throws Exception  {

		User nonValidUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
		when(mockAuthDao.getAuthData(nonValidUser.getId())).thenReturn(null);

		String validId = "12345";
		when(mockGenericDao.getSomeData(null, "where id=" + validId)).thenThrow(javax.naming.OperationNotSupportedException.class);

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);

		assertThrows(com.practica.integracion.manager.SystemManagerException.class, ()->{
			manager.stopRemoteSystem(nonValidUser.getId(), validId);
		});
		ordered.verify(mockAuthDao).getAuthData(nonValidUser.getId());
		ordered.verify(mockGenericDao).getSomeData(null, "where id=" + validId);
	}
	@Test
	public void testAddRemoteSystemWithInvalidUserAndSystem() throws OperationNotSupportedException {

		User nonValidUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
		when(mockAuthDao.getAuthData(nonValidUser.getId())).thenReturn(null);

		String validId = "12345";
		when(mockGenericDao.updateSomeData(null,  validId)).thenReturn(false);
		
		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
		
		assertThrows(com.practica.integracion.manager.SystemManagerException.class, ()->{
			manager.addRemoteSystem(nonValidUser.getId(), validId);
		});
		ordered.verify(mockAuthDao).getAuthData(nonValidUser.getId());
		ordered.verify(mockGenericDao).updateSomeData(null, validId);
	}

	@Test
	public void testAddRemoteSystemWithInvalidUserAndValidSystem() throws Exception {

		User nonValidUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
		when(mockAuthDao.getAuthData(nonValidUser.getId())).thenReturn(null);

		String nonValidId = "06654";
		when(mockGenericDao.updateSomeData(null, nonValidId)).thenReturn(false);

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);

		assertThrows(com.practica.integracion.manager.SystemManagerException.class, ()->
				manager.addRemoteSystem(nonValidUser.getId(), nonValidId)
		);
		ordered.verify(mockAuthDao).getAuthData(nonValidUser.getId());
		ordered.verify(mockGenericDao).updateSomeData(null, nonValidId);
	}
	@Test
	public void testDeleteRemoteSystemWithInvalidUserAndSystem() throws Exception {

		User nonValidUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
		when(mockAuthDao.getAuthData(nonValidUser.getId())).thenReturn(null);

		String validId = "12345";
		when(mockGenericDao.deleteSomeData(null,  validId)).thenReturn(false);

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);

		assertThrows(com.practica.integracion.manager.SystemManagerException.class, ()->{
			manager.deleteRemoteSystem(nonValidUser.getId(), validId);
		});
		ordered.verify(mockAuthDao).getAuthData(nonValidUser.getId());
		ordered.verify(mockGenericDao).deleteSomeData(null, validId);
	}

	@Test
	public void testDeleteRemoteSystemWithInvalidUserAndValidSystem() throws Exception {

		User nonValidUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
		when(mockAuthDao.getAuthData(nonValidUser.getId())).thenReturn(null);

		String nonValidId = "06654";
		when(mockGenericDao.deleteSomeData(null, nonValidId)).thenReturn(false);

		InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);

		assertThrows(com.practica.integracion.manager.SystemManagerException.class, ()->
				manager.deleteRemoteSystem(nonValidUser.getId(), nonValidId)
		);
		ordered.verify(mockAuthDao).getAuthData(nonValidUser.getId());
		ordered.verify(mockGenericDao).deleteSomeData(null, nonValidId);
	}
}
