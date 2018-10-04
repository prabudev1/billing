package com.gpware.billing.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.gpware.billing.dto.BillingReportDTO;
import com.gpware.billing.model.Billing;

@Transactional
@Repository
public class BillingDAO implements IBillingDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Billing getBillingById(int billingId) {
		return entityManager.find(Billing.class, billingId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Billing> getAllBillings(String userIdentifier, Integer limitRowCount) {

		if (limitRowCount > 0) {
			int minusDate = limitRowCount - (limitRowCount * 2);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = sdf.format(Calendar.getInstance().getTime()) + " 23:59:59";

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, minusDate);
			String fromDate = sdf.format(cal.getTime()) + " 00:00:00";

			String hql = " FROM Billing as b where b.createdBy = '"+ userIdentifier + "' and b.billingDate >= '" + fromDate + "' and b.billingDate <= '" + currentDate + "' ORDER BY b.billingDate desc ";
			Query query = entityManager.createQuery(hql);
			return (List<Billing>) query.getResultList();
		} else {
			String hql = " FROM Billing as bill where bill.createdBy = '"+ userIdentifier + "' ORDER BY bill.billingDate desc";
			Query query = entityManager.createQuery(hql);
			return (List<Billing>) query.getResultList();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Billing> getBillingList(String userIdentifier, String pFromDate, String pToDate, String orderBY) {
		String hql = " FROM Billing as b where b.createdBy = '" + userIdentifier + "' and b.billingDate >= '" + pFromDate + "' and b.billingDate <= '" + pToDate + "' ";

		if (orderBY != null) {
			if (orderBY.equalsIgnoreCase("1")) {
				hql += " ORDER BY b.billingDate desc, b.billNum desc ";
			} else if (orderBY.equalsIgnoreCase("2")) {
				hql += " ORDER BY b.billingDate asc, b.billNum desc ";
			} else if (orderBY.equalsIgnoreCase("5")) {
				hql += " ORDER BY b.total desc, b.billNum desc ";
			} else if (orderBY.equalsIgnoreCase("6")) {
				hql += " ORDER BY b.total asc, b.billNum desc ";
			}
		}
		Query query = entityManager.createQuery(hql);
		return (List<Billing>) query.getResultList();
	}

	@Override
	public void addBilling(Billing billing) {
		entityManager.persist(billing);
	}

	@Override
	public void deleteBilling(int billingId) {
		entityManager.remove(getBillingById(billingId));
	}

	@Override
	public BillingReportDTO getBillReportCOunt(String userIdentifier) {
		BillingReportDTO billReport = new BillingReportDTO();
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetReportData")
				.registerStoredProcedureParameter("userIdentifier", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("revenue", BigDecimal.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("customerCount", BigDecimal.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("productCount", BigDecimal.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("invoiceCount", BigDecimal.class, ParameterMode.OUT);
		query.setParameter("userIdentifier", userIdentifier);
		query.execute();
		
		BigDecimal revenue = (BigDecimal) query.getOutputParameterValue("revenue");
		BigDecimal customerCount = (BigDecimal) query.getOutputParameterValue("customerCount");
		BigDecimal productCount = (BigDecimal) query.getOutputParameterValue("productCount");
		BigDecimal invoiceCount = (BigDecimal) query.getOutputParameterValue("invoiceCount");

		billReport.setTotRevenue(revenue);
		billReport.setTotCustomer(customerCount);
		billReport.setTotProducts(productCount);
		billReport.setTotInvoices(invoiceCount);
		return billReport;
	}

}
