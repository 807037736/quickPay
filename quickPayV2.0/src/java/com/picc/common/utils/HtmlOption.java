/**
 * 作者 卢斌晖
 * 2014-04-21
 */
package com.picc.common.utils;

import java.util.Iterator;
import java.util.List;

public class HtmlOption 
{
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public HtmlOption(String name, String value)
    {
        this.name = "";
        this.value = "-1";
        if(null == value || value.length() < 1 || value.equalsIgnoreCase("-1"))
        {
            this.name = name;
            this.value = "-1";
        } else
        {
            this.name = name;
            this.value = value;
        }
    }

    public static HtmlOption[] getBlankHtmlOption()
    {
        HtmlOption beanList[] = new HtmlOption[1];
        HtmlOption bean = new HtmlOption("", "");
        beanList[0] = bean;
        return beanList;
    }

    public static HtmlOption[] stringArray2HtmlOptions(String sourceValues[])
    {
        return stringArray2HtmlOptionOptions(sourceValues, false);
    }

    public static HtmlOption[] stringArray2HtmlOptionOptions(String sourceValues[], boolean isAddBlank)
    {
        HtmlOption htmlOptionBean[] = null;
        HtmlOption htmlBean = null;
        if(sourceValues == null || sourceValues.length == 0)
        {
            htmlOptionBean = getBlankHtmlOption();
        } else
        {
            int index = 0;
            if(isAddBlank)
            {
                htmlOptionBean = new HtmlOption[sourceValues.length + 1];
                htmlOptionBean[0] = new HtmlOption("", "");
                index++;
            } else
            {
                htmlOptionBean = new HtmlOption[sourceValues.length];
            }
            String value = "";
            for(int i = 0; i < sourceValues.length; i++)
            {
                value = sourceValues[i];
                htmlBean = new HtmlOption(value, value);
                htmlOptionBean[index] = htmlBean;
                index++;
            }

        }
        return htmlOptionBean;
    }

    public static HtmlOption[] optionList2HtmlOptions(List sourceList, String preString)
    {
        if(null == sourceList || sourceList.isEmpty())
            return getBlankHtmlOption();
        HtmlOption options[] = new HtmlOption[sourceList.size()];
        String optionValue[] = null;
        for(int i = 0; i < sourceList.size(); i++)
        {
            optionValue = (String[])sourceList.get(i);
            options[i] = new HtmlOption(optionValue[0], preString + optionValue[1]);
        }

        return options;
    }

    public static HtmlOption[] optionList2HtmlOptions(List sourceList)
    {
        if(null == sourceList || sourceList.isEmpty())
            return getBlankHtmlOption();
        HtmlOption options[] = new HtmlOption[sourceList.size()];
        for(int i = 0; i < sourceList.size(); i++)
            options[i] = (HtmlOption)sourceList.get(i);

        return options;
    }

    public static HtmlOption[] valueList2HtmlOptions(List sourceValues)
    {
        return valueList2HtmlOptions(sourceValues, "");
    }

    public static HtmlOption[] valueList2HtmlOptions(List sourceValues, String preString)
    {
        HtmlOption htmlOptionBean[] = null;
        HtmlOption htmlBean = null;
        if(sourceValues == null || sourceValues.isEmpty())
        {
            htmlOptionBean = getBlankHtmlOption();
        } else
        {
            int index = 0;
            htmlOptionBean = new HtmlOption[sourceValues.size()];
            String value = "";
            for(Iterator iter = sourceValues.iterator(); iter.hasNext();)
            {
                value = (String)iter.next();
                htmlBean = new HtmlOption(value, preString + value);
                htmlOptionBean[index] = htmlBean;
                index++;
            }

        }
        return htmlOptionBean;
    }

    private String name;
    private String value;

}
