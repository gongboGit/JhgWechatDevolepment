$(function() {
	$('.btn-offer').click(function() {
		$('.detail-row').removeClass("row-open");

		$(this).closest("tr").next().toggleClass("row-open");
	});
	$(document).bind("click", function(e) {
		var target = $(e.target);
		if (target.closest("ul.autoconplete").length == 0) {
			$("ul.autoconplete").hide();
		}
	});

	var timer;
	$("input.autoconplete").on('input', function() {

		var autoul = $('ul.autoconplete', $(this).closest("tr"));

		let str = $.trim($(this).val());
		if (!str) return;
		autoul.css("display", "block");

		var self = this;
		if (!timer) clearTimeout(timer);

		timer = setTimeout(function() {
			$.get("/AjaxData/xmml_search", {
				str : str
			}, function(data, textStatus, req) {
				autoul.empty();
				let ls = data.data;
				ls.forEach(function(it) {
					autoul.append("<li data-bind='" + JSON.stringify(it) + "'>" + it.cMc + ' ' + it.cGg + "</li>");
				})

			});
		}, 800);

	});

})