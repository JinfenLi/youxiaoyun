/**
* @class School.util.PostProxy
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 一个继承Ajax的类，用来设置数据代理为post
*/
Ext.define('School.util.PostProxy', {
    extend: 'Ext.data.proxy.Ajax',
    alias: 'proxy.postproxy', // must to get string reference
    config: {
       actionMethods: {
            create : 'POST',
            read   : 'POST', // by default GET
            update : 'POST',
            destroy: 'POST'
        }
    }
});

