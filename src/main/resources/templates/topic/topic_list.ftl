<!DOCTYPE html>
<html lang="zh-CN" xmlns:th="http://www.thymeleaf.org">
	<head>
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0"/>
		<link rel="stylesheet" type="text/css" href="${basePath+urls.getForLookupPath('/css/weui.css')}">
		<link rel="stylesheet" type="text/css" href="${basePath+urls.getForLookupPath('/css/jquery-weui.css')}">
		<script type="text/javascript" src="//code.jquery.com/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="${basePath+urls.getForLookupPath('/js/jquery-weui.js')}"></script>
	</head>
	
	<script type="text/javascript" th:inline="javascript">
	$(document).ready(function(){
		$(document.body).pullToRefresh();
		$(document.body).on("pull-to-refresh", function() {
			window.location.reload();
		});
		$(document.body).infinite(10);
		var loading = false;  //状态标记
		var currentPage=1;
		$(document.body).infinite().on("infinite", function() {
		  if(loading) return;
		  loading = true;
		  loadQuestions();  
		});
		
		function loadQuestions(){
			$.ajax({
				url:"${basePath}/wechat/topic/list",
				type:"POST",
				data:{
					"currentPage": currentPage,
				},
				error:function(e){
					alert(e);
				},
				success:function(result){
					currentPage++;
					if(result && result.length>0){
						$.each(result,function(index,topic){
		                    var fragment='<div style="position:relative"><a href="javascript:void(0)" class="weui_media_box weui_media_appmsg" style="padding-bottom:0">'
		                  +'<div class="weui_media_hd">'
		                  +' <img class="weui_media_appmsg_thumb"  height="60" width="60" />'
		                  +'</div>'
		                  +'<div class="weui_media_bd" style="overflow: hidden">'
		                  +'<h4 class="weui_media_title">'+topic.title+'</h4>'
		                  +'<p class="weui_media_desc" >'+topic.content+'</p></div>'
		                  +'<div class="weui_media_hd"><img class="weui_media_appmsg_thumb" src="/img/best-answer-2.jpg" height="60" width="60"/></div></a> '
                	 	  +'<div class="weui_media_box weui_media_text" style="padding-bottom:0;padding-top:0;position:initial">'
	                      +'<ul class="weui_media_info">'
	                      +'<li class="weui_media_info_meta">'+topic.createDate+'</li>'
	                      +'<li class="weui_media_info_meta">'+topic.lastModifyDate+'</li>'
	                      +'<li class="weui_media_info_meta weui_media_info_meta_extra"></li></ul></div></div>';    	
		                        	
		                 $(".weui_panel").append(fragment);	
						});
						
						loading = false;
					}
				}
			});
		}
	});
	</script>

	<body>
		<div class="weui-pull-to-refresh-layer">
		    <div class="pull-to-refresh-arrow"></div> <!-- 上下拉动的时候显示的箭头 -->
		    <div class="pull-to-refresh-preloader"></div> <!-- 正在刷新的菊花 -->
		    <div class="down">下拉刷新</div><!-- 下拉过程显示的文案 -->
		    <div class="up">释放刷新</div><!-- 下拉超过50px显示的文案 -->
		    <div class="refresh">正在刷新...</div><!-- 正在刷新时显示的文案 -->
	  	</div>
		<div class="weui_panel">
			<div class="weui_panel_hd">列表共${totalCount!}条</div>
            <div class="weui_panel_bd" >
            	<#list topics as topic>
            	<div style="position:relative">
            		<a href="javascript:void(0)" class="weui_media_box weui_media_appmsg" style="padding-bottom:0">
		                 <div class="weui_media_hd">
		                     <img class="weui_media_appmsg_thumb"  height="60" width="60" />
		                 </div>
		                 <div class="weui_media_bd" style="overflow: hidden">
		                     <h4 class="weui_media_title">${topic.title}</h4>
		                     <p class="weui_media_desc" >${topic.content}</p>
		                 </div>
		                 <div class="weui_media_hd">
							<img class="weui_media_appmsg_thumb" src="${basePath+'/img/best-answer-2.jpg'}" height="60" width="60"/>
						</div>
                	</a> 
                	<div class="weui_media_box weui_media_text" style="padding-bottom:0;padding-top:0;position:initial">
	                    <ul class="weui_media_info">
	                        <li class="weui_media_info_meta">${topic.createDate}</li>
	                        <li class="weui_media_info_meta">${topic.lastModifyDate}</li>
	                        <li class="weui_media_info_meta weui_media_info_meta_extra"></li>
	                    </ul>
                	</div> 
             	</div>
             	</#list>
            </div> 
        </div>
        <div class="weui-infinite-scroll" style="visibility:hidden">
		  <div class="infinite-preloader"></div>
		 	正在加载... 
		</div>
	</body>
</html>
