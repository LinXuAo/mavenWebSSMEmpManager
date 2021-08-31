<%@ page language="java" import="java.util.*,com.po.*,com.service.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息录入</title>
<!--easy UI支持引入  -->
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
<script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
/*************获取部门下拉列表和福利的复选框begin**************/
$(function(){
	$('#form1').hide();
	$('#form2').hide();
	$("#box").panel('close');
	$("#depbox").panel('close');
	//通过Ajax向后台发送请求获取后台返回的JSON字符串
	$.getJSON("doinit_Emp.do",function(map){
		var lswf=map.lswf;//获取员工福利集合
		var lsdep=map.lsdp;//获取部门集合
		   //遍历员工福利集合获取每一个福利对象
		for (var i = 0; i < lswf.length; i++) {
			var wf=lswf[i];
			//拼出福利的复选框
			$("#wf").append("<input type='checkbox' name='wids' value='"+wf.wid+"'/>"+wf.wname);
		};
	//处理部门下拉列表
	$('#cc').combobox({    
	    data:lsdep,   
	    valueField:'depid',    
	    textField:'depname',
	    value:4,
	    panelHeight:88
	});  
 
	});
	$("div[id='back']").css("display","none");
	$("div[id='epls']").css("display","none");
	$("div[id='depback']").css("display","none");
	$("div[id='depls']").css("display","none");
	$('#win').window('close');  
});
/*************获取部门下拉列表和福利的复选框end**************/
 /*************员工数据添加begin**************/
 $(function(){
	 $("#btsave").click(function(){
			$.messager.progress();	// 显示进度条
			$('#form1').form('submit', {
				url:'save_Emp.do',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				//回调函数
				success: function(code){
					if(code=='1'){
						$.messager.alert('提示','保存成功!!!');						
						$("div[id='back']").css("display","block");
						$("div[id='epls']").css("display","block");
						$("#box").panel('open');
						$('#ep').datagrid('reload');    // 重新载入当前页面数据 
						$('#form1').hide();
					}else{
						$.messager.alert('提示','保存失败!!!');
					}
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			});

		});

	 /*************修改begin*************/	
	 $("#btupdate").click(function(){
			$.messager.progress();	// 显示进度条
			$('#form1').form('submit', {
				url:'update_Emp.do',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				//回调函数
				success: function(code){
					if(code=='1'){
						$.messager.alert('提示','修改成功!!!');
						$('#ep').datagrid('reload');    // 重新载入当前页面数据  
						$('#form1').hide();
					}else{
						$.messager.alert('提示','修改失败!!!');
					}
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			});
	    });
	     /*************修改end*************/
 });
 /*************员工数据添加end**************/
 	 /*************部门数据添加begin**************/
 $(function(){ 
 	  $("#depbtsave").click(function(){
			$.messager.progress();	// 显示进度条
			$('#form2').form('submit', {
				url:'save_Dep.do',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				//回调函数
				success: function(code){
					if(code=='1'){
						$.messager.alert('提示','保存成功!!!');						
						$("div[id='depback']").css("display","block");
						$("div[id='depls']").css("display","block");
						$("#depbox").panel('open');
						$('#dep').datagrid('reload');    // 重新载入当前页面数据 
						$('#form2').hide();
					}else{
						$.messager.alert('提示','保存失败!!!');
					}
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			});  
  });
 	 /*************部门修改begin*************/	
 	 $("#depbtupdate").click(function(){
 			$.messager.progress();	// 显示进度条
 			$('#form2').form('submit', {
 				url:'update_Dep.do',
 				onSubmit: function(){
 					var isValid = $(this).form('validate');
 					if (!isValid){
 						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
 					}
 					return isValid;	// 返回false终止表单提交
 				},
 				//回调函数
 				success: function(code){
 					if(code=='1'){
 						$.messager.alert('提示','修改成功!!!');
 						$('#dep').datagrid('reload');    // 重新载入当前页面数据  
 						$('#form2').hide();
 					}else{
 						$.messager.alert('提示','修改失败!!!');
 					}
 					$.messager.progress('close');	// 如果提交成功则隐藏进度条
 				}
 			});
 	    });
 	     /*************部门修改end*************/
});
  /*************部门数据添加end**************/
   /*************部门数据展示begin**************/
   $(function(){
	$('#dep').datagrid({    
	    url:'findPageAll_Dep.do',
	    pagination:true,
	    pageNumber:1,
	    pageSize:5,
	    pageList:[5,10,15,20],
	    striped:true, 
	    columns:[[    
	        {field:'depid',title:'编号',width:100,align:'center'},
	        {field:'depname',title:'部门名称',width:100,align:'center'},
	        {field:'depopt',title:'操作',width:300,align:'center',
	        	formatter: function(value,row,index){
	        		var bt1="<input type='button' onclick=depdelById("+row.depid+") value='删除'/>"
	        		var bt2="<input type='button' onclick=depfindById("+row.depid+") value='编辑'/>"	        		
	        		return bt1+bt2;
	        	}		
	        
	         }
	        
	        
	    ]]    
	});  

}); 
    /*************部门数据展示end**************/
//部门编辑操作
/*************部门查询单个begin*************/
function depfindById(depid){
	$("#depbox").panel('open');
	$('#form2').show();
	$.getJSON("findById_Dep.do?depid="+depid,function(dep){
		//将返回值写入指定表单，easy UI实现表单自动填充
		$('#form2').form('load',{
			'depid':dep.depid,
			'depname':dep.depname,
		});
		
	     });
	};
/*************部门查询单个end*************/
 /*************分页查询数据begin*************/
 $(function(){
	$('#ep').datagrid({    
	    url:'findPageAll_Emp.do',
	    pagination:true,
	    pageNumber:1,
	    pageSize:5,
	    pageList:[5,10,15,20],
	    striped:true, 
	    columns:[[    
	        {field:'eid',title:'编号',width:100,align:'center'},
	        {field:'ename',title:'姓名',width:100,align:'center'},
	        {field:'fname',title:'照片',width:100,align:'center',
	         	formatter: function(value,row,index){
	        		return "<a href=uppic/"+row.fname+"><img alt='图片不存在' src=uppic/"+row.fname+"  width=100 height=50></a>"
	        	}	
	        },
	        {field:'sex',title:'性别',width:100,align:'center'},
	        {field:'address',title:'地址',width:100,align:'center'},
	        {field:'sbirthday',title:'生日',width:100,align:'center'},   
	        {field:'depname',title:'部门名称',width:100,align:'center'},
	        {field:'opt',title:'操作',width:300,align:'center',
	        	formatter: function(value,row,index){
	        		var bt1="<input type='button' onclick=delById("+row.eid+") value='删除'/>"
	        		var bt2="<input type='button' onclick=findById("+row.eid+") value='编辑'/>"
	        		var bt3="<input type='button' onclick=editById("+row.eid+") value='详情'/>"
	        		return bt1+bt2+bt3;
	        	}		
	        
	         }
	        
	        
	    ]]    
	});  

}); 
 /*************分页查询数据end*************/
  /*************信息删除begin*************/
 function delById(eid){
	 $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		    	$.get("delete_Emp.do?eid="+eid,function(code){
		    		if(code=='1'){
						$.messager.alert('提示','删除成功!!!');
						$('#ep').datagrid('reload');    // 重新载入当前页面数据  
					}else{
						$.messager.alert('提示','删除失败!!!');
					}

		    	});  
		    }    
		});  

} 

  /*************信息删除end*************/
