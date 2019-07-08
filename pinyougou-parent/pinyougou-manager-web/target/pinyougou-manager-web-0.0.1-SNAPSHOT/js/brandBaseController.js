app.controller("brandBaseController",function($scope){
	
	//分页信息
	$scope.paginationConf = {
			currentPage: 1,
			totalItems: 10,
			itemsPerPage: 10,
			perPageOptions: [10, 20, 30, 40, 50],
			onChange: function(){
				$scope.reloadList();//重新加载
			}
	}


	//刷新列表
	$scope.reloadList=function(){
		$scope.findLike();
		//$scope.likeEntity,$scope.paginationConf.currentPage,
		//$scope.paginationConf.itemsPerPage
	}
	
	
});