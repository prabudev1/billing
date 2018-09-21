package com.gpware.billing.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpware.billing.dao.IBillingDAO;
import com.gpware.billing.dto.BillingDTO;
import com.gpware.billing.dto.BillingReportDTO;
import com.gpware.billing.helper.ApplicationProperties;
import com.gpware.billing.helper.BillingHelper;
import com.gpware.billing.model.Billing;

@Service
public class BillingService implements IBillingService {
	@Autowired
	private IBillingDAO billingDAO;
	
	@Autowired
	private ApplicationProperties appProp;
	
	private BillingHelper billingHelper = new BillingHelper();

	@Override
	public Billing getBillingById(int billingId) {
		Billing obj = billingDAO.getBillingById(billingId);
		return obj;
	}

	@Override
	public List<Billing> getBillingList(String fromDate, String toDate, String orderBY) {
		return billingDAO.getBillingList(fromDate, toDate, orderBY);
	}

	@Override
	public synchronized void addBilling(Billing billing) {
		billingDAO.addBilling(billing);
	}

	@Override
	public void deleteBilling(int billingId) {
		billingDAO.deleteBilling(billingId);
	}

	@Override
	public BillingReportDTO getReportData() {
		BillingReportDTO billReport = billingDAO.getBillReportCOunt();
		List<Billing> billingList = billingDAO.getAllBillings(appProp.getReportDays());
		List<BillingDTO> billingDtoList = billingHelper.copyBillingList(billingList);
		billReport.setBillingItems(billingDtoList);
		return billReport;
	}
	
	@Override
	public byte[] downloadExcel(String fromDate, String toDate, String orderBy) throws IOException {
		List<Billing> billingList = getBillingList(fromDate, toDate, orderBy);

		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Invoice Data");
        
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(font);
        headerCellStyle.setFillForegroundColor(IndexedColors.TEAL.getIndex());
        headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setBorderTop(CellStyle.BORDER_THIN);
        headerCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        
        CellStyle backgroundStyle = workbook.createCellStyle();
        backgroundStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        backgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        backgroundStyle.setBorderBottom(CellStyle.BORDER_THIN);
        backgroundStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        backgroundStyle.setBorderLeft(CellStyle.BORDER_THIN);
        backgroundStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        backgroundStyle.setBorderRight(CellStyle.BORDER_THIN);
        backgroundStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        backgroundStyle.setBorderTop(CellStyle.BORDER_THIN);
        backgroundStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MMM-yyyy"));
        dateCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        dateCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        dateCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        dateCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        dateCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dateCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        dateCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        dateCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        dateCellStyle.setBorderTop(CellStyle.BORDER_THIN);
        dateCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        
        int rowNum = 0;
        int colIndex = 0;
        Row headerRow = sheet.createRow(rowNum++);
        
        createCell(headerRow, colIndex++, headerCellStyle, "Bill No");
        createCell(headerRow, colIndex++, headerCellStyle, "Buyer Name");
        createCell(headerRow, colIndex++, headerCellStyle, "Qty");
        createCell(headerRow, colIndex++, headerCellStyle, "Sub Total");
        createCell(headerRow, colIndex++, headerCellStyle, "SGST");
        createCell(headerRow, colIndex++, headerCellStyle, "CGST");
        createCell(headerRow, colIndex++, headerCellStyle, "Grand Total (₹)");
        createCell(headerRow, colIndex++, headerCellStyle, "Generated On");
        
        Row billingRow = null;
        String custName;
		for (Billing billing : billingList) {
			colIndex = 0;
			billingRow = sheet.createRow(rowNum++);
			
			custName = "NA";
			if (billing.getCustomer() != null) {
				custName = billing.getCustomer().getName() + "(" + billing.getCustomer().getMobile() + ")";
			}
			createCell(billingRow, colIndex++, backgroundStyle, billing.getId());
			createCell(billingRow, colIndex++, backgroundStyle, custName);
			createCell(billingRow, colIndex++, backgroundStyle, billing.getTotItems().longValue());
			createCell(billingRow, colIndex++, backgroundStyle, billing.getTotValue().longValue());
			createCell(billingRow, colIndex++, backgroundStyle, billing.getGstStateVal().longValue());
			createCell(billingRow, colIndex++, backgroundStyle, billing.getGstCentralPercent().longValue());
			createCell(billingRow, colIndex++, backgroundStyle, billing.getTotal().longValue());
			createCell(billingRow, colIndex++, dateCellStyle, billing.getCreatedOn());
		}
        
		for (int cntI=0; cntI<colIndex; cntI++ ) {
			sheet.autoSizeColumn(cntI);
		}
		
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        byte[] baosByteArray = baos.toByteArray();
        baos.close();
        return baosByteArray;
	}
	
	private Cell createCell(Row billingRow, int colIndex, CellStyle backgroundStyle, Object value) {
		Cell cell = billingRow.createCell(colIndex);
		cell.setCellStyle(backgroundStyle);
		
		if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Timestamp) {
            cell.setCellValue((Timestamp) value);
        }
		
		return cell;
	}
}
