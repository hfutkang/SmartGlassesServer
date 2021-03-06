package com.sctek.smartglasses.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XmlContentHandler extends DefaultHandler {
	
	private final static String TAG = "XmlContentHandler";
	private final static String IMAGE_PREFIX = "http://%s/data/GlassData/photos/";
	private final static String VEDIO_PREFIX = "http://%s/data/GlassData/vedios/";
	
	private String nodeName;
	private ArrayList<MediaData> mediaList;
	private String urlPrefix;
	private String ip;
	
	public XmlContentHandler(String ip) {
		
		this.ip = ip;
		
		mediaList = new ArrayList<MediaData>();
		urlPrefix = String.format(IMAGE_PREFIX, ip);
		
	}
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		nodeName = localName;
		if("vedios".equals(nodeName))
			urlPrefix = String.format(VEDIO_PREFIX, ip);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		if("name".equals(nodeName)) {
			String name = new String(ch, start, length);
			MediaData md = new MediaData();
			
			md.setName(name);
			md.setUrl(urlPrefix + name); 
			mediaList.add(md);
			
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}
	
	public ArrayList<MediaData> getMedias() {
		return mediaList;
	}

}
