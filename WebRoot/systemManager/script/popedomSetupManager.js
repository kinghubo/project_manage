var panel;
var popedomSetupWestPanel;
var popedomSetupCenterPanel;

Ext.onReady(function() {
	Ext.QuickTips.init();
	popedomSetupPanel();
});

function popedomSetupPanel(){
	
	var popedomSetupRecord = Ext.data.Record.create([ {name : 'id',type : 'int'},{name : 'groupName',type : 'string'},
	                                                  {name : 'groupPopedom',type : 'string'},
	                                                  {name : 'creater',type : 'string'},{name : 'createTime',type : 'string'}
	                                             ]);
	
	var cmsGroupUserRecord = Ext.data.Record.create([ {name : 'id',type : 'int'},{name : 'groupId',type : 'int'},
	                                                  {name : 'userId',type : 'string'},{name : 'userName',type : 'string'},
	                                                  {name : 'creater',type : 'string'},{name : 'createTime',type : 'string'}
	                                                  ]);
	
	var sm = new Ext.grid.CheckboxSelectionModel( { singleSelect : false });
	var sm2 = new Ext.grid.CheckboxSelectionModel( { singleSelect : false });

	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),sm2,
	                                   {header : 'ID',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'id',width : 80},
	                                   {header : '组名',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'groupName',width : 120},
	                                   {header : '组权限',align : 'center',hidden : false,dataIndex : 'groupPopedom',width : 80,
	                                	renderer : function(v, attr, r) {
	                                	   return "<span style='margin-right:10px'><a href='javascript:lookPopedomTree("+r.get("id")+")' >查看</a></span>";
	                                   	}
	                                   },
	                                   {header : '创建人',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'creater',width : 80},
	                                   {header : '创建时间',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'createTime',width : 120}
	                                  ]);
	
	var cm2 = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),sm,
	                                   {header : '组ID',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'groupId',width : 120},
	                                   {header : '用户名',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'userName',width : 80},
	                                   {header : '创建人',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'creater',width : 80},
	                                   {header : '创建时间',sortable: true,editor:new Ext.form.TextField(),align : 'center',hidden : false,dataIndex : 'createTime',width : 120}
	                                   ]);
	
	var popedomSetupStore = new Ext.data.Store( {
			id : "popedomSetupStore",
			proxy : new Ext.data.HttpProxy( {
				url : 'queryPopedomGroup.do',
				method : 'post',
				params : {}
			}),
			reader : new Ext.data.JsonReader( {
				root : 'data.dataList',
				totalProperty : 'data.totalRows' 
			}, popedomSetupRecord)
		});
	
	var cmsGroupUserStore = new Ext.data.Store( {
		id : "cmsGroupUserStore",
		proxy : new Ext.data.HttpProxy( {
			url : 'getGroupUserByGroupId.do',
			method : 'post',
			params : {}
		}),
		reader : new Ext.data.JsonReader( {
			root : 'data.dataList',
			totalProperty : 'data.totalRows' 
		}, cmsGroupUserRecord)
	});
	
	popedomSetupCenterPanel = new Ext.grid.EditorGridPanel({
		store : cmsGroupUserStore,
		cm : cm2,
		sm : sm2,
		title : '组内成员',
		region:'center',
		width : 600,
		height : "auto",
		autoScroll : true
	});
	
	popedomSetupWestPanel = new Ext.grid.EditorGridPanel({
		store : popedomSetupStore,
		cm : cm,
		sm : sm,
		region:'west',
		title : '分组关系',
		width : 600,
		height : "auto",
		autoScroll : true,
		listeners: {
	        rowclick: function (grid,rowIndex,e) {
				if(grid.getSelectionModel().hasSelection()){
					var records = grid.getSelectionModel().getSelections();
	            	popedomSetupCenterPanel.store.load( {params : {'cmsGroupUser.groupId' : records[0].get('id')} })
				}
	        }
	    },
		tbar : [ 
		         {
					xtype : "tbtext",
					html:tbar,
					anchor : "90%"
		         }
		]
	});
	
	
	panel = new Ext.Panel({
		layout : "border",
		renderTo : "popedomSetupManagerDiv",
        split:true,
		height:450,
		width: 1200,
		items : [popedomSetupWestPanel,popedomSetupCenterPanel]
	})
	
	popedomSetupStore.load({params : {pageNo : 0,pageSize : 20} });
}

