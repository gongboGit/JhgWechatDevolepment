$(function(){
	$("form.ajaxform :submit").click(function(){
		var self=$(this);
		var form=$(this).closest("form");
		var action=form.attr("action");
		var onsubmit=form.attr("onsubmit");
		var method=form.attr("method")||'get';
		if(onsubmit){
			onsubmit='(function(){'+
			onsubmit
			+'})()';
			console.log(onsubmit)
			var rs=eval(onsubmit);
			//console.log(rs);
			if(!rs) return false;
		}
		var formData=form.serialize();
		var exec=method=='get'?$.get:$.post;
		var onPost=form.attr("onPost");
		if(onPost){
			onPost='(function(data){ return '+
			onPost+'})(data)';
			onPost=eval(onPost);
		}
		exec(action,formData,function(data){
			if(onPost) onPost.bind(self,data);
			else{
				MsgBox.alert(data.msg)
			}
		});
		return false;
	})
})