package com.zzb.common.service.nlp;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

/**
 * 
 * ClassName: HanLPService 
 * @Description: TODO 汉词语法分析
 * @author zengzhibin
 * @date 2018年7月27日
 */
@Service
public class HanLPService {
	
	private Logger log =  LoggerFactory.getLogger(this.getClass());
	
	private static final String STANDARD = "standard";// 标准分词
	private static final String NLP = "nlp";// NLP分词
	private static final String INDEX = "index";// 索引分词
	private static final String CRF = "crf";// CRF分词
	private static final String SPEED = "speed";// 极速词典分词
	
	public List<Term> segment(String type,String text){
		if(StringUtils.isNotBlank(text)){
			if(StringUtils.contains(type, STANDARD)){
				return StandardTokenizer.segment(text);
			}else if(StringUtils.contains(type, NLP)){
				return NLPTokenizer.segment(text);
			}else if(StringUtils.contains(type, INDEX)){
				return IndexTokenizer.segment(text);
			}else if(StringUtils.contains(type, CRF)){
				Segment segment;
				try {
					segment = new CRFLexicalAnalyzer();
					segment.enablePartOfSpeechTagging(true);
					return segment.seg(text);
				} catch (IOException e) {
					log.error("CRFSegment构造异常");
				}
			}else if(StringUtils.contains(type, SPEED)){
				return SpeedTokenizer.segment(text);
			}else{
				return StandardTokenizer.segment(text);
			}
		}else{
			return StandardTokenizer.segment(text);
		}
		return null;
	}
}
