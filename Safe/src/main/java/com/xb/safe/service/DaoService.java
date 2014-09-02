package com.xb.safe.service;

import com.xb.safe.domain.*;
import com.xb.safe.dto.*;
import com.xb.safe.repo.*;
import com.xb.safe.util.ContractNumberGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;

public class DaoService {

    @Autowired
    private FillingCellsRepo fillingReport;
    @Autowired
    private DelayReportRepo delayReport;
    @Autowired
    private ClosingCellsRepo closeRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private ModelRepo modelRepo;
    @Autowired
    private SafeRepo safeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private PriceRepo priceRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private DepartmentInfoRepo deptInfoRepo;
    @Autowired
    private LoginRepo loginRepo;
    @Autowired
    @Qualifier("converterService")
    private ConversionService conversionService;

    public List<Safe> getSafeList(Integer deptId) {
        List<Safe> safeList = new ArrayList<>();
        for (SafeEntity entity : safeRepo.findAllByDepartmentId(deptId)) {
            safeList.add(conversionService.convert(entity, Safe.class));
        }
        return safeList;
    }

    public List<DelayReportHolder> getDataForDelayReport(Integer deptId, String currentDay) {
        List<DelayReportHolder> dataList = new ArrayList<>();
        for (DelayReportEntity entity : delayReport.getDataForDelayReport(currentDay, deptId)) {
            DelayReportHolder convert = conversionService.convert(entity, DelayReportHolder.class);
            if (convert.getDelayDays() > 0) {
                dataList.add(convert);
            }
        }
        return dataList;
    }

    public List<DelayReportHolder> getDataForCurrentDeptDelayReport(Integer deptId, String currentDate) {
        List<DelayReportHolder> dataList = new ArrayList<>();
        for (DelayReportEntity entity : delayReport.getDataForDelayReport(currentDate, deptId)) {
            DelayReportHolder convert = conversionService.convert(entity, DelayReportHolder.class);
            if (convert.getDelayDays() > 0) {
                dataList.add(convert);
            }
        }
        return dataList;
    }

    public List<DelayReportHolder> getDataForAllDelayReport(String currentDate) {
        List<DelayReportHolder> dataList = new ArrayList<>();
        for (DelayReportEntity entity : delayReport.getDataForAllDelayReport(currentDate)) {
            DelayReportHolder convert = conversionService.convert(entity, DelayReportHolder.class);
            if (convert.getDelayDays() > 0) {
                dataList.add(convert);
            }
        }
        return dataList;
    }

    public List<Contract> getContract(Integer deptId) {
        List<Contract> contractList = new ArrayList<>();
        for (ContractEntity entity : contractRepo.findClientEntityByContractStatusAndDepartmentId(1, deptId)) {
            Contract convert = conversionService.convert(entity, Contract.class);
            if (convert.getClientId1() != null) {
                convert.setClient1(getClient(convert.getClientId1()));
            }
            if (convert.getClientId2() != null) {
                convert.setClient2(getClient(convert.getClientId2()));
            }
            contractList.add(convert);
        }
        return contractList;
    }

    public List<Contract> getContractBySurname(String surname) {

        List<Contract> contractList = new ArrayList<>();
        for (ContractEntity entity : contractRepo.findContractEntityBySurname(surname)) {
            Contract convert = conversionService.convert(entity, Contract.class);
            convert.setClient1(getClient(convert.getClientId1()));
            Department dept = getDepartmentById(convert.getDepartmentId());
            convert.setDepartmentName(dept.getName());
            contractList.add(convert);
        }
        return contractList;
    }

    public List<Contract> getContractByInn(String inn) {
        List<Contract> contractList = new ArrayList<>();
        for (ContractEntity entity : contractRepo.findClientEntityByInn(inn)) {
            Contract convert = conversionService.convert(entity, Contract.class);
            convert.setClient1(getClient(convert.getClientId1()));
            Department dept = getDepartmentById(convert.getDepartmentId());
            convert.setDepartmentName(dept.getName());
            contractList.add(convert);
        }
        return contractList;
    }

