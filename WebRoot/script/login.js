Ext.onReady(function() {
	
	Ext.QuickTips.init();
	var loginForm = 
		new Ext.FormPanel({
			frame : true,
			width : 300,
			labelWidth : 70,
			title : "务工易企业服务运营支撑系统登录",
			labelAlign : "left",
			items : [ {
						xtype : "textfield",
						fieldLabel : "用户名",
						name : "cmsUser.userName",
						value : "管理员",
						anchor : "90%",
						id : 'cmsUsername',
						listeners: {
			                specialkey: function (textfield, e) {
			                    if (e.getKey() == Ext.EventObject.ENTER) {
			                    	Ext.getCmp('cmsUserPassword').focus(true,false);
			                    }
			                }
			            }
					  },{
						xtype : "field",
						inputType : 'password',
						fieldLabel : "密&nbsp;&nbsp;&nbsp;码",
						value : "wgytgb",
						allowBlank : false,
						name : "cmsUser.password",
						id : "cmsUserPassword",
						anchor : "90%",
						listeners: {
	                        specialkey: function (textfield, e) {
	                            if (e.getKey() == Ext.EventObject.ENTER) {
	                            	login();
	                            }
	                        }
	                    }
					 }],
			buttons : [ {
							text : "确定",
							handler : login,
							formBind : true
						} ]
			});
	
	var windowWidth = document.body.clientWidth;
	var left = windowWidth/2 - 300/2;  
	var style = 'margin-top:150px;margin-left:'+left+'px;';  
	var el = Ext.get('login').applyStyles(style);
	loginForm.render(el);
	Ext.getCmp('cmsUsername').focus(true,false);//设置焦点
	
	function login() {
		
		var userName = Ext.getCmp("cmsUsername").getValue();
		var password = Ext.getCmp("cmsUserPassword").getValue();
		if(null == userName || "" == userName.trim()){
			Ext.getCmp('cmsUsername').focus(true,false);
			document.getElementById("loginMessagerDiv").innerHTML = '<font color="red" size="+1">*请填写用户名</font>';
			return ;
		}else{
			document.getElementById("loginMessagerDiv").innerHTML = '';
		}
		if(null == password || "" == password){
			Ext.getCmp('cmsUsername').focus(true,false);
			document.getElementById("loginMessagerDiv").innerHTML = '<font color="red" size="+1">*请填写密码</font>';
			return ;
		}else{
			document.getElementById("loginMessagerDiv").innerHTML = '';
		}
		var loginShow = new Ext.Window( {
			title : '登录提示',
			closeAction : 'hide',
			width : 300,
			height: 200,
			layout : 'fit',
			modal : true, // 设置遮罩
			resiziable : false,
			html:'<img width="100px" height="100px" src="css/images/login.gif"/>登录中···'
		});
		loginShow.show();
		Ext.Ajax.request( {
			url : 'login.do',
			success : function(response, options) {
				var res = Ext.util.JSON.decode(response.responseText);
				if(res.code < 0){
					Ext.getCmp('cmsUsername').focus(true,false);//设置焦点
					document.getElementById("loginMessagerDiv").innerHTML = '<font color="red" size="+1">*'+res.message+'</font>';
					loginShow.close();
				}else{
					loginShow.close();
					window.location = "index.jsp";
				}
			},
			failure : function(response, options) {
				loginShow.close();
				Ext.Msg.alert('提示', '网络延时或错误。');
			},
			params : {'cmsUser.username':userName,'cmsUser.password':password}
		});
	}
});
