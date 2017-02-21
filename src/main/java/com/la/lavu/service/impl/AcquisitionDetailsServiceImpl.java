package com.la.lavu.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.la.lavu.dao.AcquisitionIndexDAO;

import com.la.lavu.entity.AcquisitionIndex;
import com.la.lavu.dao.impl.AcquisitionIndexDAOImpl;

import lombok.Setter;

/**
 * This class fetch and save the acquisition details for companies
 * 
 * @author Sambasiva
 * @version 1.0
 *
 */
@SuppressWarnings({ "nls", "boxing" })
@EnableScheduling
public class AcquisitionDetailsServiceImpl implements com.la.lavu.service.AcquisitionDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AcquisitionDetailsServiceImpl.class);

	@SuppressWarnings("unused")
	@Autowired
	private AcquisitionIndexDAOImpl acquisitionIndexDAO;

	@Setter
	private String crunchBaseKey;

	/*@Autowired
	private CompetitorService competitorService;

	@Autowired
	private PaseGenericService paseGenericService;

	@Autowired
	private ServiceStatusDAO serviceStatusDAO;*/

	private Long companyTypeMappingId = null;

	/**
	 * This is the main method which scheduled to save acquisition details
	 */
	@Scheduled(cron="0 24 18 * * ?")
	public void execuate(){
		runService();
	}
	@Override

	public boolean runService() {
		LOGGER.info("Entered into runService method");
		boolean processed=true;
		// save service status info
		/*Date serviceStartDateTime = new Date();
		ServiceStatus serviceStatusInfo = new ServiceStatus();
		serviceStatusInfo.setFkServiceId(PASEConstants.ACQUISITION_DETAILS_SERVICE_ID);
		serviceStatusInfo.setStartDateTime(serviceStartDateTime);
		serviceStatusInfo.setStatus("In Progress");
		serviceStatusInfo.setServerName(PASEConstants.DEV_SERVER_NAME);
		serviceStatusInfo.setServerIPAddress(PASEConstants.DEV_SERVER_IP_ADDRESS);
		Long serviceStatusId = (Long) this.serviceStatusDAO.save(serviceStatusInfo);*/

		/*boolean processed = true;

		List<CompanyDetailsVO> acquisitionList = null;

		try {

			Long crunchBaseId = this.competitorService.checkForGenericAPICompaniesDetails(
					PASEConstants.CRUNCH_BASE_DOMAIN, PASEConstants.CRUNCH_BASE_NAME,
					PASEConstants.DATA_SOURCE_CRUNCH_BASE);

			acquisitionList = this.getCompaniesDetails(PASEConstants.LINK_TYPE_CRUNCH_BASE);

			for (CompanyDetailsVO acquisitionVO : acquisitionList) {
				try {
					CrawlingStatus crawlingStatusInfo = this.paseGenericService.saveDailyCrawlingStatus(
							acquisitionVO.getId(), PASEConstants.ACQUISITION_DETAILS_SERVICE_ID, serviceStatusId);
					processAcquisitionDetails(acquisitionVO, crunchBaseId);
					// daily crawl status update
					this.paseGenericService.updateDailyCrawlingStatus(crawlingStatusInfo);
				} catch (Exception e) {
					long lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
					this.paseGenericService.saveException(
							new ExceptionVO(0L, PASEConstants.ACQUISITION_DETAILS_SERVICE_ID, "runService", lineNumber,
									e.getMessage(), "for loop", ExceptionUtil.determineExceptionCodeByException(e)));

					LOGGER.error("Exception raised in runService :", e);

				}
			}
		} catch (Exception e) {

			long lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
			this.paseGenericService.saveException(new ExceptionVO(0L, PASEConstants.ACQUISITION_DETAILS_SERVICE_ID,
					"runService", lineNumber, e.getMessage(), "", ExceptionUtil.determineExceptionCodeByException(e)));

			LOGGER.error("Exception raised in runService :", e);
			processed = false;
		} finally {

			final Date serviceEndDateTime = new Date();
			String duration = this.paseGenericService.getDurationForService(serviceStartDateTime, serviceEndDateTime);

			serviceStatusInfo.setEndDateTime(serviceEndDateTime);
			serviceStatusInfo.setDuration(duration);
			serviceStatusInfo.setStatus("Completed");
			this.serviceStatusDAO.updateServiceDetails(serviceStatusInfo, serviceStatusId);
		}*/

		return processed;
	}

	

	

	

	@Override
	public String getURLResponse(String companyName) throws MalformedURLException, IOException {

		/*try {

			Thread.sleep(PASEConstants.TIME_INTERVAL_BETWEEN_REQUESTS);

			URL obj = new URL(PASEConstants.CRUNCHBASE_URL + companyName + "?user_key=" + this.crunchBaseKey);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", PASEConstants.USER_AGENT);

			InputStream inputStream = con.getInputStream();

			
			 * InputStream inputStream = new URL(PASEConstants.CRUNCHBASE_URL +
			 * companyName + "?user_key=" + this.crunchBaseKey) .openStream();
			 

			BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

			return this.paseGenericService.readAll(rd);

		} catch (Exception e) {

			final long lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
			this.paseGenericService
					.saveException(new ExceptionVO(0L, PASEConstants.ACQUISITION_DETAILS_SERVICE_ID, "getURLResponse",
							lineNumber, e.getMessage(), "", ExceptionUtil.determineExceptionCodeByException(e)));

			LOGGER.error("Exception raised in getURLResponse for company :" + companyName, e);
		}*/

		return "";
	}

	/**
	 * This method will process the input string and get the necessary data i.e
	 * no of acquisitions..etc
	 * 
	 * @param responseText
	 * @param companyId
	 * @return AcquisitionIndex
	 */
	

	/**
	 * This method will check the record existance in DB.If record is there in
	 * database for the current month, then check for the acquisition count . If
	 * both counts are not euqal , then update the record.If record is not there
	 * for current month , then it will insert new record.
	 * 
	 * @param acquisitionIndex
	 * @param companyId
	 * @return id
	 */

	

	public static boolean isValid(JSONObject jsonObject, final String propertyName) {
		try {
			if (propertyName != null && jsonObject.has(propertyName)
					&& !jsonObject.get(propertyName).toString().equalsIgnoreCase("null")
					&& !jsonObject.get(propertyName).toString().equalsIgnoreCase(""))
				return true;
		} catch (JSONException e) {
			LOGGER.error("Exception raised in isValid :", e);
		}
		return false;
	}







	@Override
	public AcquisitionIndex processResponse(String responseText, Long companyId) {
		
		return null;
	}







	@Override
	public Long checkForInsertOrUpdate(AcquisitionIndexDAO acquisitionIndex, Long companyId) {
		// TODO Auto-generated method stub
		return null;
	}
}
