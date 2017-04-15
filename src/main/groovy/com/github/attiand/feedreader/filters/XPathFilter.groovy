package com.github.attiand.feedreader.filters

import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory
import javax.xml.xpath.XPathFactoryConfigurationException

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList

import net.sf.saxon.lib.NamespaceConstant

import com.github.attiand.feedreader.XmlParseException

class XPathFilter {

	final XPathExpression expression

	public XPathFilter(String expression) {
		System.setProperty("javax.xml.xpath.XPathFactory:" + NamespaceConstant.OBJECT_MODEL_SAXON,
				"net.sf.saxon.xpath.XPathFactoryImpl");
		XPathFactory xPathFactory = XPathFactory.newInstance(NamespaceConstant.OBJECT_MODEL_SAXON);
		XPath xPath = xPathFactory.newXPath();
		this.expression = xPath.compile(expression);
	}

	public boolean elements(Document document) {
		try {
			return (Boolean) expression.evaluate(document, XPathConstants.BOOLEAN);
			//eturn IntStream.range(0, res.getLength()).mapToObj{i -> (Element) res.item(i)};
		} catch (XPathFactoryConfigurationException | XPathExpressionException e) {
			throw new XmlParseException("Can't evaluate Xpath", e);
		}
	}
}
