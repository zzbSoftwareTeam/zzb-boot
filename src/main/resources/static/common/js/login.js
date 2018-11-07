var vue = new Vue({
	el: "#app",
	data: {
		userName:'zzb',
		userPass:'123'
	},
	methods: {
		login : function(){
			var obj = new URLSearchParams();
			obj.append('userName', this.userName);
			obj.append('userPass', this.userPass);
			axios({
				method: 'post',
	      		url: '/tologin',
	      		data:obj,
	      		headers: {
	            	'Content-Type': 'application/x-www-form-urlencoded'
	            }
	    	}).then(function (response) {
				if(response.data=="success"){
	        		window.location.href="/index";
	        	}else{
	        		vue.$message({
			          showClose: true,
			          message: response.data,
			          type: 'error',
			          customClass:'messagels'
			        });
	        	}
		  	}).catch(function (error) {
		    	vue.$message({
			          showClose: true,
			          message: error,
			          type: 'error'
			        });
			}); 
		}
	}
})