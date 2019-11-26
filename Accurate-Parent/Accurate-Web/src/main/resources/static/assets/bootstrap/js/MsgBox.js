var MsgBox;
(function(box){
	box.alert=function(msg,icon){
		layer.alert(msg, {
			  icon: icon||7,
			  skin: 'layer-ext-moon'
			});
	}
	box.confirm=function(msg,ok){
		layer.confirm(msg, {
			  btn: ['确定','取消'] //按钮
		}, function(index,padd){
			ok=ok||function(){};
			ok();
			layer.close(index);
		}, function(){
		 
		});
	};
	
})(MsgBox||(MsgBox={}));