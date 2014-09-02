package com.xb.safe.service;

import com.xb.safe.dto.Client;
import com.xb.safe.dto.Contract;
import com.xb.safe.dto.Model;
import com.xb.safe.util.ContractNumberGen;
import com.xb.safe.util.DateConverter;
import com.xb.safe.util.SumConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private DaoService daoService;
    public static String orign_filepath;
    public static String chOrign_filepath;
    public static String closeOrgn_filepath;
    public static String chCloseOrgn_filepath;
    public static String repaymentDebtsOroginal_filepath;
    public static String repaymentDebtsChange_filepath;
    public static String prolongContractOriginal_filepath;
    public static String prolongContractChange_filepath;

    public DaoService getDaoService() {
        return daoService;
    }

    public void setDaoService(DaoService daoService) {
        this.daoService = daoService;
    }

    public static String getOrign_filepath() {
        return orign_filepath;
    }

    public static void setOrign_filepath(String orign_filepath) {
        ReportService.orign_filepath = orign_filepath;
    }

    public static String getChOrign_filepath() {
        return chOrign_filepath;
    }

    public static void setChOrign_filepath(String chOrign_filepath) {
        ReportService.chOrign_filepath = chOrign_filepath;
    }

    public static String getCloseOrgn_filepath() {
        return closeOrgn_filepath;
    }

    public static void setCloseOrgn_filepath(String closeOrgn_filepath) {
        ReportService.closeOrgn_filepath = closeOrgn_filepath;
    }

    public static String getChCloseOrgn_filepath() {
        return chCloseOrgn_filepath;
    }

    public static void setChCloseOrgn_filepath(String chCloseOrgn_filepath) {
        ReportService.chCloseOrgn_filepath = chCloseOrgn_filepath;
    }

    public static String getRepaymentDebtsOroginal_filepath() {
        return repaymentDebtsOroginal_filepath;
    }

    public static void setRepaymentDebtsOroginal_filepath(String repaymentDebtsOroginal_filepath) {
        ReportService.repaymentDebtsOroginal_filepath = repaymentDebtsOroginal_filepath;
    }

    public static String getRepaymentDebtsChange_filepath() {
        return repaymentDebtsChange_filepath;
    }

    public static void setRepaymentDebtsChange_filepath(String repaymentDebtsChange_filepath) {
        ReportService.repaymentDebtsChange_filepath = repaymentDebtsChange_filepath;
    }

    public static String getProlongContractOriginal_filepath() {
        return prolongContractOriginal_filepath;
    }

    public static void setProlongContractOriginal_filepath(String prolongContractOriginal_filepath) {
        ReportService.prolongContractOriginal_filepath = prolongContractOriginal_filepath;
    }

    public static String getProlongContractChange_filepath() {
        return prolongContractChange_filepath;
    }

    public static void setProlongContractChange_filepath(String prolongContractChange_filepath) {
        ReportService.prolongContractChange_filepath = prolongContractChange_filepath;
    }
    
    public InputStream getInputStream(Contract contract, Integer getDepartmentId) throws FileNotFoundException, IOException, ParseException {
        Client clientInfo = daoService.getClient(contract.getClientId1());
        Model model = daoService.getModel(contract.getSafeSize());

        SumConverter sumConverter = new SumConverter(contract.getKey());
        SumConverter priceContract = new SumConverter(contract.getPrice());
        SumConverter priceKeyContract = new SumConverter(contract.getKey());
        SumConverter pricePerDayContract = new SumConverter(contract.getPriceRentPerDay());

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String dateContractEnd = contract.getDateContractEnd();
        String dateOfIssue = clientInfo.getIssued();

        Date dateContractEndFormat = formatter.parse(dateContractEnd);
        Date dateOfIssueFormat = formatter.parse(dateOfIssue);

        String inputFilename = orign_filepath;
        String outputFilename = chOrign_filepath;

        POIFSFileSystem fs = null;
        FileInputStream fis = new FileInputStream(inputFilename);
        fs = new POIFSFileSystem(fis);
        HWPFDocument doc = new HWPFDocument(fs);

        Range range = doc.getRange();
        range.replaceText("name", clientInfo.getName());
        range.replaceText("sur", clientInfo.getSurname());
        range.replaceText("patr", clientInfo.getPatronymic());
        range.replaceText("data", DateConverter.getFormattedString(new Date()));
        range.replaceText("numcontract", ContractNumberGen.generateContractNumber(new Date()));
        range.replaceText("number", String.valueOf(contract.getSafeNumber()));
        range.replaceText("h", String.valueOf(model.getHeight()));
        range.replaceText("wi", String.valueOf(model.getWidth()));
        range.replaceText("d", String.valueOf(model.getDepth()));
        range.replaceText("key", String.valueOf(contract.getKey()));
        range.replaceText("text", sumConverter.num2str());
        range.replaceText("contractfin", DateConverter.getFormattedString(dateContractEndFormat));
        range.replaceText("inn", String.valueOf(clientInfo.getInn()));
        range.replaceText("series", String.valueOf(clientInfo.getSeries()));
        range.replaceText("ps", String.valueOf(clientInfo.getNumber()));
        range.replaceText("ept", clientInfo.getIssuedBy());
        range.replaceText("apt", DateConverter.getFormattedString(dateOfIssueFormat));
        range.replaceText("arc", clientInfo.getCountry() + ", " + clientInfo.getRegion() + " обл., м." + clientInfo.getCity() + " вул. " + clientInfo.getStreet() + " кв." + clientInfo.getFlat());
        range.replaceText("tnum", clientInfo.getMobilePhone1());
        range.replaceText("city", clientInfo.getCity() + "  вул." + clientInfo.getStreet());
        range.replaceText("econtract", DateConverter.getFormattedString(dateOfIssueFormat));
        range.replaceText("cprice", String.valueOf(contract.getPrice()));
        range.replaceText("kpr", String.valueOf(contract.getKey()));
        range.replaceText("wp", priceContract.num2str());
        range.replaceText("priceKeyContract", priceKeyContract.num2str());
        range.replaceText("pp24", String.valueOf(contract.getPriceRentPerDay()));
        range.replaceText("p24w", pricePerDayContract.num2str());

        FileOutputStream fos = new FileOutputStream(outputFilename);
        doc.write(fos);
        fis.close();
        fos.close();

        File file = new File(chOrign_filepath);
        return new FileInputStream(file);
    }

    public InputStream getInputStream1(Contract contract, Integer getDepartmentId) throws FileNotFoundException, IOException, ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        contract = daoService.getContractById(contract.getId());
        SumConverter priceKeyContract = new SumConverter(contract.getKey());
        String startContract = contract.getDateContractStart();
        Date startDateContractParse = formatter.parse(startContract);

        String inputFilename = (closeOrgn_filepath);
        String outputFilename = (chCloseOrgn_filepath);

        POIFSFileSystem fs = null;
        FileInputStream fis = new FileInputStream(inputFilename);
        fs = new POIFSFileSystem(fis);
        HWPFDocument doc = new HWPFDocument(fs);

        Range range = doc.getRange();
        range.replaceText("data", DateConverter.getFormattedString(new Date()));
        range.replaceText("kpr", String.valueOf(contract.getKey()));
        range.replaceText("wp", priceKeyContract.num2str());
        range.replaceText("number", String.valueOf(contract.getSafeNumber()));
        range.replaceText("contractCreate", DateConverter.getFormattedString(startDateContractParse));
        range.replaceText("contractNumber", contract.getNumberContract());

        FileOutputStream fos = new FileOutputStream(outputFilename);
        doc.write(fos);
        fis.close();
        fos.close();

         File file = new File(chCloseOrgn_filepath);
        return new FileInputStream(file);
    }

    public InputStream getInputStream2(Contract contract) throws FileNotFoundException, IOException, ParseException {
        SumConverter priceContract = new SumConverter(contract.getPrice());
        Client clientInfo = daoService.getClient(contract.getClientId1());
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date dateContractStaetFormat = formatter.parse(contract.getDateContractStart());

        String inputFilename = (prolongContractOriginal_filepath);
        String outputFilename = (prolongContractChange_filepath);
        POIFSFileSystem fs = null;
        FileInputStream fis = new FileInputStream(inputFilename);
        fs = new POIFSFileSystem(fis);
        HWPFDocument doc = new HWPFDocument(fs);

        Range range = doc.getRange();
        range.replaceText("data", DateConverter.getFormattedString(new Date()));
        range.replaceText("cprice", String.valueOf(contract.getPrice()));
        range.replaceText("wp", priceContract.num2str());
        range.replaceText("number", String.valueOf(contract.getSafeNumber()));
        range.replaceText("numcontract", String.valueOf(contract.getNumberContract()));
        range.replaceText("name", clientInfo.getName());
        range.replaceText("sur", clientInfo.getSurname());
        range.replaceText("patr", clientInfo.getPatronymic());
        range.replaceText("contrdate", DateConverter.getFormattedString(dateContractStaetFormat));

        FileOutputStream fos = new FileOutputStream(outputFilename);
        doc.write(fos);
        fis.close();
        fos.close();
        File file = new File(prolongContractChange_filepath);
        return new FileInputStream(file);
    }

    public InputStream getInputStream3(Integer contractId, Integer delaySum) throws FileNotFoundException, IOException, ParseException {

        Contract contract = daoService.getContractById(contractId);
        Client clientInfo = daoService.getClient(contract.getClientId1());
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = formatter.parse(contract.getDateContractStart());
        SumConverter priceDebt = new SumConverter(delaySum);    
        
        String inputFilename = (prolongContractOriginal_filepath);
        String outputFilename = (prolongContractChange_filepath);
        POIFSFileSystem fs = null;
        FileInputStream fis = new FileInputStream(inputFilename);
        fs = new POIFSFileSystem(fis);
        HWPFDocument doc = new HWPFDocument(fs);
        Range range = doc.getRange();

        range.replaceText("data", DateConverter.getFormattedString(new Date()));
        range.replaceText("name", clientInfo.getName());
        range.replaceText("sur", clientInfo.getSurname());
        range.replaceText("patr", clientInfo.getPatronymic());
        range.replaceText("pdebt", String.valueOf(delaySum));
        range.replaceText("wp", priceDebt.num2str());
        range.replaceText("contrdate", DateConverter.getFormattedString(date));
        range.replaceText("number", String.valueOf(contract.getSafeNumber()));
        range.replaceText("numcontract", String.valueOf(contract.getNumberContract()));

        FileOutputStream fos = new FileOutputStream(outputFilename);
        doc.write(fos);
        fis.close();
        fos.close();
        File file = new File(prolongContractChange_filepath);
        return new FileInputStream(file);
    }

}
