/**
 * 添加用户Grid展现页面
 */

var panel;

Ext.onReady(function() {
	Ext.QuickTips.init();
	cmsUserPanel();
});

function cmsUserPanel() {
	// 定义数据对象
	var Person = Ext.data.Record.create( [ {
			name : 'id',
			type : 'string'
		}, {
			name : 'username',
			type : 'string'
		}, {
			name : 'password', 
			type : 'string'
		}, {
			name : 'createtime',
			type : 'string'
		},
	 	{
			name : 'name',
			type : 'string'
		}, {
			name : 'phone',
			type : 'string'
		}, {
			name : 'userType',
			type : 'string'
		}, {
			name : 'managerName',
			type : 'string'
		}, {
			name : 'email',
			type : 'string'
		}, {
			name : 'creator',
			type : 'string'
		}
	]);

	var sm = new Ext.grid.CheckboxSelectionModel( {
		singleSelect : false
	});

	var cm = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(), sm, 
											{
												header : '姓名',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'name',
												width : 120
											},{
												header : '登录帐号',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'username',
												width : 120
											},{
												header : '联系电话',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'phone',
												width : 120
											},{
												header : '帐号分配责任人',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'managerName',
												width : 120
											},{
												header : '邮箱',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'email',
												width : 120
											},{
												header : '用户类型',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'userType',
												width : 180,
												renderer:function(v, attr, r) {
													if(v == 1)
														return "内部用户";
													else if(v == 2)
														return "外部用户";
												}
											},{
												header : '创建时间',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'createtime',
												width : 180
											} ]);

	var storeme = new Ext.data.Store( {
		id : "storeme",
		proxy : new Ext.data.HttpProxy( {
			url : 'queryCmsUsers.do'+"?rmd="+new Date(),
			method : 'post',
			params : {start : 0,limit : 20}
		}),
		reader : new Ext.data.JsonReader( {
			root : 'data.dataList',
			totalProperty : 'data.totalRows' // 指定分页控件属性用
		}, Person)
	});
	
	var store = new Ext.data.JsonStore({  
		 data: [
		        { 'id': '1', 'text': '内部用户' },
		        { 'id': '2', 'text': '外部用户' }
	   ],  
	    fields: ['id', 'text']  
	});  
	
	panel = new Ext.grid.EditorGridPanel({
		store : storeme,
		cm : cm,
		sm : sm,
		renderTo : "cmsUserManagerDiv",
		width: 1200,
		height: 500,
		autoScroll : true,
		tbar : [ {
			xtype : "tbtext",
			html:"&nbsp;",
			anchor : "90%"
         },{
 			xtype : 'label',
 			text : '管理员名称：'
 		},{
 			xtype : "textfield",
 			anchor : "100%",
 			id : 'name',
 			width : 150
 		},{
			xtype : "tbtext",
			html:"&nbsp;",
			anchor : "90%"
         }, {
 			xtype : 'label',
 			text : '用户类型：'
 		},{
 			xtype : "combo",
 			anchor : "100%",
 			id : 'userType',
 			hiddenName:'user_type',
 			displayField:"text",
 			valueField:'id',
 			editable: false,
 			mode:"local", 
 			width : 140,
 			triggerAction: 'all',
 			store: store 
 		}, {
 			xtype : 'button',
 			text : '查询',
 			handler:function(){
 				var userName = Ext.get("name").getValue();
 				var userType =  Ext.get("user_type").getValue();
 				
 				var param ={};
 				param.limit = 20;
 				param["cmsUser.name"] = userName;
 				param["cmsUser.userType"] = userType;
 				
 				storeme.baseParams = param;
				storeme.load();
 			}
 		}, {
			xtype : "tbtext",
			html:tbar,
			anchor : "90%"
         },{
 			xtype : 'tbseparator'
 		}],
		bbar : new Ext.PagingToolbar( {
			pageSize : 20,
			store : storeme,
			displayInfo : true,
			displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			emptyMsg : "没有记录"
		})
	});
	
	storeme.load( { params : { start : 0,limit : 20 } });
}

