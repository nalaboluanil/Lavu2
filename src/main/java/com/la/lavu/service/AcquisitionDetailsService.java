package com.la.lavu.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;


import com.la.lavu.dao.AcquisitionIndexDAO;
import com.la.lavu.entity.AcquisitionIndex;

public interface AcquisitionDetailsService {
	public boolean runService();

	//public List<CompanyDetailsVO> getCompaniesDetails(Long sourceId);

	public String getURLResponse(String companyName) throws MalformedURLException, IOException;

	public AcquisitionIndex processResponse(String responseText, Long companyId);

	public Long checkForInsertOrUpdate(AcquisitionIndexDAO acquisitionIndex, Long companyId);

	//public boolean processAcquisitionDetails(CompanyDetailsVO acquisitionVO, Long crunchBaseId);

}
