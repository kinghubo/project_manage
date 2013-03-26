var panel;

Ext.onReady(function() {
	Ext.QuickTips.init();
	menuPanel();
});

function menuPanel(){
	
	var menuRecord = Ext.data.Record.create([ {name : 'id',type : 'int'},{name : 'menuName',type : 'string'},{name : 'menuUrl',type : 'string'},
	                                          {name : 'menuType',type : 'int'},{name : 'fatherId',type : 'int'},{name : 'sort',type : 'int'},
	                                              {name : 'creater',type : 'string'},{name : 'createTime',type : 'string'}
	                                             ]);
	
	var sm = new Ext.grid.CheckboxSelectionModel( { singleSelect : false });

	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),sm,
	                                   {header : 'ID',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'id',width : 80},
	                                   {header : '菜单名',sortable: true,align : 'center',hidden : false,dataIndex : 'menuName',width : 120,
	                                	   renderer : function(v, attr, r){
	                                	   		if(1 == r.get("menuType")){
	                                	   			return "<a href='javascript:getMenuChild("+r.get("id")+","+r.get("fatherId")+")'>"+v+"</a>";
	                                	   		}else{
	                                	   			return v;
	                                	   		}
	                                   		}
	                                   },
	                                   {header : '菜单地址(方法名)',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'menuUrl',width : 200},
	                                   {header : '菜单类型',align : 'center',hidden : false,dataIndex : 'menuType',width : 120,
	                                	   renderer : function(v, attr, r) {
	                      						if(v == 1){
	                      							return "菜单";
	                      						}else{
	                      							return '按钮';
	                      						}
	                                   		}
	                                   },
	                                   {header : '父级ID',align : 'center',hidden : false,dataIndex : 'fatherId',width : 120},
	                                   {header : '排序号',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'sort',width : 80},
	                                   {header : '创建人',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'creater',width : 80},
	                                   {header : '创建时间',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'createTime',width : 120}
	                                  ]);
	
	// 写义record对象的存储容器store 表格数据
	var menustore = new Ext.data.Store( {
			id : "menustore",
			proxy : new Ext.data.HttpProxy( {
				url : 'getAllCmsMenu.do',
				method : 'post',
				params : {}
			}),
			reader : new Ext.data.JsonReader( {
				root : 'data.dataList',
				totalProperty : 'data.totalRows' // 指定分页控件属性用
			}, menuRecord)
		});
	
	panel = new Ext.grid.EditorGridPanel({
		store : menustore,
		cm : cm,
		sm : sm,
		renderTo : "menuManagerDiv",
		width: 1200,
		height: 500,
		autoScroll : true,
		listeners : {
			'activate' : function() {
				rotateAdstore.load({params : {pageNo : 0,pageSize : 20} });
			}
		},
		tbar : [ 
		         {
					xtype : "tbtext",
					html:tbar,
					anchor : "90%"
		         },{
					xtype : "hidden",
					value:'0',
					id:'gotoFatherMenu',
					anchor : "90%"
			     },{
					xtype : "hidden",
					value:'0',
					id:'gotoGranddadMenu',
					anchor : "90%"
				 },{
		        	 xtype : "button",
		        	 text:'返回',
		        	 anchor : "90%",
		        	 handler : function() {
			    	 	getMenuChild(Ext.getCmp('gotoFatherMenu').getValue(),Ext.getCmp('gotoGranddadMenu').getValue());
		         	}
		         }
		]
	});
	menustore.load({params : {pageNo : 0,pageSize : 20} });
}

function deleteMenuById(){
	
	var records = panel.getSelectionModel().getSelections();
	if(!panel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择菜单');
		return;
	}
	if(records.length >1){
		Ext.Msg.alert('消息','请逐一删除');
		return;
	}
	Ext.MessageBox.confirm("提示","删除前请确认此菜单没有子菜单，确定继续删除吗?",function(button){
		if(button=="yes"){
			Ext.Ajax.request( {
				url:"deleteMenuById.do",
				success : function(response, options) {
					var res = Ext.util.JSON.decode(response.responseText);
					if (res.code > 0) {
						panel.store.reload();
					}
					Ext.Msg.alert('提示', res.message);
				},
				failure : function(response, options) {
					Ext.Msg.alert('提示', 'error 500');
				},
				params : {
					'id' : records[0].get("id")
				}
			})
		}
	});
}

function getMenuChild(id,fatherId){
	if(null == id || "" == id){
		Ext.Msg.alert("信息","返回时,数据出错!");
		return ;
	}
	var parm = {};
	parm.pageNo = 0;
	parm.pageSize = 20;
	parm.fatherId = id;
	panel.store.baseParams = parm;
	panel.store.load();
	Ext.getCmp('gotoGranddadMenu').setValue(id);
	Ext.getCmp('gotoFatherMenu').setValue(fatherId);
}

