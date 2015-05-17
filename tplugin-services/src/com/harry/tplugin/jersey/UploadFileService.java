package com.harry.tplugin.jersey;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.harry.tplugin.bean.SendAllow;
import com.harry.tplugin.bean.SendOrder;
import com.harry.tplugin.bean.SysMain;
import com.harry.tplugin.service.SysMainService;
import com.harry.tplugin.util.JacksonUtils;
import com.harry.tplugin.util.ServiceFactoryBean;
import com.harry.tplugin.util.StoreProceduresUtils;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/upload")
public class UploadFileService {
	private SysMainService sysMainService = ServiceFactoryBean
			.getSysMainService();
	private Map<Integer, String> indexStore = new HashMap<Integer, String>();

	@SuppressWarnings("static-access")
	private String getValue(Cell ssfCell) {
		if (null == ssfCell)
			return null;
		if (ssfCell.getCellType() == ssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(ssfCell.getBooleanCellValue());
		} else if (ssfCell.getCellType() == ssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(ssfCell.getNumericCellValue());
		} else {
			return String.valueOf(ssfCell.getStringCellValue());
		}
	}

	private void writeSysMainToDB(Row row, boolean firstRow) {
		if (firstRow)
			return;
		int i = row.getFirstCellNum();
		SysMain sysMain = new SysMain(getValue(row.getCell(i++)),
				getValue(row.getCell(i++)), getValue(row.getCell(i++)),
				getValue(row.getCell(i++)));
		sysMainService.createSysMain(sysMain);
	}

	private void writeLoginToDB(Row row, boolean firstRow) {
		if (firstRow)
			return;
		int i = row.getFirstCellNum();
		StoreProceduresUtils.callLoginStatement(getValue(row.getCell(i++)),
				getValue(row.getCell(i++)), getValue(row.getCell(i++)),
				getValue(row.getCell(i++)));
	}

	private void writeProductToDB(Row row, boolean firstRow) {
		if (firstRow)
			return;System.out.println("@mwz");
		int i = row.getFirstCellNum();
		StoreProceduresUtils.callProductStatement(getValue(row.getCell(i++)),
				getValue(row.getCell(i++)), getValue(row.getCell(i++)),
				getValue(row.getCell(i++)));
	}

	private void writeSendAllowToDB(Row row, boolean firstRow) {
		int i = row.getFirstCellNum();
		int lastCellNum = row.getLastCellNum();
		if (firstRow) {
			for (i = i + 2; i <= lastCellNum; i++) {
				indexStore.put(Integer.valueOf(i), getValue(row.getCell(i)));
			}
			return;
		}
		SendAllow sendAllow = new SendAllow(getValue(row.getCell(i++)),
				getValue(row.getCell(i++)), "");
		Map<String, Boolean> mp = new HashMap<String, Boolean>();
		for (; i <= lastCellNum; i++) {
			mp.put(indexStore.get(Integer.valueOf(i)),
					Boolean.valueOf(getValue(row.getCell(i))));
		}
		sendAllow.setStoreJson(JacksonUtils.getJsonString(mp));
		StoreProceduresUtils.callSendAllowStatement(sendAllow.getState(),
				sendAllow.getProType(), sendAllow.getStoreJson());
	}

	private void writeSendOrderToDB(Row row, boolean firstRow) {
		int i = row.getFirstCellNum();
		int lastCellNum = row.getLastCellNum();
		if (firstRow) {
			for (i = i + 2; i <= lastCellNum; i++) {
				indexStore.put(Integer.valueOf(i), getValue(row.getCell(i)));
			}
			return;
		}
		SendOrder sendOrder = new SendOrder(getValue(row.getCell(i++)),
				getValue(row.getCell(i++)), "");
		Map<Integer, String> mp = new HashMap<Integer, String>();
		for (; i <= lastCellNum; i++) {
			mp.put(Integer.valueOf(getValue(row.getCell(i))),
					indexStore.get(Integer.valueOf(i)));
		}
		sendOrder.setSendIndex(JacksonUtils.getJsonString(mp));
		StoreProceduresUtils.callSendOrderStatement(sendOrder.getState(),
				sendOrder.getProType(), sendOrder.getSendIndex());
	}

	private void writeToDB(String tableItem, Row row, boolean firstRow) {System.out.println("@@mwz"+tableItem);
		if ("sysMain".equalsIgnoreCase(tableItem)) {
			writeSysMainToDB(row, firstRow);
		} else if ("logIn".equalsIgnoreCase(tableItem)) {
			writeLoginToDB(row, firstRow);
		} else if ("product".equalsIgnoreCase(tableItem)) {
			writeProductToDB(row, firstRow);
		} else if ("sendAllow".equalsIgnoreCase(tableItem)) {
			writeSendAllowToDB(row, firstRow);
		} else if ("sendOrder".equalsIgnoreCase(tableItem)) {
			writeSendOrderToDB(row, firstRow);
		}
	}

	/*
	 *  Sync Stock table
	 */
	@SuppressWarnings("unchecked")
	@Path("stock")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String syncStock(String jsonString) {
		try {
			Map<String, String> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
			if (null == map.get("proId") || null == map.get("storeId")
					|| null == map.get("number")) {
				return "proId/storeId/number can no be null";
			}
			StoreProceduresUtils.callStockStatement(map.get("proId"), map.get("storeId"), Integer.valueOf(map.get("number")), Integer.valueOf(map.get("preNumber")==null?"-1":map.get("preNumber")));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "failed exception";
	}

	@Path("excel")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadStatePolicy(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("tableItem") String tableItem) {

		try {
			Workbook workBook = WorkbookFactory.create(uploadedInputStream);
			Sheet sheet = workBook.getSheetAt(0);
			if (null == sheet) {
				return "failed";
			}
			int rowNum = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
			if (rowNum <= 0) {
				return "failed";
			}
			final int firstRowIndex = sheet.getFirstRowNum();
			if ("product".equalsIgnoreCase(tableItem))
			{
				StoreProceduresUtils.callProductStatementInit();
			}
			for (int i = firstRowIndex, e = sheet.getLastRowNum(); i <= e; i++) {
				Row row = sheet.getRow(i);
				if (null == row)
					continue;
				writeToDB(tableItem, row, firstRowIndex == i);
			}
			if ("product".equalsIgnoreCase(tableItem))
			{
				StoreProceduresUtils.callProductStatementFinish();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed exception";
		}

		return "success";
	}

}