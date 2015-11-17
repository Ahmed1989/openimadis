/**
 * Parser to parser string type config
 */
Ext.define("Manage.view.params.StringParser", {
    extend : 'Ext.Base',

    /**
     * Create component call
     */
    createComponent : function(config) {
        var params = {
            name : config["name"],
            fieldLabel : config["label"],
            allowBlank:false
        };
        var defaultValue = config["default"];
        if (defaultValue !== undefined && defaultValue !== null)
            params["value"] = defaultValue;
        return Ext.create('Ext.form.field.Text', params);
    }

});