function updatePopedomGroup(){
	
	var records = popedomSetupWestPanel.getSelectionModel().getSelections();
	if(!popedomSetupWestPanel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择权限组');
		return;
	}
	if(records.length >1){
		Ext.Msg.alert('消息','请只选择一个权限组');
		return;
	}
	updatePopedomGroupWindow = new Ext.Window( {
		title : '修改权限组',
		width : 360,
		height : 150,
		layout : 'fit',
		modal : true, // 设置遮罩
		items : [ 
		  new Ext.form.FormPanel( {
			id:"updatePopedomGroupForm",
			scope : this,
			items : [
			         {
						xtype : "field",
						maxLength : 20,
						fieldLabel : "权限组名",
						name : "cmsPopedomGroup.groupName",
						id : "groupName",
						value:records[0].get("groupName"),
						anchor : "80%"
					}
					],
					buttons : [ {
						text : "确定",
						handler : function() {
							var groupName = Ext.getCmp('groupName').getValue();
							if(null == groupName || "" == groupName){
								Ext.Msg.alert('信息', '请填写权限组名！');
							}else{
								Ext.Ajax.request( {
									url : 'updatePopedomGroup.do?cmsPopedomGroup.id='+records[0].get("id"),
									success : function(response, options) {
										var res = Ext.util.JSON.decode(response.responseText);
										if (res.code > 0) {
											popedomSetupWestPanel.store.reload();
											updatePopedomGroupWindow.close();
										}
										Ext.Msg.alert('提示消息',res.message);
									},
									failure : function(data, options) {
										Ext.Msg.alert('提示消息','提交异常，修改失败！');
									},
									params : {
										'cmsPopedomGroup.groupName' : groupName
									}
								});
							}
						}
					} ]
					}) 
		]
	});
	
	updatePopedomGroupWindow.show();
}

function deletePopedomGroup(){
	var records = popedomSetupWestPanel.getSelectionModel().getSelections();
	if(!popedomSetupWestPanel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择权限组');
		return;
	}
	if(records.length >1){
		Ext.Msg.alert('消息','请只选择一个权限组');
		return;
	}
	Ext.MessageBox.confirm("删除提示","你确定要删除选中的权限组,并将其所有用户删除吗?",function(button){
		if(button=="yes"){
			Ext.Ajax.request( {
				url : 'deletePopedomGroup.do',
				success : function(response, options) {
					var res = Ext.util.JSON.decode(response.responseText);
					if(res.code > 0) {
						popedomSetupWestPanel.store.reload();
						popedomSetupCenterPanel.store.reload();
					}
					Ext.Msg.alert('提示消息',res.message);
				},
				failure : function(data, options) {
					Ext.Msg.alert('提示消息','网络超时或者异常。');
				},
				params : {
					'cmsPopedomGroup.id' : records[0].get("id")
				}
			});
		}
	});
}

function addPopedomGroup(){
	
	addPopedomGroupWindow = new Ext.Window( {
		title : '新增权限组',
		width : 360,
		height : 200,
		layout : 'fit',
		modal : true, // 设置遮罩
		items : [ 
		  new Ext.form.FormPanel( {
			id:"addPopedomGroupForm",
			scope : this,
			items : [
			         {
						xtype : "field",
						maxLength : 20,
						fieldLabel : "权限组名",
						name : "cmsPopedomGroup.groupName",
						id : "groupName",
						anchor : "80%"
					}
					],
					buttons : [ {
						text : "确定",
						handler : function() {
							var groupName = Ext.getCmp('groupName').getValue();
							if(null == groupName || "" == groupName){
								Ext.Msg.alert('信息', '请填写权限组名！');
							}else{
								Ext.Ajax.request( {
									url : 'addPopedomGroup.do',
									success : function(response, options) {
										var res = Ext.util.JSON.decode(response.responseText);
										if (res.code > 0) {
											popedomSetupWestPanel.store.reload();
											addPopedomGroupWindow.close();
										}
										Ext.Msg.alert('提示消息',res.message);
									},
									failure : function(data, options) {
										Ext.Msg.alert('提示消息','提交异常，新增失败！');
									},
									params : {
										'cmsPopedomGroup.groupName' : groupName
									}
								});
							}
						}
					} ]
					}) 
		]
	});
	
	addPopedomGroupWindow.show();
}

