app.controller("brandController",function($scope,$controller,brandService){
			
			
			$controller('brandBaseController',{$scope:$scope});
			
			//添加
			$scope.add=function(){
				brandService.add($scope.entity).success(function(response){
					if(response.success){
						$scope.reloadList();
					}else{
						alert(response.messsage);
					}
				
				});
			}
			
			//查询一个，数据回显
			$scope.findOne=function(id){
				brandService.findOne(id).success(function(response){
					$scope.entity=response;
				});
			}
			
			//更新
			$scope.update=function(entity){
				brandService.update(entity).success(function(response){
					if(response.success){
						$scope.reloadList();
					}else{
						alert(response.message);
					}
				})
			}
			
			
			//保存方法
			$scope.save=function(){
				if($scope.entity.id==null){
					$scope.add();//无id信息则添加
				}else{
					$scope.update($scope.entity);
				}
			}
			
			
			//删除的复选框，获取id
			$scope.selectIds=[];
			$scope.addId=function($event,id){
				if($event.target.checked){
					$scope.selectIds.push(id);
				}else{
					//没选中，则将其移除
					var index=$scope.selectIds.indexOf(id);
					$scope.selectIds.splice(index,1);
				}
			}
			
			//删除操作
			/* $scope.del=function(){
				$http.post("/brand/del.do",$scope.selectIds).success(function(response){
					if(response.success){
						$scope.reloadList();
					}else{
						alert(response.message);
					}
				});
			} */
			$scope.del=function(){
				
				if(confirm("确定删除吗")){
					
				
				
				brandService.del($scope.selectIds).success(function(response){
					if(response.success){
						$scope.reloadList();
					}else{
						alert(response.message);
					}
				});
				
				
				}
			}
			
			//初始化，页面一加载不为null
			$scope.likeEntity={};
			//模糊查询函数
			$scope.findLike=function(){
				brandService.findLike($scope.paginationConf.currentPage,
						$scope.paginationConf.itemsPerPage,
						$scope.likeEntity).success(function(response){
					$scope.list=response.rows;
					$scope.paginationConf.totalItems=response.total;
					
				});
			}
			
		});