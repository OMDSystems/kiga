var AddChildDialog = function() {
  return new Ext.Window({
    width: 370,
    modal: true,
    resizable: false,
    draggable: false,
    items: [
      new Ext.form.FormPanel({
          title: 'Form Layout',
          bodyStyle: 'padding:15px',
          defaultType: 'textfield',
          defaults: {
            width: 200,
            msgTarget: 'side'
          },
          items: [{
                  fieldLabel: 'First Name',
                  name: 'firstname',
                  allowBlank: false,
                  labelSeparator: ':'
              },{
                  fieldLabel: 'Last Name',
                  name: 'lastname'
              }, {
                fieldLabel: 'Birthday',
                xtype: 'datefield'
              }, {
                fieldLabel: 'Street',
              }, {
                fieldLabel: 'Street Number',
                xtype: 'numberfield'
              }, {
                fieldLabel: 'Zipcode',
                xtype: 'numberfield'
              }, {
                fieldLabel: 'City'
              }, {
                fieldLabel: 'Allergies',
                xtype: 'textarea',
                name: 'allergies',
              }
          ],
          buttons: [
              {text: 'Save'},
              {text: 'Cancel', handler: function(n){  }}
          ],
          // config options applicable to container when layout='form':
          hideLabels: false,
          labelAlign: 'left',   // or 'right' or 'top'
          labelPad: 8           // defaults to 5, must specify labelWidth to be honored
      })
    ]
  })
}