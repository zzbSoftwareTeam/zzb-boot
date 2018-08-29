package com.zzb.module.push.controller.vo;

import java.util.Map;

public class WxinMessageVo {
	private String topcolor="#eeca00";
	private String touser;
	private String template_id="ZoiCTEDk1nZ24T_dovMndNKK8jo5JHoTSqyJwpVBgtM";
	private String url;
	private Map<String,Map<String,String>> data;
	
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, Map<String, String>> getData() {
		return data;
	}
	public void setData(Map<String, Map<String, String>> data) {
		this.data = data;
	}
	
	
}
/*"touser":"OPENID",
"template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
"url":"http://weixin.qq.com/download",  
"data":{
        "first": {
            "value":"恭喜你购买成功！",
            "color":"#173177"
        },
        "keynote1":{
            "value":"巧克力",
            "color":"#173177"
        },
        "keynote2": {
            "value":"39.8元",
            "color":"#173177"
        },
        "keynote3": {
            "value":"2014年9月22日",
            "color":"#173177"
        },
        "remark":{
            "value":"欢迎再次购买！",
            "color":"#173177"
        }
}*/
