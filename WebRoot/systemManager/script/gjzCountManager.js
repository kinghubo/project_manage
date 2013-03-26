/**
 * 关键字统计
 */

var panel;
//var khfl = [["1","职位关键字搜索"],["2","简历关键字搜索"]];
var khfl = [["2","简历关键字搜索"]];

Ext.onReady(function() {
	Ext.QuickTips.init();
	gzjCountPanel();
});

function gzjCountPanel() {

	var Person = Ext.data.Record.create( [ 
	                                       {name : 'enterpriseName',type : 'string'},{name : 'countNumber',type : 'string'}
	                                     ]);

	var sm = new Ext.grid.CheckboxSelectionModel( {
		singleSelect : false
	});
	
	var cm = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(), sm, 
	                                     {header : '关键字',sortable: true,editor:new Ext.form.TextField(),align : 'center',dataIndex : 'enterpriseName',width : 120},
	                                     {header : '被查询次数',sortable: true,editor:new Ext.form.TextField(),align : 'center',dataIndex : 'countNumber',width : 120}
	                                   ]);

	var storemetj = new Ext.data.Store( {
		id : "mestore",
		proxy : new Ext.data.HttpProxy( {
			url : 'searchCriteriaCount.do',
			method : 'post',
		}),
		reader : new Ext.data.JsonReader( {
			root : 'data.dataList',
			totalProperty : 'data.totalRows' // 指定分页控件属性用
		}, Person)
	});
	
	panel = new Ext.grid.EditorGridPanel({
		store : storemetj,
		cm : cm,
		sm : sm,
		renderTo : "gjzCountManagerDiv",
		width: 1200,
		height: 500,
		autoScroll : true,
		tbar : [
	           {xtype:'label',text:'关键字类别查询 ：'},
				{
					xtype : "combo",
					id : "typeLog",
					displayField : 'val',
					valueField : 'key',
					fieldLabel : "关键字类别查询",
					triggerAction : 'all',
					store : new Ext.data.SimpleStore( {
					proxy : new Ext.data.MemoryProxy(khfl),
					fields : [ 'key', 'val' ],
					data : khfl
					}),
					// 默认“简历关键字搜索”
					value : '2',
					emptyText : "请选择关键字类别",
					anchor : "90%"
				},{
					xtype : 'label',
					text : '开始时间：'
				},{
					xtype : "datefield",
					fieldLabel : '开始时间',
					name : 'sTime',
					anchor : "70%",
					id : 'sTime',
					format : 'Y-m-d'
				},{
					xtype : 'label',
					text : '结束时间：'
				}, {
					xtype : "datefield",
					fieldLabel : '结束时间',
					name : 'eTime',
					anchor : "70%",
					id : 'eTime',
					format : 'Y-m-d'
				}, {
					xtype : "button",
					text : "查询",
					handler : function() {
						var parm = {};
						parm.limit = 20;
						parm.logType = Ext.getCmp("typeLog").getValue();
						parm.sTime = Ext.getCmp("sTime").getValue();
						if (parm.sTime)
							parm.sTime = parm.sTime.format("Y-m-d");
						parm.eTime = Ext.getCmp("eTime").getValue();
						if (parm.eTime)
							parm.eTime = parm.eTime.format("Y-m-d");
						storemetj.baseParams = parm;
						storemetj.load();
					}
				} 
			],
		bbar : new Ext.PagingToolbar( {
			pageSize : 20,
			store : storemetj,
			displayInfo : true,
			displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			emptyMsg : "没有记录"
		})
	});
	
//	storemetj.baseParams = {  limit : 20, start : 0 };
	storemetj.baseParams = {logType : 2, limit : 20, start : 0};
	storemetj.load();
}