function addMenu(){
	
	var menuWindow = new Ext.Window( {
		title : '增加菜单',
		width : 360,
		height : 260,
		method:'post',
		layout : 'fit',
		modal : true, // 设置遮罩
		items : [ new Ext.form.FormPanel( {
			id:"addMenuForm",
			scope : this,
			url:"addMenu.do",
			items : [
			         {
						xtype : "field",
						maxLength : 50,
						fieldLabel : "菜单名字",
						name : "cmsMenu.menuName",
						id : "menuName",
						anchor : "80%"
					},{
						xtype : "field",
						maxLength : 50,
						fieldLabel : "地址(方法名)",
						id : "menuUrl",
						name:"cmsMenu.menuUrl",
						anchor : "80%"
					},{
						xtype : "hidden",
						id : "fatherId",
						name:"cmsMenu.fatherId"
					},{
						xtype : "field",
						maxLength : 50,
						fieldLabel : "父级菜单",
						id : "fatherIdShow",
						anchor : "80%",
						readOnly:true,
						listeners: {
			                'focus': function(){
								Ext.Ajax.request( {
									url : 'getMenuTree.do',
									success : function(response, options) {
									
										var menuSetupChildren =  eval('['+response.responseText+']');
										var menuSetupRoot = new Ext.tree.AsyncTreeNode({id:"0",text:"企业管理",expanded:true,leaf:false,children:menuSetupChildren});
										var menuSetupEastPanel = new Ext.tree.TreePanel({
											root:menuSetupRoot,
											region : "center",
											autoScroll:true,
											listeners:{
												dblClick:function(node,e){
													Ext.getCmp('fatherIdShow').setValue(node.text);
													Ext.getCmp('fatherId').setValue(node.id);
													getPopedomTreeRootWindow.close();
											    }
											}
										});
										
										var getPopedomTreeRootWindow = new Ext.Window( {
											title : '设置父级菜单',
											width : 300,
											height : 450,
											layout : 'fit',
											modal : true, // 设置遮罩
											items : [menuSetupEastPanel]
										});
										getPopedomTreeRootWindow.show();
									
									},
									failure : function(data, options) {
										Ext.Msg.alert('提示消息','读取失败！');
									}
								});
			                }
			            }
					},{
					    fieldLabel:'菜单类型',
					    xtype: 'radiogroup',
		                layout:'table',
		                isFormField:true,
		                items:[{
			                columnWidth:.50, 
			                name:'cmsMenu.menuType',
			                checked:true,
			                boxLabel:'菜单 ',
			                inputValue:1
		                },{
			                columnWidth:.5,
			                name:'cmsMenu.menuType',
			                boxLabel:'按钮',
			                inputValue:2
		                }]
					},{
						xtype : "field",
						maxLength : 3,
						fieldLabel : "序列号",
						id : "sort",
						name:"cmsMenu.sort",
						anchor : "80%"
					}
					],
					buttons : [ {
						text : "确定",
						handler : function() {
							var pic = Ext.getCmp('menuName').getValue();
							var fatherId = Ext.getCmp('fatherIdShow').getValue();
							var sort = Ext.getCmp('sort').getValue();
							if(null == pic || "" == pic){
								Ext.Msg.alert('信息', '请填写菜单名！');
							}else if(null == fatherId || "" == fatherId){
								Ext.Msg.alert('信息', '请选择父级菜单！');
							}else if(null == sort || "" == sort){
								Ext.Msg.alert('信息', '请填序列号！');
							}else{
								Ext.getCmp("addMenuForm").getForm().submit({
									waitMsg:'正在提交数据... ',
							        success: function(form, action){
									   var res = Ext.util.JSON.decode(action.response.responseText);
							           Ext.Msg.alert('信息', res.msg);
							           menuWindow.close();
							           panel.store.reload();
							        },   
							       failure: function(){   
							          Ext.Msg.alert('错误', '新增失败！');   
							       }
							     });
							}
						}
							} ]
					}) ]
	});
	menuWindow.show();
}

