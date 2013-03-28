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
		name : 'name',
		type : 'string'
	}, {
		name : 'explanation', 
		type : 'string'
	}, {
		name : 'level',
		type : 'string'
	}, {
		name : 'type',
		type : 'string'
	}, {
		name : 'createDate',
		type : 'string'
	}, {
		name : 'lastUpdate',
		type : 'string'
	}, {
		name : 'createUserId',
		type : 'string'
	}, {
		name : 'createUserName',
		type : 'string'
	}
	]);

	var sm = new Ext.grid.CheckboxSelectionModel( {
		singleSelect : false
	});

	var cm = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(), sm, 
	                                     	{
												header : '主键',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'id',
												width : 50
											},{
												header : '项目名称',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'name',
												width : 120
											},{
												header : '项目说明',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'explanation',
												width : 120
											},{
												header : '项目级别',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'level',
												width : 100
											},{
												header : '项目分类',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'type',
												width : 100
											},{
												header : '创建时间',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'createDate',
												width : 120
											},{
												header : '更新时间',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'lastUpdate',
												width : 120
											},{
												header : '创建人',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'createUserName',
												width : 120
											} ]);

	var storeme = new Ext.data.Store( {
		id : "storeme",
		proxy : new Ext.data.HttpProxy( {
			url : 'ProjectInfo!queryProjectInfos.do'+"?rmd="+new Date(),
			method : 'post',
			params : { start : 0,limit : 20 }
		}),
		reader : new Ext.data.JsonReader( {
			root : 'data',
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

function updateProject(){
	
	var records = panel.getSelectionModel().getSelections();
	if(!panel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择项目');
		return;
	}
	if(records.length >1){
		Ext.Msg.alert('消息','请逐一设置');
		return;
	}
	
	var updateCmsUserWindow = 
	new Ext.Window( {
		title : '修改详情',
		width : 300,
		height : 200,
		layout : 'fit',
		modal : true, // 设置遮罩
		resiziable : false,
		items : [new Ext.form.FormPanel({
					id:"addProjectForm",
					scope : this,
					url:"ProjectInfo!add.do",
					items : [ {
						xtype : "hidden",
						maxLength : 50,
						fieldLabel : "id",
						name : "pmProjectInfo.id",
						value:	records[0].get("id"),
						id:'id',
						listeners: {
			                specialkey: function (textfield, e) {
			                    if (e.getKey() == Ext.EventObject.ENTER) {
			                    	Ext.getCmp('name').focus(true,false);
			                    }
			                }
			            }
					}, {
						xtype : "textfield",
						maxLength : 50,
						fieldLabel : "项目名称",
						name : "pmProjectInfo.name",
						value:	records[0].get("name"),
						id:'name',
						listeners: {
			                specialkey: function (textfield, e) {
			                    if (e.getKey() == Ext.EventObject.ENTER) {
			                    	Ext.getCmp('explanation').focus(true,false);
			                    }
			                }
			            }
					},{
						xtype : "textfield",
						maxLength : 50,
						fieldLabel : "项目说明",
						name : "pmProjectInfo.explanation",
						value:	records[0].get("explanation"),
						id:'explanation',
						listeners: {
			                specialkey: function (textfield, e) {
			                    if (e.getKey() == Ext.EventObject.ENTER) {
			                    	Ext.getCmp('level').focus(true,false);
			                    }
			                }
			            }
					},{
						xtype : "textfield",
						maxLength : 50,
						fieldLabel : "项目级别",
						name : "pmProjectInfo.level",
						value:	records[0].get("level"),
						id:'level',
						listeners: {
			                specialkey: function (textfield, e) {
			                    if (e.getKey() == Ext.EventObject.ENTER) {
			                    	Ext.getCmp('type').focus(true,false);
			                    }
			                }
			            }
					},{
						xtype : "textfield",
						maxLength : 50,
						fieldLabel : "项目分类",
						name : "pmProjectInfo.type",
						value:	records[0].get("type"),
						id:'type'
					}]
				}) ],
		buttons : [ {
			text : "确定",
			handler : addProjectSubmit, 
			formBind : true
		} ]
	});
	updateCmsUserWindow.show();
	
	function addProjectSubmit(){
		var name = Ext.getCmp("name").getValue();
		var explanation = Ext.getCmp("explanation").getValue();
		var level = Ext.getCmp("level").getValue();
		var type = Ext.getCmp("type").getValue();
		if(null == name || "" == name.trim()){
			Ext.getCmp('name').focus(true,false);
			Ext.Msg.alert('提示','请填写项目名称');
			return;
		}else if(null == explanation || "" == explanation.trim()){
			Ext.getCmp('explanation').focus(true,false);
			Ext.Msg.alert('提示','请填写项目说明');
			return;
		}else if(null == level || "" == level.trim()){
			Ext.getCmp('level').focus(true,false);
			Ext.Msg.alert('提示','请填写项目级别');
			return;
		}else if(null == type || "" == type.trim()){
			Ext.getCmp('type').focus(true,false);
			Ext.Msg.alert('提示','请填写项目分类');
			return;
		}else{
			Ext.getCmp("addProjectForm").getForm().submit({
				waitMsg:'正在提交数据... ',
		        success: function(form, action){
				   var res = Ext.util.JSON.decode(action.response.responseText);
		           if(res.code > 0) {
						panel.store.load();
						updateCmsUserWindow.close();
					}
					Ext.Msg.alert('消息',res.message);
		        },   
		       failure: function(){
		       	  panel.store.load();
				  updateCmsUserWindow.close();
		          Ext.Msg.alert('错误', '操作成功！');   
		       }
		     });
		}
	}
	
}

function deleteProject(){
	
	if(!panel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择项目');
		return;
	}
	var records = panel.getSelectionModel().getSelections();
	var ids = "";var userNames = "";
	for ( var i = 0; i < records.length; i++) {
		ids += records[i].get('id') + ",";
	}
	ids = ids.substring(0, ids.length - 1);
	userNames = userNames.substring(0, userNames.length - 1);
	Ext.MessageBox.confirm("提示","你确定删除选中的项目吗?", function(button){
		if(button=="yes"){
			Ext.Ajax.request( {
				url:"ProjectInfo!delete.do",
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

function addProject(){
	
	var addCmsUserWindow =
	new Ext.Window( {
		title : '添加新项目',
		width : 300,
		height : 200,
		layout : 'fit',
		modal : true, // 设置遮罩
		items : [ new Ext.form.FormPanel({
					id:"addProjectForm",
					scope : this,
					url:"ProjectInfo!add.do",
					items : [ {
						xtype : "textfield",
						maxLength : 50,
						fieldLabel : "项目名称",
						name : "pmProjectInfo.name",
						id:'name',
						listeners: {
			                specialkey: function (textfield, e) {
			                    if (e.getKey() == Ext.EventObject.ENTER) {
			                    	Ext.getCmp('explanation').focus(true,false);
			                    }
			                }
			            }
					},{
						xtype : "textfield",
						maxLength : 50,
						fieldLabel : "项目说明",
						name : "pmProjectInfo.explanation",
						id:'explanation',
						listeners: {
			                specialkey: function (textfield, e) {
			                    if (e.getKey() == Ext.EventObject.ENTER) {
			                    	Ext.getCmp('level').focus(true,false);
			                    }
			                }
			            }
					},{
						xtype : "textfield",
						maxLength : 50,
						fieldLabel : "项目级别",
						name : "pmProjectInfo.level",
						id:'level',
						listeners: {
			                specialkey: function (textfield, e) {
			                    if (e.getKey() == Ext.EventObject.ENTER) {
			                    	Ext.getCmp('type').focus(true,false);
			                    }
			                }
			            }
					},{
						xtype : "textfield",
						maxLength : 50,
						fieldLabel : "项目分类",
						name : "pmProjectInfo.type",
						id:'type'
					}]
				}) ],
		buttons : [ {
			text : "确定",
			handler : addProjectSubmit, 
			formBind : true
		} ]
	});
	addCmsUserWindow.show();
	
	Ext.getCmp('name').focus(true,false);
	
	function addProjectSubmit(){
		var name = Ext.getCmp("name").getValue();
		var explanation = Ext.getCmp("explanation").getValue();
		var level = Ext.getCmp("level").getValue();
		var type = Ext.getCmp("type").getValue();
		if(null == name || "" == name.trim()){
			Ext.getCmp('name').focus(true,false);
			Ext.Msg.alert('提示','请填写项目名称');
			return;
		}else if(null == explanation || "" == explanation.trim()){
			Ext.getCmp('explanation').focus(true,false);
			Ext.Msg.alert('提示','请填写项目说明');
			return;
		}else if(null == level || "" == level.trim()){
			Ext.getCmp('level').focus(true,false);
			Ext.Msg.alert('提示','请填写项目级别');
			return;
		}else if(null == type || "" == type.trim()){
			Ext.getCmp('type').focus(true,false);
			Ext.Msg.alert('提示','请填写项目分类');
			return;
		}else{
			Ext.getCmp("addProjectForm").getForm().submit({
				waitMsg:'正在提交数据... ',
		        success: function(form, action){
				   var res = Ext.util.JSON.decode(action.response.responseText);
		           if(res.code > 0) {
						panel.store.load();
						addCmsUserWindow.close();
					}
					Ext.Msg.alert('消息',res.message);
		        },   
		       failure: function(){
		       	  panel.store.load();
				  addCmsUserWindow.close();
		          Ext.Msg.alert('错误', '操作成功！');   
		       }
		     });
		}
	}
}


function search(){
	panel.store.load();
}