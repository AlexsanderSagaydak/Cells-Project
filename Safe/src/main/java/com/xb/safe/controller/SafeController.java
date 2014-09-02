package com.xb.safe.controller;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.xb.safe.dto.Contract;
import com.xb.safe.dto.Department;
import com.xb.safe.dto.DepartmentInfo;
import com.xb.safe.dto.Departments;
import com.xb.safe.dto.Model;
import com.xb.safe.dto.ModelForXml;
import com.xb.safe.dto.Price;
import com.xb.safe.dto.Safe;
import com.xb.safe.service.ReportService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SafeController extends BaseController {

    @RequestMapping(method = RequestMethod.GET, value = "/safes")
    public ModelAndView getSafePage() {
        ModelAndView mav = new ModelAndView("safes");
        mav.addObject("departament", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/departmentCard")
    public ModelAndView getDepartmentCard() {
        ModelAndView mav = new ModelAndView("departmentCard");
        DepartmentInfo deptinfo = daoService.getDeptartmentInfo(getDepartmentId());
        mav.addObject("departamentId", getDepartmentById());
        mav.addObject("deptinfo", deptinfo);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/closingCells")
    public ModelAndView getClosingCells() {
        ModelAndView mav = new ModelAndView();
        List<Department> departmentList = daoService.getDepartmentList();
        mav.addObject("departmentList", departmentList);
        mav.addObject("departmentId", getDepartmentId());
        mav.addObject("departament", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contractRegistration")
    public ModelAndView getContractRegistration() {
        ModelAndView mav = new ModelAndView("contractRegistration");
        mav.addObject("department", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filling-profit")
    public ModelAndView getFillingProfit() {
        ModelAndView mav = new ModelAndView();
        List<Department> departmentList = daoService.getDepartmentList();
        mav.addObject("departmentList", departmentList);
        mav.addObject("departmentId", getDepartmentId());
        mav.addObject("departament", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reportDelay")
    public ModelAndView getReportDelay() {
        ModelAndView mav = new ModelAndView("reportDelay");
        List<Department> departmentList = daoService.getDepartmentList();
        mav.addObject("departmentList", departmentList);
        mav.addObject("departmentId", getDepartmentId());
        mav.addObject("departament", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/controldeal")
    public ModelAndView getTest() {
        ModelAndView mav = new ModelAndView("controldeal");
        List<Department> departmentList = daoService.getDepartmentList();
        mav.addObject("departmentList", departmentList);
        mav.addObject("clientLogin", getLogin());
        mav.addObject("departament", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/safeedit")
    public ModelAndView editSafe(@RequestParam(value = "id", required = false) Integer safeId) {
        ModelAndView mav = new ModelAndView("safeedit");
        if (safeId != null) {
            Safe safe = daoService.getSafe(safeId);
            mav.addObject("safe", safe);
        }
        List<Department> departmentList = daoService.getDepartmentList();
        mav.addObject("departmentList", departmentList);
        mav.addObject("activeSafes", true);
        mav.addObject("departament", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/models")
    public ModelAndView getModelPage() {
        ModelAndView mav = new ModelAndView("models");
        mav.addObject("activeModels", true);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/modeledit")
    public ModelAndView editModel(@RequestParam(value = "id", required = false) Integer modelId) {
        ModelAndView mav = new ModelAndView("modeledit");
        if (modelId != null) {
            Model model = daoService.getModel(modelId);
            mav.addObject("model", model);
        }
        mav.addObject("user", getUserFullName());
        mav.addObject("activeModels", true);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clients")
    public ModelAndView getClientPage() {
        ModelAndView mav = new ModelAndView("clients");
        mav.addObject("activeClients", true);
        mav.addObject("departament", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contractcreate")
    public ModelAndView getCreateContractPage() {
        ModelAndView mav = new ModelAndView("contractcreate");
        List<Model> modelList = daoService.getModelSafeByDeptId(getDepartmentId());
        mav.addObject("activeCC", true);
        mav.addObject("modelList", modelList);
        mav.addObject("departament", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contractsinfo")
    public ModelAndView getContractPage() {
        ModelAndView mav = new ModelAndView("contractsinfo");
        logger.info("Вход пользователя   " + getLogin());
        mav.addObject("activeContract", true);
        mav.addObject("departament", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contractviev")
    public ModelAndView getContractViev(@RequestParam(value = "id", required = false) Integer contractId) {
        ModelAndView mav = new ModelAndView("contractviev");
        Contract contractList = daoService.getContractById(contractId);
        mav.addObject("contract", contractList);
        mav.addObject("departament", getDepartmentById());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public void getReportCreateDeal(HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException, IOException, ServletException, ParseException {
        HttpSession session = request.getSession();
        Contract contract = (Contract) session.getAttribute("contract");
        Integer departmentId = getDepartmentId();

        InputStream input = reportService.getInputStream(contract, departmentId);
        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        try {
            stream = response.getOutputStream();
            File doc1 = new File(ReportService.chOrign_filepath);
            buf = new BufferedInputStream(input);
            response.setContentType("application/msword");
            response.addHeader("Content-Disposition", "attachment; filename=2.doc");
            response.setContentLength((int) doc1.length());

            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }
        } catch (IOException ioe) {
            loggerExeption.error(ioe);
            throw new ServletException(ioe.getMessage());
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (buf != null) {
                buf.close();
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test1")
    public void getCreateDeal(HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException, IOException, ServletException, ParseException {
        HttpSession session = request.getSession();
        Contract contract = (Contract) session.getAttribute("contract");
        Integer departmentId = getDepartmentId();

        InputStream input = reportService.getInputStream1(contract, departmentId);
        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        try {
            stream = response.getOutputStream();
            File doc1 = new File(ReportService.chCloseOrgn_filepath);
            buf = new BufferedInputStream(input);
            response.setContentType("application/msword");
            response.addHeader("Content-Disposition", "attachment; filename=2.doc");
            response.setContentLength((int) doc1.length());

            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }

        } catch (IOException ioe) {
            loggerExeption.error(ioe);
            throw new ServletException(ioe.getMessage());
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (buf != null) {
                buf.close();
            }
        }
        logger.info("Закритие контракта Id " + contract.getId() + " департамента " + departmentId + " произведено " + getLogin());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test2")
    public void getProlongDeal(HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException, IOException, ServletException, ParseException {
        HttpSession session = request.getSession();
        Contract contract1 = (Contract) session.getAttribute("contract");
        Contract contract = daoService.getContractById(contract1.getId());
        InputStream input = reportService.getInputStream2(contract);

        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        try {
            stream = response.getOutputStream();
            File doc1 = new File(ReportService.prolongContractChange_filepath);
            buf = new BufferedInputStream(input);
            response.setContentType("application/msword");
            response.addHeader("Content-Disposition", "attachment; filename=3.doc");
            response.setContentLength((int) doc1.length());

            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }

        } catch (IOException ioe) {
            loggerExeption.error(ioe);
            throw new ServletException(ioe.getMessage());
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (buf != null) {
                buf.close();
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test3")
    public void getDebt(HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException, IOException, ServletException, ParseException {
        HttpSession session = request.getSession();
        Integer contractId = (Integer) session.getAttribute("contractId");
        Integer delaySum = (Integer) session.getAttribute("allDebt");
        InputStream input = reportService.getInputStream3(contractId, delaySum);
        
        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        try {
            stream = response.getOutputStream();
            File doc1 = new File(ReportService.prolongContractChange_filepath);
            buf = new BufferedInputStream(input);
            response.setContentType("application/msword");
            response.addHeader("Content-Disposition", "attachment; filename=3.doc");
            response.setContentLength((int) doc1.length());

            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }
        } catch (IOException ioe) {
            loggerExeption.error(ioe);
            throw new ServletException(ioe.getMessage());
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (buf != null) {
                buf.close();
            }
        }
        logger.info("Оплата просрочки " + delaySum + " грн.  по контракту Id " + contractId + " была произведена: " + getLogin());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/xml")
    public void getXmlViev(HttpServletResponse response, HttpServletRequest request) throws JsonProcessingException, IOException {
        List<Departments> finalList = new ArrayList<>();
        List<Department> allDepartments = daoService.getDepartmentList();
        List<Safe> allSafeList = new ArrayList<>();
        List<ModelForXml> modelByDepartmentId = new ArrayList<>();
        List<Price> allSafePrice = new ArrayList<>();

        for (Department each : allDepartments) {
            modelByDepartmentId = daoService.getModelByDeptId(each.getId());
            
            Departments d = new Departments();
            d.setExtId(each.getExtId());
            d.setName(each.getName());
            d.setRegion(each.getRegion());
            d.setCity(each.getCity());
            d.setAddress(each.getAddress());
            d.setModelList(modelByDepartmentId);

            for (ModelForXml model : d.getModelList()) {
                allSafePrice = daoService.getPriceForXml(each.getId(), model.getId());
                allSafeList = daoService.getFreeSafes(model.getId(), each.getId());
                for (Safe safe : allSafeList) {
                    safe.setId(null);
                    safe.setModel(null);
                    safe.setDepartment(null);
                }
                for (Price price : allSafePrice) {
                    price.setId(null);
                    price.setDepartmentId(null);
                    price.setKey(null);
                    price.setModelId(null);
                }
                model.setPriceList(allSafePrice);
                model.setId(null);
            }
            finalList.add(d);
        }
        XmlMapper mapper = new XmlMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        String writeValueAsString = mapper.writeValueAsString(finalList);
        
        OutputStream out = response.getOutputStream();
        response.setHeader("Content-Type", "application/xhtml+xml; charset=UTF-8");
        out.write(writeValueAsString.getBytes());
        out.flush();
    }
}
