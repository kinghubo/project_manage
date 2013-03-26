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
	}
	]);

	var sm = new Ext.grid.CheckboxSelectionModel( {
		singleSelect : false
	});

	var cm = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(), sm, 
											{
												header : '用户名',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'username',
												width : 120
											},{
												header : '创建时间',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'createtime',
												width : 180
											},{
												header : 'ID',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'id',
												width : 180
											} ]);

	var storeme = new Ext.data.Store( {
		id : "storeme",
		proxy : new Ext.data.HttpProxy( {
			url : 'queryCmsUsers.do'+"?rmd="+new Date(),
			method : 'post',
			params : { start : 0,limit : 20 }
		}),
		reader : new Ext.data.JsonReader( {
			root : 'data.dataList',
			totalProperty : 'data.totalRows' // 指定分页控件属性用
		}, Person)
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
			html:tbar,
			anchor : "90%"
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
		title : '重置密码',
		width : 270,
		height : 150,
		layout : 'fit',
		modal : true, // 设置遮罩
		resiziable : false,
		items : [ new Ext.form.FormPanel({
					items : [{
								xtype : "textfield",
								maxLength : 30,
								fieldLabel : "登录名", 
								anchor : "90%",
								value:records[0].get("username"),
								readOnly : true
							},{
								xtype : "textfield",
								maxLength : 30,
								fieldLabel : "新密码", 
								anchor : "90%",
								id:'newPassword'
							}]
				}) ],
		buttons : [ {
			text : "确定修改",
			handler : function() {
				var newPassword = Ext.getCmp("newPassword").getValue();
				if(null == newPassword || "" == newPassword){
					Ext.Msg.alert('提示', '请填写新密码！');
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
					},
					failure : function(response, options) {
						Ext.Msg.alert('提示', 'error 500');
					},
					params : {'cmsUser.id':records[0].get("id"),'cmsUser.password':newPassword}
				});
			},
			formBind : true
		} ]
	});
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
		title : '新用户注册',
		width : 300,
		height : 200,
		layout : 'fit',
		modal : true, // 设置遮罩
		items : [ new Ext.form.FormPanel( {
					items : [ {
						xtype : "textfield",
						maxLength : 50,
						fieldLabel : "用户名",
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
						maxLength : 50,
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
						maxLength : 50,
						inputType : 'password',
						fieldLabel : "确&nbsp;&nbsp;&nbsp;认",
						id : "pass2",
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
			handler : addUserSubmit, 
			formBind : true
		} ]
	});
	addCmsUserWindow.show();
	
	Ext.getCmp('userName').focus(true,false);
	
	function addUserSubmit(){
		var userName = Ext.getCmp("userName").getValue();
		var password = Ext.getCmp("pass1").getValue();
		var password2 = Ext.getCmp("pass2").getValue();
		if(null == userName || "" == userName.trim()){
			Ext.getCmp('userName').focus(true,false);
			Ext.Msg.alert('提示','请填写用户名');
			return;
		}else if(null == password || "" == password || null == password2 || "" == password2){
			Ext.getCmp('pass1').focus(true,false);
			Ext.Msg.alert('提示','请填写密码');
			return;
		}else if(password != password2){
			Ext.getCmp('pass2').focus(true,false);
			Ext.Msg.alert('提示','两次密码不一致');
			return;
		}else{
			Ext.Ajax.request( {
				url : 'addCmsUser.do',
				success : function(response, options) {
					var res = Ext.util.JSON.decode(response.responseText);
					if(res.code > 0) {
						panel.store.load();
						addCmsUserWindow.close();
					}
					Ext.Msg.alert('消息',res.message);
				},
				failure : function(response, options) {
					Ext.Msg.alert('提示', '网络延时或错误。');
				},
				params : {'cmsUser.username':userName,'cmsUser.password':password}
			});
		}
	}
	
}