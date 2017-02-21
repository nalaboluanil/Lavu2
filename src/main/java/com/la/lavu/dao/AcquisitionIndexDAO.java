package com.la.lavu.dao;

import java.util.List;

import com.la.lavu.entity.AcquisitionIndex;



public interface AcquisitionIndexDAO {
	public Object save(AcquisitionIndex acquisitionIndex);

	public List<AcquisitionIndex> getMatchedDetails(Long companyId, Long sourceId);

	public void update(AcquisitionIndex acquisitionIndex);

	public List<AcquisitionIndex> checkForExistance(Long companyId, Long sourceId, Integer month, Integer year);

	public List<AcquisitionIndex> getAquisitionDetails(Long companyId, Integer currentMonth, Integer currentYear);
}
