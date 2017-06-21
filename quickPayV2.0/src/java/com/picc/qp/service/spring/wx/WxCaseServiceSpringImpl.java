package com.picc.qp.service.spring.wx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.DateUtil;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.wxdao.WxCaseDaoHibernate;
import com.picc.qp.enums.SelectTypeEnum;
import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMCityId;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMDistrictId;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMProvinceId;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICAccidentId;
import com.picc.qp.schema.model.QpTICAccidentProject;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTICCompanyId;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPFastCenterId;
import com.picc.qp.schema.model.WxAccident;
import com.picc.qp.schema.model.WxCase;
import com.picc.qp.schema.model.WxCaseId;
import com.picc.qp.schema.vo.Pair;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTCOMDistrictService;
import com.picc.qp.service.facade.IQpTCOMProvinceService;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICAccidentProjectService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.qp.service.wx.facade.WxAccidentService;
import com.picc.qp.service.wx.facade.WxCaseService;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service("wxCaseService")
public class WxCaseServiceSpringImpl implements WxCaseService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WxCaseDaoHibernate wxCaseDao;

	@Autowired
	private SysCommonDaoHibernate sysCommonDao;

	@Autowired
	private IQpTTPCaseService qpTTPCaseService;

	@Autowired
	private IQpTICAccidentService qpTICAccidentService;

	@Autowired
	private WxAccidentService wxAccidentService;

	@Autowired
	private IQpTTPFastCenterService qpTTPFastCenterService;

	@Autowired
	private IQpTCommonService qpTCommonService;

	@Autowired
	private IQpTICCompanyService qpTICCompanyService;

	@Autowired
	private IQpTCOMProvinceService qpTCOMProvinceService;

	@Autowired
	private IQpTCOMCityService qpTCOMCityService;

	@Autowired
	private IQpTCOMDistrictService qpTCOMDistrictService;

	@Autowired
	private IQpTICAccidentProjectService qpTICAccidentProjectService;

	/**
	 * 查询临时案件列表
	 * 
	 * @param wxCase
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	@Override
	public List<WxCase> list(WxCase wxCase) throws Exception {
		return null;
	}

	/**
	 * 查询临时案件列表分页数据
	 * 
	 * @param wxCase
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	@Override
	public Page page(WxCase wxCase, Integer pageNo, Integer pageSize)
			throws Exception {
		return null;
	}

	/**
	 * 根据临时案件ID查询临时案件
	 * 
	 * @param wxCaseId
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	@Override
	public WxCase findById(WxCaseId wxCaseId) throws Exception {
		return wxCaseDao.get(WxCase.class, wxCaseId);
	}

	/**
	 * 保存临时案件(若有ID更新，无ID新增)
	 * 
	 * @param wxCase
	 * @throws Exception
	 * @author obba
	 */
	@Override
	public void save(WxCase wxCase) throws Exception {
		wxCaseDao.save(wxCase);
	}

	/**
	 * 删除临时案件
	 * 
	 * @param wxCase
	 * @throws Exception
	 * @author obba
	 */
	@Override
	public void delete(WxCase wxCase) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 生成临时案件ID
	 * 
	 * @return
	 * @author obba
	 */
	@Override
	public String getWxCaseId() {
		return sysCommonDao.generateID("C", "WX_CASE_ID", 10);
	}


	/**
	 * 查询进行中的临时案件<br>
	 * 
	 * @throws Exception
	 * @author obba
	 */
	@Override
	public WxCase findCurrentCaseByUser(String userCode) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		if (userCode != null) {
			queryRule.addEqual("createcode", userCode);
		}
		queryRule.addDescOrder("createdate");
		List<WxCase> cases = wxCaseDao.find(queryRule);
		return cases.size() > 0 ? cases.get(0) : null;
	}

	/**
	 * 保存临时案件和相关临时当事人
	 * 
	 * @param caseinfo
	 * @param accidents
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Pair<Boolean, String> saveWxCaseAndWxAccident(WxCase caseinfo,
			List<WxAccident> accidents) throws Exception {
		Pair<Boolean, String> result = new Pair<Boolean, String>();
		try {
			// 新增临时案件
			wxCaseDao.save(caseinfo);
			// 新增临时当事人
			for (WxAccident accident : accidents) {
				wxAccidentService.save(accident);
			}
			result.setFirst(true);
			result.setSecond("保存成功");
		} catch (Exception e) {
			logger.error("", e);
			result.setFirst(false);
			result.setSecond("保存失败");
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * 保存真实案件和相关当事人
	 * 
	 * @param caseinfo
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Pair<Boolean, String> saveCaseAndAccident(WxCase caseinfo)
			throws Exception {
		Pair<Boolean, String> result = new Pair<Boolean, String>();
		try {
			String realCaseId = caseinfo.getRealCaseId();
			QpTTPCase qpTTPCase = new QpTTPCase();
			if (StringUtils.isEmptyStr(realCaseId)) {
				// 真实案件不存在,创建案件
				qpTTPCase = caseinfo.changeToCaseFromWxCase();

				// 生成认字编号
				if (ToolsUtils.isEmpty(qpTTPCase.getCaseSerialNo())) {
					QpTTPFastCenterId fastCenterId = new QpTTPFastCenterId();
					fastCenterId.setCenterId(qpTTPCase.getCenterId());
					QpTTPFastCenter fastCenter = qpTTPFastCenterService.findQpTTPFastCenterByPK(fastCenterId);
					if (fastCenter != null) {
						String serinalNo = qpTTPCaseService.generateSerinalNo(fastCenter.getReadNum());
						if (ToolsUtils.notEmpty(serinalNo)) {
							qpTTPCase.setCaseSerialNo(serinalNo);
						}
					}
				}
			} else {
				// 真实案件存在,修改案件
				QpTTPCaseId qpTTPCaseId = new QpTTPCaseId();
				qpTTPCaseId.setCaseId(realCaseId);
				qpTTPCase = qpTTPCaseService.findQpTTPCaseByPK(qpTTPCaseId);
				qpTTPCase = caseinfo.changeToCaseFromWxCase(qpTTPCase);
				qpTTPCase.setOperateTimeForHis(new Date());
			}
			// 保存真实案件
			qpTTPCaseService.saveOrupdateQpTTPCase(qpTTPCase);
			// 修改临时案件信息
			caseinfo.setRealCaseId(qpTTPCase.getId().getCaseId());
			wxCaseDao.save(caseinfo);

			WxAccident queryAccident = new WxAccident();
			queryAccident.setCaseid(caseinfo.getId().getId());
			List<WxAccident> queryAccidents = wxAccidentService.page(
					queryAccident, 1, Integer.MAX_VALUE).getResult();

			// 新增当事人
			for (WxAccident accident : queryAccidents) {
				String realAccId = accident.getRealAccID();
				QpTICAccident qpTICAccident = new QpTICAccident();
				if (StringUtils.isEmptyStr(realAccId)) {
					// 真实当事人不存在,生成当事人
					qpTICAccident = accident.changeToAccFromWxAcc(qpTTPCase);
					qpTICAccident.setAcciId(realAccId);
				} else {
					// 展示当事人存在,修改当事人
					QpTICAccidentId qpTICAccidentId = new QpTICAccidentId();
					qpTICAccidentId.setAcciId(realAccId);
					qpTICAccident = qpTICAccidentService
							.findQpTICAccidentByPK(qpTICAccidentId);
					qpTICAccident = accident.changeToAccFromWxAcc(qpTTPCase,
							qpTICAccident);
				}
				// 保存真实当事人
				qpTICAccidentService
				.saveQpTICAccident(qpTTPCase, qpTICAccident);
				// 修改临时当事人信息
				accident.setRealAccID(qpTICAccident.getAcciId());
				wxAccidentService.save(accident);
			}
			result.setFirst(true);
			result.setSecond("保存成功");
		} catch (Exception e) {
			logger.error("", e);
			result.setFirst(false);
			result.setSecond("保存失败");
			throw new RuntimeException();
		}
		return result;
	}

	@Override
	public WxCase findCaseBuRealID(String realID) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		if (realID != null) {
			queryRule.addEqual("realCaseId", realID);
		}
		queryRule.addDescOrder("createdate");
		List<WxCase> cases = wxCaseDao.find(queryRule);
		return cases.size() > 0 ? cases.get(0) : null;
	}

	@Override
	public JSONObject getLossSourceJson(String caseID,String accID,String imgUrl,String templateUrl,boolean isPrint,String lossStampUrl,boolean lossStampIsPrint) throws Exception {
		try{
			if (StringUtils.isNotEmpty(accID) && StringUtils.isNotEmpty(caseID)) {
				// 当事人
				QpTICAccidentId queryAccID = new QpTICAccidentId();
				queryAccID.setAcciId(accID);
				QpTICAccident currentAccident = qpTICAccidentService.findQpTICAccidentByPK(queryAccID);
				// 事故详情
				QpTTPCaseId queryCaseID = new QpTTPCaseId();
				queryCaseID.setCaseId(caseID);
				QpTTPCase currentCase = qpTTPCaseService.findQpTTPCaseByPK(queryCaseID);
				if (currentAccident != null && currentCase != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("imgUrl", imgUrl);
					params.put("isPrint", isPrint);
					params.put("lossStampUrl", lossStampUrl);//印章图片
					params.put("lossStampIsPrint", true);//是否打印各快赔点印章，只是图片，无法律效应
					// 承保公司
					String companyName = "";
					if (StringUtils.isNotEmpty(currentAccident.getCoId())) {
						QpTICCompanyId queryCompanyID = new QpTICCompanyId();
						queryCompanyID.setCoId(currentAccident.getCoId());
						QpTICCompany currentCompany = qpTICCompanyService.findQpTICCompanyByPK(queryCompanyID);
						if (currentCompany != null) {
							companyName = "26".equals(currentAccident.getCoId()) ? currentCompany.getCoName() + "-" + currentAccident.getCompanyNameOther() : currentCompany.getCoName();
						}
					}
					params.put("companyName", companyName);
					params.put("insurePerson", currentAccident.getInsured());
					params.put("carNumber", currentAccident.getDriverVehicleNumber());
					params.put("driver", currentAccident.getDriverName());
					// 准驾车型
					String permissionTypeDesc = qpTCommonService.getSelectCodeName(SelectTypeEnum.PERMISSION_DRIVE.getCode(), currentAccident.getPermissionDrive());
					params.put("driverPermissionType", currentAccident.getPermissionDrive() + permissionTypeDesc);
					params.put("carManufactor", currentAccident.getLabelType());
					params.put("driverNumber", currentAccident.getDriverIDNumber());
					params.put("year", DateUtil.parseToFormatString(currentCase.getCaseTime(), "yyyy"));
					params.put("month", DateUtil.parseToFormatString(currentCase.getCaseTime(), "MM"));
					params.put("day", DateUtil.parseToFormatString(currentCase.getCaseTime(), "dd"));
					params.put("hour", DateUtil.parseToFormatString(currentCase.getCaseTime(), "HH"));
					params.put("minute", DateUtil.parseToFormatString(currentCase.getCaseTime(), "mm"));
					// 省份
					String province = "";
					if (StringUtils.isNotEmpty(currentCase.getCaseProvince())) {
						QpTCOMProvinceId queryProcinceID = new QpTCOMProvinceId();
						queryProcinceID.setProvId(currentCase.getCaseProvince());;
						QpTCOMProvince currentProvince = qpTCOMProvinceService.findQpTCOMProvinceByPK(queryProcinceID);
						if (currentProvince != null) {
							province = currentProvince.getProvName();
						}
					}
					// 城市
					String city = "";
					if (StringUtils.isNotEmpty(currentCase.getCaseCity())) {
						QpTCOMCityId queryCityID = new QpTCOMCityId();
						queryCityID.setCityId(currentCase.getCaseCity());;
						QpTCOMCity currentCity = qpTCOMCityService.findQpTCOMCityByPK(queryCityID);
						if (currentCity != null) {
							city = currentCity.getCityName();
						}
					}
					// 地区
					String area = "";
					if (StringUtils.isNotEmpty(currentCase.getCaseDistrict())) {
						QpTCOMDistrictId queryAreaID = new QpTCOMDistrictId();
						queryAreaID.setDistrictId(currentCase.getCaseDistrict());
						QpTCOMDistrict currentArea = qpTCOMDistrictService.findQpTCOMDistrictByPK(queryAreaID);
						if (currentArea != null) {
							area = currentArea.getDistrictName();
						}
					}
					params.put("address", province + city + area + currentCase.getCaseStreet());
					params.put("notes", currentCase.getCaseNotes() + " " + currentCase.getCaseResult());
					params.put("carID", currentAccident.getChassisNumber());

					List<QpTICAccident> currentAccidents = qpTICAccidentService.findQpTICAccidentInfoOnly(caseID);
					String otherCarNumber = "";
					String otherCarType = "";
					String otherCompanyName = "";
					for (QpTICAccident accident : currentAccidents) {
						if (!accID.equals(accident.getAcciId()) && StringUtils.isEmptyStr(otherCarNumber)) {
							queryAccID.setAcciId(accident.getAcciId());
							QpTICAccident otherAccident = qpTICAccidentService.findQpTICAccidentByPK(queryAccID);
							// 三者承保公司
							if (StringUtils.isNotEmpty(otherAccident.getCoId())) {
								QpTICCompanyId queryCompanyID = new QpTICCompanyId();
								queryCompanyID.setCoId(currentAccident.getCoId());
								QpTICCompany otherCompany = qpTICCompanyService.findQpTICCompanyByPK(queryCompanyID);
								if (otherCompany != null) {
									otherCompanyName = otherCompany.getCoName();
								}
							}
							otherCarNumber = otherAccident.getDriverVehicleNumber();
							otherCarType = otherAccident.getLabelType();
						}
					}
					params.put("otherCarNumber", otherCarNumber);
					params.put("otherCarManufactor", otherCarType);
					params.put("otherCompanyName", otherCompanyName);

					List<Item> currentChangeItems = new ArrayList<Item>();
					List<Item> currentRepairItems = new ArrayList<Item>();
					double currentTotalMoney = 0L; 
					List<Item> otherChangeItems = new ArrayList<Item>();
					List<Item> otherRepairItems = new ArrayList<Item>();
					double otherTotalMoney = 0L; 

					QpTICAccidentProject queryAccidentProject = new QpTICAccidentProject();
					queryAccidentProject.setCaseId(caseID);
					List<QpTICAccidentProject> currentAccidentProjects = qpTICAccidentProjectService.findQpTICAccidentProjectByPK(queryAccidentProject);
					for (QpTICAccidentProject accidentProject : currentAccidentProjects) {
						if(accID.equals(accidentProject.getAcciId())) {
							// 标的车辆
							if ("1".equals(accidentProject.getType())) {
								// 更换项目 
								currentChangeItems.add(new Item(accidentProject.getAccidentProject(), Double.toString(accidentProject.getAccidentProjectMoney())));
								currentTotalMoney += accidentProject.getAccidentProjectMoney();
							} else if ("2".equals(accidentProject.getType())) {
								// 修理项目
								currentRepairItems.add(new Item(accidentProject.getAccidentProject(), Double.toString(accidentProject.getAccidentProjectMoney())));
								currentTotalMoney += accidentProject.getAccidentProjectMoney();
							}
						} else {
							// 三者车辆
							if ("1".equals(accidentProject.getType())) {
								// 更换项目 
								otherChangeItems.add(new Item(accidentProject.getAccidentProject(), Double.toString(accidentProject.getAccidentProjectMoney())));
								otherTotalMoney += accidentProject.getAccidentProjectMoney();
							} else if ("2".equals(accidentProject.getType())) {
								// 修理项目
								otherRepairItems.add(new Item(accidentProject.getAccidentProject(), Double.toString(accidentProject.getAccidentProjectMoney())));
								otherTotalMoney += accidentProject.getAccidentProjectMoney();
							}
						}
					}
					params.put("currentPersonTotalMoney", Double.toString(currentTotalMoney));
					params.put("otherPersonTotalMoney", Double.toString(otherTotalMoney));

					JRBeanCollectionDataSource currentChangeItemsDataSource = new JRBeanCollectionDataSource(currentChangeItems, false);    
					JRBeanCollectionDataSource currentRepairItemsDataSource = new JRBeanCollectionDataSource(currentRepairItems, false);    
					JRBeanCollectionDataSource otherChangeItemsDataSource = new JRBeanCollectionDataSource(otherChangeItems, false);    
					JRBeanCollectionDataSource otherRepairItemsDataSource = new JRBeanCollectionDataSource(otherRepairItems, false);    
					Items items = new Items(currentChangeItemsDataSource, currentRepairItemsDataSource, otherChangeItemsDataSource, otherRepairItemsDataSource);
					Collection<Items> itemsCollections = new Vector<Items>(); 
					itemsCollections.add(items);
					JRDataSource dataSource = new JRBeanCollectionDataSource(itemsCollections);
					
					JSONObject json = new JSONObject();
					json.put("dataSource", dataSource);
					json.put("params", params);
					return json;
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		} 
		return  null;
	}
	
	
	
	public class Items {
		private JRBeanCollectionDataSource currentChangeItems;
		private JRBeanCollectionDataSource currentRepairItems;
		private JRBeanCollectionDataSource otherChangeItems;
		private JRBeanCollectionDataSource otherRepairItems;
		public Items() {

		}
		public Items(JRBeanCollectionDataSource currentChangeItems, JRBeanCollectionDataSource currentRepairItems, JRBeanCollectionDataSource otherChangeItems, JRBeanCollectionDataSource otherRepairItems) {
			this.setCurrentChangeItems(currentChangeItems);
			this.setCurrentRepairItems(currentRepairItems);
			this.setOtherChangeItems(otherChangeItems);
			this.setOtherRepairItems(otherRepairItems);
		}
		public JRBeanCollectionDataSource getCurrentChangeItems() {
			return currentChangeItems;
		}
		public void setCurrentChangeItems(JRBeanCollectionDataSource currentChangeItems) {
			this.currentChangeItems = currentChangeItems;
		}
		public JRBeanCollectionDataSource getCurrentRepairItems() {
			return currentRepairItems;
		}
		public void setCurrentRepairItems(JRBeanCollectionDataSource currentRepairItems) {
			this.currentRepairItems = currentRepairItems;
		}
		public JRBeanCollectionDataSource getOtherChangeItems() {
			return otherChangeItems;
		}
		public void setOtherChangeItems(JRBeanCollectionDataSource otherChangeItems) {
			this.otherChangeItems = otherChangeItems;
		}
		public JRBeanCollectionDataSource getOtherRepairItems() {
			return otherRepairItems;
		}
		public void setOtherRepairItems(JRBeanCollectionDataSource otherRepairItems) {
			this.otherRepairItems = otherRepairItems;
		}
	}

	public class Item {
		private String name;
		private String money;
		public Item () {

		}
		public Item(String name, String money) {
			this.setName(name);
			this.setMoney(money);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMoney() {
			return money;
		}
		public void setMoney(String money) {
			this.money = money;
		}
	}
}