function lookPopedomTree(id){
	
	Ext.Ajax.request( {
		url : 'getPopedomTree.do',
		success : function(response, options) {
		
		var popedomSetupChildren =  eval('['+response.responseText+']');
		var popedomSetupRoot = new Ext.tree.AsyncTreeNode({id:"popedomSetupRoot",text:"所有权限",expanded:true,leaf:false,children:popedomSetupChildren});
		var popedomSetupEastPanel = new Ext.tree.TreePanel({
			id:'popedomSetupEastPanel',
			root:popedomSetupRoot,
			region : "center",
			autoScroll:true
		});
		
		getPopedomTreeRootWindow = new Ext.Window( {
			title : '权限查看&nbsp;&nbsp;&nbsp;菜单√&nbsp;&nbsp;按钮χ',
			width : 300,
			height : 450,
			layout : 'fit',
			modal : true, // 设置遮罩
			items : [popedomSetupEastPanel]
		});
		
		getPopedomTreeRootWindow.show();
		
	},
	failure : function(data, options) {
		Ext.Msg.alert('提示消息','读取失败！');
	},
	params : {
		'cmsPopedomGroup.id' : id
	}
	});
	
}

function addPopedomToGroup(){
	
	var records = popedomSetupWestPanel.getSelectionModel().getSelections();
	if(!popedomSetupWestPanel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择权限组');
		return;
	}
	if(records.length >1){
		Ext.Msg.alert('消息','请只选择一个权限组');
		return;
	}
	Ext.Ajax.request( {
		url : 'getPopedomTree.do',
		success : function(response, options) {
		
			var popedomSetupChildren =  eval('['+response.responseText+']');
			var popedomSetupRoot = new Ext.tree.AsyncTreeNode({id:"popedomSetupRoot",text:"所有权限",expanded:true,leaf:false,children:popedomSetupChildren});
			var popedomSetupEastPanel = new Ext.tree.TreePanel({
				id:'popedomSetupEastPanel',
				root:popedomSetupRoot,
				region : "center",
				tbar:[
				      {xtype : "button",text : "保存",
				    	handler : function() {
				    	  var quanxian = "";
				    	  var root = popedomSetupEastPanel.getRootNode();
				    	  var childNodes = root.childNodes;
				    	  for(var i=0;i<childNodes.length;i++){
				    		  if(childNodes[i].attributes.checked){
				    			  quanxian = quanxian + "," + childNodes[i].id;
				    			  if(childNodes[i].hasChildNodes()){
				    				  var childNodess = childNodes[i].childNodes;
					    			  for(var j=0;j<childNodess.length;j++){
					    				  if(childNodess[j].attributes.checked){
					    					  quanxian = quanxian + "," + childNodess[j].id;
					    					  if(childNodess[j].hasChildNodes()){
					    						  var childNodesss = childNodess[j].childNodes;
					    						  for(var k=0;k<childNodesss.length;k++){
					    							  if(childNodesss[k].attributes.checked){
					    								  quanxian = quanxian + "," + childNodesss[k].id;
					    								  if(childNodesss[k].hasChildNodes()){
					    									  var grandsonNode = childNodesss[k].childNodes;
					    									  for(var l=0;l<grandsonNode.length;l++){
					    										  if(grandsonNode[l].attributes.checked){
								    								  quanxian = quanxian + "," + grandsonNode[l].id;
					    										  }
					    									  }
					    								  }
					    							  }
					    						  }
					    					  }
					    				  }
					    			  }
				    			  }
				    		  }
				    	  }
				    	  Ext.Ajax.request( {
								url : 'addPopedomToGroup.do?cmsPopedomGroup.id='+records[0].get("id"),
								success : function(response, options) {
									var res = Ext.util.JSON.decode(response.responseText);
									if (res.code > 0) {
										getPopedomTreeRootWindow.close();
									}
									Ext.Msg.alert('提示消息',res.message);
								},
								failure : function(data, options) {
									Ext.Msg.alert('提示消息','网络超时，或异常。');
								},
								params : {
									'cmsPopedomGroup.groupPopedom' : quanxian.substring(1)
								}
							});
				      	}
				      }
				      ],
				autoScroll:true
			});
			
			getPopedomTreeRootWindow = new Ext.Window( {
				title : '权限管理&nbsp;&nbsp;&nbsp;菜单√&nbsp;&nbsp;按钮χ',
				width : 300,
				height : 450,
				layout : 'fit',
				modal : true, // 设置遮罩
				items : [popedomSetupEastPanel]
			});
			
			getPopedomTreeRootWindow.show();
		
		},
		failure : function(data, options) {
			Ext.Msg.alert('提示消息','读取失败！');
		},
		params : {
			'cmsPopedomGroup.id' : records[0].get("id")
		}
	});
	
}

