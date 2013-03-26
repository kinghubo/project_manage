function winHtmlContent(v){
	var showhtml = new Ext.Window( {
		title : '内容展示',
		closeAction : 'hide',
		layout : 'fit',
		modal : true, // 设置遮罩
		resiziable : false,
		html:'<iframe width=680 height=480 frameborder=0 scrolling=auto src='+v+'></iframe>'
	});
	showhtml.show();
}

function winRotateAd(v){
	var showPic = new Ext.Window( {
		title : '企业图片信息展示',
		closeAction : 'hide',
		layout : 'fit',
		modal : true, // 设置遮罩
		resiziable : false,
		html:'<a href='+v+' target="_blank"><img width="500px" height="300px" src="'+v+'"/></a>'
	});
	showPic.show();
}

String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}


//更新url对应的文本编辑器内容
function updateContent(url,store){

	Ext.Ajax.request({
		   url: "getContent.do",
		   method:"post",
		   sync:true,
		   params: { url: url },
		   success: function(data){
			   var val=data.responseText;
				updateContentWindow = 
					new Ext.Window( {
						title : '修改内容',
						width : 700,
						height : 450,
						scrollY:200,
						scrollX:200,
						renderTo:document.body,
						fileUpload:true,
						
						frame : true,
						layout : 'fit',
						modal : true, // 设置遮罩
						items : [ new Ext.form.FormPanel( {
							id:"updateContentForm",
							scope : this,
							fileUpload:true,  
							url:"updateContent.do",
							params: { url: url },
							items : [
							        
								 {
			                            xtype : "myhtmleditor",
			                            name : "content",
			                            id:"content",
			                            fieldLabel : "内容",
			                            anchor : "98%",
			                            allowBlank : false,
			                            blankText:"内容不能为空",
			                            value:val
			                        }
									],
									buttons : [ 
											{
												text : "确定",
												type:"submit",
												handler : function() {
													var txtContentsDetail = ""; //控件中的html代码
												    if (updateContentWindow.ContentvFckeditor) {
												        var editor = FCKeditorAPI.GetInstance("contentUrl");
												        txtContentsDetail = editor.GetXHTML();
												    }
												    Ext.getCmp("updateContentForm").getForm().submit({   
												    	params: { url: url },
												    	waitMsg:"正在提交修改数据，请稍候...", 
												        success: function(form, action){   
												           Ext.Msg.alert('信息', '内容修改成功！');
												           updateContentWindow.close();
												           store.load();
												        },   
												       failure: function(form,action){  
												          Ext.Msg.alert('错误', '内容修改失败！');   
												       }   
												     });
												}
											} ]
									}) ]
					});
					
				updateContentWindow.show();
		   }
		   
		});

	}

//更新url对应的文本编辑器内容
function updateImage(imageUrl,store,id,action){

			
	var oldUrl = action;
			
				updateImageWindow = 
					new Ext.Window( {
						title : '修改图片',
						width : 400,
						height : 300,
						scrollY:200,
						scrollX:200,
						renderTo:document.body,
						fileUpload:true,
						frame : true,
						layout : 'fit',
						html:'<a href='+imageUrl+' target="_blank"><img width="500px" height="300px" src="'+imageUrl+'"/></a>',
						modal : true, // 设置遮罩
						items : [ new Ext.form.FormPanel( {
							id:"updateImageForm",
							scope : this,
							fileUpload:true,  
							url:action,
							listeners : {
								'render' : function(f) {
								 this.form.findField('pic').on('render', function() {
									 Ext.get('pic').on('change',
										function(field, newValue, oldValue) {
										 	var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([gG][iI][fF]){1}$|\.([pP][nN][gG]){1}$|\.([bB][mM][pP]){1}$/;
										 	var url = 'file://'+ Ext.get('pic').dom.value;
										 	if (img_reg.test(url)) {
										 		if (Ext.isIE) {
										 			var image = Ext.get('imageBrowse').dom;
										 			image.src = Ext.BLANK_IMAGE_URL;
										 			image.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
										 		}else {
										 			Ext.Msg.confirm("确认提示","请确认上传的图片是否为:&nbsp;"+Ext.get('pic').dom.value,function(button){
														if(button == "yes"){
															Ext.getCmp("updateImageForm").getForm().url = "getPicDataURl.do";
															Ext.getCmp("updateImageForm").getForm().submit({
																success: function(form, action){
																	var showPicUrl = action.response.responseText;
																	var showPicUrl2 = showPicUrl.replace("<pre>","");
																	var showPicUrl3 = showPicUrl2.replace("</pre>","");
																	Ext.get('imageBrowse').dom.src = showPicUrl3;
																	Ext.getCmp("updateImageForm").getForm().url = oldUrl;
																},   
															   failure: function(){   
																	Ext.getCmp("updateImageForm").getForm().url = oldUrl;
																	Ext.Msg.alert('错误', '图片上传失败!');   
															   }   
															 })
														}else{
															Ext.getCmp('pic').reset();
														}
													});
										 		}
										 	}else{
												Ext.Msg.alert("错误提示","请选择格式为.jpg .jpeg .gif .png .bmp的图片");
												Ext.getCmp('pic').reset();
											}
									 	}, this);
								 	}, this);
								}
								},
							items : [
							        
								  {
									xtype : 'box',
									autoEl : {
										width : 204,
										height : 125,
										tag : 'img',
										type : 'image',
										src :Ext.BLANK_IMAGE_URL,
										style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
										complete : 'off',
										id : 'imageBrowse'
										}   
								  },
								  {	
									id:"pic",
									name:"pic",
									inputType : "file",
									xtype : "textfield",
									fieldLabel : "请选择图片",
									allowBlank : false,
									blankText : "不能为空，请正确填写",
									anchor : "80%"
						
									}
								],
									buttons : [ 
											{
												text : "确定",
												type:"submit",
												handler : function() {
								
												    Ext.getCmp("updateImageForm").getForm().submit({
												    	params: { "url":imageUrl,"id":id },
												    	waitMsg:"正在提交修改数据，请稍候。。。。。。", 
												        success: function(form, action){   
												        	
												        	var res = Ext.util.JSON.decode(action.response.responseText);
												        	
												        	if (res >= 1) {
																Ext.Msg.alert('提示消息','修改成功');
																var url = 'file://'+ Ext.get('pic').dom.value;
																Ext.Msg.alert('提示消息','图片修改成功');
																
																var url = 'file://'+ Ext.get('pic').dom.value;
															 
														 		if (Ext.isIE) {
														 			var image = Ext.get('imageBrowse').dom;
														 			
														 			image.src = Ext.BLANK_IMAGE_URL;
														 			image.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
														 		}else {
														 			Ext.get('imageBrowse1').dom.src = Ext.get('pic').dom.files.item(0).getAsDataURL()
														 		}
															 	
																updateImageWindow.close();
																store.reload();
																 
															}else {
																Ext.Msg.alert('提示消息','修改失败');
															}
												        	
												          
												        },   
												       failure: function(form,action){  
												          Ext.Msg.alert('错误', '图片修改失败！');   
												       }   
												     });
												}
											} ]
									}) ]
					});
					
				updateImageWindow.show();
			//如果有上传广告图片，则显示出来
			if(imageUrl!=null&&imageUrl!=undefined&&imageUrl!=''){
				 document.getElementById("imageBrowse").src=imageUrl;
			}
		  
		

	}

