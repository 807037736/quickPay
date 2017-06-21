package com.picc.common.dao;

import ins.framework.common.Page;
import ins.framework.dao.EntityDaoHibernate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.picc.common.CreateSqlTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository(value = "commonDao")
public class CommonDaoHibernate extends EntityDaoHibernate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 新增通用类查询方法,解决SQL查询语句中包含select查询子句的问题
	 * @author chenjin
	 * @param classType
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	public Page findBySql(final Class classType,String sql,Integer pageNo,Integer pageSize,final Object values[]){
		Assert.hasText(sql);
		if(pageNo.intValue() <= 0){pageNo = Integer.valueOf(1);	}
		if(pageSize.intValue()<=0){pageSize = Integer.valueOf(10);}
		
		String newSql = sql;
		int    pos    = 0;
		if(values != null){
			for(int i=0;i<values.length; i++){
				pos = newSql.indexOf('?',pos);
				if(pos == -1){break;}
				if((values[i] instanceof Collection) && pos > -1){
					newSql = (new StringBuilder()).append(newSql.substring(0,pos))
							.append(":queryParam").append(i).append(newSql.substring(pos+1)).toString();
				}
				pos ++;
			}
		}
		
		final String fnSql = newSql;
		final int startIndex = Page.getStartOfPage(pageNo.intValue(), pageSize.intValue());
		if(startIndex<0){return new Page();}
		
		if(isOptimizeFind() && pageNo.intValue() > 1){
			final int realPageSize = pageSize.intValue();
			List list = getHibernateTemplate().executeFind(new HibernateCallback(){
				public Object doInHibernate(Session session) throws SQLException{
					SQLQuery query = session.createSQLQuery(fnSql);
					if(values != null){
						for(int i=0; i<values.length; i++){
							if(values[i] instanceof Collection){
								query.setParameterList((new StringBuilder()).append("queryParam").append(i).toString(), (Collection)values[i]);
							}else{query.setParameter(i, values[i]);}
						}
					}
					query.setFirstResult(startIndex);
					query.setMaxResults(realPageSize);
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					return mapToEntities(query.list(),classType);
				}
			});
			return new Page(startIndex, -1L,pageSize.intValue(),list);
		}
		
		StringBuffer sqlbuf = new StringBuffer("select count(*) from (").append(fnSql).append(")");
		final String totalQuery = sqlbuf.toString();

		List totalCount = getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				SQLQuery query = session.createSQLQuery(totalQuery);
				if(values != null){
					 for(int i = 0; i < values.length; i++){
	                        if(values[i] instanceof Collection){
	                            query.setParameterList((new StringBuilder()).append("queryParam").append(i).toString(), (Collection)values[i]);
	                        }else{query.setParameter(i, values[i]);}

					 }
				}
				return query.list();
			}
		});
		
        final int realPageSize = pageSize.intValue();
        List list = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws SQLException{
                SQLQuery query  = session.createSQLQuery(fnSql);
                if(values != null){
                    for(int i = 0; i < values.length; i++){
                        if(values[i] instanceof Collection){
                            query.setParameterList((new StringBuilder()).append("queryParam").append(i).toString(), (Collection)values[i]);
                        }else{query.setParameter(i, values[i]);}
                    }
                }
                query.setFirstResult(startIndex);
                query.setMaxResults(realPageSize);
                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                return mapToEntities(query.list(),classType);
            }
        });
		return new Page(startIndex, ((BigDecimal)totalCount.get(0)).longValue(), pageSize.intValue(), list);
	}
	
	/**
	 * 将List<Map>数组转换成List<Object>对象数组,转换的对象类型由传入的classType确定
	 * @param src
	 * @param classType
	 * @return
	 */
	private List mapToEntities(List<Map> src, Class classType) {
		List des = new ArrayList();
		if(src==null || src.isEmpty()){return des;}
		for(int i=0,length=src.size(); i<length; i++){
			des.add(mapToEntity(src.get(i),classType));
		}
		return des;
	}
	
	/**
	 * 将具体的map转换成Entity对象
	 * @param src
	 * @param classType
	 * @return
	 */
	private Object mapToEntity(Map src,Class classType){
		Object entity = null;
		try {
			entity = classType.newInstance();
			if(src==null){return entity;}
			Iterator key = src.keySet().iterator();
			Field[] field = classType.getDeclaredFields();
			while(key.hasNext()){
				String index = (String)key.next();
				for(int i=0,length=field.length; i<length; i++){
					if(index.equalsIgnoreCase(field[i].getName())){
						field[i].setAccessible(true);
						field[i].set(entity, src.get(index));
					}
				}
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return entity;
	}
	
	/**计算记录的总数**/
	public Integer getTotalNumber(final String sql,final Object values[]){
		List totalCount = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if(values != null){
					 for(int i = 0; i < values.length; i++){
	                     query.setParameter(i, values[i]);
					 }
				}
				return query.list();
			}
		});
		return Integer.parseInt(totalCount.get(0).toString());
	}
	/**
	 * 多表查询返回多表的查询结果
	 * 
	 * @param _querySQL
	 *            SQL语句
	 * @param _className
	 *            自定义的VO对象类名
	 * @return 封装结果的List对象
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException
	 * @throws Exception
	 */
	
	public Page findObjectPageBySql(final String _querySQL,
			final Class<?> _className, int page, int pageSize,Object... objects) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		if (pageSize <= 0) {
			pageSize = 10;
		}
		Page _mapPage = findMapPageBySql(_querySQL, _className, page, pageSize,objects); // 获取查询结果
		// 进行对象封装
		List _resultList = null;
		List<Map> mapList = _mapPage.getResult();
		if (mapList != null) {
			Object _object = null;
			_resultList = new ArrayList();
			Field[] _fields = _className.getDeclaredFields(); // 获取类的属性集合
			for(Map<String,Object> map : mapList){
				_object = _className.newInstance();
				for(Field field : _fields){
					if(field.getType().getClassLoader()!=null){
						//自定义类
						Object obj = field.getType().newInstance();			//上级菜单新建类
						Field[] tmpFields = field.getType().getDeclaredFields();
						for(Field tmpField : tmpFields){
							for(String key : map.keySet()){
								if(tmpField.getName().equalsIgnoreCase(key)){
									tmpField.setAccessible(true);
									tmpField.set(obj, map.get(key));
									break;
								}
							}
						}
						field.setAccessible(true);
						field.set(_object, obj);
					} else {
						for(String key : map.keySet()){
							if(field.getName().equalsIgnoreCase(key)){
								field.setAccessible(true);
								field.set(_object, map.get(key));
								break;
							}
						}
					}
				}
				_resultList.add(_object);
			}
		}
		
		return new Page(_mapPage.getStart(), _mapPage.getTotalCount(), _mapPage.getPageSize(),
				_resultList);
	}
	
	/**
	 * 根据sql查询数据，并根据entityMap<tableAlias,class>将sql映射成对象返回
	 * 使用LinkedHashMap是为了保证顺序，比如put(a),put(b)后，遍历是按a,b进行，其他map会按b,a进行
	 * @param sql
	 * @param entityMap
	 * @param values
	 * @return list
	 */
	public List findBySql(final String sql, final LinkedHashMap<String,Class> entityMap,
			final Object[] values)throws Exception {
		Assert.hasText(sql);
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				if(entityMap!=null){
					Object[] keys = entityMap.keySet().toArray();
					String key = null;
					for (int i = 0; i < keys.length; i++) {
						key = (String)keys[i];
						query.addEntity(key, entityMap.get(key));
					}
				}
				return query.list();
			}
		});
		return list;
	}
	
	/**
	 * created by chenjin 2013-08-07
	 * update Object 
	 * 更新实体对象,如果对象属性为空,则不做处理
	 */
	public void update(Object entity){
		//传入对象不能为空
		Assert.notNull(entity,"entity is required!");
		Class classType = entity.getClass();
		StringBuffer sqlbuf = new StringBuffer(" update ");
		Table   tableAnno   = (Table)classType.getAnnotation(Table.class);
		Assert.notNull(tableAnno, "the table anno is required!");
		sqlbuf.append(tableAnno.name()).append(" set ");
		Field[] fields = classType.getDeclaredFields();
		if(fields!=null){
//			List args = new ArrayList();
			for(int i=0,length=fields.length; i<length; i++){
				Column columnAnno = fields[i].getAnnotation(Column.class);
				if(columnAnno==null){continue;}
			}
		}
	}
	
	/**
	 * created by liuyatao 2014年12月3日
	 * update Object 
	 * 更新实体对象,如果对象属性为空,则不做处理
	 * map 为 主键信息
	 */
	public void update(Object entity,Map map){
		/*
		//传入对象不能为空
		Assert.notNull(entity,"entity is required!");
		Class classType = entity.getClass();
		StringBuffer sqlbuf = new StringBuffer(" update ");
		Table   tableAnno   = (Table)classType.getAnnotation(Table.class);
		Assert.notNull(tableAnno, "the table anno is required!");
		sqlbuf.append(tableAnno.name()).append(" set ");
		Field[] fields = classType.getDeclaredFields();
		if(fields!=null){
//			List args = new ArrayList();
			for(int i=0,length=fields.length; i<length; i++){
				Column columnAnno = fields[i].getAnnotation(Column.class);
				if(columnAnno==null){continue;}
			}
		}
		*/
		//调用生成update sql 的方法
		String sql =  CreateSqlTools.generateUpdateSql(entity, map);
		execute(sql, null);
		logger.info(sql.toString());
	}
	
	
	
	/**
	 * 执行原生的SQL语句
	 * @param exeSQL
	 * @param params
	 */
	public void execute(final String exeSQL,final Object[] params){
		
		Assert.notNull(exeSQL,"sql statement is required!");
		
		String newSQL = exeSQL;
		int pos = 0;
		if (params != null){
			for (int i = 0; i < params.length; ++i) {
				pos = newSQL.indexOf(63, pos);
				if (pos == -1){
					break;
				}
				if ((params[i] instanceof Collection) && (pos > -1)) {
					newSQL = newSQL.substring(0, pos) + ":queryParam" + i
							+ newSQL.substring(pos + 1);
				}
				pos += 1;
			}
		}
		final String fnSQL = newSQL;
		
		try{
			this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							SQLQuery query = session.createSQLQuery(fnSQL);
							if(params!=null){
								for (int i = 0; i < params.length; i++) {
									if (params[i] instanceof Collection) {
										query.setParameterList("queryParam" + i,(Collection) params[i]);
									} else {
										query.setParameter(i, params[i]);
									}
								}
							}
							int result = query.executeUpdate();
							return result;
						}
					});
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 多表查询返回多表的关联数据不装载成Page对象返回
	 * @param _querySQL
	 *            查询SQL语句
	 * @param _className
	 *            需要实现的VO对象
	 * @return
	 * @return Map结果对象
	 */
	public List<?> findListBySql(
			final String querySQL, 			// 预处理语句
			final Class<?> _className, 		// 自动装载VO对象
			final Object... paramValue) 	// 预处理语句所对应的参数值
			throws Exception {
		String newHql = querySQL;
		int pos = 0;
		if (paramValue != null){
			for (int i = 0; i < paramValue.length; ++i) {
				pos = newHql.indexOf(63, pos);
				if (pos == -1){
					break;
				}
				if ((paramValue[i] instanceof Collection) && (pos > -1)) {
					newHql = newHql.substring(0, pos) + ":queryParam" + i
							+ newHql.substring(pos + 1);
				}
				pos += 1;
			}
		}
		final String fnHql = newHql;
		// 查询分页数据
		List<Map> resultList = null;
		try{
			resultList = (List<Map>) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session _session)
								throws HibernateException, SQLException {
							SQLQuery _query = _session.createSQLQuery(fnHql);
							// 设置参数
							if(paramValue!=null){
								for (int index = 0; index < paramValue.length; index++) {
									if (paramValue[index] instanceof Collection) {
										_query.setParameterList("queryParam" + index,
												(Collection) paramValue[index]);
									} else {
										_query.setParameter(index, paramValue[index]);
									}
								}
							}
							List list = _query.setResultTransformer(
									Transformers.ALIAS_TO_ENTITY_MAP).list();
							return list;
						}
					});
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		List _resultList = null;
		if (resultList != null) {
			Object _object = null;
			_resultList = new ArrayList();
			Field[] _fields = _className.getDeclaredFields(); // 获取类的属性集合
			for(Map<String,Object> map : resultList){
				_object = _className.newInstance();
				for(Field field : _fields){
					if(field.getType().getClassLoader()!=null){
						//自定义类
						Object obj = field.getType().newInstance();			//上级菜单新建类
						Field[] tmpFields = field.getType().getDeclaredFields();
						for(Field tmpField : tmpFields){
							for(String key : map.keySet()){
								if(tmpField.getName().equalsIgnoreCase(key)){
									tmpField.setAccessible(true);
									tmpField.set(obj, map.get(key));
									break;
								}
							}
						}
						field.setAccessible(true);
						field.set(_object, obj);
					} else {
						for(String key : map.keySet()){
							if(field.getName().equalsIgnoreCase(key)){
								field.setAccessible(true);
								field.set(_object, map.get(key));
								break;
							}
						}
					}
				}
				_resultList.add(_object);
			}
		}
		
		return _resultList;
	}
	
	
	
	
	
	
	
	

	/**
	 * 多表查询返回多表的关联数据
	 * 
	 * @param _querySQL
	 *            查询SQL语句
	 * @param _className
	 *            需要实现的VO对象
	 * @return
	 * @return Map结果对象
	 */
	private Page findMapPageBySql(
			final String querySQL, 			// 预处理语句
			final Class<?> _className, 		// 自动装载VO对象
			final Integer page, 			// 查询当前页码
			final Integer pageSize, 		// 查询当前数据条数
			final Object... paramValue) 	// 预处理语句所对应的参数值
			throws Exception {
		String newHql = querySQL;
		int pos = 0;
		if (paramValue != null){
			for (int i = 0; i < paramValue.length; ++i) {
				pos = newHql.indexOf(63, pos);
				if (pos == -1){
					break;
				}
				if ((paramValue[i] instanceof Collection) && (pos > -1)) {
					newHql = newHql.substring(0, pos) + ":queryParam" + i
							+ newHql.substring(pos + 1);
				}
				pos += 1;
			}
		}
		final String fnHql = newHql;
		int startIndex = Page.getStartOfPage(page.intValue(),
				pageSize.intValue());
		if (startIndex < 0) {
			return new Page();
		}
		// 查询分页数据
		List<Map> resultList = null;
		try{
		resultList = (List<Map>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session _session)
							throws HibernateException, SQLException {
						SQLQuery _query = _session.createSQLQuery(fnHql);
						// 设置参数
						if(paramValue!=null){
							for (int index = 0; index < paramValue.length; index++) {
								if (paramValue[index] instanceof Collection) {
									_query.setParameterList("queryParam" + index,
											(Collection) paramValue[index]);
								} else {
									_query.setParameter(index, paramValue[index]);
								}
							}
						}
						_query.setFirstResult((page - 1) * pageSize);
						_query.setMaxResults(pageSize);
						List list = _query.setResultTransformer(
								Transformers.ALIAS_TO_ENTITY_MAP).list();
						return list;
					}
				});
		}catch(Exception ex){
			ex.printStackTrace();
		}
		// 查询数据总数
		String modifyHql = null; // 查询SQL
		boolean isIncludeDistinctFlag = isIncludeDistinct(fnHql);
		if (isIncludeDistinctFlag) {
			modifyHql = getDistinctCountHql(fnHql)
					+ removeSelect(removeOrders(fnHql) );
		} else {
//			modifyHql = " select count(1) " + removeSelect(removeOrders(fnHql));
			String  tempHql = removeSelect(fnHql) ;
			if(tempHql.startsWith("from (")&&!tempHql.endsWith("tt")){
				modifyHql = " select count(1) " + removeSelect(fnHql) + " tt";
			}else{
				modifyHql = " select count(1) " + removeSelect(fnHql);
			}
		}

		final String countQueryString = modifyHql;
		List countList = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session _session)
							throws SQLException {
						SQLQuery _query = _session
								.createSQLQuery(countQueryString);
						if (paramValue != null){
							for (int index = 0; index < paramValue.length; index++) {
								if (paramValue[index] instanceof Collection) {
									_query.setParameterList("queryParam"
											+ index,
											(Collection) paramValue[index]);
								} else {
									_query.setParameter(index,
											paramValue[index]);
								}
							}
						}
						return _query.list();
					}
				});
		
		if(isIncludeDistinct(fnHql)){
			return new Page(startIndex, Integer.parseInt(String.valueOf((countList==null||countList.size()==0)?0:countList.get(0))), pageSize.intValue(),resultList);
		}else {
			return new Page(startIndex, Long.parseLong(String.valueOf((countList==null||countList.size()==0)?0:countList.get(0))), pageSize.intValue(),resultList);
		}
