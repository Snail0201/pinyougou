app.service("brandService",function($http){
			//添加服务
			this.add=function(entity){
				return $http.post("/brand/add.do",entity);
			}
			//查询一个，数据回显
			this.findOne=function(id){
				return $http.get("/brand/findOne.do?id="+id);
			}
			
			
			//更新
			this.update=function(entity){
				return $http.post("/brand/update.do",entity)
			}
			
			
			//删除选中
			this.del=function(selectIds){
				return $http.get("/brand/del.do?ids="+selectIds)
			}
			
			//模糊查询
			this.findLike=function(currentPage,itemsPerPage,likeEntity){
				return $http.post('/brand/findCondition.do?page='+currentPage+'&rows='+itemsPerPage,likeEntity)
			}
			
			
			
		});