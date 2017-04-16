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

import com.github.attiand.feedreader.XPathEvaluationException

class XPathEvaluator {

	final XPathExpression expression

	public XPathEvaluator(String expression) {
		System.setProperty("javax.xml.xpath.XPathFactory:" + NamespaceConstant.OBJECT_MODEL_SAXON,
				"net.sf.saxon.xpath.XPathFactoryImpl");
		XPathFactory factory = XPathFactory.newInstance(NamespaceConstant.OBJECT_MODEL_SAXON);
		this.expression = factory.newXPath().compile(expression);
	}

	public boolean match(Document document) {
		try {
			return (Boolean) expression.evaluate(document, XPathConstants.BOOLEAN);
		} catch (XPathFactoryConfigurationException | XPathExpressionException e) {
			throw new XPathEvaluationException("Can't evaluate XPath expression", e);
		}
	}
	
	public String value(Document document) {
		try {
			return (String) expression.evaluate(document, XPathConstants.STRING);
		} catch (XPathFactoryConfigurationException | XPathExpressionException e) {
			throw new XPathEvaluationException("Can't evaluate XPath expression", e);
		}
	}
}