function updateCmsUserPassword(){
	
	var records = panel.getSelectionModel().getSelections();
	if(!panel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择用户');
		return;
	}
	if(records.length >1){
		Ext.Msg.alert('消息','请逐一设置');
		return;
	}
	
	updatePasswordWindow = 
	new Ext.Window( {
		title : '修改资料',
		width : 280,
		height : 270,
		layout : 'fit',
		modal : true, // 设置遮罩
		resiziable : false,
		items : [ new Ext.form.FormPanel({
					items : [{
						xtype:"textfield",
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '25'},
						fieldLabel:"姓名",
						anchor : "90%",
						id:"name",
						value:records[0].get("name")						
					},{
								xtype : "textfield",
								autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '25'},
								fieldLabel : "登录帐号", 
								anchor : "90%",
								value:records[0].get("username"),
								disabled : true
							},{
								xtype:"textfield",
								autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '25'},
								fieldLabel:"电话",
								anchor : "90%",
								id:"phone",
								value:records[0].get("phone")
							},{
								xtype:"textfield",
								autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '50'},
								fieldLabel:"邮箱",
								anchor : "90%",
								id:"email",
								value:records[0].get("email")								
							},{
								xtype:"textfield",
								autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '25'},
								fieldLabel:"帐号分配责任人",
								anchor : "90%",
								id:"mamager_1",
								value:records[0].get("managerName")								
							},{
								xtype : "combo",
					 			anchor : "100%",
					 			fieldLabel : "用户类型",
					 			id : 'type',
					 			hiddenName:'cmsUser_userType',
					 			displayField:"text",
					 			valueField:'id',
					 			editable: false,
					 			mode:"local", 
					 			triggerAction: 'all',
					 			width:120,
					 			store: new Ext.data.JsonStore({  
					 				 data: [
					 				        { 'id': '1', 'text': '内部用户' },
					 				        { 'id': '2', 'text': '外部用户' }
					 			   ],  
					 			    fields: ['id', 'text']  
					 			}) 
							}]
				}) ],
		buttons : [ {
			text : "确定修改",
			handler : function() {
				var name = Ext.getCmp("name").getValue();
				var phone = Ext.getCmp("phone").getValue();
				var email = Ext.getCmp("email").getValue();
				var manager = Ext.getCmp("mamager_1").getValue();
				var userType = Ext.get("cmsUser_userType").dom.value;
				
				
				if(null == name || "" == name){
					Ext.Msg.alert('提示', '请填写用户名！');
					return;
				}
				if(null == phone || "" == phone){
					Ext.Msg.alert('提示', '请填写电话！');
					return;
				}
				if(null == email || "" == email){
					Ext.Msg.alert('提示', '请填写邮箱！');
					return;
				}
				if(null == manager || "" == manager){
					Ext.Msg.alert('提示', '请填写帐号分配责任人！');
					return;
				}
				if(null == userType || "" == userType){
					Ext.Msg.alert('提示', '请填写用户类型！');
					return;
				}
				Ext.Ajax.request( {
					url : 'updateCmsUserPassword.do',
					success : function(response, options) {
						var res = Ext.util.JSON.decode(response.responseText);
						if(res.code > 0) {
							updatePasswordWindow.close();
						}
						Ext.Msg.alert('信息', res.message);
						panel.store.load();
					},
					failure : function(response, options) {
						Ext.Msg.alert('提示', 'error 500');
					},
					params : {
						'cmsUser.id':records[0].get("id"),
						'cmsUser.name':name.trim(),
						'cmsUser.phone':phone,
						'cmsUser.email':email,
						'cmsUser.manager':manager,
						'cmsUser.userType':userType
						}
				});
			},
			formBind : true
		} ]
	});
	var type = records[0].get("userType");
	Ext.getCmp("type").setValue(type);
	updatePasswordWindow.show();
	
}

