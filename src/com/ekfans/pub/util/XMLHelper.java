package com.ekfans.pub.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * XML帮助类
 * 
 * @Description:
 * @Copyright: Copyright (c) 2014
 * @Company: ekfans
 * @author Lgy
 * @date 2014-1-29
 * @version 1.0
 */

public class XMLHelper {
	/**
	 * DOCUMENT格式化输出保存为XML
	 * 
	 * @param doc
	 *            JDOM的Document
	 * @param filePath
	 *            输出文件路径
	 * @throws Exception
	 */
	public static void doc2XML(Document doc, String filePath) throws Exception {
		Format format = Format.getCompactFormat();
		format.setEncoding("UTF-8"); // 设置XML文件的字符为UTF-8
		format.setIndent("     ");// 设置缩进

		XMLOutputter outputter = new XMLOutputter(format);// 定义输出
		// ,在元素后换行，每一层元素缩排四格
		FileWriter writer = new FileWriter(filePath);// 输出流
		outputter.output(doc, writer);
		writer.close();
	}

	/**
	 * 字符串转换为DOCUMENT
	 * 
	 * @param xmlStr
	 *            字符串
	 * @return doc JDOM的Document
	 * @throws Exception
	 */
	public static Document string2Doc(String xmlStr) throws Exception {
		java.io.Reader in = new StringReader(xmlStr);
		Document doc = (new SAXBuilder()).build(in);
		return doc;
	}

	/**
	 * Document转换为字符串
	 * 
	 * @param doc
	 * @return xmlStr 字符串
	 * @throws Exception
	 */
	public static String doc2String(Document doc) throws Exception {
		Format format = Format.getPrettyFormat();
		format.setEncoding("UTF-8");// 设置xml文件的字符为UTF-8，解决中文问题
		XMLOutputter xmlout = new XMLOutputter(format);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		xmlout.output(doc, bo);
		return bo.toString();
	}

	/**
	 * XML转换为Document
	 * 
	 * @param xmlFilePath
	 *            XML文件路径
	 * @return doc Document对象
	 * @throws Exception
	 */
	public static Document xml2Doc(String xmlFilePath) throws Exception {
		File file = new File(xmlFilePath);
		return (new SAXBuilder()).build(file);
	}
}