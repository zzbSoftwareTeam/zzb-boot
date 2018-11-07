var vue = new Vue({
	el: "#app",
	data: {
		activeIndex:"1",
		tableData: [{
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-02',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-04',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-01',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }]
	},
	methods: {
		handleSelect :function(key, keyPath) {
	        console.log(key, keyPath);
	    },
	    handleOpen :function(key, keyPath) {
	        console.log(key, keyPath);
	    },
	    handleClose :function(key, keyPath) {
	        console.log(key, keyPath);
	    },
	    handleClick :function(row) {
	        console.log(key, keyPath);
	    },
		logout : function(command){
			if(command=="logout"){
				axios({
					method: 'post',
		      		url: '/logout',
		    	}).then(function (response) {
					window.location.href="/login";
			  	}).catch(function (error) {
			    	vue.$message({
				          showClose: true,
				          message: error,
				          type: 'error'
				        });
				}); 
			}
		}
	}
})