package com.zzb.common.bean;

import java.util.List;
import java.util.Map;
@SuppressWarnings("rawtypes")
public class AjaxResponse {
	   
		private MessageType messageType;
		
		private String message;
		
		private List list;
		
		private Map map;
		
		private String code;
		
		private Object obj;
		
		public AjaxResponse() {
			super();
		}



		/**
		 * @param messageType
		 * @param message
		 * @param code
		 */
		public AjaxResponse(MessageType messageType, String message,String code) {
			super();
			this.messageType = messageType;
			this.message = message;
			this.code = code;
		}
		
		
		
		/**
		 * @param messageType
		 * @param message
		 */
		public AjaxResponse(MessageType messageType, String message) {
			super();
			this.messageType = messageType;
			this.message = message;
		}

		/**
		 * @param messageType
		 * @param message
		 * @param list
		 */
		public AjaxResponse(MessageType messageType, String message, List list) {
			super();
			this.messageType = messageType;
			this.message = message;
			this.list = list;
		}
		
		public AjaxResponse(MessageType messageType, String message, Object obj) {
			super();
			this.messageType = messageType;
			this.message = message;
			this.obj = obj;
		}

		/**
		 * @param messageType
		 * @param message
		 * @param map
		 */
		public AjaxResponse(MessageType messageType, String message, Map map) {
			super();
			this.messageType = messageType;
			this.message = message;
			this.map = map;
		}

		/**
		 * @param messageType
		 * @param message
		 * @param list
		 * @param map
		 */
		public AjaxResponse(MessageType messageType, String message, List list, Map map) {
			super();
			this.messageType = messageType;
			this.message = message;
			this.list = list;
			this.map = map;
		}

		/**
		 * @param messageType
		 * @param message
		 * @param code
		 * @param list
		 * @param map
		 */
		public AjaxResponse(MessageType messageType, String message,String code, List list, Map map){
			super();
			this.messageType = messageType;
			this.message = message;
			this.code = code;
			this.list = list;
			this.map = map;
		}
		
		public MessageType getMessageType() {
			return messageType;
		}

		public AjaxResponse setMessageType(MessageType messageType) {
			this.messageType = messageType;
			return this;
		}

		public String getMessage() {
			return message;
		}
		public AjaxResponse setMessage(String message) {
			this.message = message;
			return this;
		}
		

		
		public List getList() {
			return list;
		}

		public AjaxResponse setList(List list) {
			this.list = list;
			return this;
		}

		public Map getMap() {
			return map;
		}

		public AjaxResponse setMap(Map map) {
			this.map = map;
			return this;
		}

		

		public String getCode() {
			return code;
		}



		public AjaxResponse setCode(String code) {
			this.code = code;
			return this;
		}

		
	    public Object getObj() {
			return obj;
		}
	    
	    public AjaxResponse setObj(Object obj) {
			this.obj = obj;
			return this;
		}


		public static enum MessageType {
			INFO(6), SUCCESS(1),WARNING(0),FAILING(5),ERROR(2),LOCK(4),ASK(3);
			
			//INFO, SUCCESS,WARNING,FAILING,ERROR,LOCK,ASK;
			private int messageType;
			
			MessageType(){
				
			}
			
			MessageType(int messageType) {
				this.messageType=messageType;
			}

			public int getMessageType() {
				return messageType;
			}

			public void setMessageType(int messageType) {
				this.messageType = messageType;
			}

			@Override
			public String toString() {
				return String.valueOf(messageType);
			}
		}



		@Override
		public String toString() {
			return "AjaxResponse [messageType=" + messageType + ", message=" + message + ", list=" + list + ", map=" + map
					+ "]";
		}
		
		
		
		
}
