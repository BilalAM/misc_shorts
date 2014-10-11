package com.two;

import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UserAgentScraper {
	
	private static final String urls = "http://www.useragentstring.com/pages/Browserlist/";
	private static final String mobileUrl = "http://www.useragentstring.com/pages/Mobile%20Browserlist/";
	
	public static void main(String[] args) {
		PrintWriter pw = null;
        try {
        	pw = new PrintWriter("user_agents_mobile.txt");
			Document doc = Jsoup.connect(mobileUrl).userAgent("Mozilla").timeout(0).maxBodySize(0).get();
			Elements hrefLinks = doc.getElementsByTag("li");
			System.out.println(hrefLinks.size());			
			for( Element elem : hrefLinks ){
				pw.write(elem.text()+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( pw != null ){
				pw.close();
			}
		}
		
	}

}
