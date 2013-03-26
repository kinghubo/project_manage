var mainPanel;
Ext.onReady(function() {
	Ext.QuickTips.init();
	var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"数据加载中，请稍等..."});
	
	var treePanel = new Ext.tree.TreePanel({
		id:1,
		root:root,
		title:'功能菜单',
		rootVisible:false,
		lines:false,
		autoScroll:true,
		listeners:{
			scope:this,
			click:function(node,e){
				if(node.leaf){
					if("" != node.attributes.attributes.url){
						myMask.show();
						Ext.Ajax.request({
							url:"nodeTransferStation.do?url="+node.attributes.attributes.url,
							success : function(response, options) {
								var res = Ext.util.JSON.decode(response.responseText);
								if(res.code < 0){
									window.location = "login.jsp";
								}else{
									mainPanel.removeAll(true);
									mainPanel.add(new Ext.Panel({
						                title:node.text,
							            closable:false,
							            id:node.id,
							            autoScroll:true,
						                autoLoad:{
							            	url: "nodeTransferStation.do?url="+node.attributes.attributes.url+"&id="+node.id,
							            	nocache: true,
							            	text: '正在加载...',
							            	timeout: 30,
							            	scripts: true
						                }
					            	}));
									mainPanel.doLayout();
								}
								myMask.hide();
							},
							failure : function(response, options) {
								Ext.Msg.alert('提示', 'error 500');
								myMask.hide();
							}
						})
					}
				}
		    }
		}
	});
	
	var northPanel = new Ext.Panel({
		id:'north',
		region:'north',
		height:80,
        autoLoad:{
        	url: 'header.jsp',
        	nocache: true,
        	text: 'Loading...',
        	timeout: 30,
        	scripts: true
        }
	});
	
	var westPanel = new Ext.Panel({
		title : "功能区",
		region : "west",
		collapsible : false,
		collapseMode : 'mini',
		width : 150, 
		bodyStyle : 'text-align:left',
		layout : 'accordion',
		layoutConfig : {
			animate : true
		},
		defaults : {
			border : false,
			frame : false
		},
		items : [treePanel]
	});
	
	mainPanel = new Ext.Panel({
		id:'main',
		region:'center',
		layout : "fit",
		autoScroll : true ,
        items: [{
            title: "欢迎页面",
            id:'welcome',
            autoLoad:{
            	url: 'welcome.jsp',
            	nocache: true,
            	text: '正在加载...',
            	timeout: 30,
            	scripts: true
            }
        }]
	});
	
	new Ext.Viewport({
		layout : "border",
		autoScroll : true ,
		items : [northPanel,westPanel, mainPanel]
	});
	
	var hideMask = function() {
		Ext.get('loading').remove();
		Ext.fly('loading-mask').fadeOut({
			remove : true
		});
	}.defer(250);

});