//编辑操作
/*************查询单个begin*************/
function findById(eid){
	$("#box").panel('open');
	$('#form1').show();
	$.getJSON("findById_Emp.do?eid="+eid,function(emp){
		//先删除福利复选矿中的所有选中
		$(":checkbox[name='wids']").each(function(){
				$(this).prop("checked",false);
		});
		//将返回值写入指定表单，easy UI实现表单自动填充
		$('#form1').form('load',{
			'eid':emp.eid,
			'ename':emp.ename,
			'sex':emp.sex,
			'address':emp.address,
			'sbirthday':emp.sbirthday,
			'depid':emp.depid,
			'emoney':emp.emoney,
		});
		//图片处理
		$("#myphoto").attr('src','uppic/'+emp.fname)
		//福利复选框的处理
		var wids=emp.wids;
	     $(":checkbox[name='wids']").each(function(){
				for(var i=0;i<wids.length;i++){
					if($(this).val()==wids[i]){
						$(this).prop("checked",true);	
					}
				}
	     });
	});
  }
/*************查询单个end*************/
/*************查看详情begin*************/
 function editById(eid){
	 $.getJSON("findById_Emp.do?eid="+eid,function(emp){
		 //赋值
		 $("#enametxt").html(emp.ename);
		 $("#sextxt").html(emp.sex);
		 $("#addresstxt").html(emp.address);
		 $("#sbirthdaytxt").html(emp.sbirthday);
		 $("#fnametxt").html(emp.fname);
		 $("#deptxt").html(emp.depname);
		 $("#emoneytxt").html(emp.emoney);
		 $("#eidtxt").html(emp.eid);
		 //获取福利
		 var lswf=emp.lswf;
		 var wnames=[];//福利名称数组
		 for(var i=0;i<lswf.length;i++){
			 var wf=lswf[i];
			 wnames.push(wf.wname);
		 }
		 var strwname=wnames.join(',');
		 $("#wftxt").html(strwname); 
		 $("#domyphoto").attr('src','uppic/'+emp.fname); 
		 $('#win').window('open');  // 打开窗口
	 });	
	
}
 /*************查看详情end*************/
 /*************去查看列表begin*************/
 $(function(){
	 $("#bttoshow").click(function(){
		$('#form1').hide();
		$("#box").panel('open');
		$("div[id='back']").css("display","block");
		$("div[id='epls']").css("display","block");
		//$('#win').window('close'); 
	}); 
 });
 /*************去查看列表end*************/
 /*************员工添加按钮begin*************/
  function empaddshowById(){
	  $('#form1').show();
	  $('#addshow').window('close');
 }
  /*************员工添加按钮end*************/
  /*************部门添加按钮begin*************/
   function depaddshowById(){
	  $('#form2').show();
	  $('#addshow').window('close');
 }
   /*************部门添加按钮end*************/
 </script>
