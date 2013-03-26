/**
 * 添加用户Grid展现页面
 */

var panel;

Ext.onReady(function() {
	Ext.QuickTips.init();
	cmsUserLogPanel();
});

function cmsUserLogPanel() {
	// 定义数据对象
	var Person = Ext.data.Record.create( [ 
	                                       {name : 'id',type : 'int'}, 
	                                       {name : 'menuName',type : 'string'}, 
	                                       {name : 'content',type : 'string'}, 
	                                       {name : 'createTime',type : 'string'}
	                                      ]);

	var sm = new Ext.grid.CheckboxSelectionModel( {
		singleSelect : false
	});

	var cm = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(), sm, 
											{
												header : 'ID',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'id',
												width : 80
											},{
												header : '操作菜单',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'menuName',
												width : 150
											},{
												header : '操作内容',
												align : 'left',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'content',
												width : 300
											},{
												header : '操作时间',
												align : 'center',
												sortable: true,editor:new Ext.form.TextField(),
												dataIndex : 'createTime',
												width : 120
											}]);

	var cmsUserLogStore = new Ext.data.Store( {
		id : "cmsUserLogStore",
		proxy : new Ext.data.HttpProxy( {
			url : 'queryCmsUserLog.do',
			method : 'post',
			params : { start : 0,limit : 20 }
		}),
		reader : new Ext.data.JsonReader( {
			root : 'data.dataList',
			totalProperty : 'data.totalRows' // 指定分页控件属性用
		}, Person)
	});
	
	panel = new Ext.grid.EditorGridPanel({
		store : cmsUserLogStore,
		cm : cm,
		sm : sm,
		renderTo : "cmsUserLogDiv",
		width: 1200,
		height: 500,
		autoScroll : true,
		tbar : [ {
				xtype : "tbtext",
				html:tbar,
				anchor : "90%"
	         },{
	 			xtype : 'tbseparator'
	 		}, {
	 			xtype : 'label',
	 			text : '操作时间：'
	 		},{
	 			xtype : "datetimefield",
	 			anchor : "100%",
	 			id : 'sTime',
	 			width : 150,
	 			format : 'Y-m-d H:i'
	 		}, {
	 			xtype : 'label',
	 			text : '到：'
	 		}, {
	 			xtype : "datetimefield",
	 			anchor : "100%",
	 			width : 150,
	 			id : 'eTime',
	 			format : 'Y-m-d H:i'
	 		}, {
	 			xtype : "button",
	 			text : "查询",
	 			handler : function() {
	 				var parm = {};
	 				parm.sTime = Ext.getCmp("sTime").getValue();
	 				if(parm.sTime) parm.sTime = parm.sTime.format("Y-m-d H:i:s");
	 				parm.eTime = Ext.getCmp("eTime").getValue();
	 				if(parm.eTime) parm.eTime = parm.eTime.format("Y-m-d H:i:s");
	 				parm.limit=20;
	 				cmsUserLogStore.baseParams = parm;
	 				cmsUserLogStore.load();
	 			}
	 		},{
	 			xtype : "button",
	 			text : "清空",
	 			handler : function() {
	 				Ext.getCmp("sTime").setValue("");
	 				Ext.getCmp("eTime").setValue("");
	 			}
	 		},{xtype : 'tbseparator'}],
		bbar : new Ext.PagingToolbar( {
			pageSize : 20,
			store : cmsUserLogStore,
			displayInfo : true,
			displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			emptyMsg : "没有记录"
		})
	});
	cmsUserLogStore.load( { params : { start : 0,limit : 20 } });
}