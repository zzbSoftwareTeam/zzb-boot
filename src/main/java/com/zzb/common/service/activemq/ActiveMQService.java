package com.zzb.common.service.activemq;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.broker.jmx.TopicViewMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ClassName: ActiveMQService 
 * @Description: TODO 配置好了，直接操作activemq容器
 * @author zengzhibin
 * @date 2018年9月14日
 */
//@Service
public class ActiveMQService {
	
	/** 日志记录 */
	/*private Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	private BrokerViewMBean mBean;
	
	private MBeanServerConnection conn;
	
	public ActiveMQService(String JMX_URL, String BROKER_NAME){
		try {
			JMXServiceURL urls = new JMXServiceURL(JMX_URL);
			JMXConnector connector = JMXConnectorFactory.connect(urls,null);
			connector.connect();
			conn = connector.getMBeanServerConnection();
			ObjectName name = new ObjectName(BROKER_NAME);
			mBean = (BrokerViewMBean)MBeanServerInvocationHandler.
					newProxyInstance(conn, name, BrokerViewMBean.class, true);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
	
	public QueueViewMBean getQueueViewMBean(String queueName) throws Exception{
		for(ObjectName na : mBean.getQueues()){
			QueueViewMBean queueBean = (QueueViewMBean)MBeanServerInvocationHandler
					.newProxyInstance(conn, na, QueueViewMBean.class, true);
			if(queueName.equals(queueBean.getName())){
				return queueBean;
			}
		}
		return null;
	}
	
	public long getQueueSize(String queueName) throws Exception{
		for(ObjectName na : mBean.getQueues()){
			QueueViewMBean queueBean = (QueueViewMBean)MBeanServerInvocationHandler
					.newProxyInstance(conn, na, QueueViewMBean.class, true);
			if(queueName.equals(queueBean.getName())){
				return queueBean.getQueueSize();
			}
		}
		return 0l;
	}
	
	public TopicViewMBean getTopicViewMBean(String queueName) throws Exception{
		for(ObjectName na : mBean.getTopics()){
			TopicViewMBean topicBean = (TopicViewMBean)MBeanServerInvocationHandler
					.newProxyInstance(conn, na, TopicViewMBean.class, true);
			if(queueName.equals(topicBean.getName())){
				return topicBean;
			}
		}
		return null;
	}
	
	public long getTopicSize(String queueName) throws Exception{
		for(ObjectName na : mBean.getTopics()){
			TopicViewMBean topicBean = (TopicViewMBean)MBeanServerInvocationHandler
					.newProxyInstance(conn, na, TopicViewMBean.class, true);
			if(queueName.equals(topicBean.getName())){
				return topicBean.getDequeueCount();
			}
		}
		return 0l;
	}*/
}
