var AddChildForm;
var __hasProp = Object.prototype.hasOwnProperty, __extends = function(child, parent) {
  for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; }
  function ctor() { this.constructor = child; }
  ctor.prototype = parent.prototype;
  child.prototype = new ctor;
  child.__super__ = parent.prototype;
  return child;
};
AddChildForm = (function() {
  __extends(AddChildForm, Ext.form.FormPanel);
  function AddChildForm() {
    AddChildForm.__super__.constructor.call(this, {
      id: 'addChildForm',
      title: 'Form Layout',
      bodyStyle: 'padding:15px',
      defaultType: 'textfield',
      defaults: {
        width: 200,
        msgTarget: 'side'
      },
      items: [
        {
          fieldLabel: 'First Name',
          name: 'first_name',
          allowBlank: false,
          labelSeparator: ':'
        }, {
          fieldLabel: 'Last Name',
          name: 'last_name'
        }, {
          fieldLabel: 'Birthday',
          name: 'date_of_birth',
          xtype: 'datefield',
          format: 'd.m.Y'
        }, {
          fieldLabel: 'Street',
          name: 'street'
        }, {
          fieldLabel: 'Street Number',
          name: 'street_number'
        }, {
          fieldLabel: 'Zipcode',
          xtype: 'numberfield',
          name: 'zip'
        }, {
          fieldLabel: 'City',
          name: 'city'
        }, {
          fieldLabel: 'Additional',
          name: 'additional'
        }, {
          fieldLabel: 'Allergies',
          xtype: 'textarea',
          name: 'allergies'
        }
      ],
      buttons: [
        {
          text: 'Save',
          handler: function(obj) {
            var form;
            form = Ext.getCmp('addChildForm');
            return form.getForm().submit({
              url: '/children',
              success: function(form, action) {
                var childrenStore, window;
                window = Ext.getCmp('addChildView');
                window.close();
                childrenStore = Ext.StoreMgr.get('childrenStore');
                return childrenStore.add([new ChildRecord(action.result.data)]);
              },
              failure: function(form, action) {
                return Ext.Msg.alert("error", action.result.msg);
              }
            });
          }
        }, {
          text: 'Cancel',
          handler: function(n) {
            var window;
            window = Ext.getCmp('addChildView');
            return window.close();
          }
        }
      ],
      hideLabels: false,
      labelAlign: 'left',
      labelPad: 8
    });
  }
  return AddChildForm;
})();