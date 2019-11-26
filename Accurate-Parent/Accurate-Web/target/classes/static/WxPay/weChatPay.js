function failHandle(tip,icon,id) {
    $.toast(tip, icon, function () {
        $.ajax({
            url: '/weChatPay/deleteWeChatPayById',
            type: 'post',
            data: {"id": id},
            dataType: 'json',
            success: function (res) {

            }
        });
        window.history.back();
    });
}

function JsApiWeChatPay(description,totalFee, data,callback) {
    $.ajax({
        url: '/weChatPay/unifiedOrder',
        type: 'post',
        data: {"description": description, "totalFee": totalFee, "data": data},
        dataType: 'json',
        success: function (result) {
            if (result.code == 1) {
                WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId": result.data.appId,     //公众号名称，由商户传入
                        "timeStamp": result.data.timeStamp,         //时间戳，自1970年以来的秒数
                        "nonceStr": result.data.nonceStr, //随机串
                        "package": result.data.package,
                        "signType": result.data.signType,         //微信签名方式：
                        "paySign": result.data.paySign //微信签名
                    },
                    function (res) {
                        return callback(res.err_msg,result.data.id);
                        // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                    }
                );
            } else {
                $.toast('支付失败','forbidden',function () {
                    window.history.back();
                });
            }
        },
        error: function () {
            $.toast('支付失败','forbidden',function () {
                window.history.back();
            });
        }
    });
}

function WeChatPay(description,totalFee, data ,callback) {
    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', function () {
                JsApiWeChatPay(description,totalFee, data,function (result,id) {
                    callback(result,id);
                })
            }, false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', function () {
                JsApiWeChatPay(description,totalFee, data,function (result,id) {
                    callback(result,id);
                })
            });
            document.attachEvent('onWeixinJSBridgeReady', function () {
                JsApiWeChatPay(description,totalFee, data,function (result,id) {
                    callback(result,id);
                })
            });
        }
    } else {
        JsApiWeChatPay(description,totalFee, data,function (result,id) {
            callback(result,id);
        })
    }
}

