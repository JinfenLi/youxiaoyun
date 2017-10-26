Ext.define('Ext.ux.Tips', {
	initComponent: function () {
		this.msgCt = null;

		if(!this.msgCt){
			this.msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
		}

	},

	createBox:  function (t, s) {
		return '<div class="msg ' + Ext.baseCSSPrefix + 'border-box"><h3>' + t + '</h3><p>' + s + '</p></div>';
	},

	statics: {
		msg : function(title, format){
			if(!this.msgCt){
				this.msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
			}
			var s = Ext.String.format.apply(String, Array.prototype.slice.call(arguments, 1));
			var m = Ext.DomHelper.append(this.msgCt, createBox(title, s), true);
			m.hide();
			m.slideIn('t').ghost("t", { delay: 1000, remove: true});

			function createBox(t, s) {
				return '<div class="msg ' + Ext.baseCSSPrefix + 'border-box"><h3>' + t + '</h3><p>' + s + '</p></div>';
			}
		}

	}
});