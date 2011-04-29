class AddChildForm extends Ext.form.FormPanel
  constructor: ->
    super(
      title: 'Form Layout',
      bodyStyle: 'padding:15px',
      defaultType: 'textfield',
      defaults:
        width: 200,
        msgTarget: 'side'
      ,
      items: [
              fieldLabel: 'First Name',
              name: 'firstname',
              allowBlank: false,
              labelSeparator: ':'
          ,
              fieldLabel: 'Last Name',
              name: 'lastname'
          ,
            fieldLabel: 'Birthday',
            xtype: 'datefield'
          ,
            fieldLabel: 'Street',
          ,
            fieldLabel: 'Street Number',
            xtype: 'numberfield'
          ,
            fieldLabel: 'Zipcode',
            xtype: 'numberfield'
          ,
            fieldLabel: 'City'
          ,
            fieldLabel: 'Allergies',
            xtype: 'textarea',
            name: 'allergies',
      ],
      buttons: [
          {text: 'Save'},
          {text: 'Cancel', handler: (n) -> }
      ],
      hideLabels: false,
      labelAlign: 'left',
      labelPad: 8)