/**
* @class School.util.Format
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 格式化工具类，项目里没用到的
*/
Ext.define('School.util.Format', {
    extend: 'Ext.data.proxy.Ajax',
    alias: 'proxy.format',
    requires: [
        'School.util.FailureProcess'
    ],

    reader: {
        type: 'json',
        root: "data",
        messageProperty: "msg"
    },
    writer: {
        type: "json",
        encode: true,
        root: "data",
        allowSingle: false
    },
    listeners: {
        exception: function () {
            School.FailureProcess.Proxy.apply(this, arguments);
        }
    }

})
