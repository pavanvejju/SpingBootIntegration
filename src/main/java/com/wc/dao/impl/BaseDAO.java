package com.wc.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Type.PersistenceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author pavankumarv
 * 
 */
public abstract class BaseDAO<T, ID extends Serializable> {
	
	private static Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	
	public Class<T> persistentClass;

	@PersistenceContext(unitName="batchdb", type=PersistenceContextType.EXTENDED)
	public EntityManager entityManager;
	
	//@Value("#{RenovatedPropertiesFactoryBean['hibernate.entity.locking.strategy']}")
	private String entityLockingStrategy="NEW";
	
	@SuppressWarnings("unchecked")
	public BaseDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public BaseDAO(Class<T> clazz) {
	    this.persistentClass = clazz;
	}
	  
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public T findById(ID id, boolean lock) {
		T entity;
		if (lock) {
			if("NEW".equals(entityLockingStrategy)){
				Map<String,Object> properties = new HashMap();
				properties.put("javax.persistence.lock.timeout", 60000);
				properties.put("hibernate.cache.use_query_cache", false);
				entity = (T) getEntityManager().find(getPersistentClass(), id);
				getEntityManager().flush();
				getEntityManager().refresh(entity, LockModeType.PESSIMISTIC_WRITE, properties);
				getEntityManager().lock(entity, LockModeType.PESSIMISTIC_WRITE, properties);				
			}
			else{
				Map<String,Object> properties = new HashMap();
				properties.put("javax.persistence.lock.timeout", 60000);
				properties.put("hibernate.cache.use_query_cache", false);
				//entity = (T) getEntityManager().find(getPersistentClass(), id);//,LockModeType.PESSIMISTIC_WRITE ,properties);
				//getEntityManager().lock(entity, LockModeType.PESSIMISTIC_WRITE,properties);
				//getEntityManager().refresh(entity,LockModeType.PESSIMISTIC_WRITE ,properties);
				CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
				CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getPersistentClass());
				Root<T> rootEntity = criteriaQuery.from(getPersistentClass());
				criteriaQuery.select(rootEntity);
				criteriaQuery.where( criteriaBuilder.equal(rootEntity.get("id"),id));
				TypedQuery<T> q = getEntityManager().createQuery(criteriaQuery);
				q.setLockMode(LockModeType.PESSIMISTIC_WRITE);
				List<T> obj = q.getResultList();
				entity =  obj.get(0);
			}
			return entity;			
		} else
			entity = (T) getEntityManager().find(getPersistentClass(), id);
		return entity;
	}

	protected CriteriaQuery<T> createCriteriaQuery() {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getPersistentClass());
		Root<T> rootEntity = criteriaQuery.from(getPersistentClass());
		
		return criteriaQuery;
	}

	public List<T> findAll() {
		CriteriaQuery<T> criteriaQuery = createCriteriaQuery();
		TypedQuery<T> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	public T makePersistent(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}
	
	//Added for DE purpose. Need to change it later
	public void persist(T entity) {
		/*if(entity instanceof BaseEntity){
			BaseEntity entityRef = (BaseEntity) entity;
			Date date = Calendar.getInstance().getTime();
			if(entityRef.getVersion() == 0){
				entityRef.setCreationTime(date);
			}
			entityRef.setModifiedTime(date);
		}
		getEntityManager().persist(entity);
		flush();*/
		makePersistent(entity);
	}
	
	public T merge(T entity) {
		T mergedEntity = getEntityManager().merge(entity);
		return mergedEntity;
	}

	public void makeTransient(T entity) {
		getEntityManager().remove(entity);
		flush();
	}

	public void flush() {
		getEntityManager().flush();
	}

	public void clear() {
		getEntityManager().clear();
	}

	public EntityManager getEntityManager() {		
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public boolean setRollbackOnly() {
		try {
			 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return true;
		} catch (Exception e) {
			 logger.info(e.getMessage(), e);
			return false;
		} 
	}
}
