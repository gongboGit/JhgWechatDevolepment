$(function () {
    function loadData(element, keyName, curValue) {
        var url = ajaxRequestPath + '?keyname=' + keyName;
        var col = element.attr('data-bind-value4col') || 'keyName';

        $.get(url, function (data) {
            data.forEach(function (it, index) {
                if (index === 0)
                    element.append('<option value="">请选择' + it.value + '</option>');
                else element.append('<option value="' + it[col] + '">' + it.value + '</option>');
            });
            element.val(curValue);
        })
    }

    $('select.SysDic').each(function () {
        var dicKey = $(this).attr('data-bind-key');
        var dicValue = $(this).attr('data-bind-value');
        loadData($(this), dicKey, dicValue);
    })

    function loadRole(element) {
        element.append('<option value="">请选择角色</option>');
        $.getJSON('/AjaxData/roles', function (data, textStatus, req) {
            data.forEach(function (it, index) {

                element.append('<option value="' + it.id + '">' + it.roleName + '</option>');
            });
            element.val(element.attr('data-bind-value'));
        })
    }

    $('select.SysRole').each(function () {
        loadRole($(this));
    })

    // $('select .load-data').each(function () {
    //     var key = $(this).attr('data-bind-key');
    //     var value = $(this).attr('data-bind-value');
    //     loadData($(this), key, value);
    // });
    // function loadData(elem, key, curVal) {
    //     elem.append('<option value="">请选择' + key + '</option>');
    //     $.get("/ajax/loadData?key="+key,function (result) {
    //         result.forEach(function (item, index) {
    //             elem.append('<option value="' + item[index].key + '">' + item[index].key + '</option>');
    //         });
    //         elem.val(curVal);
    //     })
    // }
})