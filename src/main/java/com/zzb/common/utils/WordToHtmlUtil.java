package com.zzb.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

public class WordToHtmlUtil {
	/**
	 * 将word文档转化html
	 * @param sourceFilePath 源DOC
	 * @param targetFilePath 目标HTML
	 * @param saveImagePath 图片保存地址
	 * @param referenceImagePath 图片引用地址
	 */
	public static String docToHtml(String sourceFilePath,String targetFilePath,final String saveImagePath,final String referenceImagePath) {
        try{
    		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFilePath));
	        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
	        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
	        // 保存图片，并返回图片的相对路径
	        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
	            public String savePicture(byte[] content, PictureType pictureType,
	                    String suggestedName, float widthInches, float heightInches) {
	            	FileOutputStream out;
					try {
						out = new FileOutputStream(saveImagePath + suggestedName);
						out.write(content);
					} catch (Exception e) {
						e.printStackTrace();
					}
	                return referenceImagePath + suggestedName;
	            }
	        });
	        wordToHtmlConverter.processDocument(wordDocument);
	        Document htmlDocument = wordToHtmlConverter.getDocument();
	        DOMSource domSource = new DOMSource(htmlDocument);
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        StreamResult streamResult = new StreamResult(out);
	        
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer serializer = tf.newTransformer();
	        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	        serializer.setOutputProperty(OutputKeys.METHOD, "html");
	        serializer.transform(domSource, streamResult);
	        out.close();
	        writeFile(new String(out.toByteArray()), targetFilePath);
	        return "success";
        }catch (Exception e) {
        	return "error";
		}
	}

	public static String docxToHtml(String sourceFilePath,String targetFilePath,final String saveImagePath,final String referenceImagePath){
	    try {
			//判断html文件是否存在
			File htmlFile = new File(targetFilePath);
			//word文件
			File wordFile = new File(sourceFilePath); 
			// 1) 加载word文档生成 XWPFDocument对象 
			InputStream in = new FileInputStream(wordFile); 
			XWPFDocument document = new XWPFDocument(in); 
 
			// 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录) 
			File imgFolder = new File(saveImagePath);
			XHTMLOptions options = XHTMLOptions.create();
			options.setExtractor(new FileImageExtractor(imgFolder));
			//html中图片的路径 相对路径 
			options.URIResolver(new BasicURIResolver("image"));
			options.setIgnoreStylesIfUnused(false); 
			options.setFragment(true); 
			 
			// 3) 将 XWPFDocument转换成XHTML
			OutputStream out = new FileOutputStream(htmlFile); 
			XHTMLConverter.getInstance().convert(document, out, options);
			return "success";
			
		} catch (Exception e) {
		    return "error"; 
		}
	    
    }
	
	private static void writeFile(String content, String path) throws Exception {
		FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (bw != null)
                bw.close();
            if (fos != null)
                fos.close();
        }
    }
}