    public List<Contract> getAllClientsByAllDept() {
        List<Contract> allContractList = new ArrayList<>();
        for (ContractEntity entity : contractRepo.getAllClientsByAllDept()) {
            Contract convert = conversionService.convert(entity, Contract.class);
            convert.setClient1(getClient(convert.getClientId1()));
            Department dept = getDepartmentById(convert.getDepartmentId());
            convert.setDepartmentName(dept.getName());

            allContractList.add(convert);
        }
        return allContractList;
    }

    public List<Contract> getContracstByPeriod(Date dateStart, Date dateEnd, Integer deptId) {
        List<Contract> contractsByPeriod = new ArrayList<>();
        for (ContractEntity entity : contractRepo.getContractsByPeriod(dateStart, dateEnd, deptId)) {
            Contract convert = conversionService.convert(entity, Contract.class);
            convert.setClient1(getClient(convert.getClientId1()));
            contractsByPeriod.add(convert);
        }
        return contractsByPeriod;
    }

    public List<ClosingCellsHolder> getContractByEndDate(Date dateStart, Date dateEnd) {
        List<ClosingCellsHolder> contractsByPeriod = new ArrayList<>();
        for (ClosingCellsEntity entity : closeRepo.getContractByEndDate(dateStart, dateEnd)) {
            contractsByPeriod.add(conversionService.convert(entity, ClosingCellsHolder.class));
        }
        return contractsByPeriod;
    }

    public List<FillingCellsHolder> getFillingInfoByPeriodAndDeptId(Date dateStart, Date dateEnd, Integer deptId) {
        List<FillingCellsHolder> contractsByPeriod = new ArrayList<>();
        for (FilingCellsEntity entity : fillingReport.filingCellsInfoByPeriodAndDeptId(dateStart, dateEnd, deptId)) {
            contractsByPeriod.add(conversionService.convert(entity, FillingCellsHolder.class));
        }
        return contractsByPeriod;
    }

    public List<FillingCellsHolder> getFillingInfoByPeriod(Date dateStart, Date dateEnd) {
        List<FillingCellsHolder> contractsByPeriod = new ArrayList<>();
        for (FilingCellsEntity entity : fillingReport.filingCellsInfoByPeriod(dateStart, dateEnd)) {
            contractsByPeriod.add(conversionService.convert(entity, FillingCellsHolder.class));
        }
        return contractsByPeriod;
    }

    public List<ClosingCellsHolder> getContractByPeriodAndDeptId(Date dateStart, Date dateEnd, Integer deptId) {
        List<ClosingCellsHolder> contractsByPeriod = new ArrayList<>();
        for (ClosingCellsEntity entity : closeRepo.getContractByEndDateAndDeptId(dateStart, dateEnd, deptId)) {
            contractsByPeriod.add(conversionService.convert(entity, ClosingCellsHolder.class));
        }
        return contractsByPeriod;
    }

    public List<Price> getPriceListByDeptId(Integer deptId) {
        List<Price> priceList = new ArrayList<>();
        for (PriceEntity entity : priceRepo.findPriceListByDeptId(deptId)) {
            Price convert = conversionService.convert(entity, Price.class);
            convert.setModel(getModel(convert.getModelId()));
            priceList.add(convert);
        }
        return priceList;
    }

    public List<Department> getDepartFreeSafe() {
        List<Department> list = new ArrayList<>();
        for (DepartmentEntity entity : departmentRepo.findFreeSafes()) {
            Department convert = conversionService.convert(entity, Department.class);
        }
        return list;
    }

    public List<Safe> getSafeListByStatus(String status, Integer deptId) {
        List<Safe> safeList = new ArrayList<>();
        for (SafeEntity entity : safeRepo.findAllByStatusAndDepartmentId(status, deptId)) {
            Safe convert = conversionService.convert(entity, Safe.class);
            if (status.equals("r")) {
                Contract c = getContractBySafeId(entity.getId());
                convert.setDate(c.getDateContractEnd());
                safeList.add(convert);
            } else {
                safeList.add(convert);
            }
        }
        return safeList;
    }