//		return new Page(startIndex, countList.size(), pageSize.intValue(),
//				resultList);
	}
	

	public void saveWithOutSelect(final Object obj)
	{
		this.getHibernateTemplate().save(obj);
	}
	/**
	 * 根据提供的前缀名，序列名以及所需长度，生成指定格式序列
	 * 例：newcontactId = this.commonDao.generateID("MCCO"+ToolsUtils.getCurrentDate("yyyy"), "mc_seq_Comm",15);
	 * 将会生成类似一串这样的字符：MCCO201300001
	 * @param prefix 前缀
	 * @param seqName 序列名
	 * @param length  长度
	 * @return
	 */
	public String generateID(String prefix,String seqName,int length)
	{
		StringBuffer sb  = new StringBuffer();
		sb.append("select  fn_getsequence('");
		sb.append(prefix);
		sb.append("','");
		sb.append(seqName);
		sb.append("',");
		sb.append(length);
		sb.append(") from dual where 1=? ");
//		String sql = "select fn_getsequence('"+prefix+"','"+seqName+"',"+length+") from dual";
		System.err.println("sql:"+sb);
		String result = this.findBySql(sb.toString(), new Object[]{1}).get(0).toString();
		//替换认字编号前四位为当前年份
		Calendar a=Calendar.getInstance();
		result = a.get(Calendar.YEAR)+result.substring(4, result.length());
		return result ;
	}
	

	public void saveListWithOutSelect(List entities)
	{
		if(null!=entities)
		{
			for (Iterator it = entities.iterator(); it.hasNext();) 
			{
		          this.saveWithOutSelect(it.next());
		    }
		}
		 
	}
	
	public List<?> findBySql(final String sql, final Class<?> clazz,
			final Object... values) {
		Assert.hasText(sql);
		List<?> list = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						List<Map<String, Object>> list = query.list();
						List<Object> resultList = new java.util.ArrayList(list
								.size());
						Field[] fields = clazz.getDeclaredFields();
						Field[] idFields = null;
						Object idObject = null;
						Class idClazz = null;
						for (Map<String, Object> o : list) {
							try {
								Object obj = clazz.newInstance();
								for (Field f : fields) {
									if(f.getName().startsWith("ALIAS_")||f.getName().equals("serialVersionUID")) {
										continue;
									}
									if(f.getName().equalsIgnoreCase("id")) {
										
										idClazz = f.getType();
										idObject = idClazz.newInstance();
										idFields = idClazz.getDeclaredFields();
										for(Field idf: idFields) {
											for (String key : o.keySet()) {
												if(idf.getName().equalsIgnoreCase(key)) {
													idf.setAccessible(true);
													idf.set(idObject, o.get(key));
													f.setAccessible(true);
													f.set(obj, idObject);
													break;
												}
											}
										}
									}else {
										for (String key : o.keySet()) {
											if (f.getName().equalsIgnoreCase(key)) {
												f.setAccessible(true);
												f.set(obj, o.get(key));
												break;
											}
										}
									}
								}
								resultList.add(obj);
							} catch (InstantiationException e) {
								logger.error("", e);
							} catch (IllegalAccessException e) {
								logger.error("", e);
							}
						}
						return resultList;
					}
				});
		return list;
	}
	/**
	 * 返回mapList类型
	 * add by liuyatao 2014年5月20日
	 * @param sql
	 * @param clazz
	 * @param values
	 * @return
	 */
	public List<Map<String, Object>> findMapListBySql(final String sql,
			final Object... values) {
		Assert.hasText(sql);
		List<Map<String, Object>> mapList = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						List<Map<String, Object>> list = query.list();
						return list;
					}
				});
		return mapList;
	}
	
	
	/**
	 * 分页查询数据结果集合(只查询满足条件的数据,不仅Count查询)
	 * @param sql					预查询SQL语句
	 * @param clazz				查询结果需要返回的封装VO对象
	 * @param values			预查询SQL递交的查询参数
	 * @return						满足条件结果集合
	 * 2013-11-27下午5:31:19
	 * jiangweiyang
	 */
	public List<?> findBySql(final String sql, final Class<?> clazz,Integer pageNo,Integer pageSize,
			final Object... values) {
		Assert.hasText(sql);
		final int startIndex = Page.getStartOfPage(pageNo.intValue(), pageSize.intValue());
		final int realPageSize = pageSize.intValue();
		List<?> list = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						query.setFirstResult(startIndex);
						query.setMaxResults(realPageSize);
						List<Map<String, Object>> list = query.list();
						List<Object> resultList = new java.util.ArrayList(list
								.size());
						Field[] fields = clazz.getDeclaredFields();
						for (Map<String, Object> o : list) {
							try {
								Object obj = clazz.newInstance();
								for (Field f : fields) {
									for (String key : o.keySet()) {
										if (f.getName().equalsIgnoreCase(key)) {
											f.setAccessible(true);
											f.set(obj, o.get(key));
											break;
										}
									}
								}
								resultList.add(obj);
							} catch (InstantiationException e) {
								logger.error("", e);
							} catch (IllegalAccessException e) {
								logger.error("", e);
							}
						}
						return resultList;
					}
				});
		return list;
	}
	
	
	
	/**
	 * 根据预查询SQL语句及查询参数返回满足条件的数据集合总数
	 * @param sql							预查询SQL语句
	 * @param paramValue			查询递交的参数数据
	 * @return								满足条件的数据集合总数
	 * @throws Exception				程序运行过程中抛出的异常信息
	 * 2013-11-29上午9:07:26
	 * jiangweiyang
	 */
	public long getCountBySQL(String sql,final Object[] paramValue) throws Exception {
		String modifyHql = null; // 查询SQL
		boolean isIncludeDistinctFlag = isIncludeDistinct(sql);
		if (isIncludeDistinctFlag) {
			modifyHql = getDistinctCountHql(sql)
					+ removeSelect(removeOrders(sql));
		} else {
			modifyHql = " select count(1) " + removeSelect(sql) ;
		}
		final String countQueryString = modifyHql;
		List countList = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session _session)
							throws SQLException {
						SQLQuery _query = _session
								.createSQLQuery(countQueryString);
						if (paramValue != null){
							for (int index = 0; index < paramValue.length; index++) {
								if (paramValue[index] instanceof Collection) {
									_query.setParameterList("queryParam"
											+ index,
											(Collection) paramValue[index]);
								} else {
									_query.setParameter(index,
											paramValue[index]);
								}
							}
						}
						return _query.list();
					}
				});
		return (Long.parseLong(String.valueOf((countList==null||countList.size()==0)?0:countList.get(0))));
	}
	
	
}
