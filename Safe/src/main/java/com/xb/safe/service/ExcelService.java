package com.xb.safe.service;

import com.xb.safe.dto.ClosingCellsHolder;
import com.xb.safe.dto.Contract;
import com.xb.safe.dto.DelayReportHolder;
import com.xb.safe.dto.FillingCellsHolder;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ExcelService {

    public void loadExcel(OutputStream os, List<Contract> contracts) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Клиенты");

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ФИО");
        row0.createCell(1).setCellValue("ИНН");
        row0.createCell(2).setCellValue("Паспорт");
        row0.createCell(3).setCellValue("Номер");
        row0.createCell(4).setCellValue("Отделение");
        row0.createCell(5).setCellValue("Начало контракта");
        row0.createCell(6).setCellValue("Окончание контракта");
        row0.createCell(7).setCellValue("Закрытие контракта");
        row0.createCell(8).setCellValue("Статус");

        int rowCount = 2;
        for (Contract contract : contracts) {
            Row row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(contract.getClient1().getSurname() + " " + contract.getClient1().getName() + " " + contract.getClient1().getPatronymic());
            row.createCell(1).setCellValue(contract.getClient1().getInn());
            row.createCell(2).setCellValue(contract.getClient1().getSeries() + "" + contract.getClient1().getNumber());
            row.createCell(3).setCellValue(contract.getClient1().getMobilePhone1());
            row.createCell(4).setCellValue(contract.getDepartmentName());
            row.createCell(5).setCellValue(contract.getDateContractStart());
            row.createCell(6).setCellValue(contract.getDateContractEnd());
            row.createCell(7).setCellValue(contract.getDateContractClose());
            row.createCell(8).setCellValue(contract.getContractStatus());
            rowCount++;
        }
        for (int columnNumber = 0; columnNumber < sheet.getRow(0).getPhysicalNumberOfCells(); columnNumber++) {
            sheet.autoSizeColumn(columnNumber);
        }
        try {
            workbook.write(os);
        } catch (IOException ex) {
            Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadReportDelayXls(OutputStream os, List<DelayReportHolder> contracts) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Просрочка");

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ФИО");
        row0.createCell(1).setCellValue("Номер телефона");
        row0.createCell(2).setCellValue("Размер ячейки");
        row0.createCell(3).setCellValue("Номер ячейки");
        row0.createCell(4).setCellValue("Окончание");
        row0.createCell(5).setCellValue("Просрочено дней");
        row0.createCell(6).setCellValue("Сумма задоженности");
        row0.createCell(7).setCellValue("Номер отделения");

        int rowCount = 2;
        for (DelayReportHolder contract : contracts) {
            Row row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(contract.getClientSurname() + " " + contract.getClientName() + " " + contract.getClientPatronymic());
            row.createCell(1).setCellValue(contract.getClientPhone());
            row.createCell(2).setCellValue(contract.getSafeHeight() + "*" + contract.getSafeWidth() + "*" + contract.getSafeDepth());
            row.createCell(3).setCellValue(contract.getSafeNum());
            row.createCell(4).setCellValue(contract.getDateEnd());
            row.createCell(5).setCellValue(contract.getDelayDays());
            row.createCell(6).setCellValue(contract.getContractDebt());
            row.createCell(7).setCellValue(contract.getNameDepartment());
            rowCount++;
        }
        for (int columnNumber = 0; columnNumber < sheet.getRow(0).getPhysicalNumberOfCells(); columnNumber++) {
            sheet.autoSizeColumn(columnNumber);
        }
        try {
            workbook.write(os);
        } catch (IOException ex) {
            Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadReportRegistrationXls(OutputStream os, List<Contract> contracts) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Клиенты");

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("Номер договора");
        row0.createCell(1).setCellValue("Дата открытия");
        row0.createCell(2).setCellValue("№ сейфа");
        row0.createCell(3).setCellValue("ФИО");
        row0.createCell(4).setCellValue("Дата закрытия");

        int rowCount = 2;
        for (Contract contract : contracts) {
            Row row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(contract.getNumberContract());
            row.createCell(1).setCellValue(contract.getDateContractStart());
            row.createCell(2).setCellValue(contract.getSafeNumber());
            row.createCell(3).setCellValue(contract.getClient1().getSurname() + " " + contract.getClient1().getName() + " " + contract.getClient1().getPatronymic());
            row.createCell(4).setCellValue(contract.getDateContractEnd());
            rowCount++;
        }
        for (int columnNumber = 0; columnNumber < sheet.getRow(0).getPhysicalNumberOfCells(); columnNumber++) {
            sheet.autoSizeColumn(columnNumber);
        }
        try {
            workbook.write(os);
        } catch (IOException ex) {
            Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadReportClosingCellsXls(OutputStream os, List<ClosingCellsHolder> contracts) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Клиенты");

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ФИО клиента");
        row0.createCell(1).setCellValue("Номер ячейки");
        row0.createCell(2).setCellValue("Телефон Клиента");
        row0.createCell(3).setCellValue("Дата отрытия");
        row0.createCell(4).setCellValue("Дата закрытия");
        row0.createCell(5).setCellValue("Тариф ячейки");

        int rowCount = 2;
        for (ClosingCellsHolder contract : contracts) {
            Row row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(contract.getClientSurname() + " " + contract.getClientName() + " " + contract.getClientPatronymic());
            row.createCell(1).setCellValue(contract.getSafeNum());
            row.createCell(2).setCellValue(contract.getClientPhone());
            row.createCell(3).setCellValue(contract.getDateStart());
            row.createCell(4).setCellValue(contract.getDateEnd());
            row.createCell(5).setCellValue(contract.getSafePrice());
            rowCount++;
        }

        for (int columnNumber = 0; columnNumber < sheet.getRow(0).getPhysicalNumberOfCells(); columnNumber++) {
            sheet.autoSizeColumn(columnNumber);
        }

        try {
            workbook.write(os);
        } catch (IOException ex) {
            Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadReportFillingCellsXls(OutputStream os, List<FillingCellsHolder> contracts) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Клиенты");

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("Номер отделения");
        row0.createCell(1).setCellValue("Всего");
        row0.createCell(2).setCellValue("Орендовано на начало");
        row0.createCell(3).setCellValue("Открыто за период");
        row0.createCell(4).setCellValue("Закрыто за период");
        row0.createCell(5).setCellValue("Арендовано на конец");

        int rowCount = 2;
        for (FillingCellsHolder contract : contracts) {
            Row row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(contract.getDepartmentName());
            row.createCell(1).setCellValue(contract.getCountSafe());
            row.createCell(2).setCellValue(contract.getOpenOnStartDate());
            row.createCell(3).setCellValue(contract.getOpenContractByPeriod());
            row.createCell(4).setCellValue(contract.getCloseContractByPeriod());
            row.createCell(5).setCellValue(contract.getOpenOnEndPeriod());
            rowCount++;
        }
        for (int columnNumber = 0; columnNumber < sheet.getRow(0).getPhysicalNumberOfCells(); columnNumber++) {
            sheet.autoSizeColumn(columnNumber);
        }
        try {
            workbook.write(os);
        } catch (IOException ex) {
            Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
