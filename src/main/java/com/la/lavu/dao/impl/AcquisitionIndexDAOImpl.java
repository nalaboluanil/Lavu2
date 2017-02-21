package com.la.lavu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.la.lavu.dao.AcquisitionIndexDAO;
import com.la.lavu.entity.AcquisitionIndex;

@SuppressWarnings({ "nls", "unchecked" })
public class AcquisitionIndexDAOImpl implements AcquisitionIndexDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Object save(AcquisitionIndex acquisitionIndex) {
		Session session = this.sessionFactory.openSession();
		Object obj = session.save(acquisitionIndex);
		return obj;
	}

	@Override
	public void update(AcquisitionIndex acquisitionIndex) {
		/*
		 * Session session = sessionFactory.openSession();
		 * session.update(acquisitionIndex);
		 */
		Session session = this.sessionFactory.openSession();
		session.saveOrUpdate(acquisitionIndex);
		session.flush();
	}

	@Override
	public List<AcquisitionIndex> getMatchedDetails(Long companyId, Long sourceId) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select AI from AcquisitionIndex AI where AI.fkCompanyId = :companyId and AI.fkSourceId = :sourceId order by AI.activityDatetime desc");
		query.setParameter("companyId", companyId);
		query.setParameter("sourceId", sourceId);
		return query.list();
	}

	@Override
	public List<AcquisitionIndex> checkForExistance(Long companyId, Long sourceId, Integer month, Integer year) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select AI from AcquisitionIndex AI where AI.fkCompanyId = :companyId and AI.fkSourceId = :sourceId "
						+ "and  month(AI.activityDatetime) = :month and year(AI.activityDatetime) = :year "
						+ "order by AI.activityDatetime desc");
		query.setParameter("companyId", companyId);
		query.setParameter("sourceId", sourceId);
		query.setParameter("month", month);
		query.setParameter("year", year);
		return query.list();
	}

	@Override
	public List<AcquisitionIndex> getAquisitionDetails(Long companyId, Integer currentMonth, Integer currentYear) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select model from AcquisitionIndex model where model.fkCompanyId =:companyId and month(model.activityDatetime) =:cmonth and year(model.activityDatetime) =:cyear");
		query.setParameter("companyId", companyId);
		query.setParameter("cmonth", currentMonth);
		query.setParameter("cyear", currentYear);
		return query.list();
	}
}
