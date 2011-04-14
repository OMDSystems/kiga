var ChildrenView = function() {
  return new Ext.Panel({
    region: 'center',
    layout: 'fit',
    tbar: new Ext.Toolbar({
      height: 30,
      items: [
        new Ext.Button( {
          text: 'Add Child',
          icon: '/public/images/default/dd/drop-add.gif',
          handler: function(btn) {
            AddChildDialog().show()
          }
        })
      ]
    }),
    items: [
    {
      height: 200,
      title: 'foo',
      xtype: 'grid',
      cm: new Ext.grid.ColumnModel({
        defaults: {
          width: 120,
          sortable: true
        },
        columns: [
          {header: 'Last Name'},
          {header: 'First Name'},
          {header: 'Date of Birth'},
          {header: 'Allergies'},
          {header: 'Street'},
          {header: 'Zipcode'},
          {header: 'City'}
        ]
      }),
      selModel : {
       singleSelect: true
      },
      store: new Ext.data.ArrayStore({
        fields: ['id','name'],
        data: [[1,'Alice'],[2,'Bill'],[3,'Carly']]
      })
    }]
  });


  // Ext.grid.GridPanel({
  //     renderTo   : obj,
  //     title      : 'Our first grid',
  //     renderTo   : Ext.getBody(),
  //     autoHeight : true,
  //     width      : 250,
  //     view       : new Ext.grid.GridView(),
  //     colModel   : new Ext.grid.ColumnModel({
      //   defaults: {
      //     width: 120,
      //     sortable: true
      //   },
      //   columns: [
      //     {header: 'Last Name'},
      //     {header: 'First Name'},
      //     {header: 'Date of Birth'},
      //     {header: 'Allergies'}
      //   ]
      // }),
      // selModel : {
      //  singleSelect: true
      // }
  //   });
}