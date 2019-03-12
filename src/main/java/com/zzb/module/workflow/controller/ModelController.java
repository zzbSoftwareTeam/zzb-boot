package com.zzb.module.workflow.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zzb.common.bean.Result;

@Controller
@RequestMapping("/model")
public class ModelController {

    @Autowired
    ProcessEngine processEngine;
    @Autowired
    ObjectMapper objectMapper;

    
    /**
     * 新建一个空模型
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(path = "/add", method = RequestMethod.GET)  
    public String newModel() throws UnsupportedEncodingException {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //初始化一个空模型
        Model model = repositoryService.newModel();

        //设置一些默认信息
        String name = "new-process";
        String key = "process";
        String description = "";
        int revision = 1;

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        String id = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace",
                "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));
       // return ToWeb.buildResult().redirectUrl("/modeler.html?modelId="+id);
        return "redirect:/third/activiti/modeler.html?modelId="+id;
    }

    /**
     * 获取所有模型
     * @return
     */
    @GetMapping
    public String modelList(){
        String res = "";
        try {
        	RepositoryService repositoryService = processEngine.getRepositoryService();
        	List<Model> models = repositoryService.createModelQuery().list();
        	ObjectMapper mapper = new ObjectMapper();
        	res = mapper.writeValueAsString(models);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return res;
    }

    /**
     * 删除模型
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result deleteModel(@PathVariable("id")String id){
    	try {
    		RepositoryService repositoryService = processEngine.getRepositoryService();
    		repositoryService.deleteModel(id);
    		return Result.success();
    	} catch (Exception e) {
			e.printStackTrace();
		}
        return Result.error();
    }

    /**
     * 发布模型为流程定义
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("{id}/deployment")
    public Result deploy(@PathVariable("id")String id) throws Exception {
    	try {
    		//获取模型
	        RepositoryService repositoryService = processEngine.getRepositoryService();
	        Model modelData = repositoryService.getModel(id);
	        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
	
	        if (bytes == null) {
	        	return Result.error("模型数据为空，请先设计流程并成功保存，再进行发布。");
	        }
	
	        JsonNode modelNode = new ObjectMapper().readTree(bytes);
	        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
	        if(model.getProcesses().size()==0){
	        	return Result.error("数据模型不符要求，请至少设计一条主线流程。");
	        }
	        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
	
	        //发布流程
	        String processName = modelData.getName() + ".bpmn20.xml";
	        Deployment deployment = repositoryService.createDeployment()
	                .name(modelData.getName())
	                .addString(processName, new String(bpmnBytes, "UTF-8"))
	                .deploy();
	        modelData.setDeploymentId(deployment.getId());
	        repositoryService.saveModel(modelData);
	        return Result.success();
    	} catch (Exception e) {
			e.printStackTrace();
		}
        return Result.error();
    }
}