    public Contract getContractBySafeId(Integer safeId) {
        return conversionService.convert(contractRepo.getContractBySafeId(safeId), Contract.class);
    }

    public Department getDepartmentById(Integer deptId) {
        return conversionService.convert(departmentRepo.findOne(deptId), Department.class);
    }

    public Safe getSafe(Integer id) {
        return conversionService.convert(safeRepo.findOne(id), Safe.class);
    }

    public DepartmentInfo getDeptartmentInfo(Integer id) {
        return conversionService.convert(deptInfoRepo.findDepartmentEntityByDeptartmentId(id), DepartmentInfo.class);
    }

    public void saveSafe(Safe safe) {
        safeRepo.saveAndFlush(conversionService.convert(safe, SafeEntity.class));
    }

    public void saveDepartmentInfo(DepartmentInfo deptInfo) {
        deptInfoRepo.save(conversionService.convert(deptInfo, DepartmentInfoEntity.class));
    }

    public List<Safe> getAllSafe() {
        List<Safe> allSafeList = new ArrayList<>();
        for (SafeEntity entity : safeRepo.findAll()) {
            Safe convert = conversionService.convert(entity, Safe.class);
            allSafeList.add(convert);
        }
        return allSafeList;
    }

    public List<Model> getModelList() {
        List<Model> modelList = new ArrayList<>();
        for (ModelEntity entity : modelRepo.findAll()) {
            modelList.add(conversionService.convert(entity, Model.class));

        }
        return modelList;
    }

    public List<Model> getModelSafeByDeptId(Integer deptId) {
        List<Model> modelList = new ArrayList<>();
        for (ModelEntity entity : modelRepo.findModelByDeptId(deptId)) {
            modelList.add(conversionService.convert(entity, Model.class));
        }

        return modelList;
    }

    public List<ModelForXml> getModelByDeptId(Integer deptId) {
        List<ModelForXml> modelList = new ArrayList<>();
        for (ModelEntity entity : modelRepo.findModelByDeptId(deptId)) {
            modelList.add(conversionService.convert(entity, ModelForXml.class));
        }
        return modelList;
    }

    public Model getModel(Integer id) {
        return conversionService.convert(modelRepo.findOne(id), Model.class);
    }

    public List<Model> getAllModel() {
        List<Model> allModelList = new ArrayList<>();
        for (ModelEntity entity : modelRepo.findAll()) {
            Model convert = conversionService.convert(entity, Model.class);
            allModelList.add(convert);
        }
        return allModelList;
    }

    public void saveModel(Model model) {
        modelRepo.saveAndFlush(conversionService.convert(model, ModelEntity.class));
    }

    public List<Client> getClientList() {
        List<Client> clientList = new ArrayList<>();
        for (ClientEntity entity : clientRepo.findAll()) {
            clientList.add(conversionService.convert(entity, Client.class));
        }
        return clientList;
    }

    public Client searchClient(String surname, String inn) {
        List<Client> clientList = new ArrayList<>();
        Client client = null;
        for (ClientEntity entity : clientRepo.findAllBySurnameIgnoringCaseOrInn(surname, inn)) {
            clientList.add(conversionService.convert(entity, Client.class));
        }
        for (Client each : clientList) {
            if (each.getInn().equals(inn) || each.getSurname().equalsIgnoreCase(surname)) {
                client = each;
            }
        }
        return client;
    }

    public Client getClient(Integer id) {
        return conversionService.convert(clientRepo.findOne(id), Client.class);
    }

    public Client getAllClient() {
        return conversionService.convert(clientRepo.findAll(), Client.class);
    }

    public Contract getContractById(Integer contractId) {
        Contract convert = conversionService.convert(contractRepo.findOne(contractId), Contract.class);
        if (convert.getClientId1() != null) {
            convert.setClient1(getClient(convert.getClientId1()));
        }
        if (convert.getClientId2() != null) {
            convert.setClient2(getClient(convert.getClientId2()));
        }
        if (convert.getSafeSize() != null) {
            convert.setModel(getModel(convert.getSafeSize()));
        }
        return convert;
    }

