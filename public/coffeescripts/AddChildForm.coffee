class AddChildForm extends Ext.form.FormPanel
  constructor: ->
    super(
      id: 'addChildForm'
      title: 'Form Layout'
      bodyStyle: 'padding:15px'
      defaultType: 'textfield'
      defaults:
        width: 200
        msgTarget: 'side'
      ,
      items: [
            fieldLabel: 'First Name'
            name: 'first_name'
            allowBlank: false
            labelSeparator: ':'
          ,
            fieldLabel: 'Last Name'
            name: 'last_name'
          ,
            fieldLabel: 'Birthday'
            name: 'date_of_birth'
            xtype: 'datefield'
            format: 'd.m.Y'
          ,
            fieldLabel: 'Street'
            name: 'street'
          ,
            fieldLabel: 'Street Number'
            name: 'street_number'
          ,
            fieldLabel: 'Zipcode'
            xtype: 'numberfield'
            name: 'zip'
          ,
            fieldLabel: 'City'
            name: 'city'
          ,
            fieldLabel: 'Additional'
            name: 'additional'
          ,
            fieldLabel: 'Allergies'
            xtype: 'textarea'
            name: 'allergies'
      ],
      buttons: [
          text: 'Save'
          handler: (obj) ->
            form = Ext.getCmp('addChildForm')
            form.getForm().submit(
              url: '/children'
              success: (form, action) ->
                window = Ext.getCmp('addChildView')
                window.close()
                childrenStore = Ext.StoreMgr.get('childrenStore')
                childrenStore.add([new ChildRecord(action.result.data)])
              failure: (form, action) ->
                Ext.Msg.alert("error", action.result.msg)
            )
        ,
          text: 'Cancel'
          handler: (n) ->
            window = Ext.getCmp('addChildView')
            window.close()
      ],
      hideLabels: false,
      labelAlign: 'left',
      labelPad: 8)