function deleteGroupUser(){
	
	if(!popedomSetupCenterPanel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择成员！');
		return;
	}
	var cmsGroupUserRecords = popedomSetupCenterPanel.getSelectionModel().getSelections();
	if(cmsGroupUserRecords.length >1){
		Ext.Msg.alert('消息','请只选择一个成员！');
		return;
	}
	Ext.MessageBox.confirm("删除提示","你确定要删除选中的成员吗?",function(button){
		if(button=="yes"){
			Ext.Ajax.request( {
				url : 'deleteGroupUser.do',
				success : function(response, options) {
					var res = Ext.util.JSON.decode(response.responseText);
					if(res.code > 0) {
						popedomSetupCenterPanel.store.reload();
					}
					Ext.Msg.alert('提示消息',res.message);
				},
				failure : function(data, options) {
					Ext.Msg.alert('提示消息','删除失败');
				},
				params : {
					'cmsGroupUser.id' : cmsGroupUserRecords[0].get("id")
				}
			});
		}
	});
	
}

function addGroupUser(){
	
	if(!popedomSetupWestPanel.getSelectionModel().hasSelection()){
		Ext.Msg.alert('消息','请选择权限组');
		return;
	}
	var popedomRecords = popedomSetupWestPanel.getSelectionModel().getSelections();
	if(popedomRecords.length >1){
		Ext.Msg.alert('消息','请只选择一个权限组');
		return;
	}
	
	var newcomers = Ext.data.Record.create( [ {name : 'id',type : 'string'}, {name : 'username',type : 'string'}]);

	var newcomersSm = new Ext.grid.CheckboxSelectionModel( {singleSelect : false});

	var newcomersCm = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(), newcomersSm, 
	                                              {header : '用户名',sortable: true,editor:new Ext.form.TextField(),align : 'center',dataIndex : 'username',width : 80},
	                                              {header : 'ID',sortable: true,editor:new Ext.form.TextField(),align : 'center',dataIndex : 'id',width : 120}
	                                             ]);

	var newcomersStore = new Ext.data.Store( {
		autoLoad : true,
		id : "newcomersStoreme",
		proxy : new Ext.data.HttpProxy( {
			url : 'queryCmsUsers.do',
			method : 'post',
			params : {}
		}),
		reader : new Ext.data.JsonReader( {
			root : 'data.dataList',
			totalProperty : 'data.totalRows' // 指定分页控件属性用
		}, newcomers)
	});
	var newcomersPanel = new Ext.grid.EditorGridPanel({
		store : newcomersStore,
		cm : newcomersCm,
		sm : newcomersSm,
		region : 'center',
		bodyStyle : 'width:100%;height:100%',
		autoScroll : true,
		viewConfig : {
			forceFit : true
		},
		listeners : {
			'activate' : function() {
				newcomersStore.load( {
					params : {
						start : 0,
						limit : 30
					}
				});
			},
			'rowselect' : function() {
				alert("请等待");
			}
		},
		tbar : [ {
			pressed : true,
			text : '添加此用户',
			scope : this,
			handler : function() {
				if(!newcomersPanel.getSelectionModel().hasSelection()){
					Ext.Msg.alert('消息','请选择用户');
					return;
				}
				var newcomersRecords = newcomersPanel.getSelectionModel().getSelections();
				if(newcomersRecords.length >1){
					Ext.Msg.alert('消息','请只选择一个用户');
					return;
				}
				Ext.Ajax.request( {
					url : 'addGroupUser.do',
					success : function(response, options) {
						var res = Ext.util.JSON.decode(response.responseText);
						if(res.code > 0) {
							popedomSetupCenterPanel.store.reload();
							addNewcomersWindow.close();
						}
						Ext.Msg.alert('提示消息',res.message);
					},
					failure : function(data, options) {
						Ext.Msg.alert('提示消息','增加失败！');
					},
					params : {
						'cmsGroupUser.groupId' : popedomRecords[0].get("id"),
						'cmsGroupUser.userId' : newcomersRecords[0].get("id"),
						'cmsGroupUser.userName' : newcomersRecords[0].get("username")
					}
				});
			}
		}],
		bbar : new Ext.PagingToolbar( {
			pageSize : 30,
			store : newcomersStore,
			displayInfo : true,
			displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			emptyMsg : "没有记录"
		})
	});
	
	addNewcomersWindow = new Ext.Window( {
		title : '增加组用户',
		width : 500,
		height : 400,
		layout : 'fit',
		modal : true, // 设置遮罩
		items : [newcomersPanel]
	});
	
	addNewcomersWindow.show();
}