    public void saveClient(Client client) {
        clientRepo.saveAndFlush(conversionService.convert(client, ClientEntity.class));
    }

    public List<Department> getDepartmentList() {
        List<Department> departmentList = new ArrayList<>();
        for (DepartmentEntity entity : departmentRepo.findAll()) {
            departmentList.add(conversionService.convert(entity, Department.class));
        }
        return departmentList;
    }

    public List<Price> getPriceList() {
        List<Price> priceList = new ArrayList<>();
        for (PriceEntity entity : priceRepo.findAll()) {
            priceList.add(conversionService.convert(entity, Price.class));
        }
        return priceList;
    }

    public Map<String, Integer> getLoginMap() {
        Map<String, Integer> loginMap = new HashMap<>();
        for (LoginEntity entity : loginRepo.findAll()) {
            loginMap.put(entity.getLogin(), entity.getDeptId());
        }
        return loginMap;
    }

    public Client getClientByInn(String check) {
        return conversionService.convert(clientRepo.findClientEntityByInn(check), Client.class);
    }

    public List<Safe> getFreeSafes(Integer modelId, Integer deptId) {
        List<Safe> safesList = new ArrayList<>();
        for (SafeEntity entity : safeRepo.findAllByDepartmentIdAndModelIdAndStatus(deptId, modelId, "f")) {
            safesList.add(conversionService.convert(entity, Safe.class));
        }
        return safesList;
    }

    public Price getPriceForContract(Integer deptId, Integer modelId, Integer rentPeriod) {
        return conversionService.convert(priceRepo.findByDepartmentIdAndModelIdAndRentPeriod(deptId, modelId, rentPeriod), Price.class);
    }

    public Department getDeptById(Integer deptId) {
        return conversionService.convert(departmentRepo.findDepartmentEntityById(deptId), Department.class);
    }

    public Integer getPrceByDepartmentIdAndModelId(Integer deptId, Integer modelId) {
        return priceRepo.findPrce24ByDepartmentIdAndModelId(deptId, modelId);
    }

    public List<Price> getPriceForXml(Integer deptId, Integer modelId) {
        List<Price> priceList = new ArrayList<>();
        for (PriceEntity entity : priceRepo.findByDepartmentIdAndModelId(deptId, modelId)) {
            priceList.add(conversionService.convert(entity, Price.class));
        }
        return priceList;
    }

    public void saveContract(Contract contract) {
        contractRepo.saveAndFlush(conversionService.convert(contract, ContractEntity.class));
    }

    public void changeContractStatus(Integer contractStatus, String currentDate) {
        contractRepo.setContractStatus(contractStatus, currentDate);
    }

    public void setNewModelPrice(Integer newPrice, Integer priceId) {
        priceRepo.setNewModelPrice(newPrice, priceId);
    }

    public void setNewModelPrice24(Integer newPrice, Integer priceId) {
        priceRepo.setNewModelPrice24(newPrice, priceId);
    }

    public void setNewDeptId(Integer departmentId, String clientLogin) {
        loginRepo.setNewDeptId(departmentId, clientLogin);
    }

    public void changeSafeStatusBySafeId(String safeStatus, Integer safeId) {
        safeRepo.setSafeStatus(safeStatus, safeId);
    }

    public void setNumberContract(Contract contract) {
        contract.setNumberContract(ContractNumberGen.generateContractNumber(new Date()));
    }

    public void setSafeNumber(Contract contract) {
        Safe s = getSafe(contract.getSafeId());
        contract.setSafeNumber(Integer.parseInt(s.getCode()));
    }

    public void setDepartmentId(Contract contract, Integer deptId) {
        contract.setDepartmentId(deptId);
    }

    public void setPricePerRentDay(Contract contract, Integer deptId) {
        String price24 = String.valueOf(getPrceByDepartmentIdAndModelId(deptId, contract.getSafeSize()));
        contract.setPriceRentPerDay(Integer.parseInt(price24));
    }
}
