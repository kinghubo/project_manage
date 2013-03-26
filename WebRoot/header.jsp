<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.taogongbao.entity.cms.CmsUser"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>首页</title>
	</head>
	<%
		CmsUser cmsUser = (CmsUser)session.getAttribute("cmsuser");
		String username = (null == cmsUser)?"":cmsUser.getUsername();
	%>
	<body>
		<center>
			<div style="height:80px; width:1300px;"></div>
			<div style="position:relative; top:-70px;">
				<span style="font-size:30px; font-family:'微软雅黑';">项目管理系统</span>
			</div>
		</center>
		<div style="float:right; position:relative; top:-60px;">
			<span style="font-size:14px; margin-right:50px; font-family:'宋体';">您好，<%=username%>&nbsp;欢迎进入项目管理系统！[<a href="javascript:updateUserPassword();">修改密码</a>][<a href="loginOut.do">退出系统</a>]</span>
		</div>
	</body>
</html>
<script type="text/javascript">
	function updateUserPassword(){
		var updateUserPassword =
		new Ext.Window( {
			title : '修改密码',
			width : 300,
			height : 200,
			layout : 'fit',
			modal : true, // 设置遮罩
			items : [ new Ext.form.FormPanel( {
						items : [ {
							xtype : "field",
							inputType : 'password',
							maxLength : 50,
							fieldLabel : "原密码",
							id:'nowPassword',
							anchor : "80%",
							listeners: {
				                specialkey: function (textfield, e) {
				                    if (e.getKey() == Ext.EventObject.ENTER) {
				                    	Ext.getCmp('newPassword').focus(true,false);
				                    }
				                }
				            }
						},{
							xtype : "field",
							maxLength : 50,
							inputType : 'password',
							fieldLabel : "新密码",
							id : "newPassword",
							anchor : "80%",
							listeners: {
				                specialkey: function (textfield, e) {
				                    if (e.getKey() == Ext.EventObject.ENTER) {
				                    	Ext.getCmp('confirmPassword').focus(true,false);
				                    }
				                }
				            }
						},{
							xtype : "field",
							maxLength : 50,
							inputType : 'password',
							fieldLabel : "请确认",
							id : "confirmPassword",
							anchor : "80%",
							listeners: {
		                        specialkey: function (textfield, e) {
		                            if (e.getKey() == Ext.EventObject.ENTER) {
		                            	updateUserPasswordSubmit();
		                            }
		                        }
		                    }
						}]
					}) ],
			buttons : [ {
				text : "确定",
				handler : updateUserPasswordSubmit, 
				formBind : true
			} ]
		});
		updateUserPassword.show();
		
		function updateUserPasswordSubmit(){
			var nowPassword = Ext.getCmp("nowPassword").getValue();
			var newPassword = Ext.getCmp("newPassword").getValue();
			var confirmPassword = Ext.getCmp("confirmPassword").getValue();
			if(null == nowPassword || "" == nowPassword){
				Ext.getCmp('nowPassword').focus(true,false);
				Ext.Msg.alert('提示','请填写原密码。');
				return;
			}else if(null == newPassword || "" == newPassword){
				Ext.getCmp('newPassword').focus(true,false);
				Ext.Msg.alert('提示','请填写新密码。');
				return;
			}else if(null == confirmPassword || "" == confirmPassword){
				Ext.getCmp('confirmPassword').focus(true,false);
				Ext.Msg.alert('提示','请确认密码。');
				return;
			}else if(newPassword != confirmPassword){
				Ext.getCmp('confirmPassword').focus(true,false);
				Ext.Msg.alert('提示','两次密码不一致。');
				return;
			}else{
				Ext.Ajax.request( {
					url : 'updatePassword.do',
					success : function(response, options) {
						var res = Ext.util.JSON.decode(response.responseText);
						if (res.code > 0) {
							updateUserPassword.close();
						}
						Ext.Msg.alert('消息',res.message);
					},
					failure : function(response, options) {
						Ext.Msg.alert('提示', '网络连接超时。');
					},
					params : {'nowPassword':nowPassword,'newPassword':newPassword,'confirmPassword':confirmPassword}
				});
			}
		}
	}
</script>