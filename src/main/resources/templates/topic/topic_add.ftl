<!DOCTYPE html>
<html lang="zh-CN" xmlns:th="http://www.thymeleaf.org">
	<head>
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0"/>
		 <link rel="stylesheet" type="text/css" href="${urls.getForLookupPath('/css/weui.css')}">
    
    	<script type="text/javascript" src="//code.jquery.com/jquery-2.1.4.min.js"></script>
	</head>
	<script type="text/javascript" >
	$(document).ready(function(){
		  function showDialog(content){
			  var $dialog = $('#dialog');
              $dialog.show();
              $dialog.find(".weui_dialog_bd").html(content);
              $dialog.find('.weui_btn_dialog').one('click', function () {
                  $dialog.hide();
              });
		  }
		  
		  $("form").submit(function(){
		    if( (!$("#title").val()) || (!$("#content").val())){
		    	showDialog("标题或者内容不能为空");
		    	return false;
		    }
		    if($("#title").val().length>20){
		    	showDialog("标题字数超过20");
		    	return false;
		    }
		    if($("#content").val().length>200){
		    	showDialog("内容字数超过200");
		    	return false;
		    }
		    var $loadingToast = $('#loadingToast');
            if ($loadingToast.css('display') != 'none') {
                return false;
            }
            $loadingToast.show();
		 });
	});
	</script>
	<body>
		<form action="" method="POST">
			<div class="weui_cells_title">
				标题
			</div>
			<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_bd weui_cell_primary" >
							<textarea class="weui_textarea" placeholder="请输入标题" rows="1" id="title" name="title"></textarea>
							<div class="weui_textarea_counter"><span>0</span>/20</div>
						</div>
					</div>
			</div>
			<div class="weui_cells_title">
				内容
			</div>
			<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_bd weui_cell_primary" >
							<textarea class="weui_textarea" placeholder="请输入问题" rows="8" id="content" name="content"></textarea>
							<div class="weui_textarea_counter"><span>0</span>/200</div>
						</div>
					</div>
			</div>
	        <!--loading...-->
	        <div id="loadingToast" class="weui_loading_toast" style="display:none;">
			    <div class="weui_mask_transparent"></div>
			    <div class="weui_toast">
			        <div class="weui_loading">
			            <!-- :) -->
			            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
			            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
			        </div>
			        <p class="weui_toast_content">数据加载中</p>
			    </div>
			</div>
			<!--alert-->
			<div class="weui_dialog_alert" id="dialog" style="display: none;">
		        <div class="weui_mask"></div>
		        <div class="weui_dialog">
		            <div class="weui_dialog_hd"><strong class="weui_dialog_title">提醒</strong></div>
		            <div class="weui_dialog_bd"></div>
		            <div class="weui_dialog_ft">
		                <a href="javascript:;" class="weui_btn_dialog primary">确定</a>
		            </div>
		        </div>
		    </div>
		    <div style="position: fixed;bottom:0;width:100%; background-color:white;height:45px">
				<button type="submit" class="weui_btn weui_btn_primary" >提交</button>	
			</div>
		</form>
	</body>
</html>
