### Boilerplate for store

```javascript
Ext.define('School.store.yourStoreName', {
	extend: 'Ext.data.Store',

	requires: [
		'School.model.yourModelName'
	],

	model: 'School.model.yourModelName',

	proxy: {
		type: 'ajax',
		url: '',

		reader: {
			type: 'json',
			root: ''
		}
	}
});
```