</head>
<body>
		<div align="center" id="epls">员工列表展示</div>
		
		<div class="easyui-panel" id="box">
		<table id="ep"></table>
		</div> 
		<div align="center" id="back">
       <a href="emp.jsp">返回添加</a>
       </div>
		
		
		<div align="center" id="depls">部门列表展示</div>
		
		<div class="easyui-panel" id="depbox">
		<table id="dep"></table>
		</div> 
		<div align="center" id="depback">
       <a href="emp.jsp">返回添加</a>
       </div>
		
	<!-- 部门添加表单 -->
	<form action="" method="post"  id="form2" name="form2">
      <table border="1px" align="center" width="350px">
        <tr  bgcolor="#ffffcc" align="center">
           <td colspan="3">部门录入</td>
        </tr>
        <tr>
         <td align="center">添加的部门名称</td>
          <td>
           <input type="text" id="depname" name="depname" placeholder="部门名称" class="easyui-validatebox" data-options="required:true">
           </td>
        </tr>  
         <tr  bgcolor="#ffffcc" align="center">
           <td colspan="2">
           <input type="hidden" id="depid" name="depid">
           <input type="button" id="depbtsave" name="btsave" value="保存">
           <input type="button" id="depbtupdate" name="btupdate" value="修改">
           <input type="reset" id="depbtrest" name="btrest" value="取消">    
           </td>
        </tr>
       </table>
     </form>
     
    <!-- 员工添加表单 -->
    <form action="" method="post" enctype="multipart/form-data" id="form1" name="form1">
      <table border="1px" align="center" width="560px">
        <tr  bgcolor="#ffffcc" align="center">
           <td colspan="3">信息录入</td>
        </tr>
        <tr>
           <td align="center">姓名</td>
           <td>
           <input type="text" id="ename" name="ename" placeholder="姓名" class="easyui-validatebox" data-options="required:true">
           </td>
             <td width="140" rowspan="7">
             <img id="myphoto" src="uppic/default.jpg" width="200" height="190" /> 
           </td> 
        </tr>
         <tr>
	      <td>选择照片</td>
	      <td>
	      <input type="file" name="pic" id="pic" /></td>
       </tr> 
        <tr>
           <td align="center">性别</td>
           <td>
           <input type="radio"  name="sex" value="男" checked="checked">男
           <input type="radio"  name="sex" value="女">女
           </td>
        </tr>
        <tr>
           <td align="center">地址</td>
           <td>
           <input type="text" id="address" name="address" class="easyui-validatebox" data-options="required:true">
           </td>
        </tr>
         <tr>
           <td align="center">出生日期</td>
           <td>
           <input type="date" id="sbirthday" name="sbirthday" value="1995-05-21">
           </td>
        </tr> 
        <tr>
            <td align="center">部门</td>
            <td>
            <input type="text" id="cc" name="depid">
            </td> 
        </tr>  
        <tr>
            <td align="center">薪资</td>
            <td>
            <input type="text" id="emoney" name="emoney" value="2000">
            </td> 
        </tr>  
        <tr>
            <td align="center">福利</td>
            <td colspan="2">
            <span id="wf"></span>
            </td> 
        </tr>  
         <tr  bgcolor="#ffffcc" align="center">
           <td colspan="3">
           <input type="hidden" id="eid" name="eid">
           <input type="button" id="btsave" name="btsave" value="保存">
           <input type="button" id="btupdate" name="btupdate" value="修改">
           <input type="reset" id="btrest" name="btrest" value="取消">    
           </td>
        </tr>
      </table><br />
      <div align="center" id="toshow">
       <input type="button" id="bttoshow" name="bttoshow" value="查看列表">
       </div>
    </form>
    
 <!-- 员工添加和部门添加按钮 -->
    <div id="addshow" class="easyui-window" title="选择操作"  align='center' style="width:400px;height:200px"   
        data-options="iconCls:'icon-save',modal:true"> 
        <h3>请先选择要进行的操作！</h3>
        <input type='button' onclick=empaddshowById() value='员工添加' style="width:100px;height:40px;background-color:#ffffcc"/>    
        <span><input type='button' onclick=depaddshowById() value='部门添加' style="width:100px;height:40px;background-color:#ffffcc"/></span>     
   </div> 

 <!--详情展示页面  -->
    <div id="win" class="easyui-window" title="详情展示" style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true">   
       <table border="1px" align="center" width="560px" height="350px">
        <tr>
		     <td width="100px">姓名</td>
		     <td width="200px">
		     <span id="enametxt"></span>
		     </td>
		     <td rowspan="7">
		      <img id="domyphoto" alt="图片不存在" src="uppic/default.jpg" width="240px" height="260px">
		     </td>
		    </tr>
		     <tr>
		     <td>性别</td>
		     <td>
		      <span id="sextxt"></span>
		     </td>
		    </tr>
		     <tr>
		     <td>地址</td>
		     <td>
		     <span id="addresstxt"></span>
		     </td>
		     
		    </tr>
		     <tr>
		     <td>生日</td>
		     <td>
		     <span id="sbirthdaytxt"></span>
		     </td>
		     
		    </tr>
		     <tr>
		     <td>照片</td>
		     <td>
		     <span id="fnametxt"></span>
		     </td>
		     
		    </tr>
		     <tr>
		     <td>部门</td>
		     <td>
		     <span id="deptxt"></span>
		     </td>
		    
		    </tr>
		     <tr>
		     <td>薪资</td>
		     <td>
		     <span id="emoneytxt"></span>
		     </td>
		    </tr>
		     <tr>
		     <td>福利</td>
		     <td colspan="2">
		     <span id="wftxt"></span>
		     </td>
		    </tr>
		    <tr >
		     <td>编号</td>
		     <td colspan="2">
		     <span id="eidtxt"></span>
		     </td>
		    </tr>
        </table>   
   </div>  
      
 
 </body>
</html>