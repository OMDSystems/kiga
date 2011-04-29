var ChildrenView;
var __hasProp = Object.prototype.hasOwnProperty, __extends = function(child, parent) {
  for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; }
  function ctor() { this.constructor = child; }
  ctor.prototype = parent.prototype;
  child.prototype = new ctor;
  child.__super__ = parent.prototype;
  return child;
};
ChildrenView = (function() {
  __extends(ChildrenView, Ext.Panel);
  function ChildrenView() {
    ChildrenView.__super__.constructor.call(this, {
      region: 'center',
      layout: 'fit',
      tbar: new Ext.Toolbar({
        height: 30,
        items: [
          new Ext.Button({
            text: 'Add Child',
            icon: '/public/images/default/dd/drop-add.gif',
            handler: function(btn) {
              return AddChildDialog().show();
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
              {
                header: 'Last Name'
              }, {
                header: 'First Name'
              }, {
                header: 'Date of Birth'
              }, {
                header: 'Allergies'
              }, {
                header: 'Street'
              }, {
                header: 'Zipcode'
              }, {
                header: 'City'
              }
            ]
          }),
          selModel: {
            singleSelect: true
          },
          store: new Ext.data.ArrayStore({
            fields: ['id', 'name'],
            data: [[1, 'Alice'], [2, 'Bill'], [3, 'Carly']]
          })
        }
      ]
    });
  }
  return ChildrenView;
})();