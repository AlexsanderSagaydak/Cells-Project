package com.xb.safe.controller;

import com.xb.safe.dto.*;
import com.xb.safe.util.ContractNumberGen;
import com.xb.safe.util.SetHeaderUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.joda.time.Days;

@Controller
public class RestController extends BaseController {

    @RequestMapping(method = RequestMethod.GET, value = "/safe")
    @ResponseBody
    public List<Safe> getSafeList(
            @RequestParam(value = "status") String status) {
        if (status.equals("all")) {
            return daoService.getSafeList(getDepartmentId());
        }
        return daoService.getSafeListByStatus(status, getDepartmentId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model")
    @ResponseBody
    public List<Model> getModelList() {
        return daoService.getModelList();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/model")
    @ResponseBody
    public Message saveModel(@RequestBody Model model) {
        Message message = new Message();
        try {
            daoService.saveModel(model);
            message.setOk();
        } catch (Exception e) {
            loggerExeption.error(e);
            message.setError();
        }
        return message;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client")
    @ResponseBody
    public List<Client> getClientList() {
        return daoService.getClientList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client")
    @ResponseBody
    public Client getClient(@RequestParam(value = "id", required = false) Integer clientId) {
        return daoService.getClient(clientId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client")
    @ResponseBody
    public Message saveClient(@RequestBody Client client) {
        Message message = new Message();
        try {
            daoService.saveClient(client);
            logger.info("Добавлен клиент " + client + "пользователем " + getLogin());
            message.setOk();
        } catch (Exception e) {
            loggerExeption.error(e);
            e.printStackTrace();
            message.setError();
        }
        return message;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/searchClient")
    @ResponseBody
    public Client searchClient(
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "inn") String inn) {
        Client client = daoService.searchClient(surname, inn);
        return client;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/checkClient")
    @ResponseBody
    public Message checkClient(
            @RequestParam(value = "checkfield") String checkfield) {
        Message message = new Message();
        Client status = daoService.getClientByInn(checkfield);
        if (status == null) {
            message.setOk();
        } else {
            message.setError();
        }
        return message;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/department")
    @ResponseBody
    public List<Department> getDepartmentList() {
        return daoService.getDepartmentList();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getSafesForContract")
    @ResponseBody
    public List<Safe> contractCreate(
            @RequestParam(value = "modelId") Integer modelId) {
        List<Safe> safeList = daoService.getFreeSafes(modelId, getDepartmentId());
        return safeList;

    }

    private final int RENT_PERIOD_IN_HOURS = 24;
    @RequestMapping(method = RequestMethod.POST, value = "/getPriceForContract")
    @ResponseBody
    public Price getSafePrice(
            @RequestParam(value = "modelId") Integer modelId,
            @RequestParam(value = "rentPeriod") Integer rentPeriod) {
         
        if (rentPeriod.equals(RENT_PERIOD_IN_HOURS)) {
            Integer i = daoService.getPrceByDepartmentIdAndModelId(getDepartmentId(), modelId);
            Price price = new Price();
            price.setPrice(i);
            return price;
        } else {
            Price price = daoService.getPriceForContract(getDepartmentId(), modelId, rentPeriod);
            return price;
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/getPriceForProlongContract")
    @ResponseBody
    public Price getCangedSafePrice(
            @RequestParam(value = "safeId") Integer safeId,
            @RequestParam(value = "rentPeriodContract") Integer rentPeriodContract) {
        Safe s = daoService.getSafe(safeId);
        if (rentPeriodContract.equals(24)) {
            Integer i = daoService.getPrceByDepartmentIdAndModelId(getDepartmentId(), s.getModel().getId());
            Price price = new Price();
            price.setPrice(i);
            return price;
        } else {
            Price price = daoService.getPriceForContract(getDepartmentId(), s.getModel().getId(), rentPeriodContract);
            return price;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getProlongEndContractPeriod")
    @ResponseBody
    public String getProlongEndDate(
            @RequestParam(value = "rentPeriod") Integer rentPeriod,
            @RequestParam(value = "endDate") String endDate) throws ParseException {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
        Date date = dateFormat1.parse(endDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (rentPeriod.equals(24)) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        } else {
            cal.add(Calendar.MONTH, rentPeriod);
        }
        Date creationDate = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String endContract = dateFormat.format(creationDate);
        return endContract;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getEndContractPeriod")
    @ResponseBody
    public String getEndDate(
            @RequestParam(value = "rentPeriod") Integer rentPeriod) {
        String endContract = null;
        Calendar cal = new GregorianCalendar();
        if (rentPeriod.equals(24)) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        } else {
            cal.add(Calendar.MONTH, rentPeriod);
        }
        Date creationDate = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        endContract = dateFormat.format(creationDate);
        return endContract;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveContract")
    @ResponseBody
    public Message saveContract(@RequestBody Contract contract, HttpServletResponse response, HttpServletRequest request) {
        Message message = new Message();
        HttpSession session = request.getSession();
        session.setAttribute("contract", contract);
        contract.setNumberContract(ContractNumberGen.generateContractNumber(new Date()));
        Safe s = daoService.getSafe(contract.getSafeId());
        contract.setSafeNumber(Integer.parseInt(s.getCode()));
        contract.setDepartmentId(getDepartmentId());
        String price24 = String.valueOf(daoService.getPrceByDepartmentIdAndModelId(getDepartmentId(), contract.getSafeSize()));
        contract.setPriceRentPerDay(Integer.parseInt(price24));
        daoService.changeSafeStatusBySafeId("r", contract.getSafeId());
        logger.info("Контракт id" + contract.getId() + " был создан пользователем: " + getLogin());
        try {
            daoService.saveContract(contract);
            message.setOk();
        } catch (Exception e) {
            loggerExeption.error(e);
            e.printStackTrace();
            message.setError();
        }
        return message;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getContractStatus")
    @ResponseBody
    public Message getContractStatus(@RequestBody Contract contract, HttpServletResponse response, HttpServletRequest request) {
        Message message = new Message();
        HttpSession session = request.getSession();
        session.setAttribute("contract", contract);
        daoService.changeContractStatus(contract.getId(), getCurrentDate());
        daoService.changeSafeStatusBySafeId("f", contract.getSafeId());
        return message;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDepartmentInfo")
    @ResponseBody
    public Message getDepartmentInfo(@RequestBody DepartmentInfo deptInfo, HttpServletResponse response, HttpServletRequest request) {
        Message message = new Message();
        try {
            daoService.saveDepartmentInfo(deptInfo);
        } catch (Exception e) {
            message.setError();
        }
        message.setOk();
        return message;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDepartmentInfoForUpdate")
    @ResponseBody
    public Message getDepartmentInfoForUpdate(@RequestBody DepartmentInfo deptInfo, HttpServletResponse response, HttpServletRequest request) {
        Message message = new Message();
        daoService.saveDepartmentInfo(deptInfo);
        return message;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getDelayContracts")
    @ResponseBody
    public List<Contract> getContractListExpiration(
            @RequestParam(value = "status") String status) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        List<Contract> contractList = daoService.getContract(getDepartmentId());
        List<Contract> contractListExpiration = new ArrayList<>();

        if (status.equals("all")) {
            for (Contract each : contractList) {
                String dateContractEnd = each.getDateContractEnd();
                try {
                    DateTime startDate = new DateTime();
                    DateTime endDate = new DateTime(formatter.parse(dateContractEnd));
                    Days diff = Days.daysBetween(startDate, endDate);

                    if ((diff.getDays()) <= 3) {
                        contractListExpiration.add(each);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(SafeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            for (Contract each : contractList) {
                String dateContractEnd = each.getDateContractEnd();
                try {
                    DateTime startDate = new DateTime();
                    DateTime endDate = new DateTime(formatter.parse(dateContractEnd));
                    Days diff = Days.daysBetween(startDate, endDate);
                    if ((diff.getDays()) > 2) {
                        contractListExpiration.add(each);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(SafeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return contractListExpiration;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveProlongContract")
    @ResponseBody
    public Message saveProlongContract(@RequestBody Contract contract, HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("contract", contract);
        Message message = new Message();
        try {
            daoService.saveContract(contract);
            message.setOk();
            logger.info("Контракт id " + contract.getId() + " был продлен пользователем " + getLogin());
        } catch (Exception e) {
            loggerExeption.error(e);
            e.printStackTrace();
            message.setError();
        }
        return message;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDelayDays")
    @ResponseBody
    public Integer getDelayDays(@RequestParam(value = "contractId") Integer contractId) {
        Integer days = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Contract contract = daoService.getContractById(contractId);
        DateTime startDate = new DateTime();
        try {
            DateTime endDate = new DateTime(formatter.parse(contract.getDateContractEnd()));
            days = Days.daysBetween(startDate, endDate).getDays();
        } catch (ParseException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return days;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDelaySum")
    @ResponseBody
    public Message getDelaySum(@RequestParam(value = "contractId") Integer contractId,
            @RequestParam(value = "allDebt") Integer delaySum, HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("contractId", contractId);
        session.setAttribute("allDebt", delaySum);
        Message message = new Message();
        message.setOk();
        return message;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getClientBySurname")
    @ResponseBody
    public List<Contract> getClientBySurname(@RequestParam(value = "surname") String surname) {
        return daoService.getContractBySurname(surname);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getAllClientsByAllDept")
    @ResponseBody
    public List<Contract> getAllClientsByAllDept() {
        return daoService.getAllClientsByAllDept();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getClientByInn")
    @ResponseBody
    public List<Contract> getClientByInn(@RequestParam(value = "inn") String inn) {
        return daoService.getContractByInn(inn);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getPricesAndModelsByDeptId")
    @ResponseBody
    public List<Price> getPricesAndModelsByDeptId(
            @RequestParam(value = "modelId") Integer modelId,
            @RequestParam(value = "deptId") Integer deptId) {
        List<Price> priceList = daoService.getPriceListByDeptId(deptId);
        return priceList;
    }

    private final int MAX_PRICE_FOR_RENT = 24;
    @RequestMapping(method = RequestMethod.POST, value = "/getChangedModelPrice")
    @ResponseBody
    public Message getChangedModelPrice(
            @RequestParam(value = "priceId") Integer priceId,
            @RequestParam(value = "priceValue") Integer priceValue) {
        Message message = new Message();
        if (priceValue < MAX_PRICE_FOR_RENT) {
            daoService.setNewModelPrice24(priceValue, priceId);
        } else {
            daoService.setNewModelPrice(priceValue, priceId);
        }
        return message;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDeptIdAndLogin")
    @ResponseBody
    public Message getDeptIdAndLogin(
            @RequestParam(value = "departmentId") Integer departmentId,
            @RequestParam(value = "clientLogin") String clientLogin) {
        Message message = new Message();
        daoService.setNewDeptId(departmentId, clientLogin);
        updateLoginMap();
        return message;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDataForExcelReport")
    @ResponseBody
    public void getDataForExcelReport(
            @RequestParam(value = "status", required = false) String status,
            HttpServletResponse response, HttpServletRequest request) {
        try {
            OutputStream os = response.getOutputStream();
            if (status.equals("Ok")) {
                List<Contract> contractList = daoService.getAllClientsByAllDept();
                excelService.loadExcel(os, contractList);
            } else {
                List<Contract> contractList = daoService.getContract(getDepartmentId());
                for (Contract contract : contractList) {
                    Department dept = daoService.getDepartmentById(contract.getDepartmentId());
                    contract.setDepartmentName(dept.getName());
                }
                excelService.loadExcel(os, contractList);
            }
            response.setContentType("application/xls");
            response.setHeader("Content-Disposition", SetHeaderUtil.encodeContentDispositionForDownload(request, "Clients.xls"));
            os.flush();
            os.close();
        } catch (IOException e) {
            loggerExeption.info(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getContractByPeriod")
    @ResponseBody
    public List<Contract> getContractByPeriod(
            @RequestParam(value = "dateStart") Date dateStart,
            @RequestParam(value = "dateEnd") Date dateEnd,
            @RequestParam(value = "deptId") Integer deptId) {
        List<Contract> contractList = daoService.getContracstByPeriod(dateStart, dateEnd, deptId);
        return contractList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDataForDelayReport")
    @ResponseBody
    public List<DelayReportHolder> getDataForDelayReport(
            @RequestParam(value = "departmentId") Integer departmentId) {
        if (departmentId == null) {
            departmentId = getDepartmentId();
        }
        List<DelayReportHolder> list = daoService.getDataForDelayReport(departmentId, getCurrentDate());
        return list;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDataForAllDelayReport")
    @ResponseBody
    public List<DelayReportHolder> getDataForAllDelayReport() {
        List<DelayReportHolder> list = daoService.getDataForAllDelayReport(getCurrentDate());
        return list;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDataForCurrentDeptDelayReport")
    @ResponseBody
    public List<DelayReportHolder> getDataForCurrentDeptDelayReport() {
        List<DelayReportHolder> list = daoService.getDataForCurrentDeptDelayReport(getDepartmentId(), getCurrentDate());
        return list;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getContractByEndDate")
    @ResponseBody
    public List<ClosingCellsHolder> getContractByEndDate(
            @RequestParam(value = "dateStart") Date dateStart,
            @RequestParam(value = "dateEnd") Date dateEnd) {
        List<ClosingCellsHolder> list = daoService.getContractByEndDate(dateStart, dateEnd);
        return list;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getInfoForFillingReport")
    @ResponseBody
    public List<FillingCellsHolder> getFillingInfoById(
            @RequestParam(value = "dateStart") Date dateStart,
            @RequestParam(value = "dateEnd") Date dateEnd,
            @RequestParam(value = "departmentId") Integer deptId) {
        List<FillingCellsHolder> list;
        if (deptId != null) {
            list = daoService.getFillingInfoByPeriodAndDeptId(getFormatStartDate(dateStart), getFormatEndDate(dateEnd), deptId);
        } else {
            list = daoService.getFillingInfoByPeriod(dateStart, dateEnd);
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getContractByPeriodAndDeptId")
    @ResponseBody
    public List<ClosingCellsHolder> getContractByPeriodAndDeptId(
            @RequestParam(value = "dateStart") Date dateStart,
            @RequestParam(value = "dateEnd") Date dateEnd,
            @RequestParam(value = "departmentId") Integer deptId) {
        List<ClosingCellsHolder> list = daoService.getContractByPeriodAndDeptId(getFormatStartDate(dateStart), getFormatEndDate(dateEnd), deptId);
        return list;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDataForDelayReportXls")
    @ResponseBody
    public void getDataForDelayReporXls(
            @RequestParam(value = "departmentId") Integer departmentId, HttpServletResponse response, HttpServletRequest request) {
        try {
            OutputStream os = response.getOutputStream();
            if (departmentId != null) {
                List<DelayReportHolder> contractList = daoService.getDataForDelayReport(departmentId, getCurrentDate());
                excelService.loadReportDelayXls(os, contractList);
            } else {
                List<DelayReportHolder> contractList = daoService.getDataForAllDelayReport(getCurrentDate());
                excelService.loadReportDelayXls(os, contractList);
            }
            response.setContentType("application/xls");
            response.setHeader("Content-Disposition", SetHeaderUtil.encodeContentDispositionForDownload(request, "DelayReport.xls"));
            os.flush();
            os.close();
        } catch (IOException e) {
            loggerExeption.info(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDataForContractRegistrationReportXls")
    @ResponseBody
    public void getDataForContractRegistrationReportXls(
            @RequestParam(value = "dateStartHidden") Date dateStart,
            @RequestParam(value = "dateEndHidden") Date dateEnd,
            @RequestParam(value = "deptId") Integer deptId, HttpServletRequest request, HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            List<Contract> contractList = daoService.getContracstByPeriod(dateStart, dateEnd, deptId);
            excelService.loadReportRegistrationXls(os, contractList);
            response.setContentType("application/xls");
            response.setHeader("Content-Disposition", SetHeaderUtil.encodeContentDispositionForDownload(request, "ContractRegistration.xls"));
            os.flush();
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDataForClosingCellsReportXls")
    @ResponseBody
    public void getDataForClosingCellsReportXls(
            @RequestParam(value = "dateStartHidden") Date dateStart,
            @RequestParam(value = "dateEndHidden") Date dateEnd,
            @RequestParam(value = "deptIdHidden") Integer deptId,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            List<ClosingCellsHolder> contractList = daoService.getContractByPeriodAndDeptId(dateStart, dateEnd, deptId);
            excelService.loadReportClosingCellsXls(os, contractList);
            response.setContentType("application/xls");
            response.setHeader("Content-Disposition", SetHeaderUtil.encodeContentDispositionForDownload(request, "ClosingCells.xls"));
            os.flush();
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getDataForFillingCellsReportXls")
    @ResponseBody
    public void getDataForFillingCellsReportXls(
            @RequestParam(value = "dateStartHidden") Date dateStart,
            @RequestParam(value = "dateEndHidden") Date dateEnd,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            List<FillingCellsHolder> contractList = daoService.getFillingInfoByPeriod(dateStart, dateEnd);
            excelService.loadReportFillingCellsXls(os, contractList);
            response.setContentType("application/xls");
            response.setHeader("Content-Disposition", SetHeaderUtil.encodeContentDispositionForDownload(request, "FillingCells.xls"));
            os.flush();
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