function updateMenu(){
	
	var records = panel.getSelectionModel().getSelections();
	if(!panel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择菜单');
		return;
	}
	if(records.length >1){
		Ext.Msg.alert('消息','请请逐一修改菜单');
		return;
	}
	var menuWindow = new Ext.Window( {
		title : '修改菜单',
		width : 360,
		height : 260,
		method:'post',
		layout : 'fit',
		modal : true, // 设置遮罩
		items : [ new Ext.form.FormPanel( {
			id:"updateMenuForm",
			scope : this,
			items : [
			         {
			        	 xtype : "field",
			        	 maxLength : 50,
			        	 fieldLabel : "菜单名字",
			        	 name : "cmsMenu.menuName",
			        	 id : "menuName",
			        	 value:records[0].get('menuName'),
			        	 anchor : "80%"
			         },{
			        	 xtype : "field",
			        	 maxLength : 50,
			        	 fieldLabel : "地址(方法名)",
			        	 id : "menuUrl",
			        	 name:"cmsMenu.menuUrl",
			        	 value:records[0].get('menuUrl'),
			        	 anchor : "80%"
			         },{
						xtype : "hidden",
						id : "fatherId",
						name:"cmsMenu.fatherId",
						value:records[0].get('fatherId')
					 },{
			        	 xtype : "field",
			        	 maxLength : 50,
			        	 fieldLabel : "父级菜单",
			        	 id : "fatherIdShow",
			        	 value:'如需修改请点击',
			        	 anchor : "80%",
						readOnly:true,
						listeners: {
			                'focus': function(){
								Ext.Ajax.request( {
									url : 'getMenuTree.do',
									success : function(response, options) {
									
										var menuSetupChildren =  eval('['+response.responseText+']');
										var menuSetupRoot = new Ext.tree.AsyncTreeNode({id:"0",text:"企业管理",expanded:true,leaf:false,children:menuSetupChildren});
										var menuSetupEastPanel = new Ext.tree.TreePanel({
											root:menuSetupRoot,
											region : "center",
											autoScroll:true,
											listeners:{
												dblClick:function(node,e){
													Ext.getCmp('fatherIdShow').setValue(node.text);
													Ext.getCmp('fatherId').setValue(node.id);
													getPopedomTreeRootWindow.close();
											    }
											}
										});
										
										var getPopedomTreeRootWindow = new Ext.Window( {
											title : '设置父级菜单',
											width : 300,
											height : 450,
											layout : 'fit',
											modal : true, // 设置遮罩
											items : [menuSetupEastPanel]
										});
										getPopedomTreeRootWindow.show();
									
									},
									failure : function(data, options) {
										Ext.Msg.alert('提示消息','读取失败！');
									}
								});
			                }
			            }
			         },{
			        	 fieldLabel:'菜单类型',
			        	 xtype: 'radiogroup',
			        	 layout:'table',
			        	 isFormField:true,
			        	 id:"menuType",
			        	 items:[{
			        		 columnWidth:.50, 
			        		 name:'cmsMenu.menuType',
			        		 checked:records[0].get('menuType')==1?true:false,
			        		 boxLabel:'菜单 ',
			        		 inputValue:1
			        	 },{
			        		 columnWidth:.5,
			        		 name:'cmsMenu.menuType',
			        		 checked:records[0].get('menuType')==2?true:false,
			        		 boxLabel:'按钮',
			        		 inputValue:2
			        	 }]
			         },{
			        	 xtype : "field",
			        	 maxLength : 3,
			        	 fieldLabel : "序列号",
			        	 value:records[0].get('sort'),
			        	 id : "sort",
			        	 name:"cmsMenu.sort",
			        	 anchor : "80%"
			         }
			         ],
			         buttons : [ {
			        	 text : "确定",
			        	 handler : function() {
			        	 var pic = Ext.getCmp('menuName').getValue();
			        	 var fatherId = Ext.getCmp('fatherIdShow').getValue();
			        	 var sort = Ext.getCmp('sort').getValue();
			        	 if(null == pic || "" == pic){
			        		 Ext.Msg.alert('信息', '请填写菜单名！');
			        	 }else if(null == fatherId || "" == fatherId){
			        		 Ext.Msg.alert('信息', '请选择父级菜单！');
			        	 }else if(null == sort || "" == sort){
			        		 Ext.Msg.alert('信息', '请填序列号！');
			        	 }else{
			        		 var rg = Ext.getCmp("menuType");
			        		 var r = rg.getValue()
			        		 var menuType = r.inputValue;
			        		 Ext.Ajax.request( {
									url:"updateMenu.do?cmsMenu.id="+records[0].get("id"),
									success : function(response, options) {
										var res = Ext.util.JSON.decode(response.responseText);
										if (res.code > 0) {
											menuWindow.close();
											panel.store.load();
										} else {
											updateRotateAdWindow.close();
										}
										Ext.Msg.alert('消息',res.message);
									},
									failure : function(response, options) {
										Ext.Msg.alert('提示', 'error 500');
									},
									params : {
										'cmsMenu.menuName' : Ext.getCmp("menuName").getValue(),
										'cmsMenu.menuUrl' : Ext.getCmp("menuUrl").getValue(),
										'cmsMenu.fatherId' : Ext.getCmp("fatherId").getValue(),
										'cmsMenu.menuType' : menuType,
										'cmsMenu.sort' : Ext.getCmp("sort").getValue()
									}
								});
			        	 }
			         }
			         } ]
		}) ]
	});
	
	menuWindow.show();
}

