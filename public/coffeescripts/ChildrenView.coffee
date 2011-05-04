class ChildrenView extends Ext.Panel
  constructor: ->
    super(
      region: 'center'
      layout: 'fit'
      tbar: new Ext.Toolbar(
        height: 30
        items: [
          new Ext.Button
            text: 'Add Child'
            icon: '/public/images/default/dd/drop-add.gif'
            handler: (btn) ->
              view = new AddChildView()
              view.show()
        ])
      ,
      items: [
        title: 'foo'
        xtype: 'grid'
        autoHeight: true
        cm: new Ext.grid.ColumnModel(
          defaults:
            width: 120
            sortable: true
          ,
          columns: [
            {header: 'Last Name'}
            {header: 'First Name'}
            {header: 'Date of Birth', renderer: Ext.util.Format.dateRenderer('d.m.Y')}
            {header: 'Allergies'}
            {header: 'Street'}
            {header: 'Zipcode'}
            {header: 'City'}
          ])
        ,
        selModel:
         singleSelect: true
        ,
        store: new Ext.data.JsonStore
          id: 'childrenStore'
          url: '/children'
          fields: ['familyName','name', {name: 'dateOfBirth', type: 'date', format: 'd.m.Y'}, 'allergies', 'street', 'zip', 'town']
      ])