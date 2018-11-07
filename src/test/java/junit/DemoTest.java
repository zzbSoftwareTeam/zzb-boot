package junit;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.core.IURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.w3c.dom.Document;

import com.zzb.BootApplication;
import com.zzb.common.utils.CodecUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
@WebAppConfiguration
public class DemoTest {
	
	
	@Test
	public void text1(){
		for (int i = 0; i < 10; i++) {
			System.out.println(new BCryptPasswordEncoder().encode("123"));
		}
	}
	
	public static void convert2Html(String fileName, String outPutFilePath,String newFileName)    
            throws TransformerException, IOException,    
            ParserConfigurationException {
    	String substring = fileName.substring(fileName.lastIndexOf(".")+1);
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	
    	/**
    	 * word2007和word2003的构建方式不同，
    	 * 前者的构建方式是xml，后者的构建方式是dom树。
    	 * 文件的后缀也不同，前者后缀为.docx，后者后缀为.doc
    	 * 相应的，apache.poi提供了不同的实现类。
    	 */
    	if("docx".equals(substring)){
//    		writeFile(new String("<html><head>  <meta http-equiv=\"content-type\" content=\"text/html\" charset=\"utf-8\"/></head>对不起，.docx格式的word文档，暂时不能生成预览</html>".getBytes("utf-8")), outPutFilePath+newFileName); 
    		
    		//step 1 : load DOCX into XWPFDocument
    		InputStream inputStream = new FileInputStream(new File(fileName));
    		XWPFDocument document = new XWPFDocument(inputStream);
    		
    		//step 2 : prepare XHTML options
    		final String imageUrl = "";
    		
    		XHTMLOptions options = XHTMLOptions.create();
    		options.setExtractor(new FileImageExtractor(new File(outPutFilePath + imageUrl)));
    		options.setIgnoreStylesIfUnused(false);
    		options.setFragment(true);
    		options.URIResolver(new IURIResolver() {
    			@Override
    			public String resolve(String uri) {
    				return imageUrl + uri;
    			}
    		});
    		
    		//step 3 : convert XWPFDocument to XHTML
    		XHTMLConverter.getInstance().convert(document, out, options);
    	}else{
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));    
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(    
                DocumentBuilderFactory.newInstance().newDocumentBuilder()    
                        .newDocument());    
         wordToHtmlConverter.setPicturesManager( new PicturesManager()    
         {    
             public String savePicture( byte[] content,    
                     PictureType pictureType, String suggestedName,    
                     float widthInches, float heightInches )    
             {    
                 return suggestedName;    
             }    
         } );    
        wordToHtmlConverter.processDocument(wordDocument);    
        //save pictures    
        List pics=wordDocument.getPicturesTable().getAllPictures();    
        if(pics!=null){    
            for(int i=0;i<pics.size();i++){    
                Picture pic = (Picture)pics.get(i);    
                System.out.println();    
                try {    
                    pic.writeImageContent(new FileOutputStream(outPutFilePath    
                            + pic.suggestFullFileName()));    
                } catch (FileNotFoundException e) {    
                    e.printStackTrace();    
                }      
            }    
        }    
        Document htmlDocument = wordToHtmlConverter.getDocument();    
        DOMSource domSource = new DOMSource(htmlDocument);    
        StreamResult streamResult = new StreamResult(out);    
    
        TransformerFactory tf = TransformerFactory.newInstance();    //这个应该是转换成xml的
        Transformer serializer = tf.newTransformer();    
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");    
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");    
        serializer.setOutputProperty(OutputKeys.METHOD, "html");    
        serializer.transform(domSource, streamResult);    
    	}    
    	
    	out.close();    
    	writeFile(new String(out.toByteArray()), outPutFilePath+newFileName);    
    }
	
	public static void writeFile(String content, String path) {    
        FileOutputStream fos = null;    
        BufferedWriter bw = null;
        try {    
            File file = new File(path);
            if(!file.exists()){
            	
            }
            fos = new FileOutputStream(file);    
            bw = new BufferedWriter(new OutputStreamWriter(fos));    
            bw.write(content);  
        } catch (FileNotFoundException fnfe) {    
            fnfe.printStackTrace();    
        } catch (IOException ioe) {    
            ioe.printStackTrace();    
        } finally {    
            try {    
                if (bw != null)    
                    bw.close();    
                if (fos != null)    
                    fos.close();    
            } catch (IOException ie) {    
            }    
        }    
    }
}