function deleteCmsUsers(){
	
	if(!panel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择用户');
		return;
	}
	var records = panel.getSelectionModel().getSelections();
	var ids = "";var userNames = "";
	for ( var i = 0; i < records.length; i++) {
		ids += records[i].get('id') + ",";
		userNames += records[i].get('username') + ",";
	}
	ids = ids.substring(0, ids.length - 1);
	userNames = userNames.substring(0, userNames.length - 1);
	Ext.MessageBox.confirm("提示","你确定删除这些用户吗?<br>"+userNames,function(button){
		if(button=="yes"){
			Ext.Ajax.request( {
				url:"deleteCmsUsers.do",
				success : function(response, options) {
					var res = Ext.util.JSON.decode(response.responseText);
					if(res.code > 0) {
						panel.store.reload();
					}
					Ext.Msg.alert('信息', res.message);
				},
				failure : function(response, options) {
					Ext.Msg.alert('提示', 'error 500');
				},
				params : {
					'ids' : ids
				}
			})
		}
	})
	
}

function addCmsUser(){
	
	var addCmsUserWindow =
	new Ext.Window( {
		title : '增加新用户',
		width : 300,
		height : 290,
		layout : 'fit',
		modal : true, // 设置遮罩
		items : [ new Ext.form.FormPanel( {
					items : [{
							xtype : "textfield",
							autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '25'},
							fieldLabel : "姓名",
							name : "cmsUser.name",
							id:'name',
							listeners: {
				                specialkey: function (textfield, e) {
				                    if (e.getKey() == Ext.EventObject.ENTER) {
				                    	Ext.getCmp('userName').focus(true,false);
				                    }
				                }
				            }
						},{
							xtype : "textfield",
							autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '25'},
							fieldLabel : "登陆帐号",
							name : "cmsUser.username",
							id:'userName',
							listeners: {
								specialkey: function (textfield, e) {
									if (e.getKey() == Ext.EventObject.ENTER) {
										Ext.getCmp('pass1').focus(true,false);
									}
								}
							}
						},{
						xtype : "field",
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '20'},
						inputType : 'password',
						fieldLabel : "密&nbsp;&nbsp;&nbsp;码",
						name : "cmsUser.password",
						id : "pass1",
						listeners: {
			                specialkey: function (textfield, e) {
			                    if (e.getKey() == Ext.EventObject.ENTER) {
			                    	Ext.getCmp('pass2').focus(true,false);
			                    }
			                }
			            }
					},{
						xtype : "field",
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '20'},
						inputType : 'password',
						fieldLabel : "确&nbsp;&nbsp;&nbsp;认",
						id : "pass2",
						listeners: {
	                        specialkey: function (textfield, e) {
	                            if (e.getKey() == Ext.EventObject.ENTER) {
	                            	Ext.getCmp('phone').focus(true,false);
	                            }
	                        }
	                    }
					},{
						xtype : "textfield",
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '30'},
						fieldLabel : "电&nbsp;&nbsp;&nbsp;话",
						name : "cmsUser.phone",
						id : "phone",
						listeners: {
	                        specialkey: function (textfield, e) {
	                            if (e.getKey() == Ext.EventObject.ENTER) {
	                            	Ext.getCmp('email').focus(true,false);
	                            }
	                        }
	                    }
					},{
						xtype : "textfield",
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '50'},
						fieldLabel : "邮&nbsp;&nbsp;&nbsp;箱",
						name : "cmsUser.email",
						id : "email",
						listeners: {
	                        specialkey: function (textfield, e) {
	                            if (e.getKey() == Ext.EventObject.ENTER) {
	                            	Ext.getCmp('manager').focus(true,false);
	                            }
	                        }
	                    }
					},{
						xtype : "textfield",
						autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '25'},
						fieldLabel : "帐号分配责任人",
						name : "cmsUser.managerName",
						id : "manager",
						value:'',
						listeners: {
	                        specialkey: function (textfield, e) {
	                            if (e.getKey() == Ext.EventObject.ENTER) {
	                            	Ext.getCmp('userType').focus(true,false);
	                            }
	                        }
	                    }
					},{
						xtype : "combo",
			 			anchor : "100%",
			 			fieldLabel : "用户类型",
			 			id : 'type',
			 			hiddenName:'cmsUser.userType',
			 			displayField:"text",
			 			valueField:'id',
			 			editable: false,
			 			mode:"local", 
			 			triggerAction: 'all',
			 			width:120,
			 			store: new Ext.data.JsonStore({  
			 				 data: [
			 				        { 'id': '1', 'text': '内部用户' },
			 				        { 'id': '2', 'text': '外部用户' }
			 			   ],  
			 			    fields: ['id', 'text']  
			 			}) ,
						listeners: {
	                        specialkey: function (textfield, e) {
	                            if (e.getKey() == Ext.EventObject.ENTER) {
	                            	addUserSubmit();
	                            }
	                        }
	                    }
					}]
				}) ],
		buttons : [ {
			text : "确定",
			id : "btnAddCmsUser",
			handler : addUserSubmit, 
			formBind : true
		} ]
	});
	
	Ext.Ajax.request({
		url:"getCurrentAccountName.do"+"?rid="+new Date(),
		success: function(response, options) {
			Ext.getCmp("manager").setValue(response.responseText);		
		},
		failure: function (response, options) {
			//alert("fail:"+response.responseText);
		}
	});
	
	addCmsUserWindow.show();
	
	Ext.getCmp('name').focus(true,false);
	
	function addUserSubmit(){
		var userName = Ext.getCmp("userName").getValue();
		var password = Ext.getCmp("pass1").getValue();
		var password2 = Ext.getCmp("pass2").getValue();
		var name = Ext.getCmp("name").getValue();
		var phone = Ext.getCmp("phone").getValue();
		var email = Ext.getCmp("email").getValue();
		var manager = Ext.getCmp("manager").getValue();
		var userType = Ext.get("cmsUser.userType").dom.value;
		
		if(null == name || "" == name.trim()){
			Ext.getCmp('name').focus(true,false);
			Ext.Msg.alert('提示','请填写姓名');
			return;
		}
		else if(null == userName || "" == userName.trim()){
			Ext.getCmp('userName').focus(true,false);
			Ext.Msg.alert('提示','请填写登陆名');
			return;
		}else if(null == password || "" == password || null == password2 || "" == password2){
			Ext.getCmp('pass1').focus(true,false);
			Ext.Msg.alert('提示','请填写密码');
			return;
		}else if(password != password2){
			Ext.getCmp('pass2').focus(true,false);
			Ext.Msg.alert('提示','两次密码不一致');
			return;
		} else if(null == phone || ""== phone.trim()){
			Ext.getCmp('phone').focus(true,false);
			Ext.Msg.alert('提示','请输入电话');
			return;
		} else if(null == email || ""== email.trim()){
			Ext.getCmp('email').focus(true,false);
			Ext.Msg.alert('提示','请输入邮箱');
			return;
		} else if(null == manager || ""== manager.trim()){
			Ext.getCmp('manager').focus(true,false);
			Ext.Msg.alert('提示','请输入账号分配责任人');
			return;
		} else if(null == userType || ""== userType.trim()){
			Ext.getCmp('userType').focus(true,false);
			Ext.Msg.alert('提示','请输入用户类型');
			return;
		}else{
			Ext.getCmp('btnAddCmsUser').disable();
			Ext.Ajax.request( {
				url : 'addCmsUser.do',
				success : function(response, options) {
					var res = Ext.util.JSON.decode(response.responseText);
					if(res.code > 0) {
						panel.store.load();
						addCmsUserWindow.close();
					} else if(res.code == -1){
						Ext.Msg.alert('消息',"帐号分配责任人填写不正确");
						Ext.getCmp('btnAddCmsUser').enable();
					} else {
						Ext.getCmp('btnAddCmsUser').enable();
					}
					Ext.Msg.alert('消息',res.message);
				},
				failure : function(response, options) {
					Ext.Msg.alert('提示', '网络延时或错误。');
					Ext.getCmp('btnAddCmsUser').enable();
				},
				params : {
					'cmsUser.username' : userName.trim(),
					'cmsUser.password' : password,
					'cmsUser.name' : name,
					'cmsUser.phone' : phone,
					'cmsUser.userType' : userType,
					'cmsUser.manager' : manager,
					'cmsUser.email' : email
				}
			});
		}
	}
	
}