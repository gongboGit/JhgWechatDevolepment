var tableSelector;
(function(ts){
	//绑定全选事件
	$(document).on('click', 'th input:checkbox' , function(){
		var that = this;
		$(this).closest('table').find('tr > td input[class=ace]:checkbox')
		.each(function(){
			this.checked = that.checked;
			//$(this).closest('tr').toggleClass('selected');
		});
	});
	//获取选中的checkbox的id
	ts.selectedLast=function(){
		let ids=ts.getSelectList4Ids();
		if(ids&&ids.length>0) return ids[ids.length-1];
		return null;
	}
	ts.getSelectList4Ids=function(){
		var ls=[];
        $('.table.table-striped tbody td input[class=ace]:checked:checkbox').each(function (it) {
            ls.push($(this).closest('td').attr('data-bind-id'));
        });
		return ls;
	}
	ts.selectedFirst = function () {
        let ids=ts.getSelectList4Ids();
        if(ids&&ids.length>0) return ids[0];
        return null;
    }
})(tableSelector||(tableSelector={}));