package com.picc.common.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class AppConfig {
	private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);
	  private static Map configMap = Collections.synchronizedMap(new TreeMap());
	  private List configNameTrace;
	  private static boolean init = false;
	  private static final File CONFIG_FILE = FileUtils.getUniqueFile(AppConfig.class, ".config");
	  private static long lastModified = -5499635008970686464L;
	  private static int configFileCount = 0;
	  private static File localConfigFile;

	  public AppConfig()
	  {
	    this.configNameTrace = null;
	  }

	  public static synchronized void configure(String confPath)
	    throws Exception
	  {
	    if (!(init)){
	    	reconfigure(confPath);
	    }
	  }

	  public static synchronized void reconfigure(String confPath)
	    throws Exception
	  {
	    FileUtils.write(confPath, CONFIG_FILE);

	    configMap.clear();
	    File[] confFiles = null;

	    File conf = new File(confPath);
	    if (!(conf.exists())){
	    	return;
	    }

	    confFiles = conf.listFiles(new FilenameFilter()
	    {
	      public boolean accept(File dir, String name) {
	        return (name.toLowerCase().endsWith(".xml"));
	      }

	    });
	    configFileCount = confFiles.length;
	    if (configFileCount == 1) {
	      localConfigFile = confFiles[0];
	      lastModified = localConfigFile.lastModified();
	    }
	    AppConfig appConfig = new AppConfig();
	    for (int i = 0; i < confFiles.length; ) {
	      try {
	        appConfig.iniAppConfig(confFiles[i]);
	        logger.info("Success load Application configuration at \"" + confFiles[i].getPath() + "\".");
	        init = true;
	      } catch (Exception e) {
	        logger.info("Fail load Application configuration at \"" + confFiles[i].getPath() + "\".", e);
	        throw e;
	      }
	      ++i;
	    }

	    init = true;
	  }

	  private void iniAppConfig(File appConfigFile)
	    throws Exception
	  {
	    this.configNameTrace = new ArrayList();
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();

	    Document document = builder.parse(appConfigFile);

	    Node rootNode = document.getDocumentElement();

	    parseNode(rootNode);
	  }

	  private void parseNode(Node node)
	    throws Exception
	  {
	    this.configNameTrace.add(node.getNodeName());

	    NodeList childNodes = node.getChildNodes();
	    Node childNode = null;
	    Node textNode = null;
	    String configName = "";
	    String configValue = "";

	    for (int i = 0; i < childNodes.getLength(); ++i) {
	      childNode = childNodes.item(i);

	      if (childNode.getNodeType() != 1) {
	        continue;
	      }

	      textNode = childNode.getChildNodes().item(0);
	      if ((childNode.getChildNodes().getLength() == 1) && (textNode.getNodeType() == 3)) {
	        configName = "";
	        configValue = "";
	        configName = getConfigName() + childNode.getNodeName();
//	        configName = childNode.getNodeName();
	        configValue = textNode.getNodeValue().trim();
	        configMap.put(configName, configValue);
	      }
	      else
	      {
	        parseNode(childNode);

	        this.configNameTrace.remove(this.configNameTrace.size() - 1);
	      }
	    }
	  }

	  private String getConfigName()
	  {
	    StringBuilder buffer = new StringBuilder();
	    for (int i = 0; i < this.configNameTrace.size(); ++i) {
	      buffer.append((String)this.configNameTrace.get(i));
	      buffer.append('.');
	    }
	    return buffer.toString();
	  }

	  @SuppressWarnings("unused")
	public static String get(String configName)
	  {
	    try
	    {
	      if (init) {
	        if ((configFileCount == 1) && (lastModified < localConfigFile.lastModified())){
	        	reconfigure(localConfigFile.getParentFile().getAbsolutePath());
	        }
	      }
	      else {
	        if ((!(CONFIG_FILE.exists())) && (!(CONFIG_FILE.createNewFile()))){
	        	logger.info("Create file " + CONFIG_FILE.getAbsolutePath() + " fail.");
	        }

	        if (CONFIG_FILE.exists()) {
	          String confPath = FileUtils.read(CONFIG_FILE);
	          if (new File(confPath).exists()){
	        	  configure(confPath);
	          }
	        }
	      }
	    }
	    catch (Exception e) {
	      logger.error(e.getMessage(), e);
	    }
	    return ((String)configMap.get(configName));
	  }

	  public static boolean isInit()
	  {
	    return init;
	  }
}
